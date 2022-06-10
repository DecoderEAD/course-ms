package com.ead.coursems.services.impl;

import com.ead.coursems.repositories.ModuleRepository;
import com.ead.coursems.services.ModuleService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ModuleServiceImpl implements ModuleService {
    private final ModuleRepository moduleRepository;
}
