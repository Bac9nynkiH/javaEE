package unit;

import kma.topic2.junit.model.NewUser;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class NewUserTest {

    @ParameterizedTest(name = "create NewUser with username {0} password {1} and fullName {2}")
    @CsvSource({"login1,19,fullName4", "login2,19,fullName4" , "login3,19,fullName4" , "login3,V1l2a3d,fullName4"})
    public void createNewUser(String login, String password, String fullName){
        NewUser newUser = NewUser.builder().login(login).password(password).fullName(fullName).build();
        assertEquals(newUser.getLogin(),login);
        assertEquals(newUser.getPassword(),password);
        assertEquals(newUser.getFullName(),fullName);
    }
}
