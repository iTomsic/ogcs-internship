package com.ogcs.ticketsystem.employee;

import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class EmployeeService {

    private final EmployeeRepository employeeRepository;
    private final ModelMapper modelMapper;

    public EmployeeService(EmployeeRepository employeeRepository, ModelMapper modelMapper) {
        this.employeeRepository = employeeRepository;
        this.modelMapper = modelMapper;
    }

    public List<EmployeeDTO> getEmployees(){
        return employeeRepository.findAll()
                .stream()
                .map(employee -> modelMapper.map(employee, EmployeeDTO.class))
                .collect(Collectors.toList());
    }

    public EmployeeDTO getEmployeeById(Integer id){
        Employee employee = employeeRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                "Employee with " + id + " not found"));

        return modelMapper.map(employee, EmployeeDTO.class);
    }

    public EmployeeDTO insertEmployee(Employee employee){
        Employee savedEmployee = employeeRepository.save(employee);

        return modelMapper.map(savedEmployee, EmployeeDTO.class);
    }

    public void deleteEmployeeById(Integer id) {
        employeeRepository.deleteById(id);
    }

    public EmployeeDTO updateEmployeeById(Integer id, Employee updatedEmployee) {

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

        Employee savedEmployee = employeeRepository.save(existingEmployee);

        return modelMapper.map(savedEmployee, EmployeeDTO.class);
    }

    public EmployeeDTO deactivateEmployeeById(Integer id){

        Employee existingEmployee = employeeRepository.findById(id).
                orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                        "Employee with " + id + " not found"));

        existingEmployee.setActivityStatus(false);

        Employee deactivatedEmployee = employeeRepository.save(existingEmployee);
        System.out.println("Employee with id: " + id + " deactivated");
        return modelMapper.map(deactivatedEmployee, EmployeeDTO.class);
    }

    public EmployeeDTO activateEmployeeById(Integer id){

        Employee existingEmployee = employeeRepository.findById(id).
                orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                        "Employee with " + id + " not found"));

        existingEmployee.setActivityStatus(true);

        Employee activatedEmployee = employeeRepository.save(existingEmployee);
        System.out.println("Employee with id: " + id + " activated");
        return modelMapper.map(activatedEmployee, EmployeeDTO.class);
    }

}
