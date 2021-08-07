package com.example.codefellowship.Infrastructure;

import com.example.codefellowship.domain.ApplicationUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ApplicationUserRepository extends JpaRepository<ApplicationUser, Long> {

    ApplicationUser findApplicationUserByUsername(String username);

    ApplicationUser findApplicationUserByUserId(Long id);

}
