package com.example.usedCarSales.service.interfaces;

import java.util.List;

import com.example.usedCarSales.dto.RoleDTO;

public interface RoleService {
    RoleDTO createRole(RoleDTO roleDTO);
    RoleDTO updateRole(Long id, RoleDTO roleDTO);
    boolean deleteRole(Long id);
    RoleDTO getRoleById(Long id);
    List<RoleDTO> getAllRole();
}
