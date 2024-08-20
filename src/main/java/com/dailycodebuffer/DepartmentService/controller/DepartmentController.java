package com.dailycodebuffer.DepartmentService.controller;

import com.dailycodebuffer.DepartmentService.client.EmployeeClient;
import com.dailycodebuffer.DepartmentService.model.Department;
import com.dailycodebuffer.DepartmentService.repository.DepartmentRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/department")
public class DepartmentController {

    private static Logger logger = LoggerFactory.getLogger(DepartmentController.class);

    @Autowired
    private DepartmentRepository repository;

    @Autowired
    private EmployeeClient employeeClient;

    @PostMapping
    public Department add(@RequestBody Department department){
        logger.info("addDepartment: department = " + department);
        return repository.addDepartment(department);
    }

    @GetMapping
    public List<Department> findAll(){
        logger.info("findAll:  ");
        return repository.findAll();
    }

    @GetMapping("/{id}")
    public Department findById(@PathVariable Long id){
        logger.info("findById: id= " + id);
        return repository.findById(id);
    }

    @GetMapping("/with-employees")
    public List<Department> findAllWithEmployees(){
        logger.info("findAll Departments with-employees  ");
        List<Department> departments =
                repository.findAll();

        for (Department department : departments) {
            logger.info("findAll Departments with-employees, department = "+ department );
        }
        departments.forEach(department -> department.setEmployees(
                employeeClient.findByDepartment( department.getId())
        ));
        return departments;
    }
}