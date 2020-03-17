package han.oose.dea.spottitube.datasource;

import han.oose.dea.spottitube.service.datasource.LoginDAO;
import org.apache.commons.codec.digest.DigestUtils;
import org.junit.jupiter.api.Test;

public class LoginDAOTest {

    private LoginDAO sut = new LoginDAOImpl();

    @Test
    public void validateLoginTest() {
        System.out.println(sut.validateLogin("plakplaatje", "5E884898DA28047151D0E56F8DC6292773603D0D6AABBDD62A11EF721D1542D8"));
    }
}
