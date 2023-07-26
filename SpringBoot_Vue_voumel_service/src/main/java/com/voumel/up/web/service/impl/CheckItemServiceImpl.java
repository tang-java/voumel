package com.voumel.up.web.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.volume.up.pojo.CheckItem;
import com.voumel.up.entity.PageResult;
import com.voumel.up.entity.QueryPageBean;
import com.voumel.up.web.mapper.CheckItemMapper;
import com.voumel.up.web.service.CheckItemService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author 小唐
 * @version 1.0
 * @project SpringBoot_Vue_voumel_parent
 * @description
 * @date 2023/7/24 15:25:42
 */
@Service
public class CheckItemServiceImpl implements CheckItemService {
    @Resource
    private CheckItemMapper checkItemMapper;

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
        return checkItemMapper.addCheckItem(checkItem);
    }

    @Override
    public CheckItem findCheckItemById(Integer id) {
        return checkItemMapper.findCheckItemById(id);

    }

    @Override
    public Integer updateCheckItemStatus(CheckItem checkItem) {
        if (checkItem.getStatus()==1){
            checkItem.setStatus(0);
        }else if (checkItem.getStatus()==0){
            checkItem.setStatus(1);
        }
        return checkItemMapper.updateCheckItemStatus(checkItem);
    }

    @Override
    public Integer updateCheckItem(CheckItem checkItem) {
        return checkItemMapper.updateCheckItem(checkItem);
    }

    @Override
    public List<CheckItem> findAllCheckItem() {
        return checkItemMapper.findAllCheckItem();
    }
}
