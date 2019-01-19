package com.dialog.service678.converter;

import com.dialog.service678.dto.RoleDto;
import com.dialog.service678.entity.Role;
import org.springframework.stereotype.Component;

@Component
public class RoleConverter {
    public RoleDto entityToDto(Role role) {

        RoleDto roleDto = new RoleDto();
        roleDto.setId(role.getId());
        roleDto.setName(role.getName());
        return roleDto;
    }

}
