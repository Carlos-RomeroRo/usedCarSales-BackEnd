package com.example.usedCarSales.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.example.usedCarSales.dto.RoleDTO;
import com.example.usedCarSales.entity.Role;

@Mapper(componentModel = "spring")
public interface RoleMapper {
    @Mapping(source = "idRole", target = "roleId")
    RoleDTO RoletoRoleDto(Role role);

    default Role RoleDtoToRole(RoleDTO roleDTO) {
        if (roleDTO == null) {
            return null;
        }
        Role role = new Role();
        role.setIdRole(roleDTO.roleId());
        role.setName(roleDTO.name());
        return role;
    }
}
