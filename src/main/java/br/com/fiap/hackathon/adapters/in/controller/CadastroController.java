package br.com.fiap.hackathon.adapters.in.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import br.com.fiap.hackathon.domain.Usuario;

@Controller
public class CadastroController {

	@GetMapping("/cadastro")
	public String showNewUsersForm(Model model) {
		Usuario user = new Usuario();
		model.addAttribute("usuario", user);
		return "cadastro";
	}
}
