package com.example.gateway.clients;

import com.example.gateway.dto.FinishRegistrationRequestDTO;
import com.example.gateway.entity.Application;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@FeignClient(name = "feignGatewayDeal", url = "${custom.deal.url}")
public interface FeignConveyorDeal {

    @PutMapping("/calculate/{applicationId}")
    void addFinishDTO(@PathVariable(name = "applicationId") Long applicationId, @Valid @RequestBody FinishRegistrationRequestDTO finishRegistrationRequestDTO);

    @PostMapping("/document/{applicationId}/send")
    void createDocuments(@PathVariable(name = "applicationId") Long applicationId);

    @PostMapping("/document/{applicationId}/sign")
    void createSES(@PathVariable(name = "applicationId") Long applicationId);

    @PostMapping("/document/{applicationId}/code")
    void enterSES(@PathVariable(name = "applicationId") Long applicationId, @RequestBody Integer code);

    @PostMapping("/document/{applicationId}/denied")
    void deniedCredit(@PathVariable(name = "applicationId") Long applicationId);

    @GetMapping("/admin/application/{applicationId}")
    ResponseEntity<Application> getApplication(@PathVariable(name = "applicationId") Long applicationId);

    @GetMapping("/admin/application")
     ResponseEntity<List<Application>> getAllApplications();
}


