package com.tw.apistackbase.service;

import com.tw.apistackbase.entity.Company;
import com.tw.apistackbase.entity.Employee;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class CompanyService {
    private Map<Integer, Company> companyMap = new HashMap<>();
    private int idGeneratorIndex = 0;

    public List<Company> findAll() {
        return new ArrayList<>(companyMap.values());
    }

    public Company save(Company company) {
        company.setId(++idGeneratorIndex);
        companyMap.put(company.getId(), company);
        return company;
    }

    public Company findById(Integer companyId) {
        return companyMap.get(companyId);
    }

    public List<Company> findByPage(Integer page, Integer pageSize) {
        int startIndex = (page - 1) * pageSize;
        int endIndex = startIndex + pageSize;
        return new ArrayList<>(companyMap.values()).subList(startIndex, endIndex);
    }


    public Company update(Company company) {
        return companyMap.put(company.getId(), company);
    }

    public Company delete(Integer companyId) {
        return companyMap.remove(companyId);
    }

    public Company addEmployee(Integer companyId, Employee employee) {
        Company company = companyMap.get(companyId);
        if (company != null) {
            company.addEmployee(employee);
            return companyMap.put(company.getId(), company);
        }
        return null;
    }

}
