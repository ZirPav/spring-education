package com.zirpav.springeducation.springboot.controller;

import com.zirpav.springeducation.springboot.api.BankBookService;
import com.zirpav.springeducation.springboot.dto.BankBookDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/rest/bank-book")
public class BankBookController {

    private final BankBookService bankBookService;

    public BankBookController(BankBookService bankBookService) {
        this.bankBookService = bankBookService;
    }

    @GetMapping("/by-user-id/{userId}")
    public ResponseEntity<List<BankBookDto>> getBankBookList(@PathVariable Integer userId) {
        List<BankBookDto> bankBooksByUserId = bankBookService.findByUserId(userId);
        return ResponseEntity.ok().body(bankBooksByUserId);
    }

    @GetMapping("/{bankBookId}")
    public ResponseEntity<BankBookDto> getBankBook(@PathVariable Integer bankBookId) {
        BankBookDto bankBookByBankBookId = bankBookService.findById(bankBookId);
        return ResponseEntity.ok().body(bankBookByBankBookId);
    }

    @PostMapping
    public ResponseEntity<BankBookDto> createBankBook(@RequestBody BankBookDto dto) {
        BankBookDto bankBookDto = bankBookService.create(dto);
        return ResponseEntity.ok().body(bankBookDto);
    }

    @PutMapping
    public ResponseEntity<BankBookDto> updateBankBook(@RequestBody BankBookDto dto) {
        BankBookDto bankBookDto = bankBookService.update(dto);
        return ResponseEntity.ok().body(bankBookDto);
    }

    @DeleteMapping("/{bankBookId}")
    public void deleteBankBookByBankBookId(@PathVariable Integer bankBookId) {
        bankBookService.delete(bankBookId);
    }

    @DeleteMapping("/by-user-id/{userId}")
    public void deleteBankBooksByUserId(@PathVariable Integer userId) {
        bankBookService.deleteByUserId(userId);
    }

}
