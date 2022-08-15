package com.example.gateway.dto;

import com.example.gateway.enums.Status;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;
import java.time.LocalDateTime;

@Getter
@Setter
@ToString
public class ApplicationStatusHistoryDTO implements Serializable {
    Status status;
    LocalDateTime time;
    Status changeType;

    public ApplicationStatusHistoryDTO(Status status, LocalDateTime time, Status changeType) {
        this.status = status;
        this.time = time;
        this.changeType = changeType;
    }


}
