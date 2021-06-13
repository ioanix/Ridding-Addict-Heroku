package ubb.postuniv.riddingaddict.controller;

import io.swagger.annotations.ApiParam;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ubb.postuniv.riddingaddict.mapper.Mapper;
import ubb.postuniv.riddingaddict.model.dto.AppUserDTORequest;
import ubb.postuniv.riddingaddict.model.dto.AppUserDTOResponse;
import ubb.postuniv.riddingaddict.model.pojo.AppUser;
import ubb.postuniv.riddingaddict.service.AppUserService;


import java.util.List;

@RestController
@RequestMapping("/api")
@Log4j2
@CrossOrigin(origins = "*")
public class AppUserController {

    @Autowired
    private AppUserService appUserService;

    @Autowired
    private Mapper<AppUser, AppUserDTORequest> appUserMapper;

    @Autowired
    private Mapper<AppUser, AppUserDTOResponse> appUserResponseMapper;


    @GetMapping("/users")
    public ResponseEntity<List<AppUserDTOResponse>> showAllUsers() {

        List<AppUser> appUsers = appUserService.getAll();

        log.info("appUserList = {}", appUsers);

        return new ResponseEntity<>(appUserResponseMapper.convertModelsToDtos(appUsers), HttpStatus.OK);
    }

    @DeleteMapping("/users/{userCode}")
    public void removeUser(@ApiParam(value = "The user's unique identification code", required = true) @PathVariable String userCode){

        log.info("userCode = {}", userCode);

        appUserService.deleteUser(userCode);
    }


    @PostMapping(path = "/users/register")
    public void addUser(@RequestBody AppUserDTORequest appUserDtoRequest) {

        log.info("appUserDto = {}", appUserDtoRequest);

        appUserService.addUser(appUserMapper.convertDtoToModel(appUserDtoRequest));
    }

    @PutMapping("/users/update/{userCode}")
    public void updateUser(@RequestBody AppUserDTORequest appUserDTORequest, @ApiParam(value = "The user's unique identification code", required = true) @PathVariable String userCode){

        log.info("userCode = {}", userCode);
        log.info("appUserDto = {}", appUserDTORequest);

        appUserService.updateUser(appUserMapper.convertDtoToModel(appUserDTORequest), userCode);
    }
}
