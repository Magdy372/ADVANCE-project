package com.adv.adv.test;

import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.ModelAndView;
import com.adv.adv.controller.WishlistController;
import com.adv.adv.model.Wishlist;
import com.adv.adv.service.WishlistService;
import com.adv.adv.repository.ProductRepository;
import com.adv.adv.repository.userRepository;
import jakarta.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;
import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

public class WishlistControllerTest {

    
    private WishlistController makeWishlistController() {
        WishlistController controller = new WishlistController();
        controller.wishlistService = mock(WishlistService.class);
        controller.productRepository = mock(ProductRepository.class);
        controller.userRepository = mock(userRepository.class);
        return controller;
    }

    @Test
    public void GetItemsByUserId_NoUserId_RedirectsToLogin() {
        WishlistController controller = makeWishlistController();
        HttpSession session = mock(HttpSession.class);
        when(session.getAttribute("id")).thenReturn(null);

        ModelAndView result = controller.getItemsByUserId(session);

        assertEquals("redirect:/login", result.getViewName());
    }

    @Test
    public void GetItemsByUserId_UserIdExists_ReturnsWishlist() {
        WishlistController controller = makeWishlistController();
        HttpSession session = mock(HttpSession.class);
        when(session.getAttribute("id")).thenReturn(1L);
        List<Wishlist> wishlistItems = new ArrayList<>();
        wishlistItems.add(new Wishlist());
        when(controller.wishlistService.getItemsByUserId(1L)).thenReturn(wishlistItems);

        ModelAndView result = controller.getItemsByUserId(session);

        assertEquals("wishlist.html", result.getViewName());
        assertNotNull(result.getModel().get("wishlistItems"));
    }

    @Test
    public void GetItemsByUserId_EmptyWishlist_ShowsMessage() {
        WishlistController controller = makeWishlistController();
        HttpSession session = mock(HttpSession.class);
        when(session.getAttribute("id")).thenReturn(1L);
        when(controller.wishlistService.getItemsByUserId(1L)).thenReturn(new ArrayList<>());

        ModelAndView result = controller.getItemsByUserId(session);

        assertEquals("wishlist.html", result.getViewName());
        assertEquals("Your wishlist is empty", result.getModel().get("message"));
    }
}