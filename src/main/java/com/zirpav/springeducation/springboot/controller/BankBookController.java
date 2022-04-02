package com.zirpav.springeducation.springboot.controller;

import com.zirpav.springeducation.springboot.api.BankBookService;
import com.zirpav.springeducation.springboot.dto.BankBookDto;
import com.zirpav.springeducation.springboot.dto.ErrorDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class BankBookController {

    private final BankBookService bankBookService;

    public BankBookController(BankBookService bankBookService) {
        this.bankBookService = bankBookService;
    }

    @GetMapping("/bank-book/by-user-id/{userId}")
    public ResponseEntity getBankBookList(@PathVariable Integer userId) {
        if (userId == null) {
            return ResponseEntity.ok().body(ErrorDto.builder().info("NOT_FOUND_CLIENT").build());
        }
        List<BankBookDto> bankBooksByUserId = bankBookService.getBankBookByUserId(userId);
        if (bankBooksByUserId == null) {
            return ResponseEntity.ok().body(ErrorDto.builder().info("NOT_FOUND").build());
        }
        return ResponseEntity.ok().body(bankBooksByUserId);
    }

    @GetMapping("/bank-book/{bankBookId}")
    public ResponseEntity getBankBook(@PathVariable Integer bankBookId) {
        if (bankBookId == null) {
            return ResponseEntity.ok().body(ErrorDto.builder().info("NOT_FOUND_BANK_BOOK_ID").build());
        }
        try {
            BankBookDto bankBookByBankBookId = bankBookService.getBankBookByBankBookId(bankBookId);
            return ResponseEntity.ok().body(bankBookByBankBookId);
        } catch (Exception exception) {
            return ResponseEntity.ok().body(ErrorDto.builder().info("NOT_FOUND_BANK_BOOK").build());
        }
    }

    @PostMapping("/bank-book/{userId}")
    public ResponseEntity createBankBook(@RequestBody BankBookDto dto, @PathVariable Integer userId) {
        try {
            BankBookDto bankBookDto = bankBookService.create(dto);
            return ResponseEntity.ok().body(bankBookDto);
        } catch (Exception exception) {
            return ResponseEntity.ok().body(ErrorDto.builder().info(exception.getMessage()).build());
        }
    }

    @PutMapping("/bank-book/{userId}")
    public ResponseEntity updateBankBook(@RequestBody BankBookDto dto, @PathVariable Integer userId) {
        try {
            BankBookDto bankBookDto = bankBookService.update(dto);
            if (bankBookDto == null) {
                return ResponseEntity.ok().body(ErrorDto.builder().info("Нечего обновлять").build());
            }
            return ResponseEntity.ok().body(bankBookDto);
        } catch (Exception exception) {
            return ResponseEntity.ok().body(ErrorDto.builder().info(exception.getMessage()).build());
        }
    }

    @DeleteMapping("/bank-book/{bankBookId}")
    public void deleteBankBookByBankBookId(@PathVariable Integer bankBookId) {
        bankBookService.deleteBankBookByBankBookId(bankBookId);
    }

    @DeleteMapping("/bank-book/by-user-id/{userId}")
    public ResponseEntity deleteBankBooksByUserId(@PathVariable Integer userId) {
        List<BankBookDto> bankBookDtos = bankBookService.deleteBankBookByUserId(userId);
        return ResponseEntity.ok().body(bankBookDtos);
    }

}
