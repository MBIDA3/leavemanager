package com.app.leavemanager.api;

import com.app.leavemanager.dto.HolidayDTO;
import com.app.leavemanager.service.HolidayService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/holiday")
public class HolidayResources {

    private final HolidayService holidayService;

    @PostMapping
    public ResponseEntity<Long> createHoliday(@RequestBody(required = true) HolidayDTO holidayDTO) {
        return ResponseEntity
                .status(HttpStatus.CREATED).
                body(holidayService.createHoliday(holidayDTO, getCurrentUsername()));
    }

    @GetMapping
    public ResponseEntity<List<HolidayDTO>> getAllHoliday() {
        return ResponseEntity.status(HttpStatus.CREATED).body(holidayService.getAllHoliday());
    }

    @GetMapping("/{holidayId}")
    public ResponseEntity<HolidayDTO> getHolidayById(@PathVariable("holidayId") Long holidayId) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(holidayService.getHolidayById(holidayId, getCurrentUsername()));
    }

    @PutMapping("/{holidayId}")
    public ResponseEntity<Void> updateHoliday(@PathVariable("holidayId") Long holidayId,
                                              @RequestBody(required = true) HolidayDTO holidayDTO) {
        holidayService.updateHoliday(holidayId, holidayDTO, getCurrentUsername());
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @DeleteMapping("/{holidayId}")
    public ResponseEntity<Void> deleteHoliday(@PathVariable("holidayId") Long holidayId) {
        holidayService.deleteHolidayById(holidayId, getCurrentUsername());
        return ResponseEntity.status(HttpStatus.ACCEPTED).build();
    }

    @PutMapping("/approve/{holidayId}")
    public ResponseEntity<Void> approveHolidayById(@PathVariable("holidayId") Long holidayId) {
        holidayService.approveHolidayById(holidayId, getCurrentUsername());
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @PutMapping("/publish/{holidayId}")
    public ResponseEntity<Void> publishHolidayById(@PathVariable("holidayId") Long holidayId) {
        holidayService.publishHolidayById(holidayId, getCurrentUsername());
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @PutMapping("/unapproved/{holidayId}")
    public ResponseEntity<Void> unapprovedHolidayById(@PathVariable("holidayId") Long holidayId) {
        holidayService.unapprovedHolidayById(holidayId, getCurrentUsername());
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @PutMapping("/unpublished/{holidayId}")
    public ResponseEntity<Void> unpublishedHolidayById(@PathVariable("holidayId") Long holidayId) {
        holidayService.unpublishedHolidayById(holidayId, getCurrentUsername());
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    private static String getCurrentUsername() {
        return SecurityContextHolder.getContext().getAuthentication().getName();
    }
}
