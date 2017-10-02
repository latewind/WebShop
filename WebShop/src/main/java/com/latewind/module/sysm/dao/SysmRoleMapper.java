package com.latewind.module.sysm.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.latewind.module.sysm.entity.SysmRole;

public interface SysmRoleMapper {
    int deleteByPrimaryKey(Integer roleId);

    int insert(SysmRole record);

    int insertSelective(SysmRole record);

    SysmRole selectByPrimaryKey(Integer roleId);
    
    
    SysmRole selectByRoleName(String roleName);
    
    
    List<SysmRole> selectAllRole();
    
   Integer insertRoleFunction(@Param("list")List<Integer> list,@Param("roleId") Integer roleId);
   
   Integer deleteRoleFunction(Integer roleId);

    int updateByPrimaryKeySelective(SysmRole record);

    int updateByPrimaryKey(SysmRole record);
}