package br.com.fiap.hackathon.adapters.in.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.com.fiap.hackathon.application.usecases.CadastrarMedicoUseCase;
import br.com.fiap.hackathon.domain.Medico;

@Controller
@RequestMapping("/cadastro/medico")
public class CadastroMedicoController {
	
    private final CadastrarMedicoUseCase cadastrarMedicoUseCase;

    @Autowired
    public CadastroMedicoController(CadastrarMedicoUseCase cadastrarMedicoUseCase) {
        this.cadastrarMedicoUseCase = cadastrarMedicoUseCase;
    }
	
	@PostMapping
	public String cadastrarUsuarioMedico(@ModelAttribute("usuario") Medico medico, RedirectAttributes redirectAttributes) throws Exception {
		cadastrarMedicoUseCase.executar(medico);
		
		redirectAttributes.addFlashAttribute("mensagem", "Usuário criado com sucesso");
		
		return "redirect:/login";
	}
	
}