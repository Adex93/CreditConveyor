package com.example.gateway.entity;

import com.example.gateway.enums.EmploymentStatus;
import com.example.gateway.enums.Position;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.math.BigDecimal;

@Getter
@Setter
@ToString
public class Employment {

    EmploymentStatus employmentStatus;

    String employer;

    BigDecimal salary;

    Position position;

    Integer workExperienceTotal;

    Integer workExperienceCurrent;
}
