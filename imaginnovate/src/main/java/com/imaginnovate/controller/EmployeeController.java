package com.imaginnovate.controller;

import com.imaginnovate.exception.UserException;
import com.imaginnovate.model.Employee;
import com.imaginnovate.model.TaxCalc;
import com.imaginnovate.repository.EmployeeRepository;
import com.imaginnovate.service.EmployeeService;
import com.imaginnovate.util.TestUtil;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/employeeManagement")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private TestUtil util;

    private static Logger logger = LoggerFactory.getLogger(EmployeeController.class);

    @PostMapping(value = "/create/employee")
    public Employee createEmployee(@RequestBody Employee employee) throws UserException {

        try {
            logger.info("Validating employee details ");

            util.validateEmployee(employee);

            employeeRepository.save(employee);
            logger.info("Successfully saved employee details ");


        } catch (Exception exception) {

            System.out.println("Found Exception: Please enter correct details");
            logger.info("Invalid details found for employee");
        }

         return employee;

}



    @PostMapping(value = "/findTax/{empId}")
    public TaxCalc calcTax(@PathVariable Long empId){


        return employeeService.getTaxDetailsForEmployee(empId);



    }


}
