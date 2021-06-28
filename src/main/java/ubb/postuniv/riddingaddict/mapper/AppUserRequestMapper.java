package ubb.postuniv.riddingaddict.mapper;

import org.springframework.stereotype.Component;
import ubb.postuniv.riddingaddict.model.dto.AppUserDTORequest;
import ubb.postuniv.riddingaddict.model.pojo.AppUser;

import java.util.Collections;

@Component
public class AppUserRequestMapper extends AbstractMapper<AppUser, AppUserDTORequest> {


    @Override
    public AppUser convertDtoToModel(AppUserDTORequest appUserDTORequest) {

        return new AppUser(appUserDTORequest.getFirstName(),
                appUserDTORequest.getLastName(),
                appUserDTORequest.getUsername(),
                appUserDTORequest.getEmail(),
                appUserDTORequest.getPassword(),
                Collections.singleton(appUserDTORequest.getRole()));
    }

    @Override
    public AppUserDTORequest convertModelToDto(AppUser appUser) {

        return new AppUserDTORequest(appUser.getFirstName(),
                appUser.getLastName(),
                appUser.getUsername(),
                appUser.getEmail(),
                appUser.getPassword(),
                appUser.getRoles().stream().findFirst().orElseThrow());
    }
}
