package com.adv.adv.test;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.springframework.web.servlet.ModelAndView;
import jakarta.servlet.http.HttpSession;
import org.mockito.Mockito;

import com.adv.adv.controller.CartController;
import com.adv.adv.model.Cart;
import com.adv.adv.service.CartService;

import java.util.ArrayList;
import java.util.List;

public class CartControllerTest {

    private CartController makeCartController() {
        return new CartController();
    }

    @Test
    public void GetItemsByUserId_UserIdNull_RedirectsToLogin() {
        CartController controller = makeCartController();
        HttpSession session = Mockito.mock(HttpSession.class);
        Mockito.when(session.getAttribute("id")).thenReturn(null);

        // Act
        ModelAndView result = controller.getItemsByUserId(session);

        // Assert
        assertEquals("redirect:/login", result.getViewName());
    }

    @Test
    public void GetItemsByUserId_WithItems_ReturnsCartAndTotal() {
        CartController controller = makeCartController();
        HttpSession session = Mockito.mock(HttpSession.class);
        Mockito.when(session.getAttribute("id")).thenReturn(1L);
        CartService cartService = Mockito.mock(CartService.class);
        controller.cartService = cartService;

        List<Cart> carts = new ArrayList<>();
        Cart cart = new Cart();
        carts.add(cart);
        Mockito.when(cartService.getItemsByUserId(1L)).thenReturn(carts);
        Mockito.when(cartService.calculateTotalPrice(carts)).thenReturn(100.0);

        // Act
        ModelAndView result = controller.getItemsByUserId(session);

        // Assert
        assertEquals("cart.html", result.getViewName());
        assertEquals(carts, result.getModel().get("cartItems"));
        assertEquals(100.0, result.getModel().get("totalPrice"));
    }

    @Test
    public void GetItemsByUserId_NoItems_ReturnsEmptyMessage() {
        CartController controller = makeCartController();
        HttpSession session = Mockito.mock(HttpSession.class);
        Mockito.when(session.getAttribute("id")).thenReturn(1L);
        CartService cartService = Mockito.mock(CartService.class);
        controller.cartService = cartService;

        List<Cart> carts = new ArrayList<>();
        Mockito.when(cartService.getItemsByUserId(1L)).thenReturn(carts);

        // Act
        ModelAndView result = controller.getItemsByUserId(session);

        // Assert
        assertEquals("cart.html", result.getViewName());
        assertTrue(result.getModel().containsKey("message"));
        assertEquals("Your cart is empty", result.getModel().get("message"));
    }
}