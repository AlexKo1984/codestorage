package com.ank.codestorage.controller;

import com.ank.codestorage.dto.*;
import com.ank.codestorage.service.LangCodeService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/lang_code")
@RequiredArgsConstructor
public class LangCodeController {
    private final LangCodeService langCodeService;

    @GetMapping
    public List<LangCodeDto> findAll() {
        return langCodeService.findAll();
    }

}
