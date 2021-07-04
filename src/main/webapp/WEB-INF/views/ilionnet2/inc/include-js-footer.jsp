<%@ include file="/ilionnet/taglibs.jsp"%>
<!-- Scripts Starts -->

<script>
ENDERECO_SITE='<ilion:url/>';
</script>

<script src="<ilion:url/>ilionnet/pmd-admin/assets/js/jquery-1.12.2.min.js"></script>

<script src="<ilion:url/>ilionnet/pmd-admin/assets/js/bootstrap.min.js"></script>

<script>

    $(document).ready(function() {

        var sPath=window.location.pathname;

        var sPage = sPath.substring(sPath.lastIndexOf('/') + 1);

        $(".pmd-sidebar-nav").each(function(){

            $(this).find("a[href='"+sPage+"']").parents(".dropdown").addClass("open");

            $(this).find("a[href='"+sPage+"']").parents(".dropdown").find('.dropdown-menu').css("display", "block");

            $(this).find("a[href='"+sPage+"']").parents(".dropdown").find('a.dropdown-toggle').addClass("active");

            $(this).find("a[href='"+sPage+"']").addClass("active");

        });

    });

</script>


<script src="<ilion:url/>ilionnet/pmd-admin/assets/js/propeller.min.js"></script>

<c:if test="${ not empty msgSuccess }">
	<button 
		type="button" 
		data-positionX="right" 
		data-positionY="top" 
		data-effect="fadeInUp" 
		data-message="${msgSuccess}"
		data-type="success" 
		class="btn pmd-ripple-effect btn-success pmd-z-depth pmd-alert-toggle"
		id="alertMsgSuccess"
		style="display:none;">
		Sucesso
	</button>
	<script type="text/javascript">
		(function() {
			setTimeout(function() {
				$('#alertMsgSuccess').click();
			}, 300);
		})();
	</script>
</c:if>

<c:if test="${ not empty msgError }">
	<button 
		type="button" 
		data-positionX="right" 
		data-positionY="top" 
		data-effect="fadeInUp" 
		data-message="${msgError}"
		data-type="error" 
		class="btn pmd-ripple-effect btn-error pmd-z-depth pmd-alert-toggle"
		id="alertMsgError"
		style="display:none;">
		Erro
	</button>
	<script type="text/javascript">
		(function() {
			setTimeout(function() {
				$('#alertMsgError').click();
			}, 300);
		})();
	</script>
</c:if>