<%@ include file="/ilionnet/taglibs.jsp"%>
<div class="bg-bege">
    <div class="container">
        <div class="row">
			
            <div class="col-12 col-md-4 col-xl-5">
                <div class="dados-person">
                    <div class="img-person">
                        <figure>
                            <img src="${pessoa.foto.imagemApresentar == null ? '../assets/images/perfil.png' : pessoa.foto.link}" alt="">
                        </figure>
                    </div>

                    <div class="info-person">
                        <span>Olá ${pessoa.nome}</span>
                        <a href="#" class="button-white" style="font-size: 2rem;"> Profissional</a>
                        <span>${pessoa.email}</span>
                    </div>
                </div>
            </div>
			
			<div class="col-12 col-md-4 col-xl-3">
				<c:if test="${sessionScope.profissonalSessaoCompleto eq 'false'}">
					<div class="dados-atualizados">
						<svg width="27" height="28" viewBox="0 0 27 28" fill="none" xmlns="http://www.w3.org/2000/svg">
							<path fill-rule="evenodd" clip-rule="evenodd" d="M6.75 19.23H20.25C23.9779 19.23 27 22.252 27 25.98C27 26.7255 26.3956 27.33 25.65 27.33C24.9577 27.33 24.3871 26.8088 24.3091 26.1374L24.2931 25.742C24.1745 23.6947 22.5352 22.0555 20.488 21.9368L20.25 21.93H6.75C4.51325 21.93 2.7 23.7432 2.7 25.98C2.7 26.7255 2.09558 27.33 1.35 27.33C0.604416 27.33 0 26.7255 0 25.98C0 22.3501 2.86511 19.3895 6.4572 19.2362L6.75 19.23H20.25H6.75ZM13.5 0.329956C17.9735 0.329956 21.6 3.95645 21.6 8.42996C21.6 12.9035 17.9735 16.53 13.5 16.53C9.0265 16.53 5.4 12.9035 5.4 8.42996C5.4 3.95645 9.0265 0.329956 13.5 0.329956ZM13.5 3.02995C10.5177 3.02995 8.1 5.44761 8.1 8.42995C8.1 11.4123 10.5177 13.8299 13.5 13.8299C16.4823 13.8299 18.9 11.4123 18.9 8.42995C18.9 5.44761 16.4823 3.02995 13.5 3.02995Z" fill="#851F24"/>
						</svg>

						Mantenha seu perfil <br/>sempre atualizado!

					</div>
				</c:if>
			</div>
			
            <div class="col-12 col-md-4 col-xl-4">
                <div class="entrar-consulta">
				    <input type="text" value="${agendaDia}" id="agendaDia" style="display: contents;"/>
                    <a href=""  id="linkAcesso" class="not-active button-secundary">Entrar na consulta</a>
                    <span id="proximaConsulta" style="position: absolute;top: 76%; color: #ff2626; font-size: 14px; font-weight: 600; padding-right: 5px"></span>
                </div>
            </div>
			
            <div class="col-12">
				<span class="notice-text">
					Todos os agendamentos seguem o horário de Brasília - <div id="hora" style="display: contents;"></div>.
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
            if(agendas.length == 0){
            	document.getElementById("proximaConsulta").innerHTML = 'Sem consulta para ser liberada';
            }
            for (var i = 0; i < agendas.length; i++) {
            	if (displayDate.substr(0, 5) >= agendas[i].horaLiberar &&  displayDate.substr(0, 5) < agendas[i].horaBloquear) {
            		document.getElementById("linkAcesso").className = "button-secundary";
            		document.getElementById("linkAcesso").href = "<ilion:url/>consulta/"+agendas[i].id;
//             		var idAgenda = agendas[i].id;
//             		document.getElementById("linkAcesso").onclick = function() {consultaAgenda(idAgenda)};
				}
            }
            for (var i = 0; i < agendas.length; i++) {
            	if (displayDate.substr(0, 5) < agendas[i].horaLiberar &&  displayDate.substr(0, 5) < agendas[i].horaBloquear) {
            		document.getElementById("proximaConsulta").innerHTML = 'Proxima consulta a ser liberada '+agendas[i].horaLiberar;
            		break;
				}else{
					document.getElementById("proximaConsulta").innerHTML = 'Sem consulta para ser liberada';
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