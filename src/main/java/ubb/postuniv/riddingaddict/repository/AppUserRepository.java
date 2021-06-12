package ubb.postuniv.riddingaddict.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ubb.postuniv.riddingaddict.model.pojo.AppUser;

import java.util.Optional;

public interface AppUserRepository extends JpaRepository<AppUser, Long> {

   Optional<AppUser> findByUserCode(String userCode);

   Optional<AppUser> findByUsername(String username);
}
