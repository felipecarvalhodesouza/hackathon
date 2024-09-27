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

window.onload = function() {
    updateFormAction();
};
