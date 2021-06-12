package ubb.postuniv.riddingaddict.service;

import ubb.postuniv.riddingaddict.model.pojo.AppUser;

import java.util.List;

public interface AppUserService {

    List<AppUser> getAll();

    void addUser(AppUser appUser);

    void deleteUser(String userCode);

    void updateUser(AppUser user, String userCode);
}
