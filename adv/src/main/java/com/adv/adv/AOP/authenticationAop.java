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
    private HttpSession httpsession;

    @Around("execution(* com.adv.adv.controller.AdminController.*(..))")
    public Object authentication(ProceedingJoinPoint joinPoint) throws Throwable {
        Object userTypeObj = httpsession.getAttribute("userType");
        if (userTypeObj == null || !(userTypeObj instanceof User.UserType)) {
            return new ModelAndView("/index");
        }
        User.UserType userType = (User.UserType) userTypeObj;

        if (userType != User.UserType.ADMIN) {
            // Redirect if the user is not an admin
            return new ModelAndView("/index");
        }

        // Proceed with method execution if authenticated as admin
        return joinPoint.proceed();
    }
}
