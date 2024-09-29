package br.com.fiap.hackathon.adapters.out.gateway;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import br.com.fiap.hackathon.application.ports.out.EnvioNotificacaoGateway;
import br.com.fiap.hackathon.domain.Consulta;
import br.com.fiap.hackathon.domain.Medico;

@Service
public class EnvioNotificacaoGatewayImp implements EnvioNotificacaoGateway{

    private JavaMailSender mailSender;
    
    @Autowired
    public EnvioNotificacaoGatewayImp(JavaMailSender mailSender) {
    	this.mailSender = mailSender;
    }

    @Override
    public void enviarNotificacao(Consulta consulta) {
    	Medico medico = consulta.getMedico();
        
    	SimpleMailMessage message = new SimpleMailMessage();
		message.setTo(medico.getEmail());
        message.setSubject("Health&Med - Nova consulta agendada");
        message.setText("Olá, Dr. " + medico.getNome() + "!\n" +
                        "Você tem uma nova consulta marcada!\n" +
                        "Paciente: " + consulta.getPaciente().getNome() + ".\n" +
                        "Data e horário: " + consulta.getDia() + " às " + consulta.getHorario() + ".");
        mailSender.send(message);
    }
}
