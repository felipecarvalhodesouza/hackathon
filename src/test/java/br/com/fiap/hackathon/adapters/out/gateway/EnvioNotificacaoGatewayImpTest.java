package br.com.fiap.hackathon.adapters.out.gateway;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;

import br.com.fiap.hackathon.domain.Consulta;
import br.com.fiap.hackathon.domain.Medico;
import br.com.fiap.hackathon.domain.Usuario;

public class EnvioNotificacaoGatewayImpTest {

    @Mock
    private JavaMailSender mailSender;

    @InjectMocks
    private EnvioNotificacaoGatewayImp envioNotificacaoGateway;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testEnviarNotificacao() {
        Medico medico = new Medico();
        medico.setNome("Dr. John Doe");
        medico.setEmail("medico@example.com");

        Usuario paciente = new Usuario();
        paciente.setNome("Jane Doe");

        Consulta consulta = new Consulta();
        consulta.setMedico(medico);
        consulta.setPaciente(paciente);

        envioNotificacaoGateway.enviarNotificacao(consulta);

        verify(mailSender, times(1)).send(any(SimpleMailMessage.class));
    }

    @Test
    public void testEnviarNotificacaoConteudo() {
        Medico medico = new Medico();
        medico.setNome("Dr. John Doe");
        medico.setEmail("medico@example.com");

        Usuario paciente = new Usuario();
        paciente.setNome("Jane Doe");

        Consulta consulta = new Consulta();
        consulta.setMedico(medico);	
        consulta.setPaciente(paciente);

        envioNotificacaoGateway.enviarNotificacao(consulta);

        verify(mailSender).send(any(SimpleMailMessage.class));
    }
}

