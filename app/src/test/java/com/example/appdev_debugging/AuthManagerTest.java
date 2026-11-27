package com.example.appdev_debugging;

import static org.junit.Assert.*;
import org.junit.Test;
import com.example.appdev_debugging.auth.AuthManager;
import com.example.appdev_debugging.data.InMemoryUserRepository;

public class AuthManagerTest {
    @Test
    public void loginIsCaseInsensitiveForEmail() {
        AuthManager auth = new AuthManager(new InMemoryUserRepository());
        auth.register("Juan", "Juan.DelaCruz@Mail.com", "Abc12345", "Abc12345");
        assertTrue(auth.login("juan.delacruz@mail.com", "Abc12345").isSuccess());
    }

    @Test
    public void duplicateEmailIgnoringCaseIsRejected() {
        AuthManager auth = new AuthManager(new InMemoryUserRepository());
        auth.register("Juan", "a@b.com", "Abc12345", "Abc12345");
        assertFalse(auth.register("Juan", "A@B.com", "Abc12345", "Abc12345").isSuccess());
    }
}