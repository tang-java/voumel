package com.voumel.up.web.mapper;

import com.volume.up.pojo.Member;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author 小唐
 * @version 1.0
 * @project SpringBoot_Vue_voumel_parent
 * @description
 * @date 2023/8/1 19:57:26
 */
@Mapper
public interface MemberMapper {
    Member findMemberByPhoneNum(String phoneNum);

    void addMember(Member member);
}
