package br.com.fiap.hackathon.adapters.in.controller;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import br.com.fiap.hackathon.application.usecases.ObterHorariosAtendimentoUseCase;

@Controller
public class LoginController {
	
    private final ObterHorariosAtendimentoUseCase obterHorariosAtendimentoUseCase;

    @Autowired
    public LoginController(ObterHorariosAtendimentoUseCase obterHorariosAtendimentoUseCase) {
        this.obterHorariosAtendimentoUseCase = obterHorariosAtendimentoUseCase;
    }

	@GetMapping("/login")
	public String login() {
		return "login";
	}
	
	@GetMapping("/")
	public String home(Authentication authentication, Model model) {
		model.addAttribute("usuario", authentication.getName());
        model.addAttribute("horariosAtendimento", obterHorariosAtendimentoUseCase.obterHorariosAtendimento(authentication.getName(), LocalDate.now()));
		return "home";
	}
}
