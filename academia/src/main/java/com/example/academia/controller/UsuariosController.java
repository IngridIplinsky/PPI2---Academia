package com.example.academia.controller;

import com.example.academia.model.Usuarios;
import com.example.academia.repository.UsuariosRepository;
import com.example.academia.service.CookieService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

@Controller
public class UsuariosController {

    private static String caminhoImagem = "C:/Users/ingri/Desktop/imagens";
    @Autowired
    private UsuariosRepository repository;

    @GetMapping("/usuarios")
    public String index(Model model){
        List<Usuarios> users = (List<Usuarios>) repository.findAll();
        model.addAttribute("usuarios", users);
        return "";
    }

    @GetMapping("/registre")
    public String registre(Model model){
        return "login/register";
    }

    @PostMapping("/novoUsuario")
    public String novoUsuario(Model model, @Validated Usuarios user, BindingResult result,
                              @RequestParam("file") MultipartFile img) {

        if (result.hasErrors()) {
            model.addAttribute("error", "Erro ao cadastrar usuário!");
            return "/login/register";
        }else {

            try {
                if (!img.isEmpty()) {
                    byte[] bytes = img.getBytes();
                    Path caminho = Paths.get(caminhoImagem + String.valueOf(Math.random()+ img.getOriginalFilename()));
                    Files.write(caminho, bytes);
                    user.setImagem(String.valueOf(user.getId()) + img.getOriginalFilename());

                    repository.saveAndFlush(user);
                }
            } catch (IOException e) {
                e.printStackTrace();
                model.addAttribute("error", "Erro ao cadastrar usuário!");
                return "/login/register";
            }


            model.addAttribute("confirmado", "Usuário cadastrado com sucesso!");
            return "login/register";
        }
    }

    @GetMapping("/alterar")
    public String alterar(Model model, HttpServletRequest req) throws UnsupportedEncodingException {
        model.addAttribute("nome", CookieService.getCookie(req, "userName"));

        return "home/alterar_perfil";
    }
}

