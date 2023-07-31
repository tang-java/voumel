package com.voumel.up.web.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.volume.up.pojo.SetMeal;
import com.voumel.up.constant.RedisConstant;
import com.voumel.up.entity.PageResult;
import com.voumel.up.entity.QueryPageBean;
import com.voumel.up.web.mapper.SetMealAndCheckGroupMapper;
import com.voumel.up.web.mapper.SetMealMapper;
import com.voumel.up.web.service.SetMealService;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;
import java.util.concurrent.CountDownLatch;

/**
 * @author 小唐
 * @version 1.0
 * @project SpringBoot_Vue_voumel_parent
 * @description
 * @date 2023/7/26 19:53:22
 */
@Service
public class SetMealServiceImpl implements SetMealService {
    @Resource
    private SetMealMapper setMealMapper;

    @Resource
    private StringRedisTemplate stringRedisTemplate;

    @Resource
    private SetMealAndCheckGroupMapper setMealAndCheckGroupMapper;

    @Resource
    private CountDownLatch countDownLatch;

    @Override
    public PageResult findSetMeal(QueryPageBean queryPageBean) {
        Integer currentPage = queryPageBean.getCurrentPage();
        Integer pageSize = queryPageBean.getPageSize();
        if (currentPage == null || currentPage == 0) {
            queryPageBean.setCurrentPage(1);
        }
        if (pageSize == null || pageSize == 0) {
            queryPageBean.setPageSize(4);
        }
        PageHelper.startPage(queryPageBean.getCurrentPage(), queryPageBean.getPageSize());
        List<SetMeal> setMealList = setMealMapper.findSetMealByPageHelper(queryPageBean);
        PageInfo<SetMeal> pageInfo = new PageInfo<>(setMealList);
        long total = pageInfo.getTotal();
        PageResult pageResult = new PageResult(total, setMealList);
        return pageResult;
    }

    @Override
    public SetMeal findSetMealById(Integer id) {
        //一：

        return setMealMapper.findSetMealById(id);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED,readOnly = false)
    public void addSetMeal(SetMeal setMeal, Integer[] checkGroupIds) {
        if (checkGroupIds.length > 0) {
            setMeal.setStatus(1);
            setMealMapper.addSetMeal(setMeal);
            //添加到redis数据库
            stringRedisTemplate.opsForSet().add(RedisConstant.URL_OF_ADD_SETMEAL_TO_DB, setMeal.getImg());
            //向中间表插入关系
            for (Integer checkGroupId : checkGroupIds) {
                setMealMapper.addCheckGroupToSetMeal(setMeal.getId(), checkGroupId);
            }
            countDownLatch.countDown();
        }
    }

    @Override
    public Integer updateSetMeal(SetMeal setMeal,Integer[] checkGroupIds) {
        Integer count = setMealMapper.updateSetMeal(setMeal);
        //先删除，再添加
        setMealAndCheckGroupMapper.deleteCheckGroupOfSetMeal(setMeal.getId());
        if (checkGroupIds.length>0){
            setMealAndCheckGroupMapper.batchAddCheckGroupToSetMeal(checkGroupIds,setMeal.getId());
        }
        return count;
    }

    @Override
    public Integer updateSetMeal(SetMeal setMeal) {
        Integer count = setMealMapper.updateSetMeal(setMeal);
        return count;
    }
}
