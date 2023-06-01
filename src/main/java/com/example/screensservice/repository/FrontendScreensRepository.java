package com.example.frontendscreensservice.repository;

import com.example.frontendscreensservice.model.entity.FrontendScreen;
import org.springframework.data.jpa.repository.JpaRepository;

import java.math.BigDecimal;

public interface FrontendScreensRepository extends JpaRepository<FrontendScreen, BigDecimal> {
}
