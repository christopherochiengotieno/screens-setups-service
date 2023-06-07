package com.example.screensservice.repository;

import com.example.screensservice.model.entity.SelectOptionPerField;
import com.example.screensservice.model.entity.SelectOptionPerFieldKey;
import org.springframework.data.jpa.repository.JpaRepository;

import java.math.BigDecimal;

public interface SelectOptionPerFieldRepository extends JpaRepository<SelectOptionPerField, SelectOptionPerFieldKey> {
}
