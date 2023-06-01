package com.example.screensservice.mapper;

import com.example.screensservice.model.dto.FrontendScreenDto;
import com.example.screensservice.model.entity.FrontendScreen;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;

@Service
public class FrontendScreensMapperImpl implements FrontendScreensMapper {

    ObjectMapper objectMapper = new ObjectMapper();
    @Override
    public FrontendScreenDto toDto(FrontendScreen frontendScreen){

        FrontendScreenDto frontendScreenDto = null;
        try {
            frontendScreenDto = FrontendScreenDto.builder()
                    .screenName(frontendScreen.getScreenName())
                    .targetSystem(frontendScreen.getTargetSystem())
                    .id(frontendScreen.getId())
                    .description(frontendScreen.getDescription())
                    .shortDescription(frontendScreen.getShortDescription())
                    .screenFields(objectMapper.readValue(frontendScreen.getScreenFields(), Object.class))
                    .build();
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
        return frontendScreenDto;
    }
}
