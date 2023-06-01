package com.example.screensservice.repository;

import com.example.screensservice.model.entity.FrontendScreen;
import org.springframework.data.jpa.repository.JpaRepository;

import java.math.BigDecimal;

public interface FrontendScreensRepository extends JpaRepository<FrontendScreen, BigDecimal> {
}
