<h2 class="filterTitle"><spring:message code="filter.title.monitoringType" /></h2>
<div class="filterOptions">
    <c:forEach var="item" items="${monitoringTypeItems}">
        <spring:message code="${item.label}" var="i18n"/> 
        <form:radiobutton cssClass="submit" value="${item.value}" label="${i18n}" disabled="${item.disabled}" path="monitoringType" /> 
    </c:forEach>
</div>
<div class="filterErrors">
    <form:errors path="monitoringType" cssClass="error" />
</div>