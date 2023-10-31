package com.ank.codestorage.service.impl;

import com.ank.codestorage.dto.LangCodeDto;
import com.ank.codestorage.exception.NotFoundException;
import com.ank.codestorage.mapper.LangCodeMapper;
import com.ank.codestorage.model.LangCode;
import com.ank.codestorage.repositorie.LangCodeRepository;
import com.ank.codestorage.service.LangCodeService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class LangCodeServiceImpl implements LangCodeService {
    private final LangCodeRepository langCodeRepository;
    private final LangCodeMapper langCodeMapper;

    @Override
    public List<LangCodeDto> findAll() {
        return langCodeMapper.mapToLangCodeDto(langCodeRepository.findAll());
    }

    @Override
    public LangCode findById(Integer id) {
        return langCodeRepository.findById(id).orElseThrow(() -> throwLandCodeNotFoundException(id));
    }
    private NotFoundException throwLandCodeNotFoundException(Integer id) {
        String message = "Язык с id " + id + " не найден!.";
        log.warn(message);
        return new NotFoundException(message);
    }
}
