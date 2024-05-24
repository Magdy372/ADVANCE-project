package com.adv.adv.test;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;
import com.adv.adv.controller.AdminController;
import com.adv.adv.model.User;
import com.adv.adv.repository.userRepository;

public class AdminControllerTest {
    
    private AdminController makeAdminController(userRepository userRepository) {
        return new AdminController(userRepository);
    }

    @Test
    public void showAddAdminForm_ReturnsCorrectModelAndView() {
        userRepository mockUserRepository = Mockito.mock(userRepository.class);
        AdminController controller = makeAdminController(mockUserRepository);

        // Act
        ModelAndView mav = controller.showAddAdminForm();

        // Assert
        assertEquals("addAdmin.html", mav.getViewName());
        assertTrue(mav.getModel().containsKey("admin"));
        assertTrue(mav.getModel().get("admin") instanceof User);
    }

    @Test
    public void addAdmin_SavesUserWithHashedPasswordAndRedirects() {
        userRepository mockUserRepository = Mockito.mock(userRepository.class);
        AdminController controller = makeAdminController(mockUserRepository);
        User user = new User();
        user.setUsername("testUser");
        user.setPassword("password123");

        // Act
        RedirectView redirectView = controller.addAdmin(user);

        // Assert
        verify(mockUserRepository).save(any(User.class));
        assertEquals("/adminPage/addAdmin", redirectView.getUrl());
    }

    @Test
    public void deleteUser_DeletesByIdAndRedirects() {
        userRepository mockUserRepository = mock(userRepository.class);
        AdminController controller = makeAdminController(mockUserRepository);
        Long userId = 1L;

        // Act
        RedirectView redirectView = controller.deleteUser(userId);

        // Assert
        verify(mockUserRepository).deleteById(userId);
        assertEquals("/adminPage/userlists", redirectView.getUrl());
    }
}
