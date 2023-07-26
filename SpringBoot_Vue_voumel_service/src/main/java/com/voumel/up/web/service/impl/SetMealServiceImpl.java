package com.voumel.up.web.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.volume.up.pojo.SetMeal;
import com.voumel.up.entity.PageResult;
import com.voumel.up.entity.QueryPageBean;
import com.voumel.up.web.mapper.SetMealMapper;
import com.voumel.up.web.service.SetMealService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

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

    @Override
    public PageResult findSetMeal(QueryPageBean queryPageBean) {
        Integer currentPage = queryPageBean.getCurrentPage();
        Integer pageSize = queryPageBean.getPageSize();
        if (currentPage==null||currentPage==0){
            queryPageBean.setCurrentPage(1);
        }
        if (pageSize==null||pageSize==0){
            queryPageBean.setPageSize(4);
        }
        PageHelper.startPage(queryPageBean.getCurrentPage(),queryPageBean.getPageSize());
        List<SetMeal> setMealList=setMealMapper.findSetMealByPageHelper(queryPageBean);
        PageInfo<SetMeal> pageInfo = new PageInfo<>(setMealList);
        long total = pageInfo.getTotal();
        PageResult pageResult = new PageResult(total,setMealList);
        return pageResult;
    }
}
