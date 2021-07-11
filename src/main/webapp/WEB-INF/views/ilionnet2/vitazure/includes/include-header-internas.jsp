<%@ include file="/ilionnet/taglibs.jsp"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<header class="header-internas">
    <div class="container">
        <div class="row">
            <div class="col-6 col-md-8 col-xl-8">
                <div class="menu-list">
                    <a href="index.jsp"><img src="../assets/images/logo.png" alt="Vitazure"></a>

                    <div class="menu d-none d-md-block">
                        <ul>
                            <li><a href="<ilion:url/>como-funciona" class="line">Como funciona</a></li>

                            <li><a href="<ilion:url/>aqui-e-para-voce" class="line">Aqui é para você</a></li>

                            <li><a href="<ilion:url/>sou-profissional" class="line">Sou Profissional</a></li>

                            <li><a href="<ilion:url/>para-sua-empresa" class="line">Para sua empresa</a></li>
                        </ul>
                    </div>
                </div>
            </div>

            <div class="col-6 d-md-none text-right">
                <a href="#menu" class="menu-mobile d-md-none">
                    <svg width="26px" height="16px" viewBox="0 0 26 16" version="1.1" xmlns="http://www.w3.org/2000/svg" xmlns:xlink="http://www.w3.org/1999/xlink">
                        <g id="Page-1" stroke="none" stroke-width="1" fill="none" fill-rule="evenodd">
                            <g id="home" transform="translate(-408.000000, -56.000000)" fill="#0097d6">
                                <rect id="Rectangle1" x="408" y="56" width="26" height="2"></rect>
                                <rect id="Rectangle2" x="408" y="63" width="26" height="2"></rect>
                                <rect id="Rectangle3" x="408" y="70" width="26" height="2"></rect>
                            </g>
                        </g>
                    </svg>
                </a>
            </div>

            <div class="col-12 col-md-4 col-xl-4 d-none d-md-block text-right">
                <div class="logado">
                    <span>
                        Olá ${pessoa.nome} <img src="../assets/images/arrow-down.png" alt="">
                    </span>
                </div>
            </div>
        </div>
    </div>
</header>