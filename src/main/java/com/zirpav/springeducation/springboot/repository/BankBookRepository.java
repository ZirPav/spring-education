package com.zirpav.springeducation.springboot.repository;

import com.zirpav.springeducation.springboot.model.entity.BankBook;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BankBookRepository extends JpaRepository<BankBook, Long> {
}
