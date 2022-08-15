package com.example.gateway.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.springframework.ui.context.Theme;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class EmailMessage {

    String address;
    Theme theme;
    Long applicationId;


}
