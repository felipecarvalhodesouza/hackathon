package br.com.fiap.hackathon.adapters.in.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.com.fiap.hackathon.application.usecases.CadastrarPacienteUseCase;
import br.com.fiap.hackathon.domain.Usuario;

@Controller
@RequestMapping("/cadastro/paciente")
public class CadastroPacienteController {
	
    private final CadastrarPacienteUseCase cadastrarPacienteUseCase;

    @Autowired
    public CadastroPacienteController(CadastrarPacienteUseCase cadastrarPacienteUseCase) {
        this.cadastrarPacienteUseCase = cadastrarPacienteUseCase;
    }
	
	@PostMapping
	public String cadastrarUsuarioPaciente(@ModelAttribute("usuario") Usuario usuario, RedirectAttributes redirectAttributes) throws Exception {
		cadastrarPacienteUseCase.executar(usuario);
		
		redirectAttributes.addFlashAttribute("mensagem", "Usuário criado com sucesso");
		
		return "redirect:/login";
	}
	
}