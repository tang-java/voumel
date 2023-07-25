package com.voumel.up.web.controller;

import com.voumel.up.constant.MessageConstant;
import com.volume.up.pojo.CheckItem;
import com.voumel.up.entity.PageResult;
import com.voumel.up.entity.QueryPageBean;
import com.voumel.up.entity.Result;
import com.voumel.up.web.service.CheckItemService;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.stream.Collectors;

/**
 * @author 小唐
 * @version 1.0
 * @project SpringBoot_Vue_voumel_parent
 * @description
 * @date 2023/7/24 13:49:17
 */
@RestController
public class ChickItemController {
    @Resource
    private CheckItemService checkItemService;

    @GetMapping("/checkItem/{page}/{pageSize}")
    public Result findItemByConditionAndPaging(@PathVariable("page") Integer currentPage, @PathVariable Integer pageSize,String queryString) {
        Result checkItemResult = new Result();
        QueryPageBean queryPageBean = new QueryPageBean();
        PageResult pageResult = null;
        queryPageBean.setCurrentPage(currentPage);
        queryPageBean.setPageSize(pageSize);
        queryPageBean.setQueryString(queryString);
        try {
            pageResult = checkItemService.findItemByConditionAndPaging(queryPageBean);

            checkItemResult.setData(pageResult);
            checkItemResult.setMessage(MessageConstant.QUERY_CHECKITEM_SUCCESS);
            checkItemResult.setFlag(true);
        } catch (Exception e) {
            checkItemResult.setMessage(MessageConstant.QUERY_CHECKITEM_FAIL);
            checkItemResult.setFlag(false);
            e.printStackTrace();
        } finally {
            return checkItemResult;
        }
    }

    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    @PostMapping("/checkItem")
    public Result addCheckItem(@RequestBody @Validated CheckItem checkItem, BindingResult bindingResult) {
        Result result = new Result();
        if (bindingResult.hasErrors()) {
            result.setFlag(false);
            result.setMessage(bindingResult.getAllErrors().stream().map(x -> x.getDefaultMessage()).collect(Collectors.toList()).toString());
            return result;
        }
        Integer count = checkItemService.addCheckItem(checkItem);
        if (count > 0) {
            result.setFlag(true);
            result.setMessage(MessageConstant.ADD_CHECKITEM_SUCCESS);
        } else {
            result.setFlag(false);
            result.setMessage(MessageConstant.ADD_CHECKITEM_FAIL);
        }
        return result;
    }

    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    @PutMapping("/checkItem")
    public Result updateCheckItem(@RequestBody CheckItem checkItem) {
        CheckItem check = checkItemService.findCheckItemById(checkItem.getId());
        checkItem.setStatus(check.getStatus());
        if (check != null) {
            try {
                checkItemService.updateCheckItem(checkItem);
                return new Result(true, MessageConstant.EDIT_CHECKITEM_SUCCESS);
            } catch (Exception e) {
                e.printStackTrace();
                return new Result(false, MessageConstant.EDIT_CHECKITEM_FAIL);
            }
        } else {
            return new Result(false, "修改的数据不存在");
        }
    }

    @GetMapping("/checkItem/delete/{id:\\d+}")
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    public Result deleteCheckItem(@PathVariable Integer id) {
        // 做删除前的确认
        CheckItem checkItemById = checkItemService.findCheckItemById(id);
        if (checkItemById != null || checkItemById.getStatus() == 1) {
            try {
                checkItemService.updateCheckItemStatus(checkItemById);
                return new Result(true, MessageConstant.DELETE_CHECKGROUP_SUCCESS);
            } catch (Exception e) {
                e.printStackTrace();
                return new Result(false, MessageConstant.DELETE_CHECKITEM_FAIL);
            }
        }
        return new Result(false, "要删除的检查项不存在！！");
    }
}
