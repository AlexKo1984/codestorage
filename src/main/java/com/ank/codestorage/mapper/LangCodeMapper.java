package com.ank.codestorage.mapper;

import com.ank.codestorage.dto.LangCodeDto;
import com.ank.codestorage.dto.PostDto;
import com.ank.codestorage.model.LangCode;
import com.ank.codestorage.model.Post;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class LangCodeMapper {
    public LangCodeDto mapToLangCodeDto(LangCode langCode) {
        return new LangCodeDto(langCode.getId(), langCode.getName(), langCode.getTitle());
    }

    public List<LangCodeDto> mapToLangCodeDto(List<LangCode> langCodes) {
        List<LangCodeDto> result = new ArrayList<>();
        for (LangCode langCode: langCodes) {
            result.add(mapToLangCodeDto(langCode));
        }

        return result;
    }
    public LangCode mapToLangCode(LangCodeDto langCodeDto) {
        return new LangCode(langCodeDto.getId(), langCodeDto.getName(), langCodeDto.getTitle());
    }
}
