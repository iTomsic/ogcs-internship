package com.ogcs.ticketsystem.employee;

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
    public List<Employee> getEmployees(){
        return employeeService.getEmployees();
    }

    @GetMapping("{id}")
    public Employee getEmployeeById(@PathVariable Integer id){
        return employeeService.getEmployeeById(id);
    }

    @PostMapping
    public Employee addNewEmployee(@RequestBody Employee employee){
        return employeeService.insertEmployee(employee);
    }

    @DeleteMapping("{id}")
    public void deleteEmployeeById(@PathVariable Integer id){
        employeeService.deleteEmployeeById(id);
    }

    @PatchMapping("{id}")
    public Employee updateEmployeeById(@PathVariable Integer id, @RequestBody Employee employee) {
        return employeeService.updateEmployeeById(id, employee);
    }
}
