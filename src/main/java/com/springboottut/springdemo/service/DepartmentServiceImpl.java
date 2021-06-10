package com.springboottut.springdemo.service;

import com.springboottut.springdemo.entity.Department;
import com.springboottut.springdemo.error.DepartmentNotFoundException;
import com.springboottut.springdemo.repository.DepartmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class DepartmentServiceImpl implements DepartmentService{

    @Autowired
    private DepartmentRepository departmentRepository;

    @Override
    public Department saveDepartment(Department department) {
        return departmentRepository.save(department);
    }

    @Override
    public List<Department> fetchDepartmentList() {
        return departmentRepository.findAll();
    }

    @Override
    public Department fetchDepartmentById(Long departmentId) throws DepartmentNotFoundException {
        Optional<Department> department = departmentRepository.findById(departmentId);

        if(!department.isPresent()){
            throw new DepartmentNotFoundException("Department Not Available");
        }
        return  department.get();
    }

    @Override
    public void deleteDepartmentById(Long departmentId) {
            departmentRepository.deleteById(departmentId);
    }

    @Override
    public Department updateDepartment(Long departmentId, Department department) {
        Department depDB = departmentRepository.findById(departmentId).get();

        if(Objects.nonNull(department.getDename()) && !"".equalsIgnoreCase(department.getDename())){
            depDB.setDename(department.getDename());
        }

        if(Objects.nonNull(department.getDeCode()) && !"".equalsIgnoreCase(department.getDeCode())){
            depDB.setDeCode(department.getDeCode());
        }
        if(Objects.nonNull(department.getDeaddress()) && !"".equalsIgnoreCase(department.getDeaddress())){
            depDB.setDeaddress(department.getDeaddress());
        }
        return departmentRepository.save(depDB);

    }

    @Override
    public Department fetchDepartmentByName(String departmentName) {
        return departmentRepository.findByDenameIgnoreCase(departmentName);
    }
}
