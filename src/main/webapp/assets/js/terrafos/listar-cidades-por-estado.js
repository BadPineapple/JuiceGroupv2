function Cidades() {

	this.init = function() {

		var self = this;

		$('#uf').change(function(){
			var idEstado = $(this).val();

			if(idEstado != ''){
				self.buscarCidadePorEstado(idEstado);
			}else{
				self.caregarSelectCidadesVazio();
			}
		});
		
		$('#cidade').change(function(){
			$(this).data('nome', $(this).find(':selected').attr('data-nome') );
		});

	};

	this.caregarSelectCidadesVazio = function(cidades) {

		var options = "";
		options += '<option id="" value="">' + 'Selecione a cidade:' + '</option>';
		$("#cidade").html(options);

	};

	this.carregarCidades = function(cidades) {

		var options = "";

		options += '<option id="" value="">' + 'Selecione a cidade:' + '</option>';

			$.each(cidades, function(key, cidade){
				options += '<option name="cidade" id="'+cidade.id+'" data-nome="'+cidade.nome+'" value="' +cidade.id + '">' + cidade.nome + '</option>';
			});

		$("#cidade").html(options);

	};

	this.buscarCidadePorEstado = function(uf) {
		
		var self = this;
		$.ajax({
			type: "POST",
			url: ENDERECO_SITE+'api/cidades/'+uf,
			dataType: 'json',
			success: function(data) {

				if( data.erro ) {
					console.log(data.erro);
				} else {
					self.carregarCidades(data);
				}
			}
		});

	};

}

$(function() {

	var cidades = new Cidades();
	cidades.init();

});
