<h2 class="filterTitle"><spring:message code="filter.title.establishmentList" /></h2>
<div id="establishmentsListContainer" class="filterOptions">
    <table id="establishmentsList" class="pretty">
        <c:forEach var="establishment" items="${establishmentsItems}">
            <tr>
               <td><form:checkbox path="establishments" value="${establishment.value}" /></td>
               <td><c:out value="${establishment.label}"/></td>
            <tr>
        </c:forEach>
    </table>
</div>
<div class="filterErrors">
    <form:errors path="establishments" cssClass="error" />
</div>