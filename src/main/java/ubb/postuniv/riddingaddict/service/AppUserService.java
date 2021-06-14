package ubb.postuniv.riddingaddict.service;

import org.springframework.security.core.userdetails.UserDetailsService;
import ubb.postuniv.riddingaddict.model.pojo.AppUser;

import java.util.List;

public interface AppUserService extends UserDetailsService {

    List<AppUser> getAll();

    AppUser getUser(String userCode);

    void addUser(AppUser appUser);

    void deleteUser(String userCode);

    void updateUser(AppUser user, String userCode);
}
