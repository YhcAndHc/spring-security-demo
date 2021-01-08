package com.yhc.demo.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.yhc.demo.entity.UserEntity;

@Mapper
public interface UserMapper {
	
	// for spring security valid
	UserEntity selectByName(@Param("userName")String userName);
	
	// public select
	UserEntity select(UserEntity user);

}
