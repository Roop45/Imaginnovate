package com.imaginnovate.model;

import lombok.Builder;
import lombok.Data;

import java.util.Date;

@Data
@Builder
public class TaxCalc {

    private Long employeeId;

    private String firstName;

    private String lastName;

    private int yearlySalary;

    private Double taxAmt;

    private Double cessAmt;



}
