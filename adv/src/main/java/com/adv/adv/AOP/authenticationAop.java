package com.adv.adv.AOP;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.ModelAndView;

import com.adv.adv.model.User;

import jakarta.servlet.http.HttpSession;

@Component
@Aspect
public class authenticationAop {

    @Autowired
    private HttpSession httpSession;

    @Around("execution(* com.adv.adv.controller.AdminController.*(..)) || " +
            "execution(* com.adv.adv.controller.DashBoardControllel.*(..)) || " +
            "execution(* com.adv.adv.controller.ProductController.*(..)) && " +
            "!execution(* com.adv.adv.controller.ProductController.getproduct(..)) && " +
            "!execution(* com.adv.adv.controller.ProductController.searchProducts(..)) || " +
            "execution(* com.adv.adv.controller.CaregoryController.*(..))")
    public Object authentication(ProceedingJoinPoint joinPoint) throws Throwable {
        Object userTypeObj = httpSession.getAttribute("userType");
        if (userTypeObj == null || !(userTypeObj instanceof User.UserType)) {
            return new ModelAndView("redirect:/");
        }
        User.UserType userType = (User.UserType) userTypeObj;

        if (userType != User.UserType.ADMIN) {
            // Redirect if the user is not an admin
            return new ModelAndView("redirect:/");
        }

        // Proceed with method execution if authenticated as admin
        return joinPoint.proceed();
    }
}
