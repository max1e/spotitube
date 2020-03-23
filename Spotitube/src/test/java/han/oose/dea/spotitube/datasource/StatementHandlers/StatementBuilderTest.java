package han.oose.dea.spotitube.datasource.StatementHandlers;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;

public class StatementBuilderTest {

    @Test
    public void test() {
        var ha = "sp_validateLogin(plakplaatje, 5e884898da28047151d0e56f8dc6292773603d0d6aabbdd62a11ef721d1542d8);";
        var list = new ArrayList<Object>();
        list.add(ha);

        System.out.println(list.get(0).getClass());
        System.out.println((String) list.get(0));
    }
}
