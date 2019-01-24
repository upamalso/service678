package com.dialog.service678.converter;

import com.dialog.service678.dto.ActionFormDto;
import com.dialog.service678.entity.Action;
import com.dialog.service678.entity.DService;
import com.dialog.service678.entity.SCApi;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
public class ActionConverter {

    public static ActionFormDto entityToDto(Action action) {
        ActionFormDto actionFormDto = new ActionFormDto();
//        actionFormDto.setServiceId(action.getServiceId());
        actionFormDto.setActionDesc(action.getActionDesc());
//        actionFormDto.setApiId(action.getApiId());
//        actionFormDto.setKeyWordFromDtos(action.getKeyWordsByActionId().stream().map(KeyWordConverter::entityToDto).collect(Collectors.toList()));
        return actionFormDto;
    }

    public static Action dtoToEntity(ActionFormDto actionFormDto, DService dService, SCApi scApi) {
        Action action = new Action();
        action.setService(dService);
        action.setActionDesc(actionFormDto.getActionDesc());
//        action.setApiId(actionFormDto.getApiId());
//        action.setKeyWordsByActionId(actionFormDto.getKeyWordFromDtos().stream().map(KeyWordConverter::dtoToEntity).collect(Collectors.toList()));
//        action.setService(dService);

        return action;
    }
}
