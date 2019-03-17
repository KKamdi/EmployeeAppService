package com.engineeernitesh.employeesapi;

import javax.transaction.Transactional;
import org.springframework.data.repository.CrudRepository;

@Transactional
public interface  EmployeeRepository extends CrudRepository<Employee, Long> {

    Employee findById(long id);
    
    Employee findByLastname(String Lastname);

}