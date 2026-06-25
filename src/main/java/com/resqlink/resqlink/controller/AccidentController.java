package com.resqlink.resqlink.controller;

import com.resqlink.resqlink.dto.AccidentDTO;
import com.resqlink.resqlink.entity.AccidentLog;
import com.resqlink.resqlink.entity.User;
import com.resqlink.resqlink.repository.AccidentRepository;
import com.resqlink.resqlink.repository.UserRepository;
import com.resqlink.resqlink.service.TwilioService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class AccidentController {
    private final TwilioService twilioService;


    private final UserRepository userRepository;
    private final AccidentRepository accidentRepository;

    @PostMapping("/accident")
    public ResponseEntity<?> reportAccident(@RequestBody AccidentDTO dto) {

        User user = userRepository.findByDevices_DeviceId(dto.getDeviceId())
                .orElseThrow(() -> new RuntimeException("Device not registered"));

        AccidentLog log = new AccidentLog();
        log.setDeviceId(dto.getDeviceId());
        log.setLatitude(dto.getLatitude());
        log.setLongitude(dto.getLongitude());
        log.setTime(LocalDateTime.now());
        log.setUser(user);
        accidentRepository.save(log);
        twilioService.sendAlert(user, dto);

        return ResponseEntity.ok("Accident logged successfully");
    }
}
