package com.voumel.up.web.controller;

import com.volume.up.pojo.CheckGroup;
import com.voumel.up.constant.MessageConstant;
import com.voumel.up.entity.PageResult;
import com.voumel.up.entity.QueryPageBean;
import com.voumel.up.entity.Result;
import com.voumel.up.web.service.CheckGroupService;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.ArrayList;

/**
 * @author 小唐
 * @version 1.0
 * @project SpringBoot_Vue_voumel_parent
 * @description 检查组的Controller
 * @date 2023/7/25 10:21:36
 */
@RestController
public class CheckGroupController {

    @Resource
    private CheckGroupService checkGroupService;

    /**
     * 新增checkGroup检查组
     *
     * @param checkGroup---新增的检查组
     * @param checkItemIds        ---需要添加的检查项的id数组
     * @return
     */
    @PutMapping({"/checkGroup/{ids}", "/checkGroup"})
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    public Result addCheckGroup(@RequestBody @Validated CheckGroup checkGroup, @PathVariable("ids") Integer[] checkItemIds) {
        //TODO 检查组新增页面
        if (checkGroup != null) {
            try {
                checkGroupService.addCheckGroup(checkGroup, checkItemIds);
                return new Result(true, MessageConstant.ADD_CHECKGROUP_SUCCESS);
            } catch (Exception e) {
                e.printStackTrace();
                return new Result(false, MessageConstant.ADD_CHECKGROUP_FAIL);
            }
        } else {
            return new Result(false, "传递的参数无效，新增失败");
        }
    }

    /**
     * 删除检查组，并将检查组与检查项之间的关联关系页一并删除
     *
     * @param id
     * @return
     */
    @GetMapping("/checkGroup/{id:\\d+}")
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    @Deprecated
    public Result deleteCheckGroupById(@PathVariable Integer id) {
        CheckGroup checkGroup = checkGroupService.findCheckGroupById(id);
        if (checkGroup != null) {
            try {
                checkGroup.setStatus(0);
                checkGroupService.updateCheckGroup(checkGroup);
                return new Result(true, MessageConstant.DELETE_CHECKGROUP_SUCCESS);
            } catch (Exception e) {
                e.printStackTrace();
                return new Result(false, MessageConstant.DELETE_CHECKGROUP_FAIL);
            }
        } else {
            return new Result(false, "没有该检查组或检查组已被删除");
        }
    }

    /**
     * 通过传入的checkGroup对象来修改表的数据
     *
     * @param checkGroup
     * @return result
     */
    @PostMapping("/checkGroup/update")
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    public Result updateCheckGroup(@RequestBody @Validated CheckGroup checkGroup) {
        CheckGroup checkGroupById = checkGroupService.findCheckGroupById(checkGroup.getId());
        if (checkGroupById != null) {
            try {
                checkGroupService.updateCheckGroup(checkGroup);
                return new Result(true, MessageConstant.EDIT_CHECKGROUP_SUCCESS);
            } catch (Exception e) {
                e.printStackTrace();
                return new Result(false, MessageConstant.EDIT_CHECKGROUP_FAIL);
            }
        } else {
            return new Result(false, "没有该检查组！！！");
        }
    }

    /**
     * 带条件的模糊、分页查询
     *
     * @param currentPage 当前页码
     * @param pageSize    页面数据条数
     * @param queryString 模糊查询的条件----可以为空
     * @return result
     */
    @GetMapping("/checkGroup/{page}/{pageSize}")
    public Result findCheckGroup(@PathVariable("page") Integer currentPage, @PathVariable Integer pageSize, String queryString) {
        QueryPageBean queryPageBean = new QueryPageBean(currentPage, pageSize, queryString);
        PageResult pageResult = null;
        try {
            pageResult = checkGroupService.findCheckGroup(queryPageBean);
            return new Result(true, MessageConstant.QUERY_CHECKGROUP_SUCCESS, pageResult);
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false, MessageConstant.QUERY_CHECKGROUP_FAIL);
        }
    }

    /**
     * 查询所有检查组中的所有检查项,分页+模糊查询
     *
     * @param currentPage ---当前页码
     * @param pageSize    ---当前页面大小
     * @param queryString ----模糊查询条件--可以为空
     * @return result
     */
    @GetMapping("/checkGroup/checkItem/findAll/{page}/{pageSize}")
    public Result findAllCheckItemByCheckGroup(@PathVariable("page") Integer currentPage, @PathVariable("pageSize") Integer pageSize, String queryString) {
        QueryPageBean queryPageBean = new QueryPageBean(currentPage, pageSize, queryString);
        try {
            PageResult pageResult = checkGroupService.findAllCheckItemByCheckGroup(queryPageBean);
            return new Result(true, "查询检查组中的检查项成功", pageResult);
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(true, "查询检查组中的检查项失败!!!");
        }
    }

    /**
     * 通过检查组id查询检查组，以及检查组下包含的检查项
     *
     * @param checkGroupId
     * @return
     */
    @GetMapping("/checkGroup/find/{id}")
    public Result findAllCheckItemByCheckGroupId(@PathVariable("id") Integer checkGroupId) {
        try {
            CheckGroup checkGroup = checkGroupService.findAllCheckItemByCheckGroupId(checkGroupId);
            ArrayList<CheckGroup> checkGroupArrayList = new ArrayList<>();
            checkGroupArrayList.add(checkGroup);
            PageResult pageResult = new PageResult(Long.valueOf(1), checkGroupArrayList);
            return new Result(true, "通过检查组Id查询检查项成功", pageResult);
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false, "通过检查组Id查询检查项失败");
        }
    }

    /**
     * 修改检查组，包括组包括的检查项
     * 修改检查项，之前会先删除中间表的数据，再将传入的ids,遍历---插入中间表
     *
     * @param checkGroup   --要被修改的检查组的信息
     * @param checkItemIds --该检查组包含的检查项的id数组
     * @return
     */
    @PostMapping("/checkGroup/{ids}")
    public Result updateCheckGroup(@RequestBody CheckGroup checkGroup, @PathVariable("ids") Integer[] checkItemIds) {
        CheckGroup checkGroupById = checkGroupService.findCheckGroupById(checkGroup.getId());
        if (checkGroupById == null) {
            return new Result(false, MessageConstant.EDIT_CHECKGROUP_FAIL + "该检查组可能不存在");
        }
        try {
            checkGroupService.updateCheckGroup(checkGroup, checkItemIds);
            return new Result(true, MessageConstant.EDIT_CHECKGROUP_SUCCESS);
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false, MessageConstant.EDIT_CHECKGROUP_FAIL);
        }
    }


    /**
     * 一个检查组中可以有多个检查项
     * 通过检查组Id，将检查项，单个或者批量的形式添加进入检查组
     *
     * @return result
     */
    /*
    @PostMapping("/checkGroup")
    public Result AddCheckItemsToTheCheckGroupByCheckGroupId(@PathVariable Integer[] checkItemIds, @PathVariable Integer checkGroupId) {
        //TODO 还未测试,与新增接口重叠
        try {
            Integer count = checkGroupService.AddCheckItemsToTheCheckGroupByCheckGroupId(checkItemIds, checkGroupId);
            return new Result(true, "在检查组中新增检查项成功---新增" + count + "个检查项");
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false, "在检查组中新增检查项失败");
        }
    }
    */


}
