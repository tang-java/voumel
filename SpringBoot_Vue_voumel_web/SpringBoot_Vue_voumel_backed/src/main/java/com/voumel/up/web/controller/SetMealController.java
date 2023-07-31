package com.voumel.up.web.controller;

import com.volume.up.pojo.SetMeal;
import com.voumel.up.constant.MessageConstant;
import com.voumel.up.entity.PageResult;
import com.voumel.up.entity.QueryPageBean;
import com.voumel.up.entity.Result;
import com.voumel.up.web.service.SetMealService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Set;

/**
 * @author 小唐
 * @version 1.0
 * @project SpringBoot_Vue_voumel_parent
 * @description 这是菜单的Controller
 * @date 2023/7/26 19:47:20
 */
@RestController
public class SetMealController {
    @Resource
    private SetMealService setMealService;

    /**
     * 根据条件查询所有的套餐
     *
     * @param currentPage ---当前页码
     * @param pageSize    ---当前页面大小
     * @param queryString ---模糊查询的数据
     * @return
     */
    @GetMapping("/setMeal/{page}/{pageSize}")
    public Result findSetMeal(@PathVariable("page") Integer currentPage, @PathVariable Integer pageSize, String queryString) {
        QueryPageBean queryPageBean = new QueryPageBean(currentPage, pageSize, queryString);
        try {
            PageResult pageResult = setMealService.findSetMeal(queryPageBean);
            return new Result(true, MessageConstant.QUERY_SETMEALLIST_SUCCESS, pageResult);
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false, MessageConstant.QUERY_SETMEALLIST_FAIL);
        }
    }

    /**
     * 新增套餐，并将检查组加入套餐中
     *
     * @param setMeal
     * @param checkGroupIds
     * @return
     */
    @PostMapping("/setMeal/{ids}")
    public Result addSetMeal(@RequestBody SetMeal setMeal, @PathVariable("ids") Integer[] checkGroupIds) {
        if (setMeal != null) {
            try {
                setMealService.addSetMeal(setMeal, checkGroupIds);
                return new Result(true, MessageConstant.ADD_SETMEAL_SUCCESS);
            } catch (Exception e) {
                e.printStackTrace();
                return new Result(false, MessageConstant.ADD_SETMEAL_FAIL);
            }
        }
        return new Result(false, MessageConstant.ADD_SETMEAL_FAIL + "可能的原因是参数没有封装");
    }

    /**
     * 删除套餐，将套餐的status修改为0
     * @param setMealId ----套餐id
     * @return result
     */
    @GetMapping("/setMeal/{setMealId}")
    public Result deleteSetMeal(@PathVariable Integer setMealId){
        SetMeal setMeal = setMealService.findSetMealById(setMealId);
        if (setMeal!=null){
            setMeal.setStatus(0);
            try {
                setMealService.updateSetMeal(setMeal);
                return new Result(true,MessageConstant.EDIT_SETMEAL_SUCCESS);
            } catch (Exception e) {
                e.printStackTrace();
                return new Result(false,MessageConstant.EDIT_SETMEAL_FAIL);
            }
        }
        return new Result(false,MessageConstant.EDIT_SETMEAL_FAIL);
    }

    /**
     * 修改套餐以及套餐详情，并且可以修改套餐关联的检查组
     *
     * 修改关联关系之前，要先删除与该套餐id 有关系的中间表的数据，再插入关联数据
     * @param setMeal ---套餐
     * @param checkGroupIds ---套餐关联的检查组
     * @return result
     */
    @PutMapping("/setMeal/{ids}")
    public Result updateSetMeal(@RequestBody SetMeal setMeal,@PathVariable("ids") Integer[] checkGroupIds){
        try {
            setMealService.updateSetMeal(setMeal,checkGroupIds);
            return new Result(true,MessageConstant.EDIT_SETMEAL_SUCCESS);
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false,MessageConstant.EDIT_SETMEAL_FAIL);
        }
    }

    /**
     * 通过id查询套餐的详情
     * @param setMealId ---套餐的id
     * @return result  包含检查组以及检查组中的检查项
     */
    @GetMapping("/setMealFind/{setMealId}")
    public Result findSetMealById(@PathVariable Integer setMealId){
        try {
            SetMeal setMeal = setMealService.findSetMealById(setMealId);
            return new Result(true,MessageConstant.QUERY_SETMEAL_SUCCESS,setMeal);
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false,MessageConstant.QUERY_SETMEAL_FAIL);
        }
    }
}
