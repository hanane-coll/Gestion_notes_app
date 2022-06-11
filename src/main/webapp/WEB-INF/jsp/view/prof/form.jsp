<%--
  Created by IntelliJ IDEA.
  User: highsierra
  Date: 6/9/22
  Time: 14:24
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<%@ taglib prefix="f" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="s" uri="http://www.springframework.org/security/tags"%>
<%@ taglib prefix="th" uri="http://www.springframework.org/tags/form" %>


<jsp:include page="../fragments/userheader.jsp" />
<div class="container">

    <nav class="navbar navbar-expand-lg navbar-light bg-light">
        <div class="container-fluid">

            <jsp:include page="../fragments/profmenu.jsp" />

        </div>
    </nav>

    <div>
        <h3>Prof home page</h3>
        <p>Hello and welcome to your application</p>

        <s:authorize access="isAuthenticated()">
            You are connected with:
            <s:authentication property="principal.username" /> <br>
            Your Email : <s:authentication property="principal.email" /><br>
            Your First Name : <s:authentication property="principal.firstName" /><br>
            Your Last name : <s:authentication property="principal.LastName" /><br>
        </s:authorize>
    </div>


    <f:form action="/prof/exportNoteModule" method="POST" modelAttribute="NotesModel">

    <div class="row">
        <div class="col">

            <label>Module</label>
            <select name="module">
                <c:forEach items="${listemodule}" var="module">

                    <p>${module.enseignant.idUtilisateur}</p>
                    <c:if test = "${module.enseignant.idUtilisateur == idEns}">
                        <option value="${module.idModule}">${module.titre}</option>
                    </c:if>
                </c:forEach>
            </select>
        </div>

            <div class="col">
                <label for="session">Session</label>
                <select id="session" class="form-control" name="session">
                    <option selected>Normale</option>
                    <option>Rattrapage</option>
                </select>
            </div>

    </div>
        <br/><br/>
        <input type="submit" value="Génerer le fichier de saisie" />
        </f:form>
    </div>








    <jsp:include page="../fragments/userfooter.jsp" />



