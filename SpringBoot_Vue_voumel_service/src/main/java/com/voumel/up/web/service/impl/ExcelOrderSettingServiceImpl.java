package com.voumel.up.web.service.impl;

import com.voumel.up.entity.ExcelOrderSetting;
import com.voumel.up.web.mapper.ExcelOrderSettingMapper;
import com.voumel.up.web.service.ExcelOrderSettingService;
import org.apache.ibatis.annotations.Mapper;
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
    public void addOrderSetting(List<ExcelOrderSetting> excelOrderSettingList) {
        //TODO 实际开发不能这样，需要考虑数据的条数
        // 如果是多条，那么就会导致cpu消耗大-----需要优化
        for (ExcelOrderSetting excelOrderSetting : excelOrderSettingList) {
            excelOrderSettingMapper.addOrderSetting(excelOrderSetting);
        }
    }
}
