package com.zirpav.springeducation.springboot.repository;

import com.zirpav.springeducation.springboot.model.entity.Currency;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CurrencyRepository extends JpaRepository<Currency, Long> {
}
