package integration;

import kma.topic2.junit.Main;
import kma.topic2.junit.exceptions.ConstraintViolationException;
import kma.topic2.junit.exceptions.LoginExistsException;
import kma.topic2.junit.exceptions.UserNotFoundException;
import kma.topic2.junit.model.NewUser;
import kma.topic2.junit.repository.UserRepository;
import kma.topic2.junit.service.UserService;
import kma.topic2.junit.validation.UserValidator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.test.annotation.DirtiesContext;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@SpringBootTest(classes = Main.class)
public class UserServiceIntegrationTest {
    @SpyBean
    private UserService userService;

    @MockBean
    private UserRepository userRepository;

    @BeforeEach
    public void initRepo(){
        when(userRepository.isLoginExists("login1")).thenReturn(true);
        when(userRepository.isLoginExists("login2")).thenReturn(true);
        when(userRepository.isLoginExists("login3")).thenReturn(true);
        when(userRepository.isLoginExists("login4")).thenReturn(false);
        when(userRepository.isLoginExists("login")).thenReturn(false);
    }

    @ParameterizedTest(name = "creation of user with username {0} and password {1} should throw a loginExistsException")
    @CsvSource({"login1,19,fullName4", "login2,19,fullName4" , "login3,19,fullName4" , "login3,V1l2a3d,fullName4"})
    public void userCreationTestWithExistingUsernames(String login, String password, String fullName){
        assertThrows(LoginExistsException.class,
                () -> userService.createNewUser(NewUser.builder().login(login).password(password).fullName(fullName).build()));
        verify(userRepository,never()).saveNewUser(any());
    }

    @ParameterizedTest(name = "creation of user with username {0} and password {1} should throw a ConstraintViolationException and not be saved in repository")
    @CsvSource({"login4,19,fullName4", "login4,o,fullName4" , "login4,v-ad,fullName4" , "login4,V1l2a3d4,fullName4"})
    public void userCreationTestWithInvalidPassword(String login, String password, String fullName){
        NewUser newUser = NewUser.builder().login(login).password(password).fullName(fullName).build();
        assertThrows(ConstraintViolationException.class,
                () -> userService.createNewUser(newUser));
        verify(userRepository,never()).saveNewUser(any());
    }

    @ParameterizedTest(name = "password validation  of user with username {0} and password {1} should not throw anything")
    @CsvSource({"login4,V1l2a3d,fullName4", "login5,vlad,fullName4" , "login6,vad,fullName4"})
    public void saveUserWithCorrectCredentials(String login, String password, String fullName){
        userService.createNewUser(NewUser.builder().login(login).password(password).fullName(fullName).build());
        verify(userRepository,times(1)).saveNewUser(any());
    }

    @Test
    public void nulPasswordShouldNotBeCreatedAndShouldNotBeSaved(){
        NewUser newUser = NewUser.builder().password(null).login("login").fullName("fullname").build();
        assertThrows(NullPointerException.class, () -> userService.createNewUser(newUser));
        verify(userRepository,never()).saveNewUser(any());
    }
}
