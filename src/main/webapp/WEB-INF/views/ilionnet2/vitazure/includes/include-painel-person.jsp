<%@ include file="/ilionnet/taglibs.jsp"%>
<div class="bg-bege">
    <div class="container">
        <div class="row">
            <div class="col-12 col-md-6 col-xl-6">
                <div class="dados-person">
                    <div class="img-person">
                        <figure>
                            <img src="${pessoa.foto.imagemApresentar == null ? '../assets/images/perfil.png' : pessoa.foto.link}" alt="" style="height: 150px;width: 150px;">
                        </figure>
                    </div>
                    <div class="info-person">
                        <span>Olá ${pessoa.nome}</span>
                        <a href="#" class="button-white"> Paciente</a>
                        <span>${pessoa.email}</span>
                    </div>
                </div>
            </div>

            <div class="col-12 col-md-6 col-xl-6">
                <div class="entrar-consulta">
                    <input type="text" value="${agendaDia}" id="agendaDia" style="display: contents;"/>
                    <a href="" target="_blank" id="linkAcesso" class="not-active button-secundary">Entrar na consulta</a>
<!--                     <a href="http://localhost:8080/consulta/38"  id="linkAcesso" class="button-secundary">Entrar na consulta</a> -->
                </div>
            </div>
            <div style="width: 100%; padding-bottom: 5px;">
                <span class="notice-text">
                            Todos os agendamentos seguem o horário de Brasília -
                             <div id="hora" style="padding-left: 4px;"></div>
                        </span>
            </div>
        </div>
    </div>
</div>

<style>
.not-active {
  pointer-events: none;
  cursor: default;
  text-decoration: none;
  color: #f3f3f3;
  background: #ddd;
}
</style>

<script>

class Agenda{
  constructor(agenda) {
	const agendaVO = agenda.split(',');  
    this.id = agendaVO[0];
    this.horaLiberar = agendaVO[1];
    this.horaBloquear = agendaVO[2];
    this.horaAlert = agendaVO[3];
    this.url = agendaVO[4];
    this.liberadoAcessoa = false;
  }
}

 var agendas = new Array();
    var myVar = setInterval(myTimer ,2000);
    function myTimer() {
        var d = new Date(), displayDate;
       if (navigator.userAgent.toLowerCase().indexOf('firefox') > -1) {
          displayDate = d.toLocaleTimeString('pt-BR');
       } else {
          displayDate = d.toLocaleTimeString('pt-BR', {timeZone: 'America/Belem'});
       }
            agendas = new Array();      
            var opa = document.getElementById("agendaDia").value;
            if(document.getElementById("hora") != null){
             document.getElementById("hora").innerHTML = displayDate.substr(0, 5);
            }
             const agendaVO = opa.split('Agenda[');
            var maisTeste = agendaVO[0];
            agendaVO.forEach(eu);
            for (var i = 0; i < agendas.length; i++) {
            	if (displayDate.substr(0, 5) >= agendas[i].horaLiberar &&  displayDate.substr(0, 5) < agendas[i].horaBloquear) {
            		document.getElementById("linkAcesso").className = "button-secundary";
            		document.getElementById("linkAcesso").href = "https://vitazure.com.br/consulta/"+agendas[i].id;
				}
            }
    };
    
	

function eu(item, indice){
	if (indice > 0) {
    	var io = item.replace('{','').replace('}]]','').replace('}],','');
	    const agendaVO = io.split(',');
	    var agenda = new Agenda(io);
	    agendas.push(agenda);
	}
};
    
    
    </script>