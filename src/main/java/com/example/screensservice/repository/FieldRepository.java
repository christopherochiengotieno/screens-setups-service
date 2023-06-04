package com.example.screensservice.repository;

import com.example.screensservice.model.entity.Field;
import org.springframework.data.jpa.repository.JpaRepository;

import java.math.BigDecimal;
import java.util.List;

public interface FieldRepository extends JpaRepository<Field, BigDecimal> {
    List<Field> findAllByScreenId(BigDecimal screenField);
}
