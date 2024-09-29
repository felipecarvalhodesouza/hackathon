package br.com.fiap.hackathon.adapters.in.controller;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import br.com.fiap.hackathon.application.usecases.AtualizarAgendaUseCase;
import br.com.fiap.hackathon.application.usecases.BuscarAgendaUseCase;
import br.com.fiap.hackathon.application.usecases.BuscarHorariosAtendimentoUseCase;
import br.com.fiap.hackathon.domain.Agenda;

@Controller
public class MedicoController {
	
    private final AtualizarAgendaUseCase atualizarAgendaUseCase;
    private final BuscarAgendaUseCase buscarAgendaUseCase;
    private final BuscarHorariosAtendimentoUseCase buscarHorariosAtendimentoUseCase;
    
    @Autowired
    public MedicoController(AtualizarAgendaUseCase atualizarAgendaUseCase, BuscarAgendaUseCase buscarAgendaUseCase, BuscarHorariosAtendimentoUseCase obterHorariosAtendimentoUseCase) {
        this.atualizarAgendaUseCase = atualizarAgendaUseCase;
        this.buscarAgendaUseCase = buscarAgendaUseCase;
        this.buscarHorariosAtendimentoUseCase = obterHorariosAtendimentoUseCase;
    }
	
	@GetMapping("/configurarAgenda")
	public String configurarAgenda(Authentication authentication, Model model) {
		model.addAttribute("usuario", authentication.getName());
	    model.addAttribute("agenda", buscarAgendaUseCase.executar(authentication.getName()));
		return "configurarAgenda";
	}
	
	@PostMapping("/configurarAgenda")
	public String cadastrarAgendaMedico(@ModelAttribute Agenda agenda, Authentication authentication, Model model) {
		model.addAttribute("usuario", authentication.getName());
		atualizarAgendaUseCase.executar(agenda, authentication.getName());
		model.addAttribute("horariosAtendimento", buscarHorariosAtendimentoUseCase.executar(authentication.getName(), LocalDate.now()));
		return "home";
	}
}
