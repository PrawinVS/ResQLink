package com.resqlink.resqlink.controller;

import com.resqlink.resqlink.entity.AccidentLog;
import com.resqlink.resqlink.entity.Device;
import com.resqlink.resqlink.entity.EmergencyContact;
import com.resqlink.resqlink.repository.AccidentRepository;
import com.resqlink.resqlink.repository.DeviceRepository;
import com.resqlink.resqlink.repository.EmergencyContactRepository;
import com.resqlink.resqlink.service.TwilioService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import com.resqlink.resqlink.entity.User;
import com.resqlink.resqlink.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.ui.Model;
import java.security.Principal;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class WebController {
    private final AccidentRepository accidentRepository;

    private final DeviceRepository deviceRepository;

    private final EmergencyContactRepository emergencyContactRepository;

    private final BCryptPasswordEncoder passwordEncoder;

    private final UserRepository userRepository;
    private final TwilioService twilioService;
    @GetMapping("/")
    public String home() {
        return "index";
    }

    @GetMapping("/register")
    public String registerPage() {
        return "register";
    }
    @PostMapping("/register")
    public String registerUser(@RequestParam String name,
                               @RequestParam String email,
                               @RequestParam String phone,
                               @RequestParam String password,
                               @RequestParam String preferredLanguage) {

        User user = new User();
        user.setName(name);
        user.setEmail(email);
        user.setPhone(phone);
        user.setPassword(passwordEncoder.encode(password));
        user.setPreferredLanguage(preferredLanguage);

        userRepository.save(user);

        return "redirect:/login";
    }
    @GetMapping("/login")
    public String loginPage() {
        return "login";
    }
    @GetMapping("/dashboard")
    public String dashboard(Model model, Principal principal) {

        String email = principal.getName(); // logged-in email

        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found"));

        model.addAttribute("user", user);
        model.addAttribute("contactsCount", user.getContacts().size());
        model.addAttribute("accidentCount", accidentRepository.findByUser(user).size());

        return "dashboard";
    }

    @GetMapping("/contacts")
    public String contactsPage(Model model, Principal principal) {

        String email = principal.getName();

        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found"));

        model.addAttribute("user", user);
        model.addAttribute("newContact", new EmergencyContact());

        return "contacts";
    }

    @PostMapping("/contacts/add")
    public String addContact(@RequestParam String name,
                             @RequestParam String phone,
                             Principal principal) {

        String email = principal.getName();

        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found"));

        EmergencyContact contact = new EmergencyContact();
        contact.setName(name);
        contact.setPhone(phone);
        contact.setUser(user);

        emergencyContactRepository.save(contact);

        return "redirect:/contacts";
    }
    @PostMapping("/contacts/delete/{id}")
    public String deleteContact(@PathVariable Long id) {

        emergencyContactRepository.deleteById(id);

        return "redirect:/contacts";
    }
    @GetMapping("/devices")
    public String devicesPage(Model model, Principal principal) {

        String email = principal.getName();

        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found"));

        model.addAttribute("user", user);

        return "devices";
    }
    @PostMapping("/devices/add")
    public String addDevice(@RequestParam String deviceId,
                            Principal principal) {

        String email = principal.getName();

        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found"));

        Device device = new Device();
        device.setDeviceId(deviceId);
        device.setUser(user);

        deviceRepository.save(device);

        return "redirect:/devices";
    }
    @PostMapping("/devices/delete/{id}")
    public String deleteDevice(@PathVariable Long id) {

        deviceRepository.deleteById(id);

        return "redirect:/devices";
    }
    @GetMapping("/accidents")
    public String accidentLogs(Model model, Principal principal) {

        String email = principal.getName();

        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found"));

        List<AccidentLog> accidents = accidentRepository.findByUser(user);
        model.addAttribute("accidents", accidents);

        return "accidents";
    }
    @PostMapping("/test-hindi-call")
    public String testHindiCall(@RequestParam String phoneNumber) {

        twilioService.callHindiTest(phoneNumber);

        return "Hindi call triggered!";
    }
}
