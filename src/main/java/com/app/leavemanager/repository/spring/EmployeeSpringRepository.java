package com.app.leavemanager.repository.spring;

import com.app.leavemanager.domain.employee.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeSpringRepository extends JpaRepository<Employee, Long> {
}
