package ru.job4j.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.job4j.domain.Employee;
import ru.job4j.domain.Person;
import ru.job4j.repository.EmployeeRepository;

import java.util.List;

@RestController
@RequestMapping("/emp")
public class EmployeeController {

    private final EmployeeRepository employeeRepository;

    public EmployeeController(final EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    @GetMapping("/")
    public List<Employee> findAll() {
     return (List<Employee>) this.employeeRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Employee> findById(@PathVariable int id) {
        var person = this.employeeRepository.findById(id);
        return new ResponseEntity<Employee>(
                person.orElse(new Employee()),
                person.isPresent() ? HttpStatus.OK : HttpStatus.NOT_FOUND
        );
    }

    @PostMapping("/")
    public ResponseEntity<Employee> create(@RequestBody Employee emp) {
        return new ResponseEntity<Employee>(
                this.employeeRepository.save(emp),
                HttpStatus.CREATED
        );
    }

    @PutMapping("/")
    public ResponseEntity<Void> update(@RequestBody Employee emp) {
        this.employeeRepository.save(emp);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable int id) {
        Employee emp = new Employee();
        emp.setId(id);
        this.employeeRepository.delete(emp);
        return ResponseEntity.ok().build();
    }
}
