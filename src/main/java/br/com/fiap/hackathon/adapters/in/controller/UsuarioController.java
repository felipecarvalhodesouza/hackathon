package br.com.fiap.hackathon.adapters.in.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class UsuarioController {

	@GetMapping("/login")
	public String login() {
		return "login";
	}
}
