package com.example.usedCarSales.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.example.usedCarSales.dto.RoleDTO;
import com.example.usedCarSales.entity.Role;

@Mapper(componentModel = "spring")
public interface RoleMapper {
    @Mapping(source = "idRole", target = "roleId")
    RoleDTO RoletoRoleDto(Role role);

    @Mapping(source = "roleId", target = "idRole")
    Role RoleDtoToRole(RoleDTO roleDTO);
}
