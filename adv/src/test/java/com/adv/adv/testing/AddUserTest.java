package com.adv.adv.testing;

import org.junit.jupiter.api.Test;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import com.adv.adv.controller.AddUser;
import com.adv.adv.model.User;
import com.adv.adv.repository.userRepository;
import org.springframework.validation.BindingResult;
import org.mockito.Mockito;

public class AddUserTest {

    private AddUser makeAddUser(userRepository userRepository) {
        AddUser controller = new AddUser();
        controller.userRepository = userRepository;
        return controller;
    }

    @Test
    public void GetAllUsers_ReturnsAllUsersAndView() {
        userRepository mockRepository = Mockito.mock(userRepository.class);
        AddUser controller = makeAddUser(mockRepository);

        // Act
        ModelAndView result = controller.getAllUsers();

        // Assert
        assertEquals("UserList.html", result.getViewName());
        assertNotNull(result.getModel().get("users"));
    }

    @Test
    public void SaveUser_WithValidUser_Redirects() {
        userRepository mockRepository = Mockito.mock(userRepository.class);
        AddUser controller = makeAddUser(mockRepository);
        User user = new User();
        BindingResult mockBindingResult = Mockito.mock(BindingResult.class);
        Mockito.when(mockBindingResult.hasErrors()).thenReturn(false);

        // Act
        ModelAndView result = controller.saveUser(user, mockBindingResult);

        // Assert
        assertEquals("redirect:/users", result.getViewName());
    }

    @Test
    public void DeleteUser_RedirectsAfterDeletion() {
        userRepository mockRepository = Mockito.mock(userRepository.class);
        AddUser controller = makeAddUser(mockRepository);
        Long userId = 1L;

        // Act
        RedirectView result = controller.deleteUser(userId);

        // Assert
        assertEquals("/users", result.getUrl());
    }
}