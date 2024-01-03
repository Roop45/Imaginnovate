package com.imaginnovate.service;

import com.imaginnovate.controller.EmployeeController;
import com.imaginnovate.model.Employee;
import com.imaginnovate.model.TaxCalc;
import com.imaginnovate.repository.EmployeeRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Optional;

@Service
public class EmployeeService {

    private static Logger logger = LoggerFactory.getLogger(EmployeeService.class);
    @Autowired
    private EmployeeRepository employeeRepository;

    public TaxCalc getTaxDetailsForEmployee(Long employeeId) {

        logger.info("Fetching employee details for the empId : {}",employeeId);
        Optional<Employee> employee = employeeRepository.findById(employeeId);
        int yearlySalary=0;

        int monthlySalary=employee.get().getSalaryPerMonth();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/YYYY");
       LocalDate finYearStartDate=LocalDate.parse("01/04/2023",formatter);
        LocalDate joiningDate = LocalDate.parse(employee.get().getDOJ(), formatter);

        if (joiningDate.isBefore(finYearStartDate)) {
            yearlySalary = monthlySalary * 12;
        } else if(joiningDate.isAfter(finYearStartDate))

        {

            int noOfMonths=joiningDate.getMonthValue()-finYearStartDate.getMonthValue();
            int noOfDays=joiningDate.getDayOfMonth()-finYearStartDate.getDayOfMonth();

            yearlySalary=noOfMonths*monthlySalary+ (noOfDays/30)*monthlySalary;

        }

        double taxAmt = 0;
        double cessAmt = 0;
        if (yearlySalary <= 250000) {
            taxAmt = 0;
        } else if (yearlySalary > 250000 && yearlySalary <= 500000) {
            taxAmt = 0.05 * (yearlySalary - 250000);

        } else if (yearlySalary > 500000 && yearlySalary <= 1000000) {
            taxAmt = 12500 + 0.1 * (yearlySalary - 500000);

        } else if (yearlySalary > 1000000) {

            taxAmt = 62500 + 0.2 * (yearlySalary - 100000);

            if (yearlySalary >= 250000) {
                cessAmt = (yearlySalary - 2500000) * 0.02;
            }
        }

        logger.info("Returning taxDetailsforEmployee");

        return TaxCalc.builder().taxAmt(taxAmt).
                cessAmt(cessAmt).
                yearlySalary(yearlySalary).
                employeeId(employee.get().getEmployeeId())
                .firstName(employee.get().getFirstName())
                .lastName(employee.get().getLastName()).build();

    }
}
