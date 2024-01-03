package com.imaginnovate.model;


import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import javax.persistence.Id;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Data
public class Employee {

    @Id
    @JsonProperty(value = "id",required = true)
    private Long employeeId;

    @JsonProperty(value = "first_name", required = true)
    private String firstName;

    @JsonProperty(value = "last_name", required = true)
    private String lastName;

    @JsonProperty(value = "email_id",required = true)
    private String emailId;

    @JsonProperty(value = "phone_number",required = true)
    private List<String> phoneNumber;

    @JsonProperty(value = "doj",required = true)
    private String DOJ;

    @JsonProperty(value = "salary_per_month",required = true)
    private Integer salaryPerMonth;



}
