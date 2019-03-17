package com.engineeernitesh.employeesapi;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/employee")
@Api(value="employees-home")
public class EmployeesDataController {

    private final static Logger LOGGER = Logger.getLogger(EmployeesDataController.class.getName());

    @Autowired
    EmployeeRepository employeeRepository;
    
    //-------------------Retrieve all employees--------------------------------------------------------
    @RequestMapping(value = "", method= RequestMethod.GET, produces = "application/json")
    @ApiOperation(value = "View a list of employees", response = Iterable.class)
    List<Employee> getEmployees() {
        List<Employee> result;
        LOGGER.log(Level.INFO, "Getting all employees");
        result = new ArrayList();
        Iterable<Employee> employeeList = employeeRepository.findAll();
        for (Employee employee : employeeList) {
            result.add(employee);
        }
        return result;
    }
    
    //-------------------Retrieve a employee by id--------------------------------------------------------
    @RequestMapping(value = "/{id}", method= RequestMethod.GET, produces = "application/json")
    @ApiOperation(value = "Get an employee by id", response = Employee.class)
    public Employee getEmployee(@PathVariable long id) {
        Employee result;
        LOGGER.log(Level.INFO, "Getting employee with id " + id);
        result = employeeRepository.findById(id);
        return result;
    }
  
    //-------------------Add an employee--------------------------------------------------------------------
    @PreAuthorize("hasRole('ADMIN')")
    @RequestMapping(method = RequestMethod.POST, produces = "application/text")
    @ApiOperation(value = "Add a new employee")
    public ResponseEntity saveEmployee(@RequestBody Employee input) {
        LOGGER.log(Level.INFO, "Saving employee " + input.getLastname());
        Employee employee = new Employee();
        employee.setFirstname(input.getFirstname());
        employee.setLastname(input.getLastname());    
	employeeRepository.save(employee);
        return new ResponseEntity("Employee saved successfully", HttpStatus.OK);
    }
    
    //-------------------Delete a employee by id------------------------------------------------------------
    @PreAuthorize("hasRole('ADMIN')")
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE, produces = "application/text")
    @ApiOperation(value = "Delete an employee by id")
    public ResponseEntity deleteEmployee(@PathVariable long id) {
        LOGGER.log(Level.INFO, "Deleting employee " + id);
	employeeRepository.delete(id);
        return new ResponseEntity("Employee deleted successfully", HttpStatus.OK);
    }
}
