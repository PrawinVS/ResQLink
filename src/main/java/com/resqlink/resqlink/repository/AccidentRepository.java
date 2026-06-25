package com.resqlink.resqlink.repository;

import com.resqlink.resqlink.entity.AccidentLog;
import com.resqlink.resqlink.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
public interface AccidentRepository extends JpaRepository<AccidentLog, Long> {
    List<AccidentLog> findByUser(User user);
}
