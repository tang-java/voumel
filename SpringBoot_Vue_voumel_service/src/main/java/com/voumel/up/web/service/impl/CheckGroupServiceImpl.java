package com.voumel.up.web.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.volume.up.pojo.CheckGroup;
import com.voumel.up.entity.PageResult;
import com.voumel.up.entity.QueryPageBean;
import com.voumel.up.web.mapper.CheckGroupMapper;
import com.voumel.up.web.service.CheckGroupService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.List;

/**
 * @author 小唐
 * @version 1.0
 * @project SpringBoot_Vue_voumel_parent
 * @description
 * @date 2023/7/25 10:23:28
 */
@Service
public class CheckGroupServiceImpl implements CheckGroupService {

    @Resource
    private CheckGroupMapper checkGroupMapper;

    @Override
    public Integer addCheckGroup(CheckGroup checkGroup) {
        return checkGroupMapper.addCheckGroup(checkGroup);
    }

    @Override
    public Integer deleteCheckGroup(Integer checkGroupId) {
        // ----TODO 这里还要完成事务控制
        try {
            Integer deleteCheckGroup = checkGroupMapper.deleteCheckGroup(checkGroupId);
            Integer deleteRelationship = checkGroupMapper.deleteTheRelationshipBetweenCheckGroupsAndCheckItems(checkGroupId);
            return deleteCheckGroup + deleteRelationship;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }

    @Override
    public CheckGroup findCheckGroupById(Integer id) {
        return checkGroupMapper.findCheckGroupById(id);
    }

    @Override
    public Integer updateCheckGroup(CheckGroup checkGroup) {
        return checkGroupMapper.updateCheckGroup(checkGroup);
    }

    @Override
    public PageResult findCheckGroup(QueryPageBean queryPageBean) {
        Integer currentPage = queryPageBean.getCurrentPage();
        Integer pageSize = queryPageBean.getPageSize();
        if (currentPage == 0 || currentPage == null) {
            currentPage = 1;
        }
        if (pageSize == 0 || pageSize == null) {
            pageSize = 5;
        }
        PageHelper.startPage(currentPage, pageSize);
        List<CheckGroup> checkGroups = checkGroupMapper.findCheckGroup(queryPageBean);
        PageInfo<CheckGroup> pageInfo = new PageInfo<>(checkGroups);
        long total = pageInfo.getTotal();
        return new PageResult(total, checkGroups);
    }

    @Override
    public Integer AddCheckItemsToTheCheckGroupByCheckGroupId(String[] checkItemIds, Integer checkGroupId) {
        Integer sum=0;
        if (checkItemIds != null) {
            for (String checkItemId : checkItemIds) {
                Integer count=checkGroupMapper.AddCheckItemToCheckGroup(Integer.valueOf(checkItemId), checkGroupId);
                sum=sum+count;
            }
        }
        return sum;
    }
}
