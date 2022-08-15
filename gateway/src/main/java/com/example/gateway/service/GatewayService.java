package com.example.gateway.service;

import com.example.gateway.clients.FeignConveyorApplication;
import com.example.gateway.clients.FeignConveyorDeal;
import com.example.gateway.dto.FinishRegistrationRequestDTO;
import com.example.gateway.dto.LoanApplicationRequestDTO;
import com.example.gateway.dto.LoanOfferDTO;
import com.example.gateway.entity.Application;
import feign.FeignException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.stereotype.Service;

import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
@Slf4j
public class GatewayService {

    final
    FeignConveyorApplication feignConveyorApplication;

    final
    FeignConveyorDeal feignConveyorDeal;

    public GatewayService(FeignConveyorApplication feignConveyorApplication, FeignConveyorDeal feignConveyorDeal) {
        this.feignConveyorApplication = feignConveyorApplication;
        this.feignConveyorDeal = feignConveyorDeal;
    }

    public ResponseEntity<List<LoanOfferDTO>> addNewApplication(LoanApplicationRequestDTO loanApplicationRequestDTO) {

        try {
            log.info("Произведен запрос на /application/ MC Application");
            return feignConveyorApplication.addNewApplication(loanApplicationRequestDTO);
        } catch (FeignException.InternalServerError e) {
            log.error("Возникла ошибка: " + e.getMessage());
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
        }
    }

    public void addClientOffer(LoanOfferDTO loanOfferDTO) {

        try {
            log.info("Произведен запрос на application/offer/ MC Application");
            feignConveyorApplication.addClientOffer(loanOfferDTO);
        } catch (FeignException.InternalServerError e) {
            log.error("Возникла ошибка: " + e.getMessage());
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
        }
    }

    public void addFinishDTO(Long applicationId, FinishRegistrationRequestDTO finishRegistrationRequestDTO) {

        try {
            log.info("Произведен запрос на /calculate/{applicationId} MC Deal");
            feignConveyorDeal.addFinishDTO(applicationId, finishRegistrationRequestDTO);
        } catch (FeignException.InternalServerError e) {
            log.error("Возникла ошибка: " + e.getMessage());
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
        }
    }

    public void createDocuments(Long applicationId) {

        try {
            log.info("Произведен запрос на /document/{applicationId}/send MC Deal");
            feignConveyorDeal.createDocuments(applicationId);
        } catch (FeignException.InternalServerError e) {
            log.error("Возникла ошибка: " + e.getMessage());
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
        }
    }


    public void createSES(Long applicationId) {

        try {
            log.info("Произведен запрос на /document/{applicationId}/sign MC Deal");
            feignConveyorDeal.createSES(applicationId);
        } catch (FeignException.InternalServerError e) {
            log.error("Возникла ошибка: " + e.getMessage());
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
        }
    }


    public void enterSES(Long applicationId, Integer code) {

        try {
            log.info("Произведен запрос на /document/{applicationId}/code MC Deal");
            feignConveyorDeal.enterSES(applicationId, code);
        } catch (FeignException.InternalServerError e) {
            log.error("Возникла ошибка: " + e.getMessage());
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
        }
    }

    public void deniedCredit(Long applicationId) {

        try {
            log.info("Произведен запрос на /document/{applicationId}/denied MC Deal");
            feignConveyorDeal.deniedCredit(applicationId);
        } catch (FeignException.InternalServerError e) {
            log.error("Возникла ошибка: " + e.getMessage());
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
        }
    }

    public ResponseEntity<Application> getApplication(Long applicationId) {

        try {
            log.info("Произведен запрос на /admin/application/{applicationId} MC Deal");
            return feignConveyorDeal.getApplication(applicationId);
        } catch (FeignException.InternalServerError e) {
            log.error("Возникла ошибка: " + e.getMessage());
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
        }
    }

    public ResponseEntity<List<Application>> getAllApplications() {

        log.info("Произведен запрос на /admin/application MC Deal");
        return feignConveyorDeal.getAllApplications();
    }


}
