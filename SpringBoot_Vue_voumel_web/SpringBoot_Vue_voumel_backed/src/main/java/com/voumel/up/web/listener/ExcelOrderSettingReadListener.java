package com.voumel.up.web.listener;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.voumel.up.entity.ExcelOrderSetting;
import com.voumel.up.web.service.ExcelOrderSettingService;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * @author 小唐
 * @version 1.0
 * @project SpringBoot_Vue_voumel_parent
 * @description
 * @date 2023/7/28 20:43:00
 */
@Component
public class ExcelOrderSettingReadListener extends AnalysisEventListener<ExcelOrderSetting> {
    @Resource
    private ExcelOrderSettingService excelOrderSettingService;

    private List<ExcelOrderSetting>  excelOrderSettingList=new ArrayList<>();

    @Override
    public void invoke(ExcelOrderSetting excelOrderSetting, AnalysisContext analysisContext) {
        excelOrderSettingList.add(excelOrderSetting);
    }

    @Override
    public void doAfterAllAnalysed(AnalysisContext analysisContext) {
        //调用Service插入数据库
        if (excelOrderSettingList.size()>0){
            excelOrderSettingService.addOrderSetting(excelOrderSettingList);
        }
    }
}
