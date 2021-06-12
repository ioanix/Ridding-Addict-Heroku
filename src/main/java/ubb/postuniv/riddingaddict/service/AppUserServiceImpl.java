package ubb.postuniv.riddingaddict.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ubb.postuniv.riddingaddict.exception.ItemNotFoundException;
import ubb.postuniv.riddingaddict.exception.ShopException;
import ubb.postuniv.riddingaddict.model.pojo.AppUser;
import ubb.postuniv.riddingaddict.repository.AppUserRepository;

import java.util.List;
import java.util.Optional;


@Service
public class AppUserServiceImpl implements AppUserService {

    @Autowired
    private AppUserRepository appUserRepository;


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
}
