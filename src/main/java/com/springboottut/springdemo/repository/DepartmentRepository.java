package com.springboottut.springdemo.repository;

import com.springboottut.springdemo.entity.Department;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DepartmentRepository extends JpaRepository<Department, Long> {
    public Department findByDename(String dename);

    public Department findByDenameIgnoreCase(String dename);

}
