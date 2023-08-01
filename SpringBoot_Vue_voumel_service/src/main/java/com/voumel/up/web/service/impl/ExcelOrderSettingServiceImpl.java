package com.voumel.up.web.service.impl;

import com.voumel.up.entity.ExcelOrderSetting;
import com.voumel.up.web.mapper.ExcelOrderSettingMapper;
import com.voumel.up.web.service.ExcelOrderSettingService;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author 小唐
 * @version 1.0
 * @project SpringBoot_Vue_voumel_parent
 * @description
 * @date 2023/7/28 20:50:51
 */
@Service
public class ExcelOrderSettingServiceImpl implements ExcelOrderSettingService {
    @Resource
    private ExcelOrderSettingMapper excelOrderSettingMapper;
    @Override
    @Async("asyncExecutor")
    public void BatchAddOrderSetting(List<ExcelOrderSetting> excelOrderSettingList) {
        excelOrderSettingMapper.batchAddOrderSetting(excelOrderSettingList);
//        for (ExcelOrderSetting excelOrderSetting : excelOrderSettingList) {
//            excelOrderSettingMapper.addOrderSetting(excelOrderSetting);
//        }
    }
}
