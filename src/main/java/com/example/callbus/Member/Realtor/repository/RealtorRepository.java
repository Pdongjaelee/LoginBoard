package com.example.callbus.Member.Realtor.repository;

import com.example.callbus.Member.Realtor.entity.Realtor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface RealtorRepository extends JpaRepository<Realtor, Long> {
    Optional<Realtor> findByEmail(String email);
}

