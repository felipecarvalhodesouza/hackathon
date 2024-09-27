window.onload = function() {
	const modal = document.getElementById('alertModal');
	if (modal) {
		modal.style.display = 'block'; // Exibe o modal se ele existir
	}
};

function fecharModal() {
	document.getElementById('alertModal').style.display = 'none';
}

window.onclick = function(event) {
	const modal = document.getElementById('alertModal');
	if (event.target == modal) {
		modal.style.display = 'none';
	}
};