package com.tw.apistackbase.controller;

import com.tw.apistackbase.entity.Company;
import com.tw.apistackbase.entity.Employee;
import com.tw.apistackbase.service.CompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/companies")
public class CompanyController {

    @Autowired
    private CompanyService companyService;

    @GetMapping("")
    public List<Company> findAll() {
        return companyService.findAll();
    }

    @GetMapping("/{id}")
    public Company findById(@PathVariable Integer id) {
        return companyService.findById(id);
    }

    @GetMapping(value = "", params = {"page", "pageSize"})
    public List<Company> findByPage(@RequestParam Integer page, @RequestParam Integer pageSize) {
        return companyService.findByPage(page, pageSize);
    }

    @PostMapping
    public Company addCompany(@RequestBody Company company) {
        return companyService.save(company);
    }

    @PutMapping
    public Company updateCompany(@RequestBody Company company) {
        return companyService.update(company);
    }

    @DeleteMapping("/{id}")
    public Company deleteCompany(@PathVariable Integer id) {
        return companyService.delete(id);
    }

    @PostMapping("/{id}/employees")
    public Company addEmployee(@PathVariable Integer id, @RequestBody Employee employee) {
        return companyService.addEmployee(id, employee);
    }
}
