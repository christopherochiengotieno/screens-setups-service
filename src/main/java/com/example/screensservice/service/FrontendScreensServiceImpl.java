package com.example.frontendscreensservice.service;

import com.example.frontendscreensservice.mapper.FrontendScreensMapper;
import com.example.frontendscreensservice.model.dto.FrontendScreenDto;
import com.example.frontendscreensservice.repository.FrontendScreensRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class FrontendScreensServiceImpl implements FrontendScreensService {

    private final FrontendScreensRepository frontendScreensRepository;
    private final FrontendScreensMapper frontendScreensMapper;
    @Override
    public List<FrontendScreenDto> getListOfFrontendScreens() {
        return frontendScreensRepository.findAll().stream().map(frontendScreensMapper::toDto).collect(Collectors.toList());
    }
}
