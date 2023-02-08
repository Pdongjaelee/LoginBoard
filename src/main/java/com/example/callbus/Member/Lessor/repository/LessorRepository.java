package com.example.callbus.Member.Lessor.repository;

import com.example.callbus.Member.Lessor.entity.Lessor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface LessorRepository extends JpaRepository<Lessor, Long> {
    Optional<Lessor> findByEmail(String email);
}

