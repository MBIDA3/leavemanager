package com.app.leavemanager.configurations.bean;

import com.app.leavemanager.configurations.security.repository.UserSpringRepository;
import com.app.leavemanager.repository.dao.DefaultEmployeeRepository;
import com.app.leavemanager.repository.dao.DefaultHolidayRepository;
import com.app.leavemanager.domain.employee.EmployeeRepository;
import com.app.leavemanager.domain.holiday.HolidayRepository;
import com.app.leavemanager.repository.spring.EmployeeSpringRepository;
import com.app.leavemanager.repository.spring.HolidayConfigSpringRepository;
import com.app.leavemanager.repository.spring.HolidaySpringRepository;
import com.app.leavemanager.repository.spring.HolidayTypeSpringRepository;
import com.app.leavemanager.repository.spring.NoticeSpringRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class LeaveManagerBean {

    private final UserSpringRepository userSpringRepository;
    private final EmployeeSpringRepository employeeSpringRepository;
    private final HolidaySpringRepository holidaySpringRepository;
    private final HolidayTypeSpringRepository holidayTypeSpringRepository;
    private final HolidayConfigSpringRepository holidayConfigSpringRepository;
    private final NoticeSpringRepository noticeSpringRepository;

    @Bean
    public EmployeeRepository employeeRepository() {
        return new DefaultEmployeeRepository(
                employeeSpringRepository,
                userSpringRepository
        );
    }

    @Bean
    public HolidayRepository holidayRepository() {
        return new DefaultHolidayRepository(
                holidaySpringRepository,
                holidayTypeSpringRepository,
                holidayConfigSpringRepository,
                noticeSpringRepository
        );
    }

}
