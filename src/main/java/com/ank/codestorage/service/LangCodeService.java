package com.ank.codestorage.service;

import com.ank.codestorage.dto.LangCodeDto;
import com.ank.codestorage.model.LangCode;

import java.util.List;

public interface LangCodeService {
    List<LangCodeDto> findAll();

    LangCode findById(Integer id);
}
