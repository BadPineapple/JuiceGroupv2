<%@ include file="/ilionnet/taglibs.jsp"%>
<!-- Header Starts -->

<!--Start Nav bar -->

<nav class="navbar navbar-inverse navbar-fixed-top pmd-navbar pmd-z-depth">



    <div class="container-fluid">

        <div class="pmd-navbar-right-icon pull-right navigation">

            <!-- Notifications -->

            <div class="dropdown notification icons pmd-dropdown">

            

                <a href="javascript:void(0)" title="Notification" class="dropdown-toggle pmd-ripple-effect"  data-toggle="dropdown" role="button" aria-expanded="true">

                    <!-- <div data-badge="0" class="material-icons md-light pmd-sm pmd-badge  pmd-badge-overlap">notifications_none</div> -->
                    <div data-badge="0" class="material-icons md-light pmd-sm">notifications_none</div>
					
                </a>

            

                <div class="dropdown-menu dropdown-menu-right pmd-card pmd-card-default pmd-z-depth" role="menu">

                    <!-- Card header -->

                    <div class="pmd-card-title">

                        <div class="media-body media-middle">

                            <!-- <a href="notifications.html" class="pull-right">3 new notifications</a> -->

                            <h3 class="pmd-card-title-text">Avisos</h3>

                        </div>

                    </div>

                    

                    <!-- Notifications list -->

                    <ul class="list-group pmd-list-avatar pmd-card-list">

                        <li class="list-group-item" style="display:;">

                            <p class="notification-blank">

                                <span class="dic dic-notifications-none"></span> 

                                <span>Sem avisos no momento.</span>

                            </p>

                        </li>

                        <%-- <li class="list-group-item unread">

                            <a href="javascript:void(0)">

                                <div class="media-left">

                                    <span class="avatar-list-img40x40">

                                        <img 
                                            alt="40x40" 
                                            data-src="holder.js/40x40" 
                                            class="img-responsive" 
                                            src="<ilion:url/>ilionnet/pmd-admin/themes/images/profile-1.png" 
                                            data-holder-rendered="true">

                                    </span>

                                </div>

                                <div class="media-body">

                                    <span class="list-group-item-heading"><span>Prathit</span> posted a new challanegs</span>

                                    <span class="list-group-item-text">5 Minutes ago</span>

                                </div>

                            </a>

                        </li> --%>

                        


                    </ul><!-- End notifications list -->



                </div>

                

                

            </div> <!-- End notifications -->

        </div>

        <!-- Brand and toggle get grouped for better mobile display -->

        <div class="navbar-header">

            <a href="javascript:void(0);" class="btn btn-sm pmd-btn-fab pmd-btn-flat pmd-ripple-effect pull-left margin-r8 pmd-sidebar-toggle"><i class="material-icons">menu</i></a>   

          <a href="<ilion:url/>agenda" class="navbar-brand">
			
			Vitazure
			
          </a>

        </div>

    </div>



</nav><!--End Nav bar -->

<!-- Header Ends -->
