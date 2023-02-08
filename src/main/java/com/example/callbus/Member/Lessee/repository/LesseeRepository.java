package com.example.callbus.Member.Lessee.repository;

import com.example.callbus.Member.Lessee.entity.Lessee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface LesseeRepository extends JpaRepository<Lessee, Long> {
    Optional<Lessee> findByEmail(String email);
}

