package com.example.usedCarSales.service.implementation;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.usedCarSales.dto.RoleDTO;
import com.example.usedCarSales.entity.Role;
import com.example.usedCarSales.mapper.RoleMapper;
import com.example.usedCarSales.repository.RoleRepository;
import com.example.usedCarSales.service.interfaces.RoleService;

import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class RoleServiceImpl implements RoleService {
    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private RoleMapper roleMapper;

    @Override
    public RoleDTO createRole(RoleDTO roleDTO) {
        if (roleDTO.roleId() != null && roleRepository.existsById(roleDTO.roleId())) {
            throw new IllegalArgumentException("Role with this ID already exists");
        }
        try {
            Role newRole = roleMapper.RoleDtoToRole(roleDTO);
            Role savedRole = roleRepository.save(newRole);
            log.info("Role with ID {} created successfully", savedRole.getIdRole());
            return roleMapper.RoletoRoleDto(savedRole);
        } catch (Exception e) {
            log.error("Error creating role: {}", e.getMessage());
            throw new RuntimeException("Error creating role");
        }
    }

    @Override
    public RoleDTO updateRole(Long id, RoleDTO roleDTO) {
        try {
            Role roleExisting = roleRepository.findById(id)
                    .orElseThrow(() -> new IllegalArgumentException("Role not found"));
            roleExisting.setName(roleDTO.name());
            Role updatedRole = roleRepository.save(roleExisting);
            log.info("Role with ID {} updated successfully", updatedRole.getIdRole());
            return roleMapper.RoletoRoleDto(updatedRole);
        } catch (Exception e) {
            log.error("Error updating role: {}", e.getMessage());
            throw new RuntimeException("Error updating role");
        }
    }

    @Override
    @Transactional
    public boolean deleteRole(Long id) {
        try {
            if (!roleRepository.existsById(id)) {
                log.warn("Role with ID {} not found", id);
                throw new IllegalArgumentException("Role not found");
            }
            roleRepository.deleteById(id);
            log.info("Role with ID {} deleted successfully", id);
            return true;
        } catch (Exception e) {
            log.error("Error deleting role: {}", e.getMessage());
            throw new RuntimeException("Error deleting role");
        }
    }

    @Override
    public RoleDTO getRoleById(Long id) {
        try {
            Role role = roleRepository.findById(id)
                    .orElseThrow(() -> new IllegalArgumentException("Role not found"));
            log.info("Role with ID {} retrieved successfully", role.getIdRole());
            return roleMapper.RoletoRoleDto(role);
        } catch (Exception e) {
            log.error("Error retrieving role: {}", e.getMessage());
            throw new RuntimeException("Error retrieving role");
        }
    }

    @Override
    public List<RoleDTO> getAllRole() {
        try {
            List<Role> roles = roleRepository.findAll();
            log.info("Retrieved {} roles from the database", roles.size());
            return roles.stream().map(roleMapper::RoletoRoleDto).toList();
        } catch (Exception e) {
            log.error("Error retrieving roles: {}", e.getMessage());
            throw new RuntimeException("Error retrieving roles");
        }
    }

}
