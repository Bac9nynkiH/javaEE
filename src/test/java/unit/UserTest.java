package unit;

import kma.topic2.junit.model.NewUser;
import kma.topic2.junit.model.User;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class UserTest {

    @ParameterizedTest(name = "create NewUser with username {1} password {2} and fullName {3}")
    @CsvSource({"login1,19,fullName4", "login2,19,fullName4" , "login3,19,fullName4" , "login3,V1l2a3d,fullName4"})
    public void createNewUser(String login, String password, String fullName){
        User user = User.builder().login(login).password(password).fullName(fullName).build();
        assertEquals(user.getLogin(),login);
        assertEquals(user.getPassword(),password);
        assertEquals(user.getFullName(),fullName);
    }

}
