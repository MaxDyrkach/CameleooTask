package mx.ojt.pxm.kameleoontask.service;


import mx.ojt.pxm.kameleoontask.model.User;
import mx.ojt.pxm.kameleoontask.model.dto.UserDto;
import mx.ojt.pxm.kameleoontask.repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.util.HashSet;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepo userRepo;

    @Transactional(readOnly = true)
    public Optional<User> getUser(Long uId) {
        return userRepo.findById(uId);
    }

    @Transactional(readOnly = true)
    public Optional<User> getUser(String uName) {
        return userRepo.findUserByName(uName);

    }

    @Transactional
    public Optional<User> createUser(UserDto uDto) {
        User newUser = User.builder().name(uDto.getName())
                .password(uDto.getPassword()) //it must be w password encoder in real
                .email(uDto.getEmail()).dateCreation(Instant.now())
                .quotes(new HashSet<>()).build();
        return Optional.ofNullable(userRepo.save(newUser));

    }
}
