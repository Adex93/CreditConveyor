package com.example.gateway.clients;

import com.example.gateway.dto.LoanApplicationRequestDTO;
import com.example.gateway.dto.LoanOfferDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import javax.validation.Valid;
import java.util.List;


@FeignClient(name = "feignGatewayApp", url = "${custom.application.url}")
public interface FeignConveyorApplication {

    @PostMapping("/application/")
    ResponseEntity<List<LoanOfferDTO>> addNewApplication(@Valid @RequestBody LoanApplicationRequestDTO loanApplicationRequestDTO);

    @PutMapping("/application/offer/")
    void addClientOffer(@Valid @RequestBody LoanOfferDTO loanOfferDTO);


}


