function updateFormAction() {
    var isMedico = document.getElementById('roleSwitch').checked;
    var form = document.getElementById("cadastroForm");

    if (isMedico) {
        form.action = "/cadastro/medico";
        document.getElementById("crm-field").style.display = 'block'; // Exibe o campo CRM
    } else {
        form.action = "/cadastro/paciente";
        document.getElementById("crm-field").style.display = 'none'; // Esconde o campo CRM
    }
}

function aplicarMascaraCPF(cpf) {
	cpf = cpf.replace(/\D/g, '');

	cpf = cpf.replace(/(\d{3})(\d)/, '$1.$2'); 
	cpf = cpf.replace(/(\d{3})(\d)/, '$1.$2'); 
	cpf = cpf.replace(/(\d{3})(\d{1,2})$/, '$1-$2');
	return cpf;
}

function formatarCPF() {
	const cpfInput = document.getElementById('cpf');
	cpfInput.value = aplicarMascaraCPF(cpfInput.value);
}

function validarCPF(cpf) {
	const regex = /^\d{3}\.\d{3}\.\d{3}-\d{2}$/;
	return regex.test(cpf);
}

function validarFormulario(event) {
	updateFormAction();

	const cpfInput = document.getElementById('cpf');
	const cpf = cpfInput.value;

	if (!validarCPF(cpf)) {
		alert('Por favor, insira um CPF válido');
		event.preventDefault();
		return false;
	}
	return true;
}

window.onload = function() {
    updateFormAction();
};
