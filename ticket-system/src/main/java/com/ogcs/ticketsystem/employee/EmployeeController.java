package com.ogcs.ticketsystem.employee;

import jakarta.validation.*;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/employees")
public class EmployeeController {

    private final EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping
    public ResponseEntity<List<EmployeeDTO>> getEmployees() {
        List<EmployeeDTO> employees = employeeService.getEmployees();
        return new ResponseEntity<List<EmployeeDTO>>(employees, HttpStatus.OK);
    }

    @GetMapping("{id}")
    public ResponseEntity<EmployeeDTO> getEmployeeById(@PathVariable Integer id){
        EmployeeDTO employeeDTO = employeeService.getEmployeeById(id);
        return new ResponseEntity<EmployeeDTO>(employeeDTO, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<EmployeeDTO> addNewEmployee(@Valid @RequestBody Employee employee){
        EmployeeDTO savedEmployee = employeeService.insertEmployee(employee);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedEmployee);
    }

    @DeleteMapping("{id}")
    public void deleteEmployeeById(@PathVariable Integer id){
        employeeService.deleteEmployeeById(id);
    }

    @PatchMapping("{id}")
    public ResponseEntity<EmployeeDTO> updateEmployeeById(@PathVariable Integer id, @RequestBody Employee employee) {
        EmployeeDTO savedEmployee = employeeService.updateEmployeeById(id, employee);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedEmployee);
    }

    @PatchMapping("{id}/deactivate")
    public ResponseEntity<EmployeeDTO> deactivateEmployeeById(@PathVariable Integer id) {
        EmployeeDTO deactivatedEmployee = employeeService.deactivateEmployeeById(id);
        return ResponseEntity.status(HttpStatus.CREATED).body(deactivatedEmployee);
    }

    @PatchMapping("{id}/activate")
    public ResponseEntity<EmployeeDTO> activateEmployeeById(@PathVariable Integer id) {
        EmployeeDTO activatedEmployee = employeeService.activateEmployeeById(id);
        return ResponseEntity.status(HttpStatus.CREATED).body(activatedEmployee);
    }
}
