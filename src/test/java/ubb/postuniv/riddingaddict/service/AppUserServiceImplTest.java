package ubb.postuniv.riddingaddict.service;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import ubb.postuniv.riddingaddict.exception.ItemNotFoundException;
import ubb.postuniv.riddingaddict.exception.ShopException;
import ubb.postuniv.riddingaddict.model.pojo.AppUser;
import ubb.postuniv.riddingaddict.model.pojo.Role;
import ubb.postuniv.riddingaddict.repository.AppUserRepository;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class AppUserServiceImplTest {


    private static final String USER_CODE = "ebs100";
    private static final String USERNAME = "johndoe";
    private static final String NEW_USERNAME = "johndoe";
    private static final String PASSWORD = "password";

    @Mock
    private AppUserRepository appUserRepositoryMock;

    @Mock
    private BCryptPasswordEncoder passwordEncoderMock;

    @InjectMocks
    private AppUserServiceImpl underTest;

    private AppUser appUser;
    private AppUser newAppUser;


    @BeforeAll
    void setup() {

        Set<Role> roles = new HashSet<>();
        roles.add(new Role("ADMIN"));

        appUser = new AppUser(USER_CODE, USERNAME, PASSWORD, roles);
        newAppUser = new AppUser(USER_CODE, NEW_USERNAME, PASSWORD, roles);
    }


    @Test
    void testCanGetAllUsers() {

        // when
        underTest.getAll();

        //then
        verify(appUserRepositoryMock).findAll();
    }

    @Test
    void testCanAddUser() {

        //given
        given(appUserRepositoryMock.findByUsername(USERNAME)).willReturn(Optional.empty());

        //when
        underTest.addUser(appUser);

        //then
        ArgumentCaptor<AppUser> appUserArgumentCaptor =
                ArgumentCaptor.forClass(AppUser.class);

        verify(appUserRepositoryMock)
                .save(appUserArgumentCaptor.capture());

        AppUser capturedAppUser = appUserArgumentCaptor.getValue();

        assertThat(capturedAppUser).isEqualTo(appUser);
    }

    @Test
    void testCanAddUserWilThrowExceptionIfTheUserAlreadyExists() {

        //given
        given(appUserRepositoryMock.findByUsername(USERNAME)).willReturn(Optional.of(appUser));

        //when
        //then
        assertThatThrownBy(() -> underTest.addUser(appUser))
                .isInstanceOf(ShopException.class)
                .hasMessageContaining("There is already an account registered with this username");
    }

    @Test
    void testCanLoadUserByUsernameWillThrowExceptionIfTheUserDoesNotExist() {

        //given
        given(appUserRepositoryMock.findByUsername(USERNAME)).willReturn(Optional.empty());

        //when
        //then
        assertThatThrownBy(() -> underTest.loadUserByUsername(USERNAME))
                .isInstanceOf(UsernameNotFoundException.class)
                .hasMessageContaining("Could not find user");
    }

    @Test
    void canDeleteUser() {

        //given
        given(appUserRepositoryMock.findByUserCode(USER_CODE)).willReturn(Optional.of(appUser));

        //when
        underTest.deleteUser(USER_CODE);

        //then
        assertThat(appUserRepositoryMock.findAll().size()).isZero();
    }

    @Test
    void canDeleteUserWillThrowExceptionIfUserDoesNotExistInDatabase() {

        //given
        given(appUserRepositoryMock.findByUserCode(USER_CODE)).willReturn(Optional.empty());

        //when
        //then
        assertThatThrownBy(() -> underTest.deleteUser(USER_CODE))
                .isInstanceOf(ItemNotFoundException.class)
                .hasMessageContaining("Could not find user");
    }

    @Test
    void canUpdateUser() {

        //given
        given(appUserRepositoryMock.findByUserCode(USER_CODE)).willReturn(Optional.of(appUser));

        //when
        underTest.updateUser(newAppUser, USER_CODE);

        //then
        assertThat(appUser.getUsername()).isEqualTo(NEW_USERNAME);
    }

    @Test
    void canUpdateUserWillThrowExceptionIfUserDoesNotExistInDatabase() {

        //given
        given(appUserRepositoryMock.findByUserCode(USER_CODE)).willReturn(Optional.empty());

        //when
        //then
        assertThatThrownBy(() -> underTest.updateUser(newAppUser, USER_CODE))
                .isInstanceOf(ItemNotFoundException.class)
                .hasMessageContaining("Could not find user");
    }

    @Test
    void testCanGetOneUser() {

        //given
        given(appUserRepositoryMock.findByUserCode(USER_CODE)).willReturn(Optional.of(appUser));

        //when
        //then
        assertThat(underTest.getUser(USER_CODE)).isEqualTo(appUser);
    }

    @Test
    void testCanGetOneUserWillThrowExceptionIfTheUserIsNotFound() {

        //given
        given(appUserRepositoryMock.findByUserCode(USER_CODE)).willReturn(Optional.empty());

        //when
        //then
        assertThatThrownBy(() -> underTest.getUser(USER_CODE))
                .isInstanceOf(ItemNotFoundException.class)
                .hasMessageContaining("The user with code " + USER_CODE + " could not be found");
    }
}