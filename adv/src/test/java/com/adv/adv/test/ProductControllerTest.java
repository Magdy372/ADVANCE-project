package com.adv.adv.test;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.web.servlet.ModelAndView;
import com.adv.adv.controller.ProductController;
import com.adv.adv.model.Product;
import org.springframework.web.servlet.view.RedirectView;
import com.adv.adv.repository.CategoryRepository;
import com.adv.adv.repository.MetalRepository;
import com.adv.adv.repository.ProductRepository;
import org.springframework.validation.BindingResult;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

public class ProductControllerTest {

    @Mock
    private ProductRepository productRepository;

    @Mock
    private MetalRepository metalRepository;

    @Mock
    private CategoryRepository categoryRepository;

    @InjectMocks
    private ProductController productController;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void getAll_ReturnsAllProductsAndView() {
        // Arrange
        List<Product> products = new ArrayList<>();
        when(productRepository.findAll()).thenReturn(products);

        // Act
        ModelAndView result = productController.getAll();

        // Assert
        assertEquals("ProductsList.html", result.getViewName());
        assertTrue(result.getModel().containsKey("products"));
        assertTrue(result.getModel().get("products") instanceof List);
        assertEquals(products, result.getModel().get("products"));
    }

    @Test
    public void saveProduct_WithValidationErrors_ReturnsFormWithErrors() throws IOException {
        // Arrange
        Product product = new Product();
        BindingResult bindingResult = mock(BindingResult.class);
        when(bindingResult.hasErrors()).thenReturn(true);
        MultipartFile multipartFile = mock(MultipartFile.class);

        // Act
        ModelAndView result = productController.saveProduct(product, bindingResult, multipartFile);

        // Assert
        assertEquals("create-product.html", result.getViewName());
        assertTrue(result.getModel().containsKey("bindingResult"));
        verify(metalRepository, times(1)).findAll();
        verify(categoryRepository, times(1)).findAll();
    }

    @Test
    public void deleteProduct_RedirectsAfterDeletion() {
        // Act
        RedirectView result = productController.deleteProduct(1);

        // Assert
        assertEquals("/products", result.getUrl());
        verify(productRepository, times(1)).deleteById(1);
    }

    @Test
    public void showEditForm_ReturnsEditFormWithProduct() {
        // Arrange
        Product product = new Product();
        when(productRepository.findById(1)).thenReturn(product);

        // Act
        ModelAndView result = productController.showEditForm(1);

        // Assert
        assertEquals("editProduct.html", result.getViewName());
        assertTrue(result.getModel().containsKey("product"));
        assertTrue(result.getModel().containsKey("metals"));
        assertTrue(result.getModel().containsKey("categories"));
        verify(metalRepository, times(1)).findAll();
        verify(categoryRepository, times(1)).findAll();
    }

    @Test
    public void editProduct_WithValidationErrors_ReturnsFormWithErrors() throws IOException {
        // Arrange
        Product product = new Product();
        BindingResult bindingResult = mock(BindingResult.class);
        when(bindingResult.hasErrors()).thenReturn(true);
        MultipartFile multipartFile = mock(MultipartFile.class);

        // Act
        ModelAndView result = productController.editProduct(product, bindingResult, multipartFile);

        // Assert
        assertEquals("editProduct.html", result.getViewName());
        assertTrue(result.getModel().containsKey("product"));
        verify(metalRepository, times(1)).findAll();
        verify(categoryRepository, times(1)).findAll();
    }

    @Test
    public void getProductDetails_ReturnsProductDetailsView() {
        // Arrange
        Product product = new Product();
        when(productRepository.findById(1)).thenReturn(product);

        // Act
        ModelAndView result = productController.getproduct(1);

        // Assert
        assertEquals("product-details.html", result.getViewName());
        assertTrue(result.getModel().containsKey("product"));
        assertTrue(result.getModel().containsKey("metals"));
        assertTrue(result.getModel().containsKey("categories"));
        verify(metalRepository, times(1)).findAll();
        verify(categoryRepository, times(1)).findAll();
    }

    @Test
    public void searchProducts_ReturnsListOfProducts() {
        // Arrange
        List<Product> products = new ArrayList<>();
        when(productRepository.findByNameContaining("test")).thenReturn(products);

        // Act
        List<Product> result = productController.searchProducts("test");

        // Assert
        assertEquals(products, result);
    }
}
