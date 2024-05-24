package com.adv.adv.test;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import org.springframework.web.servlet.ModelAndView;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.adv.adv.controller.IndexController;
import com.adv.adv.model.Product;
import java.util.List;

public class IndexControllerTest {

    private IndexController MakeIndexController() {
        return new IndexController();
    }

    @Test
    public void getHomePage_ReturnsCorrectModelAndView() {
        IndexController controller = MakeIndexController();

        // Act
        ModelAndView mav = controller.getHomePage();

        // Assert
        assertEquals("index.html", mav.getViewName());
        assertNotNull(mav.getModel().get("newProducts"));
        assertNotNull(mav.getModel().get("featuredProducts"));
        assertEquals(3, ((List<Product>) mav.getModel().get("newProducts")).size());
        assertEquals(3, ((List<Product>) mav.getModel().get("featuredProducts")).size());
    }

    @Test
    public void getAll_ReturnsAllProducts() {
        IndexController controller = MakeIndexController();

        // Act
        ModelAndView mav = controller.getAll();

        // Assert
        assertEquals("shop.html", mav.getViewName());
        assertNotNull(mav.getModel().get("products"));
        assertTrue(mav.getModel().get("products") instanceof List);
    }

    @Test
    public void GetProductByCataegory_ReturnsFilteredProducts() {
        IndexController controller = MakeIndexController();
        int categoryId = 1; // Example category ID

        // Act
        ModelAndView mav = controller.GetProductByCataegory(categoryId);

        // Assert
        assertEquals("shop.html", mav.getViewName());
        assertNotNull(mav.getModel().get("products"));
        assertTrue(mav.getModel().get("products") instanceof List);
        // Assuming there's a method to check if all products have the correct category ID
        assertTrue(((List<Product>) mav.getModel().get("products")).stream().allMatch(p -> p.getId() == categoryId));
    }
}