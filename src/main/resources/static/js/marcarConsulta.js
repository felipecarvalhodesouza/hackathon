$(document).ready(function() {
    $('#medicoSelect').change(function() {
        var medicoId = $(this).val();
        if (medicoId) {
            $.ajax({
                url: `/horariosDisponiveis/${medicoId}`,
                type: 'GET',
                success: function(data) {
                    var horariosSelect = $('#horarioSelect');
                    horariosSelect.empty(); // Limpa os horários antigos
                    horariosSelect.append('<option value="">-- Selecione um horário --</option>');

                    if (data.length > 0) {
                        $.each(data, function(index, horario) {
                            horariosSelect.append('<option value="' + horario + '">' + horario + '</option>');
                        });
                        $('#horariosDisponiveis').show();
                    } else {
                        $('#horariosDisponiveis').hide();
                    }
                }
            });
        } else {
            $('#horariosDisponiveis').hide();
        }
    });
});
