package com.example.frontendscreensservice.mapper;

import com.example.frontendscreensservice.model.dto.FrontendScreenDto;
import com.example.frontendscreensservice.model.entity.FrontendScreen;
import org.springframework.stereotype.Service;

@Service
public class FrontendScreensMapperImpl implements FrontendScreensMapper {
    @Override
    public FrontendScreenDto toDto(FrontendScreen frontendScreen) {
        return FrontendScreenDto.builder()
                .screenName(frontendScreen.getScreenName())
                .targetSystem(frontendScreen.getTargetSystem())
                .id(frontendScreen.getId())
                .screenFields(frontendScreen.getScreenFields())
                .build();
    }
}
