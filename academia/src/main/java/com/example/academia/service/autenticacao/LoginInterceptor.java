package com.example.academia.service.autenticacao;

import com.example.academia.service.CookieService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

@Component
public class LoginInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest req, HttpServletResponse res, Object handler) throws Exception{
        if(CookieService.getCookie(req, "userId") != null){
            return true;
        }
        res.sendRedirect("/");
        return false;
    }
}
