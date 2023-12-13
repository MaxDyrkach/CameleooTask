package mx.ojt.pxm.kameleoontask.repo;

import mx.ojt.pxm.kameleoontask.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepo extends JpaRepository<User, Long> {

    Optional<User> findUserByName(String uName);

}
