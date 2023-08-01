package com.voumel.up.web.controller;

import com.volume.up.pojo.TbTcmConstitutionIdentification;
import com.voumel.up.constant.MessageConstant;
import com.voumel.up.entity.Result;
import com.voumel.up.web.service.TcmConstitutionIdentificationService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author 小唐
 * @version 1.0
 * @project SpringBoot_Vue_voumel_parent
 * @description
 * @date 2023/7/31 20:51:42
 */
@RestController
public class TcmConstitutionIdentificationController {

    @Resource
    private TcmConstitutionIdentificationService tcmConstitutionIdentificationService;
    @GetMapping("/findAllTcmConstitutionIdentification")
    public Result findAllTcmConstitutionIdentification() {
        try {
            List<TbTcmConstitutionIdentification> list=tcmConstitutionIdentificationService.findAll();
            return new Result(true, MessageConstant.QUERY_CHINESE_MEDICAL_SUCCESS,list);
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false,MessageConstant.QUERY_CHINESE_MEDICAL_FAIL);
        }
    }
}
