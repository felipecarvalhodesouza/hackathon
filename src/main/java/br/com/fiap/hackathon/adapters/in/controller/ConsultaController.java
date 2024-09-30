package br.com.fiap.hackathon.adapters.in.controller;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import br.com.fiap.hackathon.application.usecases.BuscarHorariosDisponiveisUseCase;
import br.com.fiap.hackathon.application.usecases.BuscarMedicosDisponiveisUseCase;
import br.com.fiap.hackathon.application.usecases.CadastrarConsultaUseCase;
import br.com.fiap.hackathon.domain.exception.HorarioIndisponivelException;

@Controller
public class ConsultaController {
	
    private final BuscarHorariosDisponiveisUseCase buscarHorariosDisponiveisUseCase;
    private final BuscarMedicosDisponiveisUseCase buscarMedicosDisponiveisUseCase;
    private final CadastrarConsultaUseCase cadastrarConsultaUseCase;

    @Autowired
    public ConsultaController(BuscarHorariosDisponiveisUseCase buscarHorariosDisponiveisUseCase, BuscarMedicosDisponiveisUseCase buscarMedicosDisponiveisUseCase, CadastrarConsultaUseCase cadastrarConsultaUseCase) {
        this.buscarHorariosDisponiveisUseCase = buscarHorariosDisponiveisUseCase;
        this.buscarMedicosDisponiveisUseCase = buscarMedicosDisponiveisUseCase;
        this.cadastrarConsultaUseCase = cadastrarConsultaUseCase;
    }

	@GetMapping("/marcarConsulta")
	public String mostrarMarcarConsulta(Authentication authentication, Model model) {
		model.addAttribute("usuario", authentication.getName());
		model.addAttribute("medicos", buscarMedicosDisponiveisUseCase.executar(LocalDate.now()));
		return "marcarConsulta";
	}
	
    @PostMapping("/marcarConsulta")
    public String marcarConsulta(@RequestParam("medico") Long medicoId, @RequestParam("horario") String horario, Model model, Authentication authentication) {
		model.addAttribute("usuario", authentication.getName());
    	
    	try{
    		cadastrarConsultaUseCase.executar(medicoId, authentication.getName(), LocalDate.now(), horario);
    		model.addAttribute("mensagem", "Consulta agendada com sucesso!");
    	} catch (HorarioIndisponivelException e) {
    		model.addAttribute("mensagem", e.getMessage());
		}

        return "home";
    }

	@GetMapping("/horariosDisponiveis/{medicoId}")
	public List<String> buscarHorariosDisponiveis(@PathVariable Long medicoId) {
		return buscarHorariosDisponiveisUseCase.buscarHorariosDisponiveis(medicoId, LocalDate.now());
	}
}
