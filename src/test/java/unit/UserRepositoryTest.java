package unit;

import kma.topic2.junit.model.NewUser;
import kma.topic2.junit.model.User;
import kma.topic2.junit.repository.UserRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class UserRepositoryTest {
    private UserRepository userRepository = new UserRepository();

    static List<NewUser> testUsersProvider(){
        List<NewUser> users = new ArrayList<>();
        for(int i=4; i<=6; i++){
            users.add(NewUser.builder().login("login" + i).password("password" + i).fullName("fullName" + i).build());
        }
        return users;
    }

    @ParameterizedTest(name = "username {1} get does not throw exception")
    @CsvSource({"login1", "login2" , "login3"})
    public void getUsersFromInitRepo(String login){
        assertDoesNotThrow(() -> userRepository.getUserByLogin(login));
    }

    @ParameterizedTest(name = "username {1} exists")
    @CsvSource({"login1", "login2" , "login3"})
    public void checkUsersFromInitRepo(String login){
        assertTrue(() -> userRepository.isLoginExists(login));
    }

    @ParameterizedTest(name = "username")
    @MethodSource("testUsersProvider")
    public void saveNewUser(NewUser newUser){
        userRepository.saveNewUser(newUser);
        assertTrue(() -> userRepository.isLoginExists(newUser.getLogin()));
        assertDoesNotThrow(() -> userRepository.getUserByLogin(newUser.getLogin()));
    }
}
