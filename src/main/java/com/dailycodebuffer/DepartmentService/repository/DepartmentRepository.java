package com.dailycodebuffer.DepartmentService.repository;

import com.dailycodebuffer.DepartmentService.model.Department;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class DepartmentRepository {

    private List<Department> departments = new ArrayList<>();

    public Department addDepartment(Department department){
        departments.add(department);
        return department;
    }

    public Department findById(Long id){
        // need to figure why streaming API is not recognised here..
        // return departments.stream().filter( department --> department.getId().equals(id)).findFirst().orElseThrow();
        for (Department department : departments) {
            if (department.getId().equals(id)) {
                return department;
            }
        }
        return null;
    }

    public List<Department> findAll(){
        return departments;
    }
}
