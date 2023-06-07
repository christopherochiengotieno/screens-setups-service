package com.example.screensservice.repository;

import com.example.screensservice.model.entity.SelectOption;
import org.springframework.data.jpa.repository.JpaRepository;

import java.math.BigDecimal;
import java.util.Optional;

public interface SelectOptionRepository extends JpaRepository<SelectOption, BigDecimal> {
    Optional<SelectOption> findByValueAndText(String value, String text);
}
