package com.example.screensservice.service;

import com.example.screensservice.mapper.FrontendScreensMapper;
import com.example.screensservice.model.dto.FrontendScreenDto;
import com.example.screensservice.repository.FrontendScreensRepository;
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
