package com.example.academia.controller;

import com.example.academia.model.Horarios;
import com.example.academia.model.Usuarios;
import com.example.academia.repository.HorariosRepository;
import com.example.academia.service.CookieService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.Optional;

@Controller
@CrossOrigin
public class HorariosController {

    @Autowired
    private HorariosRepository repository;

    @GetMapping("/horarios")
    public String index(Model model, HttpServletRequest req) throws UnsupportedEncodingException {
        model.addAttribute("nome", CookieService.getCookie(req, "userName"));
        List<Horarios> horarios = (List<Horarios>) repository.findAll();
        model.addAttribute("horarios", horarios);

        return "home/horarios";
    }

}
