package com.adv.adv.test;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import org.springframework.web.servlet.ModelAndView;

import com.adv.adv.controller.IndexController;
import com.adv.adv.repository.ProductRepository;
import com.adv.adv.model.Product;

import java.util.Arrays;
import java.util.List;

public class IndexControllerTest {

    private IndexController makeIndexControllerWithMocks() {
        IndexController controller = new IndexController();
        ProductRepository mockProductRepository = Mockito.mock(ProductRepository.class);
        
        Product product1 = new Product(); // Assuming Product has a default constructor
        Product product2 = new Product();
        Product product3 = new Product();
        List<Product> products = Arrays.asList(product1, product2, product3);
        
        when(mockProductRepository.findTop3ByOrderByIdDesc()).thenReturn(products);
        when(mockProductRepository.findTop3ByOrderByRatingDesc()).thenReturn(products);
        when(mockProductRepository.findAll()).thenReturn(products);
        when(mockProductRepository.findAllByCategoryId(1)).thenReturn(products);
        
        controller.productRepository = mockProductRepository;  // Directly setting the mocked repository
        
        return controller;
    }


    @Test
    public void getHomePage_ReturnsCorrectModelAndView() {
        IndexController controller = makeIndexControllerWithMocks();
        
        ModelAndView result = controller.getHomePage();
        
        assertEquals("index.html", result.getViewName());
        assertEquals(2, result.getModel().size());
    }

    @Test
    public void getAll_ReturnsCorrectModelAndView() {
        IndexController controller = makeIndexControllerWithMocks();
        
        ModelAndView result = controller.getAll();
        
        assertEquals("shop.html", result.getViewName());
        assertEquals(1, result.getModel().size());
    }

    @Test
    public void GetProductByCataegory_ReturnsCorrectModelAndView() {
        IndexController controller = makeIndexControllerWithMocks();
        
        ModelAndView result = controller.GetProductByCataegory(1);
        
        assertEquals("shop.html", result.getViewName());
        assertEquals(1, result.getModel().size());
    }
}