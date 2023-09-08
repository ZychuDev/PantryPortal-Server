package com.zti.pantry_portal.service;

import com.zti.pantry_portal.config.SecurityConfig;
import com.zti.pantry_portal.model.Person;
import com.zti.pantry_portal.model.User;
import com.zti.pantry_portal.model.UserPrincipal;
import com.zti.pantry_portal.repository.PersonRepository;
import com.zti.pantry_portal.repository.UserRepository;

import jakarta.transaction.Transactional;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import java.util.Optional;


@Service
public class UserService implements UserDetailsService {

    private final UserRepository userRepository;
    private final PersonRepository personRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository, PersonRepository personRepository){
        this.userRepository = userRepository;
        this.personRepository = personRepository;
    }


    @Transactional
    public void addUser(User user){
        Person person = new Person(user.getName());
        user.setPassword(this.passwordEncoder.encode(user.getPassword()));
        userRepository.insert(user);
        personRepository.save(person);
    }

    public User getUserByName(String name){
        return userRepository.findByName(name).orElseThrow(() -> new RuntimeException(
                String.format("Cannot Find User with name: %s", name)
        ));
    }

    public void deleteUser(String id){
        userRepository.deleteById(id);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> user = userRepository.findByName(username);
        if(user.isEmpty()){
            throw new UsernameNotFoundException(username);
        }
        return new UserPrincipal(user.get());
    }
}
