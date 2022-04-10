package com.zirpav.springeducation.springboot.controller;

import com.zirpav.springeducation.springboot.service.api.BankBookService;
import com.zirpav.springeducation.springboot.model.dto.BankBookDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BankBookController {

    private final BankBookService bankBookService;

    public BankBookController(BankBookService bankBookService) {
        this.bankBookService = bankBookService;
    }

    @GetMapping("/bank-book/{bankBookId}")
    public ResponseEntity<BankBookDto> getBankBook(@PathVariable Long bankBookId) {
        BankBookDto bankBookByBankBookId = bankBookService.getBankBookById(bankBookId);
        return ResponseEntity.ok().body(bankBookByBankBookId);
    }

    @PostMapping("/bank-book/create")
    public ResponseEntity<BankBookDto> createBankBook(@RequestBody BankBookDto dto) {
        BankBookDto bankBookDto = bankBookService.create(dto);
        return ResponseEntity.ok().body(bankBookDto);
    }

    @PutMapping("/bank-book")
    public ResponseEntity<BankBookDto> updateBankBook(@RequestBody BankBookDto dto) {
        BankBookDto bankBookDto = bankBookService.update(dto);
        return ResponseEntity.ok().body(bankBookDto);
    }

    @DeleteMapping("/bank-book/{bankBookId}")
    public void deleteBankBookByBankBookId(@PathVariable Long bankBookId) {
        bankBookService.deleteBankBookById(bankBookId);
    }

}
