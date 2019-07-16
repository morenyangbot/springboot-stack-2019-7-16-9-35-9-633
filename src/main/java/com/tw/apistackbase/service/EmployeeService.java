package com.tw.apistackbase.service;

import com.tw.apistackbase.entity.Employee;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class EmployeeService {

    private Map<Integer, Employee> employeeMap = new HashMap<>();
    private int idGeneratorIndex = 0;

    public List<Employee> findAll() {
        return new ArrayList<>(employeeMap.values());
    }

    public Employee save(Employee employee) {
        employee.setId(++idGeneratorIndex);
        employeeMap.put(employee.getId(), employee);
        return employee;
    }

    public Employee findById(Integer employeeId) {
        return employeeMap.get(employeeId);
    }

    public List<Employee> findByPage(Integer page, Integer pageSize) {
        int startIndex = (page - 1) * pageSize;
        int endIndex = startIndex + pageSize;
        return new ArrayList<>(employeeMap.values()).subList(startIndex, endIndex);
    }

    public List<Employee> findByGender(String gender) {
        return employeeMap.values().stream().filter(employee -> employee.getGender().equals(gender)).collect(Collectors.toList());
    }

    public Employee update(Employee employee) {
        return employeeMap.put(employee.getId(), employee);
    }

    public Employee delete(Integer employeeId) {
        return employeeMap.remove(employeeId);
    }
}
