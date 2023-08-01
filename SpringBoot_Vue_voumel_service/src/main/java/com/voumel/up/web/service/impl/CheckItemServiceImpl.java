package com.voumel.up.web.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.volume.up.pojo.CheckItem;
import com.voumel.up.constant.RedisConstant;
import com.voumel.up.entity.PageResult;
import com.voumel.up.entity.QueryPageBean;
import com.voumel.up.web.mapper.CheckItemMapper;
import com.voumel.up.web.service.CheckItemService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author 小唐
 * @version 1.0
 * @project SpringBoot_Vue_voumel_parent
 * @description
 * @date 2023/7/24 15:25:42
 */
@Service
public class CheckItemServiceImpl implements CheckItemService {
    Logger log= LoggerFactory.getLogger(CheckItemServiceImpl.class);
    @Resource
    private CheckItemMapper checkItemMapper;
    @Resource
    private RedisTemplate redisTemplate;
    @Override
    public PageResult findItemByConditionAndPaging(QueryPageBean queryPageBean) {
        Integer currentPage = queryPageBean.getCurrentPage();
        Integer pageSize = queryPageBean.getPageSize();
        if (currentPage <= 0 || currentPage == null) {
            currentPage = 1;
        }
        if (pageSize <= 0 || pageSize == null) {
            pageSize = 10;
        }
        PageHelper.startPage(currentPage, pageSize);
        List<CheckItem> checkItems = checkItemMapper.findItemByConditionAndPaging(queryPageBean);
        PageInfo<CheckItem> pageInfo = new PageInfo<>(checkItems);
        long total = pageInfo.getTotal();
        PageResult pageResult = new PageResult(total, checkItems);
        return pageResult;
    }

    @Override
    public Integer addCheckItem(CheckItem checkItem) {
        checkItem.setStatus(1);
        Integer count = checkItemMapper.addCheckItem(checkItem);
        log.info("====>新增到mysql");
        redisTemplate.opsForHash().put(RedisConstant.T_CHECKITEM_LIST_,RedisConstant.T_CHECKITEM+checkItem.getId(),checkItem);
        log.info("====>新增到redis");
        return count;
    }

    @Override
    public CheckItem findCheckItemById(Integer id) {
        CheckItem checkItem=null;
        log.info("====>redis,id查询CheckItem");
        checkItem = (CheckItem) redisTemplate.opsForHash().get(RedisConstant.T_CHECKITEM_LIST_, RedisConstant.T_CHECKITEM+id);
        if (checkItem!=null){
            log.info("<====redis,通过id查询CheckItem");
            return checkItem;
        }
        log.info("<====0,redis中没有数据");
        log.info("====>mysql,通过id查询CheckItem");
        checkItem  = checkItemMapper.findCheckItemById(id);
        return checkItem;
    }

    @Override
    public Integer updateCheckItemStatus(CheckItem checkItem) {
        if (checkItem.getStatus()==1){
            checkItem.setStatus(0);
            redisTemplate.opsForHash().delete(RedisConstant.T_CHECKITEM_LIST_, RedisConstant.T_CHECKITEM + checkItem.getId());
        }else if (checkItem.getStatus()==0){
            checkItem.setStatus(1);
        }
        return checkItemMapper.updateCheckItemStatus(checkItem);
    }

    @Override
    public Integer updateCheckItem(CheckItem checkItem) {
        if (checkItem.getStatus()==0){
            redisTemplate.opsForHash().delete(RedisConstant.T_CHECKITEM_LIST_, RedisConstant.T_CHECKITEM + checkItem.getId());
        }else {
            //修改的同时，更新redis的数据
            redisTemplate.opsForHash().delete(RedisConstant.T_CHECKITEM_LIST_,RedisConstant.T_CHECKITEM+checkItem.getId());
            redisTemplate.opsForHash().put(RedisConstant.T_CHECKITEM_LIST_,RedisConstant.T_CHECKITEM+checkItem.getId(),checkItem);
        }
        return checkItemMapper.updateCheckItem(checkItem);
    }

    @Override
    public List<CheckItem> findAllCheckItem() {
        List<CheckItem> checkItemList= new ArrayList<>();
        //先从redis中取数据,增加删除修改都需要维护Redis
        log.info("查询所有checkitem=====>从redis中查询");
        Map map = redisTemplate.opsForHash().entries(RedisConstant.T_CHECKITEM_LIST_);
        if (!map.isEmpty()){
            log.info("=====>redis中存有数据，查询所有checkitem========");
            Stream entryStream = map.entrySet().stream();
            List<Map.Entry<String,Object>> entryList = (List<Map.Entry<String, Object>>) entryStream.collect(Collectors.toList());
            for (Map.Entry<String, Object> entry : entryList) {
                CheckItem value = (CheckItem) entry.getValue();
                checkItemList.add(value);
            }
            return checkItemList;
        }
        checkItemList = checkItemMapper.findAllCheckItem();
        log.info("查询所有checkitem=====>向redis存入在数据库中查询到的数据");
        for (CheckItem checkItem : checkItemList) {
            redisTemplate.opsForHash().put(RedisConstant.T_CHECKITEM_LIST_,RedisConstant.T_CHECKITEM+checkItem.getId(),checkItem);
        }
        return checkItemList;
    }
}
