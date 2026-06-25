# 🚗 ResQLink

**ResQLink v2** is an IoT-based Intelligent Vehicular Emergency Communication System that automatically detects road accidents and instantly notifies emergency contacts with the vehicle's live location. The system integrates embedded hardware, a Spring Boot backend, and cloud communication services to reduce emergency response time.

---

## 📌 Features

* 👤 User Registration and Login
* 🚗 Vehicle (Car ID) Management
* 📞 Emergency Contact Management
* 🌐 Secure Web Dashboard using Thymeleaf
* 🚨 Automatic Accident Detection using ESP32 + MPU6050
* 📍 GPS Location Tracking
* 🗺️ Google Maps Location Link Generation
* 📲 Automatic SMS Notification to Emergency Contacts
* ☎️ Automated Voice Call Alerts
* 📝 Accident History and Log Management
* 📡 Periodic Live Location Updates(Future Enhancement)

---

## 🛠️ Tech Stack

### Backend

* Java 21
* Spring Boot
* Spring MVC
* Spring Security
* Spring Data JPA
* Thymeleaf
* Maven

### Database

* PostgreSQL

### IoT Hardware

* ESP32
* MPU6050 (Accelerometer & Gyroscope)
* NEO-6M GPS Module

---

## 🏗️ System Architecture

```
ESP32 + MPU6050 + GPS
            │
            │ HTTPS (JSON)
            ▼
     Spring Boot Backend
            │
    ┌───────┴────────┐
    │                │
PostgreSQL      Twilio API
    │                │
    ▼                ▼
Dashboard      SMS & Voice Call
```

---

## 📂 Project Modules

* User Authentication
* Device Management
* Emergency Contact Management
* Accident Detection API
* Accident Log Management

---

## 📊 Database Tables

* users
* devices
* emergency_contacts
* accident_logs

---

## 🔄 Workflow

1. User registers an account.
2. User logs into the dashboard.
3. User registers their vehicle (Car ID).
4. User adds one or more emergency contacts.
5. ESP32 continuously monitors accelerometer and gyroscope values.
6. If an accident exceeds the threshold:

   * GPS coordinates are obtained.
   * ESP32 sends a JSON request to the backend.
   * Accident is stored in PostgreSQL.
   * SMS containing a Google Maps link is sent.
   * An automated emergency voice call is initiated.
7. ESP32 also sends live location updates every 15 minutes to keep the user's latest location available(Future Enhancements).

---

## 📡 REST API Endpoints

### Authentication

| Method | Endpoint     | Description       |
| ------ | ------------ | ----------------- |
| GET    | `/register`  | Registration Page |
| POST   | `/register`  | Register User     |
| GET    | `/login`     | Login Page        |
| GET    | `/dashboard` | User Dashboard    |

### ESP32 APIs

| Method | Endpoint            | Description          |
| ------ | ------------------- | -------------------- |
| POST   | `/api/esp/accident` | Send Accident Data   |
| POST   | `/api/esp/location` | Update Live Location |

---

## Example Accident Request

```json
{
  "carId": "TN38AB1234",
  "latitude": 11.0168,
  "longitude": 76.9558
}
```

---

## Future Enhancements

* JWT Authentication
* Email Notifications
* Live Vehicle Tracking
* Multi-language Voice Alerts
* Mobile Application
* Cloud Deployment (AWS)
* AI-based Accident Detection
* Emergency Contact Acknowledgement
* Hospital & Ambulance Integration

---

## Learning Outcomes

* Developed a complete CRUD application using Spring Boot.
* Integrated IoT hardware with a RESTful backend.
* Implemented PostgreSQL database operations using JPA.
* Built secure authentication using Spring Security.
* Integrated third-party communication APIs.
* Learned embedded-to-cloud communication using HTTP and JSON.

---

## Author

**Prawin V S**

Final Year B.E. Electronics and Communication Engineering

Backend Developer | Java | Spring Boot | IoT | Cloud Enthusiast

---

## License

This project is developed for academic and research purposes.
