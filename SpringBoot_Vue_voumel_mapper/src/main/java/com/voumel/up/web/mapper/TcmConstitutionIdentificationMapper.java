package com.voumel.up.web.mapper;

import com.volume.up.pojo.TbTcmConstitutionIdentification;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @author 小唐
 * @version 1.0
 * @project SpringBoot_Vue_voumel_parent
 * @description
 * @date 2023/7/31 20:55:40
 */
@Mapper
public interface TcmConstitutionIdentificationMapper {
    List<TbTcmConstitutionIdentification> findAll();
}
