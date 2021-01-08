package com.yhc.demo.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface UserRoleRelationMapper {

	String selectRoleNameByUserId(@Param("userId") int userId);

}
