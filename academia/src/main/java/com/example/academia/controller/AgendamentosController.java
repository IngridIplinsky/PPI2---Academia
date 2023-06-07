package com.example.academia.controller;

import com.example.academia.model.Agendamentos;
import com.example.academia.model.Horarios;
import com.example.academia.repository.AgendamentosRepository;
import com.example.academia.repository.HorariosRepository;
import com.example.academia.service.CookieService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.io.UnsupportedEncodingException;
import java.sql.Time;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Controller
@CrossOrigin
public class AgendamentosController {

    @Autowired
    private HorariosRepository horRep;
    @Autowired
    private AgendamentosRepository agendRep;


    @GetMapping("/agendar")
    public String index(Model model, HttpServletRequest req) throws UnsupportedEncodingException {
        model.addAttribute("nome", CookieService.getCookie(req, "userName"));
        String email = CookieService.getCookie(req, "userEmail");
        Date d = new Date();
        Collection<Agendamentos> agenda = agendRep.PesquisarUser(email, d);
        model.addAttribute("agendamentos", agenda);
        return "home/agendamentos";
    }

    @GetMapping("/agendar/{id}/marcar")
    public String marcar(Model model, @PathVariable Long id, HttpServletRequest req) throws UnsupportedEncodingException {
        String email = CookieService.getCookie(req, "userEmail");
        Optional<Horarios> hora = horRep.findById(id);
        Date d = new Date();
        int vaga = hora.get().getVaga();

        if (vaga == 0){
            model.addAttribute("error", "Horário não possui mais vagas");
        }
        else {
            Agendamentos agenda = agendRep.Pesquisar(email, hora.get().getHorario(), d);
            if(agenda == null) {
                vaga = vaga - 1;
                horRep.Agendar(id, vaga);
                agendRep.Marcar(email, hora.get().getAula(), hora.get().getHorario(), d);
                model.addAttribute("confirmado", "Aula agendada com sucesso");
                model.addAttribute("valor", "1");
            }
            else {
                model.addAttribute("error", "Já foi realizado agendamento para está aula");
            }
        }
        model.addAttribute("nome", CookieService.getCookie(req, "userName"));
        List<Horarios> horarios = (List<Horarios>) horRep.findAll();
        model.addAttribute("horarios", horarios);

        return "home/horarios";
    }

    @GetMapping("/agendar/{id}/desmarcar")
    public String desmarcar(Model model, @PathVariable Long id, HttpServletRequest req) throws UnsupportedEncodingException {
        model.addAttribute("nome", CookieService.getCookie(req, "userName"));
        Optional<Agendamentos> agenda = agendRep.findById(id);
        String aula = agenda.get().getAula();
        Time hora = agenda.get().getHorario();
        Horarios horario = horRep.PesquisarVagaAula(aula, hora);
        int vaga = horario.getVaga();
        vaga++;
        horRep.Agendar(horario.getId(), vaga);
        agendRep.deleteById(id);
        model.addAttribute("confirmado", "Aula desmarcada com sucesso");
        return "home/agendamentos";
    }
}
