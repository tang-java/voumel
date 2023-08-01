package com.voumel.up.web.service.impl;

import com.volume.up.pojo.TbTcmConstitutionIdentification;
import com.voumel.up.web.mapper.TcmConstitutionIdentificationMapper;
import com.voumel.up.web.service.TcmConstitutionIdentificationService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author 小唐
 * @version 1.0
 * @project SpringBoot_Vue_voumel_parent
 * @description
 * @date 2023/7/31 20:54:32
 */
@Service
public class TcmConstitutionIdentificationServiceImpl implements TcmConstitutionIdentificationService {
    @Resource
    private TcmConstitutionIdentificationMapper tcmConstitutionIdentificationMapper;
    @Override
    public List<TbTcmConstitutionIdentification> findAll() {
        return tcmConstitutionIdentificationMapper.findAll();
    }
}
