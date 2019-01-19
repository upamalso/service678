package com.dialog.service678.converter;

import com.dialog.service678.dto.RoleFormDto;
import com.dialog.service678.entity.Role;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
public class RoleFormConverter {

    public Role dtoToEntity(RoleFormDto roleFormDto){

        Role role = new Role();
        role.setId(roleFormDto.getId());
        role.setName(roleFormDto.getName());
        role.setPrivileges(roleFormDto.getPrivilegeDtos().stream().map(PrivilegeConverter::dtoToEntity).collect(Collectors.toList()));
        return role;
    }
}
