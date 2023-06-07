package com.example.academia.controller;

import com.example.academia.service.CookieService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.io.UnsupportedEncodingException;

@Controller
public class PublicacaoController {

    @GetMapping("/forum")
    public String forum(Model model, HttpServletRequest req) throws UnsupportedEncodingException {
        model.addAttribute("nome", CookieService.getCookie(req, "userName"));
        return "home/forum";
    }
}
