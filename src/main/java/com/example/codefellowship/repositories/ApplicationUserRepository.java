package com.example.codefellowship.repositories;

import com.example.codefellowship.modles.ApplicationUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ApplicationUserRepository extends JpaRepository<ApplicationUser, Long> {
}
