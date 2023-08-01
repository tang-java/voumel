package com.voumel.up.web.mapper;

import com.voumel.up.entity.ExcelOrderSetting;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @author 小唐
 * @version 1.0
 * @project SpringBoot_Vue_voumel_parent
 * @description
 * @date 2023/7/28 20:53:58
 */
@Mapper
public interface ExcelOrderSettingMapper {
    void addOrderSetting(ExcelOrderSetting excelOrderSetting);

    void batchAddOrderSetting(List<ExcelOrderSetting> excelOrderSettingList);
}
