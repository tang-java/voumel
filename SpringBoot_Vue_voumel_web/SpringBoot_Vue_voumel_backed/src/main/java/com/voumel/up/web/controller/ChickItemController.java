package com.voumel.up.web.controller;

import com.volume.up.constant.MessageConstant;
import com.volume.up.pojo.CheckItem;
import com.voumel.up.entity.PageResult;
import com.voumel.up.entity.QueryPageBean;
import com.voumel.up.entity.Result;
import com.voumel.up.web.service.CheckItemService;
import org.apache.ibatis.jdbc.Null;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

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
}
