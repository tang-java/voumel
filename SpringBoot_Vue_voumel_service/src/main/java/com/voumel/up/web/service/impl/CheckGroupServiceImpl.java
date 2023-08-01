package com.voumel.up.web.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.volume.up.pojo.CheckGroup;
import com.voumel.up.entity.PageResult;
import com.voumel.up.entity.QueryPageBean;
import com.voumel.up.web.mapper.CheckGroupAndCheckItemMapper;
import com.voumel.up.web.mapper.CheckGroupMapper;
import com.voumel.up.web.service.CheckGroupService;
import com.voumel.up.web.service.CheckItemService;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
    @Resource
    private CheckGroupAndCheckItemMapper checkGroupAndCheckItemMapper;

    @Override
    public Integer addCheckGroup(CheckGroup checkGroup) {
        return checkGroupMapper.addCheckGroup(checkGroup);
    }

    @Override
    public Integer deleteCheckGroup(Integer checkGroupId) {
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
    @Async("asyncExecutor")
    public Integer updateCheckGroup(CheckGroup checkGroup, Integer[] checkItemIds) {
        Integer checkGroupId = checkGroup.getId();
        checkGroupAndCheckItemMapper.deleteRelationshipOfCheckGroupAndCheckItem(checkGroupId);
        Integer count=checkGroupMapper.batchAddCheckItemToCheckGroup(checkItemIds,checkGroupId);
//        for (Integer checkItemId : checkItemIds) {
//            Integer count = checkGroupMapper.addCheckItemToCheckGroup(checkItemId, checkGroupId);
//            sum = sum + count;
//        }
        return count;
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
    public Integer AddCheckItemsToTheCheckGroupByCheckGroupId(Integer[] checkItemIds, Integer checkGroupId) {
        Integer sum = 0;
        if (checkItemIds.length>0) {
            for (Integer checkItemId : checkItemIds) {
                Integer count = checkGroupMapper.addCheckItemToCheckGroup(checkItemId, checkGroupId);
                sum = sum + count;
            }
        }
        return sum;
    }

    @Override
    public PageResult findAllCheckItemByCheckGroup(QueryPageBean queryPageBean) {
        Integer currentPage = queryPageBean.getCurrentPage();
        Integer pageSize = queryPageBean.getPageSize();
        if (currentPage == 0 || currentPage == null) {
            currentPage = 1;
        }
        if (pageSize == 0 || pageSize == null) {
            pageSize = 3;
        }
        PageHelper.startPage(currentPage, pageSize);
        List<CheckGroup> checkGroupList = checkGroupMapper.findCheckGroupAndCheckItem(queryPageBean);
        PageInfo<CheckGroup> pageInfo = new PageInfo<>(checkGroupList);
        long total = pageInfo.getTotal();
        PageResult pageResult = new PageResult(total, checkGroupList);
        return pageResult;
    }

    @Override
    public CheckGroup findAllCheckItemByCheckGroupId(Integer checkGroupId) {
        CheckGroup checkGroup = checkGroupMapper.findCheckGroupById(checkGroupId);
        return checkGroup;
    }

    @Override
    @Async("asyncExecutor")
    public Integer addCheckGroup(CheckGroup checkGroup, Integer[] checkItemIds) {
        Integer sum = 0;
        CheckGroup checkGroupById = checkGroupMapper.findCheckGroupById(checkGroup.getId());
        if (checkGroupById == null) {
            checkGroup.setStatus(1);
            checkGroupMapper.addCheckGroup(checkGroup);
            if (checkItemIds.length > 0) {
                for (Integer checkItemId : checkItemIds) {
                    Integer count = checkGroupMapper.addCheckItemToCheckGroup(checkItemId, checkGroup.getId());
                    sum = sum + count;
                }
            }
        }
        return sum;
    }

    @Override
    public List<CheckGroup> findCheckGroup() {
        return checkGroupMapper.findALLCheckGroup();
    }
}
