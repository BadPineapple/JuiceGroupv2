function alert_warning(t){Swal.fire({position:"center",type:"warning",title:t})}function alert_error(t){Swal.fire({position:"center",type:"error",title:t})}function alert_success(t,i){Swal.fire({position:"center",type:"success",title:t}).then(i)}function toast_success(t,i){iziToast.success({title:t,message:i})}function toast_warning(t,i){iziToast.warning({title:t,message:i})}function toast_error(t,i){iziToast.error({title:t,message:i})}function toast_info(t,i){iziToast.info({title:t,message:i})}function showSpinner(){$(".loading-io").show()}function hideSpinner(){$(".loading-io").hide()}
$(document).ready(function(){$(".login-for").click(function(){$(".login-card").hide(),$(".forgot-password-card").show()}),$(".signin").click(function(){$(".login-card").show(),$(".forgot-password-card").hide()}),$(".forgot-password").click(function(){$(".login-card").hide(),$(".register-card").hide(),$(".forgot-password-card").show()})});
$(document).ready(function(){var e=$("#table-checkbox").DataTable({buttons:{buttons:[{extend:"csv"}]},responsive:!1,columnDefs:[{orderable:!1,className:"select-checkbox",targets:0}],select:{style:"multi",selector:"td:first-child"},order:[1,"desc"],bFilter:!0,bLengthChange:!0,pagingType:"simple",paging:!0,searching:!0,language:{select:{rows:"%d linha(s) selecionada(s)"},emptyTable:"Sem dados disponíveis na tabela",info:" _START_ - _END_ de _TOTAL_ ",infoEmpty:"Mostrando 0 de 0 registros.",zeroRecords:"Nenhum registro correspondente encontrado.",processing:"Processando...",infoFiltered:"(filtrado de _MAX_ registros totais.)",sLengthMenu:"<span class='custom-select-title'>Qtde. de registros:</span> <span class='custom-select'> _MENU_ </span>",sSearch:"",sSearchPlaceholder:"Palavra chave",paginate:{sNext:" ",sPrevious:" ",first:"Primeira",last:"Última",next:"Próxima",previous:"Anterior"}},dom:"<'pmd-card-title'<'data-table-title'><'search-paper pmd-textfield'f>><'custom-select-info'<'custom-select-item'><'custom-select-action'>><'row'<'col-sm-12'tr>><'pmd-card-footer' <'pmd-datatable-pagination' l i p>>"});$(".custom-select-info").hide(),$("#table-checkbox tbody").on("click","tr",function(){if($(this).hasClass("selected")){var e=$(this).closest(".dataTables_wrapper").find(".select-info").text();$(this).closest(".dataTables_wrapper").find(".custom-select-info .custom-select-item").text(e),null!=$(this).closest(".dataTables_wrapper").find(".custom-select-info .custom-select-item").text()?$(this).closest(".dataTables_wrapper").find(".custom-select-info").show():$(this).closest(".dataTables_wrapper").find(".custom-select-info").hide()}else{e=$(this).closest(".dataTables_wrapper").find(".select-info").text();$(this).closest(".dataTables_wrapper").find(".custom-select-info .custom-select-item").text(e)}0==$("#table-checkbox").find(".selected").length&&$(this).closest(".dataTables_wrapper").find(".custom-select-info").hide()}),$(".custom-select-action").html('<button class="btn btn-sm pmd-btn-fab pmd-btn-flat pmd-ripple-effect btn-primary" type="button" id="delete-selected-button"><i class="material-icons pmd-sm">delete</i></button>'),$("#btn-export").on("click",function(){e.button(".buttons-csv").trigger()})});
$(document).ready(function(){var a=window.location.pathname,d=a.substring(a.lastIndexOf("/")+1);$(".pmd-sidebar-nav").each(function(){$(this).find("a[href='"+d+"']").parents(".dropdown").addClass("open"),$(this).find("a[href='"+d+"']").parents(".dropdown").find(".dropdown-menu").css("display","block"),$(this).find("a[href='"+d+"']").parents(".dropdown").find("a.dropdown-toggle").addClass("active"),$(this).find("a[href='"+d+"']").addClass("active")}),$(".auto-update-year").html((new Date).getFullYear())});
//# sourceMappingURL=bundle.scripts.ilionnet.js.map