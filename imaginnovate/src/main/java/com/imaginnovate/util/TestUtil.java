package com.imaginnovate.util;

import com.imaginnovate.exception.UserException;
import com.imaginnovate.model.Employee;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class TestUtil {

    public void validateEmployee(Employee employee) throws UserException {
        String phone="^[0-9]{10}$";
        String mailId = "^[a-zA-Z0-9+_.-]+@[a-zA-Z0-9.-]+$";
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/YYYY");

        if(!employee.getEmailId().matches(mailId)){
              throw new UserException("Plz Enter Correct Mail Id");

        }
         else if(!employee.getEmailId().matches(phone)){
             throw new UserException("Plz enter valid phone number");
         }
         else if(!(LocalDate.parse(employee.getDOJ(),formatter)).isAfter(LocalDate.now())){

             throw new UserException("Plz enter valid DOJ");
        }


    }
}
