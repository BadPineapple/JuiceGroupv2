function buscaCidades(uf, callback) {
	if( ! uf || uf.trim() == '' ) {
		return;
	}
	let url = ENDERECO_SITE+'/api/cidades/'+uf;
	$.get(url, function(cidades) {
		var $cidade = $("#cidade");
		$cidade.empty();
		$cidade.append($("<option />").val('').text('Selecione sua cidade'));
		$.each(cidades, function() {
			$cidade.append($("<option />").val(this.id).text(this.nome));
			
		});
		if( callback ) {
			callback();
		}
	});
	
}