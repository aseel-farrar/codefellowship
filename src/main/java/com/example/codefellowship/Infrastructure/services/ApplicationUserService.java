package com.example.codefellowship.Infrastructure.services;

import com.example.codefellowship.domain.ApplicationUser;
import com.example.codefellowship.Infrastructure.ApplicationUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class ApplicationUserService implements UserDetailsService {
    @Autowired
    ApplicationUserRepository applicationUserRepository;


    public void createApplicationUser (ApplicationUser newApplicationUser){
//        newApplicationUser.getRoles().add(rolesService.getRoleByName("USER"));
        applicationUserRepository.save(newApplicationUser);
    }

    public ApplicationUser getUserById(Long id){
        return applicationUserRepository.findApplicationUserByUserId(id);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        ApplicationUser applicationUser = applicationUserRepository.findApplicationUserByUsername(username);
        if (applicationUser == null) {
            System.out.print("Username not found");
            throw new UsernameNotFoundException((username + " not found"));
        }
        return applicationUser;
    }

    public ApplicationUser findUserByName(String username) {
        return applicationUserRepository.findApplicationUserByUsername(username);
    }
}
