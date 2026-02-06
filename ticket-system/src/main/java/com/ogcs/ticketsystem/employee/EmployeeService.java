package com.ogcs.ticketsystem.employee;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

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
        return employeeRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                "Employee with " + id + " not found"));
    }

    public Employee insertEmployee(Employee employee){
        return employeeRepository.save(employee);
    }

    public void deleteEmployeeById(Integer id) {
        employeeRepository.deleteById(id);
    }

    public Employee updateEmployeeById(Integer id, Employee updatedEmployee) {

        Employee existingEmployee = employeeRepository.findById(id)
                .orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND,
                        "Employee with "+ id + " not found!"));

        if (updatedEmployee.getName() != null) {
            existingEmployee.setName(updatedEmployee.getName());
        }

        if (updatedEmployee.getEmail() != null) {
            existingEmployee.setEmail(updatedEmployee.getEmail());
        }

        if (updatedEmployee.getDepartment() != null) {
            existingEmployee.setDepartment(updatedEmployee.getDepartment());
        }

        if (updatedEmployee.getActivityStatus() != null) {
            existingEmployee.setActivityStatus(updatedEmployee.getActivityStatus());
        }

        return employeeRepository.save(existingEmployee);
    }

    public Employee deactivateEmployeeById(Integer id){

        Employee employee = employeeRepository.findById(id).
                orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                        "Employee with " + id + " not found"));

        employee.setActivityStatus(false);

        return employeeRepository.save(employee);

    }

    public Employee activateEmployeeById(Integer id){

        Employee employee = employeeRepository.findById(id).
                orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                        "Employee with " + id + " not found"));

        employee.setActivityStatus(true);

        return employeeRepository.save(employee);

    }

}
