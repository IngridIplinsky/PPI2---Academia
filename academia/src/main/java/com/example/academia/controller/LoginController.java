package com.example.academia.controller;

import com.example.academia.model.Horarios;
import com.example.academia.model.Usuarios;
import com.example.academia.repository.HorariosRepository;
import com.example.academia.repository.UsuariosRepository;
import com.example.academia.service.CookieService;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import org.hibernate.annotations.NotFound;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.io.IOException;
import java.util.Collection;
import java.util.List;

@Controller
@CrossOrigin
public class LoginController {

    @Autowired
    private UsuariosRepository userRep;

    @GetMapping("/")
    public String index(Model model) {
        return "login/login";
    }

    @PostMapping("/logar")
    public String logar(Model model, String email, String senha, String lembrar, HttpServletResponse response) throws IOException {
        Usuarios usuario = userRep.Login(email, senha);
        if (usuario != null){
            int tempoLogado = (60*60);

            if (lembrar != null){ tempoLogado = (60*60*24);}

            CookieService.setCookie(response, "userId", String.valueOf(usuario.getId()), tempoLogado);
            CookieService.setCookie(response, "userName", String.valueOf(usuario.getNome()), tempoLogado);
            CookieService.setCookie(response, "userEmail", String.valueOf(usuario.getEmail()), tempoLogado);
            return "redirect:/home";
        }
        model.addAttribute("error", "Email ou senha incorretos");
        return "login/login";
    }
}
