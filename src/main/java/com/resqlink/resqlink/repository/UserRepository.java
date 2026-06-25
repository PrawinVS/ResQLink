package com.resqlink.resqlink.repository;

import com.resqlink.resqlink.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByDevices_DeviceId(String deviceId);
    Optional<User> findByEmail(String email);

}
