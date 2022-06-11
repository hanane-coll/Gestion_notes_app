<%--
  Created by IntelliJ IDEA.
  User: highsierra
  Date: 6/9/22
  Time: 14:21
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<%@ taglib prefix="f" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="s" uri="http://www.springframework.org/security/tags"%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<jsp:include page="../fragments/userheader.jsp" />
<div class="container">

    <nav class="navbar navbar-expand-lg navbar-light bg-light">
        <div class="container-fluid">

            <jsp:include page="../fragments/profmenu.jsp" />

        </div>
    </nav>

    <div>
        <h3>Affichage des notes par modules</h3>
    </div>
    <div>


        <f:form action="affichageModule" method="POST">

        <div class="row">
            <div class="col">
                <label>CIN</label>
                <f:input path="cin" type="text" class="form-control"
                         placeholder="cin" />
                <f:errors path="cin" class="text-danger" />
            </div>

            <div class="col">
                <label>Nom</label>
                <f:input path="nom" type="text" class="form-control"
                         placeholder="nom" />
                <f:errors path="nom" class="text-danger" />
            </div>
            <f:hidden path="typePerson"  />
            <c:if test="${personModel.typePerson== PersonModel.TYPE_PROF}" var="variable">
                <div class="col">
                    <label>Spécialité</label>
                    <f:input path="specialite" type="text" class="form-control"
                             placeholder="Spécialité" />
                    <f:errors path="specialite" class="text-danger" />
                </div>
            </c:if>
            <c:if test="${personModel.typePerson== PersonModel.TYPE_STUDENT}" var="variable">
                <div class="col">
                    <label>CNE</label>
                    <f:input path="cne" type="text" class="form-control"
                             placeholder="cne" />
                    <f:errors path="cne" class="text-danger" />
                </div>
            </c:if>
            <c:if test="${personModel.typePerson== PersonModel.TYPE_CADRE_ADMIN}" var="variable">
                <div class="col">
                    <label>Grade</label>
                    <f:input path="grade" type="text" class="form-control"
                             placeholder="grade" />
                    <f:errors path="grade" class="text-danger" />
                </div>
            </c:if>
        </div>


        <div class="row">
            <div class="col">
                <label>Prénom</label>
                <f:input path="prenom" type="text" class="form-control"
                         placeholder="prenom" />
                <f:errors path="prenom" class="text-danger" />
            </div>

            <div class="col">
                <label>Télé</label>
                <f:input path="telephone" type="text" class="form-control"
                         placeholder="telephone" />
                <f:errors path="telephone" class="text-danger" />
            </div>
        </div>
        <div class="row">

            <div class="row">
                <div class="col">
                    <label>Email</label>
                    <f:input path="email" class="form-control" placeholder="Email" />
                    <f:errors path="email" class="text-danger" />
                </div>


            </div>



            <div class="row">
                <div class="col">
                    <label>Nom Arabe</label>
                    <f:input path="nomArabe" type="text" class="form-control"
                             placeholder="nomArabe" />
                    <f:errors path="nomArabe" class="text-danger" />
                </div>
                <div class="col">
                    <label>Prénom Arabe</label>
                    <f:input path="prenomArabe" type="text" class="form-control"
                             placeholder="prenomArabe" />
                    <f:errors path="prenomArabe" class="text-danger" />
                </div>


            </div>


            <div style="text-align: right">
                <button type="submit" class="btn btn-primary">Enregistrer</button>
                <button type="reset" class="btn btn-secondary">Annuler</button>
            </div>
            </f:form>
        </div>
    </div>
</div>