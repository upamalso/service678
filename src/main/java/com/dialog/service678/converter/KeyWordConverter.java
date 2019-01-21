package com.dialog.service678.converter;

import com.dialog.service678.dto.KeyWordFromDto;
import com.dialog.service678.entity.Actions;
import com.dialog.service678.entity.KeyWord;
import org.springframework.stereotype.Component;

@Component
public class KeyWordConverter {

    public static KeyWord dtoToEntity(KeyWordFromDto keyWordFromDto) {
        KeyWord keyWord = new KeyWord();
        keyWord.setActionId(keyWordFromDto.getActionId());
        keyWord.setFirstKey(keyWordFromDto.getFirstKey());
        keyWord.setRegEx(keyWordFromDto.getRegEx());
        keyWord.setFullMatch(keyWordFromDto.getFullMatch());
        keyWord.setIgnoreCase(keyWordFromDto.getIgnoreCase());
        return keyWord;
    }

    public static KeyWordFromDto entityToDto(KeyWord keyWord) {
        KeyWordFromDto keyWordFromDto = new KeyWordFromDto();
        keyWordFromDto.setActionId(keyWord.getActionId());
        keyWordFromDto.setFirstKey(keyWord.getFirstKey());
        keyWordFromDto.setRegEx(keyWord.getRegEx());
        keyWordFromDto.setIgnoreCase(keyWord.getIgnoreCase());
        return keyWordFromDto;
    }
}
