package com.pay.dao;

import org.apache.ibatis.annotations.Param;

import com.pay.entity.UserInfo;

public interface UserDaoMapper {
	public UserInfo login(@Param("userId") String userId, @Param("userPassword") String userPassword);
}
