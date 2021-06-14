package ubb.postuniv.riddingaddict.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import ubb.postuniv.riddingaddict.exception.ItemNotFoundException;
import ubb.postuniv.riddingaddict.exception.ShopException;
import ubb.postuniv.riddingaddict.model.pojo.AppUser;
import ubb.postuniv.riddingaddict.repository.AppUserRepository;
import ubb.postuniv.riddingaddict.security.model.SecurityUser;

import java.util.List;
import java.util.Optional;

@Service
public class AppUserServiceImpl implements AppUserService {

    @Autowired
    private AppUserRepository appUserRepository;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;


    @Override
    public List<AppUser> getAll() {

        return appUserRepository.findAll();
    }

    @Override
    public void addUser(AppUser appUser) {

        Optional<AppUser> optionalAppUser = appUserRepository.findByUsername(appUser.getUsername());

        if (optionalAppUser.isPresent()) {

            throw new ShopException("There is already an account registered with this username");
        }

        appUser.setPassword(passwordEncoder.encode(appUser.getPassword()));
        appUserRepository.save(appUser);
    }

    @Override
    public void deleteUser(String userCode) {

        Optional<AppUser> user = appUserRepository.findByUserCode(userCode);

        if (user.isEmpty()) {

            throw new ItemNotFoundException("Could not find user");
        }
        appUserRepository.delete(user.get());
    }

    @Override
    public void updateUser(AppUser user, String userCode) {

        Optional<AppUser> userFound = appUserRepository.findByUserCode(userCode);

        if (!userFound.isPresent()) {

            throw new ItemNotFoundException("Could not find user");
        }
        userFound.get().setUserCode(user.getUserCode());
        userFound.get().setFirstName(user.getFirstName());
        userFound.get().setLastName(user.getLastName());
        userFound.get().setUsername(user.getUsername());
        userFound.get().setEmail(user.getEmail());
        userFound.get().setPassword(user.getPassword());
        userFound.get().setRoles(user.getRoles());

        appUserRepository.save(userFound.get());
    }

    @Override
    public UserDetails loadUserByUsername(String username) {

        Optional<AppUser> optionalAppUser = appUserRepository.findByUsername(username);

        if (!optionalAppUser.isPresent()) {

            throw new UsernameNotFoundException("Could not find user");
        }

        return new SecurityUser(optionalAppUser.get());
    }

    @Override
    public AppUser getUser(String userCode) {

        return appUserRepository.findByUserCode(userCode).orElseThrow(() ->
                new ItemNotFoundException("The user with code " + userCode + " could not be found"));
    }
}
