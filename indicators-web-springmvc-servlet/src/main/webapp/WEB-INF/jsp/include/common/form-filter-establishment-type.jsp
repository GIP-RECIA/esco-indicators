<h2 class="filterTitle"><spring:message code="filter.title.establishmentType" /></h2>
<div class="filterOptions">
    <c:forEach var="item" items="${estbalishmentsTypesItems}">
        <spring:message code="${item.label}" var="i18n"/> 
        <form:checkbox cssClass="submit" value="${item.value}" label="${i18n}" disabled="${item.disabled}" path="establishmentsTypes" /> 
    </c:forEach>     
</div>
<div class="filterErrors">
    <form:errors path="establishmentsTypes" cssClass="error" />
</div>