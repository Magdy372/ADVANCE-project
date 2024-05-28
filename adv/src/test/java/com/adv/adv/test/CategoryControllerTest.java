package com.adv.adv.test;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.verify;
import java.util.Arrays;
import java.util.List;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import com.adv.adv.controller.CaregoryController;
import com.adv.adv.model.Category;
import com.adv.adv.repository.CategoryRepository;

public class CategoryControllerTest {

    private CaregoryController makeCategoryController(CategoryRepository mockRepository) {
        CaregoryController controller = new CaregoryController();
        controller.categoryrepository = mockRepository;
        return controller;
    }

    @Test
    public void getAll_ReturnsModelAndViewWithAllCategories() {
        CategoryRepository mockRepository = mock(CategoryRepository.class);
        CaregoryController controller = makeCategoryController(mockRepository);
        Category category1 = new Category();
        category1.setName("Electronics");
        Category category2 = new Category();
        category2.setName("Books");
        List<Category> categories = Arrays.asList(category1, category2);
        when(mockRepository.findAll()).thenReturn(categories);

        ModelAndView result = controller.getAll();

        assertEquals("CategoriesList.html", result.getViewName());
        assertEquals(categories, result.getModel().get("categories"));
    }

    @Test
    public void deleteCategory_ValidId_RedirectsCorrectly() {
        CategoryRepository mockRepository = mock(CategoryRepository.class);
        CaregoryController controller = makeCategoryController(mockRepository);
        int categoryId = 1;

        RedirectView result = controller.deleteCtaegory(categoryId);

        verify(mockRepository).deleteById(categoryId);
        assertEquals("/categories", result.getUrl());
    }

    @Test
    public void addCategory_ValidInput_Redirects() {
        CategoryRepository mockRepository = mock(CategoryRepository.class);
        CaregoryController controller = makeCategoryController(mockRepository);
        Category category = new Category();
        category.setName("Gardening");
        when(mockRepository.save(category)).thenReturn(category);

        ModelAndView result = controller.addCategory(category, new BindingResultStub(), "Gardening");

        assertEquals("redirect:/categories", result.getViewName());
    }

    // Stub class for BindingResult to avoid complex mocking
    private static class BindingResultStub extends org.springframework.validation.BeanPropertyBindingResult {
        public BindingResultStub() {
            super(new Object(), "objectName");
        }

        @Override
        public boolean hasErrors() {
            return false;
        }
    }
}