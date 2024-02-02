package com.app.leavemanager.repository.spring;

import com.app.leavemanager.domain.holiday.Holiday;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HolidaySpringRepository extends JpaRepository<Holiday, Long> {
}
