package com.EmployeeAppService.app.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.EmployeeAppService.app.model.Employee;

@RestController
public class EmployeeController {
	
	
	@RequestMapping("/employeesList")
    public List<Employee> getEmployees() 
    {
		List<Employee> employeesList = new ArrayList<Employee>();
		employeesList.add(new Employee(1,"monal","rani","ranimonal@gmail.com","Redmond, WA"));
		System.out.println("employee lists");
		return employeesList;
    }


}
