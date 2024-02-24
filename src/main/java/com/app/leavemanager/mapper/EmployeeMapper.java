package com.app.leavemanager.mapper;

import com.app.leavemanager.domain.employee.Employee;
import com.app.leavemanager.domain.employee.user.User;
import com.leavemanager.openapi.model.EmployeeDTO;
import com.leavemanager.openapi.model.ScopeDTO;
import org.mapstruct.BeanMapping;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.time.ZoneId;
import java.util.Optional;

@Mapper(
        componentModel = "spring",
        injectionStrategy = InjectionStrategy.CONSTRUCTOR
)
@Component
public interface EmployeeMapper {

    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "id")
    @Mapping(target = "firstname")
    @Mapping(target = "lastname")
    @Mapping(target = "dateOfBirth")
    @Mapping(target = "isActivated", source = "activated")
    @Mapping(target = "email", source = "user", qualifiedByName = "getUserEmail")
    @Mapping(target = "scope", source = "user", qualifiedByName = "getUserRole")
    EmployeeDTO toDTO(Employee employee);

    default OffsetDateTime map(LocalDateTime value) {
        ZoneId zoneId = ZoneId.systemDefault();
        return value.atZone(zoneId).toOffsetDateTime();
    }

    @Named("getUserEmail")
    default String getUserEmail(User user) {
        return user.getEmail();
    }

    @Named("getUserRole")
    default ScopeDTO getUserRole(User user) {
        return Optional.of(user)
                .map(User::getRole)
                .map(scope -> ScopeDTO.valueOf(scope.name()))
                .orElse(null);
    }
}
