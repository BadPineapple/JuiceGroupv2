<%@ include file="/ilionnet/taglibs.jsp"%>
<div class="area-blue">
    <div class="container">
        <div class="row">
            <div class="col-12">
              <c:if test="${pessoa ne null}">
	              <c:if test="${pessoa.cliente}">
	                <a href="<ilion:url/>vitazure/informacoes-perfil" class="line">
	                    <i class="fal fa-home-alt" style="font-size: 20px;padding-right: 6px;font-weight: 300;"></i>
	                    Painel do Paciente
	                </a>
				</c:if>
				 <c:if test="${pessoa.cliente}">
	                <a href="<ilion:url/>vitazure/listaProfissionais" class="line">
	                   <i class="fas fa-search-location" style="font-size: 20px;padding-right: 6px;font-weight: 300;"></i>
	                    Localize um profissional
	                </a>
				</c:if>
	                <a href="<ilion:url/>vitazure/lista-de-consultas" class="line">
	                    <i class="far fa-calendar-alt" style="font-size: 20px;padding-right: 6px;font-weight: 300;"></i>
	                    Minhas consultas
	                </a>
	
	                <a href="<ilion:url/>vitazure/consultaTrasacoes" class="line">
	                    <i class="fas fa-dollar-sign" style="font-size: 20px;padding-right: 6px;font-weight: 300;"></i>
	                    Histórico de pagamentos      
	                </a>
	                <c:if test="${pessoa.psicologo}">
	                <a href="<ilion:url/>vitazure/informacoes-perfil" class="line">
	                    <svg width="21" height="20" viewBox="0 0 21 20" fill="none" xmlns="http://www.w3.org/2000/svg">
	                        <path fill-rule="evenodd" clip-rule="evenodd" d="M5.66992 14H15.6699C18.4313 14 20.6699 16.2386 20.6699 19C20.6699 19.5523 20.2222 20 19.6699 20C19.1571 20 18.7344 19.614 18.6766 19.1166L18.6648 18.8237C18.577 17.3072 17.3627 16.093 15.8462 16.0051L15.6699 16H5.66992C4.01307 16 2.66992 17.3431 2.66992 19C2.66992 19.5523 2.22221 20 1.66992 20C1.11764 20 0.669922 19.5523 0.669922 19C0.669922 16.3112 2.79223 14.1182 5.45303 14.0046L5.66992 14H15.6699H5.66992ZM10.6699 0C13.9836 0 16.6699 2.68629 16.6699 6C16.6699 9.31371 13.9836 12 10.6699 12C7.35621 12 4.66992 9.31371 4.66992 6C4.66992 2.68629 7.35621 0 10.6699 0ZM10.6699 2C8.46078 2 6.66992 3.79086 6.66992 6C6.66992 8.20914 8.46078 10 10.6699 10C12.8791 10 14.6699 8.20914 14.6699 6C14.6699 3.79086 12.8791 2 10.6699 2Z" fill="#52545E"/>
	                    </svg>
	                    Meu Perfil          
	                </a>
	                </c:if>
	                <a href="<ilion:url/>vitazure/meuCadastro" class="line">
	                    <i class="far fa-user" style="font-size: 20px;padding-right: 6px;font-weight: 300;"></i>
	                    Meus Dados          
	                </a>
	                <c:if test="${pessoa.psicologo}">
	                <a href="<ilion:url/>vitazure/minhaAssinatura" class="line">
	                    <i class="fas fa-file-contract" style="font-size: 20px;padding-right: 6px;font-weight: 300;"></i>
	                    Minha Assinatura          
	                </a>
	                </c:if>
	             </c:if>   
            </div>
        </div>
    </div>
</div>