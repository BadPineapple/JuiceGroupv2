// funcao mostra esconde
function mostra(qual) {
	var obj = document.getElementById(qual);
	if(obj != null) {
		$("#"+qual).fadeIn(300);
	}
}
function esconde(qual) {
	var obj = document.getElementById(qual);
	if(obj != null) {
		$("#"+qual).hide();
	}
}

//funcao para setar focus no momento que se clica sobre o input
function SetFocus( idToFocus ){
	var TextBox = document.getElementById( idToFocus );
	TextBox.focus();
}
function SetRadio( idToChange ){
	var RadioButton = document.getElementById( idToChange );
	RadioButton.checked = true;
}

function mostraEsconde(id) {
	if ($('#'+id).css('display')=='none') {
	  $('#'+id).fadeIn(300);
	} else {
	  $('#'+id).hide();
	}
}

function abrirDetalhes(id) {
	if ($('#'+id).css('display')=='none') {
		$("tr[id^='arquivo']").hide();
		$('#'+id).fadeIn(300);
	} else {
		$('#'+id).fadeOut(300);
	}
}

function ocultarMensagemRetorno() {
	setTimeout(function(){
		$('#mensagemRetorno').fadeOut(500);
	}, 2000);
}

function selecionarTodos() {
	if( ! $("#todos").is(':checked') ) {
		$("input[name^='idsArquivo']").attr('checked', '');
	} else {
		$("input[name^='idsArquivo']").attr('checked', 'checked');
	}
}