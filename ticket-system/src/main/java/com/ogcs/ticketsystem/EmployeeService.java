package com.ogcs.ticketsystem;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeService {

    private final EmployeeRepository employeeRepository;

    public EmployeeService(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    public List<Employee> getEmployees(){
        return employeeRepository.findAll();
    }

    public Employee getEmployeeById(Integer id){
        return employeeRepository.findById(id).orElseThrow(() -> new IllegalStateException(id + " not found"));
    }

    public void insertEmployee(Employee employee){
        employeeRepository.save(employee);
    }
    
}
