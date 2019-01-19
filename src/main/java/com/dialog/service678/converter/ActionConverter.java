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
        actionFormDto.setActionDesc(action.getActionDesc());
        return actionFormDto;
    }

    public Action dtoToEntity(ActionFormDto actionFormDto, DService dService, SCApi scApi) {
        Action action = new Action();
        action.setActionDesc(actionFormDto.getActionDesc());
//        action.setKeyWords(actionFormDto.getKeyWordFromDtos().stream().map(KeyWordConverter::dtoToEntity).collect(Collectors.toList()));
        action.setService(dService);
        action.setScApi(scApi);
        return action;
    }
}
