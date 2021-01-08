package com.yhc.demo.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface RoleMapper {

	String selectById(@Param("id") int id);

	// 根据多个id查询角色名
	List<String> selectByIds(@Param("ids") List<Integer> id);

}
