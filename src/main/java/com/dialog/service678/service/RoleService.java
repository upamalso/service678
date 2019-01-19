package com.dialog.service678.service;

import com.dialog.service678.converter.RoleConverter;
import com.dialog.service678.converter.RoleFormConverter;
import com.dialog.service678.dto.RoleDto;
import com.dialog.service678.dto.RoleFormDto;
import com.dialog.service678.entity.Role;
import com.dialog.service678.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class RoleService {

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private RoleConverter roleConverter;

    @Autowired
    private RoleFormConverter roleFormConverter;

    public List<RoleDto> findAll() {
        return roleRepository.findAll().stream().map(roleConverter::entityToDto).collect(Collectors.toList());
    }

    public void saveOrUpdate(RoleFormDto roleFormDto) {
        Role role = roleFormConverter.dtoToEntity(roleFormDto);
        roleRepository.save(role);
    }
}
