package com.springboottut.springdemo.service;

import com.springboottut.springdemo.entity.Department;
import com.springboottut.springdemo.repository.DepartmentRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class DepartmentServiceTest {

    @Autowired
    private DepartmentService departmentService;

    @MockBean
    private DepartmentRepository departmentRepository;

    @BeforeEach
    void setUp() {

        Department department = Department.builder().dename("IT")
                .deaddress("Pune")
                .deCode("IT-06")
                .departmentId(1L)
                .build();
        Mockito.when(departmentRepository.findByDenameIgnoreCase("IT"))
                .thenReturn(department);
    }

    @Test
    @DisplayName("Get Data based on Valid Department name")
    public void whenValidDepartmentName_thenDepartmentShouldFound(){
        String dename = "IT";
        Department found = departmentService.fetchDepartmentByName(dename);
        assertEquals(dename, found.getDename());
    }
}