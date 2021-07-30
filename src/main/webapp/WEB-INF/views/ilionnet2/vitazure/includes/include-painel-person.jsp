<%@ include file="/ilionnet/taglibs.jsp"%>
<div class="bg-bege">
    <div class="container">
        <div class="row">
            <div class="col-12 col-md-6 col-xl-6">
                <div class="dados-person">
                    <div class="img-person">
                        <label for="avatar" class="photo-perfil">
                           <input type="file" name="avatar" id="avatar" style="display: none;">
                             <figure>
                               <img id="img" src="${profissional.pessoa.foto.imagemApresentar == null ? '../assets/images/perfil.png' : profissional.pessoa.foto.link}" alt="">
                             </figure>
                        </label>
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
                    <a href="https://vitazure.whereby.com/634ec6d1-a49c-439e-a41d-9f8fa52c1122?embed&logo=on" target="_blank" class="button-secundary">Entrar na consulta</a>
                </div>
            </div>
        </div>
    </div>
    <script>
 $(function(){
	 $('#avatar').change(function(){
	 	const file = $(this)[0].files[0]
	 	const fileReader = new FileReader()
	 	fileReader.onloadend = function(){
			$('#img').attr('src',fileReader.result)
		}
	 	fileReader.readAsDataURL(file)
	 })
 })

</script>
</div>