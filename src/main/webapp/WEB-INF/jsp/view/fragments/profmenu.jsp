<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="f" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="th" uri="http://www.springframework.org/tags/form" %>
<div class="collapse navbar-collapse" id="navbarNav">
    <ul class="navbar-nav">
        <li class="nav-item">
            <a class="nav-link active" aria-current="page"
               href="${pageContext.request.contextPath}/prof/showHome">Home</a>
        </li>

        <li class="nav-item dropdown"><a class="nav-link dropdown-toggle" href="#" id="navbarScrollingDropdown" role="button"
                                         data-bs-toggle="dropdown" aria-expanded="false">Affichage des notes</a>
            <ul class="dropdown-menu" aria-labelledby="navbarScrollingDropdown">
                <li class="dropdown-item"><a class="nav-link"
                                             href="${pageContext.request.contextPath}/selectModule">Par module</a></li>
                <li class="dropdown-item"><a class="nav-link"
                                             href="${pageContext.request.contextPath}/affichageNiveau">Par niveau</a></li>
            </ul>
        </li>

        <li class="nav-item dropdown"><a class="nav-link dropdown-toggle" href="#" id="navbarScrollingDropdown" role="button" data-bs-toggle="dropdown" aria-expanded="false">Notes par module</a>
            <ul class="dropdown-menu" aria-labelledby="navbarScrollingDropdown">
                <li class="dropdown-item"><a class="nav-link"
                                             href="${pageContext.request.contextPath}/prof/showForm">Saisie des notes</a></li>
                <li class="dropdown-item"><a class="nav-link"
                                             href="${pageContext.request.contextPath}/admin/exportPersons">Afficher les notes</a></li>
            </ul>
        </li>


        <li class="nav-item">
            <f:form action="${pageContext.request.contextPath}/logout" method="POST">
                <button type="submit" class="btn btn-link">logout</button>
            </f:form></li>

    </ul>
</div>


