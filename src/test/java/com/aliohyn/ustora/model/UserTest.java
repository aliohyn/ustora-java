package com.aliohyn.ustora.model;

import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class UserTest {
    @Test
    public void checkMethods() {
        User item = new User();

        item.setUsername("username");
        assertThat(item.getUsername().equals("username")).isTrue();

        item.setPassword("password");
        assertThat(item.getPassword().equals("password")).isTrue();

        item.setCity("city");
        assertThat(item.getCity().equals("city")).isTrue();

        item.setFullName("fullName");
        assertThat(item.getFullName().equals("fullName")).isTrue();

        item.setStreet("street");
        assertThat(item.getStreet().equals("street")).isTrue();

        item.setPhoneNumber("phone");
        assertThat(item.getPhoneNumber().equals("phone")).isTrue();

        item.setZip("zip");
        assertThat(item.getZip().equals("zip")).isTrue();

        User item2 = new User();
        item2.setUsername("username");
        item2.setPassword("password");
        item2.setCity("city");
        item2.setFullName("fullName");
        item2.setStreet("street");
        item2.setPhoneNumber("phone");
        item2.setZip("zip");

        assertThat(item.equals(item2)).isTrue();
        assertThat(item.hashCode()).isEqualTo(item2.hashCode());

        item2.setUsername("username2");
        assertThat(item.equals(item2)).isFalse();
        assertThat(item.hashCode()).isNotEqualTo(item2.hashCode());
    }

    @Test(expected = NoSuchMethodException.class)
    public void isSetIdMethodExist() throws NoSuchMethodException {
        User item = new User();
        Class[] cArg = new Class[1];
        cArg[0] = Long.class;
        item.getClass().getDeclaredMethod("setId", cArg);
    }
}
