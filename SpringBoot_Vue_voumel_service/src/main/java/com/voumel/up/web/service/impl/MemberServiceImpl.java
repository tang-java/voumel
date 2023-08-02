package com.voumel.up.web.service.impl;

import com.voumel.up.web.mapper.MemberMapper;
import com.voumel.up.web.service.MemberService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author 小唐
 * @version 1.0
 * @project SpringBoot_Vue_voumel_parent
 * @description
 * @date 2023/8/1 19:55:17
 */
@Service
public class MemberServiceImpl implements MemberService {

    @Resource
    private MemberMapper memberMapper;
}
