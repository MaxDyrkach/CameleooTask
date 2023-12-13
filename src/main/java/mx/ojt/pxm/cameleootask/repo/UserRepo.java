package mx.ojt.pxm.cameleootask.repo;

import mx.ojt.pxm.cameleootask.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepo extends JpaRepository<User, Long> {

    Optional<User> findUserByName(String uName);

}
