/**
 *
 */


function alertaErro(titulo, mensagem) {

	swal(titulo, mensagem, "warning")
}

function informarMensagemAtencao(titulo, mensagem) {

	swal(titulo, mensagem, "warning")
}

function alertaDadosInvalidos(mensagem) {

	swal("Dados inv\u00e1lidos", mensagem, "warning")
}

function alertaSucesso(titulo, mensagem) {

	swal({
		title:titulo,
		text:mensagem,
		confirmButtonColor: '#d80b34',
		type:"success"
	});
}

window.alert = function(message) {
	alertaErro('Atenção!', message);
}