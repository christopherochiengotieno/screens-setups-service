package com.example.screensservice.repository;

import com.example.screensservice.model.entity.Screen;
import org.springframework.data.jpa.repository.JpaRepository;

import java.math.BigDecimal;

public interface ScreensRepository extends JpaRepository<Screen, BigDecimal> {
}
