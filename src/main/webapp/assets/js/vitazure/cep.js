/**
 * 
 */
function buscaEndereco(e) {
	let valorCep = e;
	if( ! valorCep || valorCep.trim() == '' || valorCep.length < 10 ) {
		alert('Problema no Cep Informado.');
		return;
	}

	let urlService = 'https://viacep.com.br/ws/' + valorCep.replace(/[.-]/, "") + '/json/'
	
	$.get(urlService, _processaRetornoCep);
	
}

function _processaRetornoCep(retorno) {
	$('#logradouro').val(retorno.logradouro);
	$('#bairro').val(retorno.bairro);
	$('#uf').val(retorno.uf);
	$('#cidade').val(retorno.localidade);	
}
