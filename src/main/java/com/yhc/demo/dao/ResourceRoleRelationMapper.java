package com.yhc.demo.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.yhc.demo.entity.vo.UrlRolenameVo;

@Mapper
public interface ResourceRoleRelationMapper {

	List<UrlRolenameVo> selectAll();

}
