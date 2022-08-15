package com.example.gateway.controllers;

import com.example.gateway.dto.FinishRegistrationRequestDTO;
import com.example.gateway.dto.LoanApplicationRequestDTO;
import com.example.gateway.dto.LoanOfferDTO;
import com.example.gateway.entity.Application;
import com.example.gateway.service.GatewayService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;


@Slf4j
@RestController
@Validated
public class MainController {

    final
    GatewayService gatewayService;

    public MainController(GatewayService gatewayService) {
        this.gatewayService = gatewayService;
    }

    @Tag(name = "The application API")
    @Operation(summary = "Create loan application")
    @PostMapping("/application")
    public ResponseEntity<List<LoanOfferDTO>> addNewApplication(@Valid @RequestBody LoanApplicationRequestDTO loanApplicationRequestDTO) {

        log.info("Произвёлся POST запрос /application со следующим телом: " + loanApplicationRequestDTO);
        log.info("Вызвана функция addNewApplication класса GatewayService");
        return gatewayService.addNewApplication(loanApplicationRequestDTO);
    }

    @Tag(name = "The application API")
    @Operation(summary = "Apply offer")
    @PutMapping("/application/apply")
    public void addClientOffer(@Valid @RequestBody LoanOfferDTO loanOfferDTO) {

        log.info("Произвёлся PUT запрос /application/apply со следующим телом: " + loanOfferDTO);
        log.info("Вызвана функция addClientOffer класса GatewayService");
        gatewayService.addClientOffer(loanOfferDTO);
    }

    @Tag(name = "The application API")
    @Operation(summary = "Finish registration")
    @PutMapping("/application/registration/{applicationId}")
    public void addFinishDTO(@PathVariable(name = "applicationId") Long applicationId, @Valid @RequestBody FinishRegistrationRequestDTO finishRegistrationRequestDTO) {

        log.info("Произвёлся PUT запрос /application/registration/{applicationId} с applicationId: " + applicationId + " и телом FinishRegistrationRequestDTO: " + finishRegistrationRequestDTO);
        log.info("Вызвана функция addFinishDTO класса GatewayService");
        gatewayService.addFinishDTO(applicationId, finishRegistrationRequestDTO);
    }

    @Tag(name = "The document API")
    @Operation(summary = "Create documents")
    @PostMapping("/document/{applicationId}/send")
    public void createDocuments(@PathVariable(name = "applicationId") Long applicationId) {

        log.info("Произвёлся POST запрос /document/{applicationId}/send с applicationId: " + applicationId);
        log.info("Вызвана функция createDocuments класса GatewayService");
        gatewayService.createDocuments(applicationId);
    }

    @Tag(name = "The document API")
    @Operation(summary = "Sign documents")
    @PostMapping("/document/{applicationId}/sign")
    public void createSES(@PathVariable(name = "applicationId") Long applicationId) {

        log.info("Произвёлся POST запрос /document/{applicationId}/sign с applicationId: " + applicationId);
        log.info("Вызвана функция createSES класса GatewayService");
        gatewayService.createSES(applicationId);
    }

    @Tag(name = "The document API")
    @Operation(summary = "Send SES code")
    @PostMapping("/document/{applicationId}/code")
    public void enterSES(@PathVariable(name = "applicationId") Long applicationId, @RequestBody Integer code) {

        log.info("Произвёлся PUT запрос /document/{applicationId}/code с applicationId: " + applicationId + " и телом Integer: " + code);
        log.info("Вызвана функция enterSES класса GatewayService");
        gatewayService.enterSES(applicationId, code);
    }

    @Tag(name = "The application API")
    @Operation(summary = "Deny application")
    @PostMapping("/application/{applicationId}/deny")
    public void deniedCredit(@PathVariable(name = "applicationId") Long applicationId) {

        log.info("Произвёлся PUT запрос /application/{applicationId}/deny с applicationId: " + applicationId);
        log.info("Вызвана функция deniedCredit класса GatewayService");
        gatewayService.deniedCredit(applicationId);
    }

    @Tag(name = "Admin")
    @Operation(summary = "Get all applications")
    @GetMapping("/admin/application")
    public ResponseEntity<List<Application>> getAllApplication() {

        log.info("Произвёлся POST запрос /application");
        log.info("Вызвана функция getAllApplications класса GatewayService");
        return gatewayService.getAllApplications();
    }

    @Tag(name = "Admin")
    @Operation(summary = "Get application by ID")
    @GetMapping("/admin/application/{applicationId}")
    public ResponseEntity<Application> getApplicationById(@PathVariable(name = "applicationId") Long applicationId) {

        log.info("Произвёлся Get запрос /admin/application/{applicationId} с applicationId: " + applicationId);
        log.info("Вызвана функция getApplication класса GatewayService");
        return gatewayService.getApplication(applicationId);
    }

}
