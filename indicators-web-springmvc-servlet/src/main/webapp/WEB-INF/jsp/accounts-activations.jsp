<%@ include file="/WEB-INF/jsp/header.jsp"%>

<h1><spring:message code="header.title"/></h1>

<hr/>

<div id="mainMessage">

  <p><spring:message code="menu.accounts-activations"/></p>
  
</div>

<form:form method="POST" commandName="accountactivationform">

    <p><spring:message code="filter.title.monitoringType" /></p>
    <p>
        <c:forEach var="item" items="${monitoringTypeItems}">
            <form:radiobutton cssClass="submit" value="${item.value}" label="${item.label}" disabled="${item.disabled}" path="monitoringType" /> 
        </c:forEach>         
        <form:errors path="monitoringType" cssClass="error" />
    </p>
    
    <p><spring:message code="filter.title.establishmentType" /></p>
    <p>
        <c:forEach var="item" items="${estbalishmentsTypesItems}">
            <form:checkbox cssClass="submit" value="${item.value}" label="${item.label}" disabled="${item.disabled}" path="establishmentsTypes" /> 
        </c:forEach>     
        <form:errors path="establishmentsTypes" cssClass="error" />
    </p>
    
    <p><spring:message code="filter.title.period" /></p>
    <p>
        <form:input path="startDatePicker"  /> 
        <form:hidden id="startDate" path="startDate" />  
        <form:errors path="startDate" cssClass="error" />
        
        <form:input path="endDatePicker" />
        <form:hidden id="endDate" path="endDate" />  
        <form:errors path="endDate" cssClass="error" />
    </p>
    
    <p><spring:message code="filter.title.filter" /></p>
    <div>
    
        <p><spring:message code="filter.title.userProfile" /></p>
        <p>
            <c:forEach var="item" items="${usersProfilesItems}">
                <form:checkbox value="${item.value}" label="${item.label}" disabled="${item.disabled}" path="usersProfiles" /> 
            </c:forEach> 
            <form:errors path="usersProfiles" cssClass="error" />
        </p>
        
        <p><spring:message code="filter.title.county" /></p>
        <p>
            <form:select cssClass="submit" path="county">
                <c:forEach var="item" items="${countyItems}">
                   <form:option cssClass="submit" value="${item.value}" label="${item.label}" disabled="${item.disabled}" path="county" /> 
                </c:forEach>
            </form:select> 
            <form:errors path="county" cssClass="error" />
                        
            <c:forEach var="item" items="${sumOnCountiesItems}">
                <form:checkbox cssClass="submit" value="${item.value}" label="${item.label}" disabled="${item.disabled}" path="sumOnCounties" /> 
            </c:forEach> 
        </p>
        
        <p><spring:message code="filter.title.lyceeType" /></p>
        <p>
            <c:forEach var="item" items="${lyceesTypesItems}">
                   <form:checkbox cssClass="submit" value="${item.value}" label="${item.label}" disabled="${item.disabled}" path="lyceesTypes" /> 
            </c:forEach>
            <form:errors path="lyceesTypes" cssClass="error" />
        </p>
        
        <p><spring:message code="filter.title.laType" /></p>
        <p>
            <c:forEach var="item" items="${laTypesItems}">
                   <form:checkbox cssClass="submit" value="${item.value}" label="${item.label}" disabled="${item.disabled}" path="laTypes" /> 
            </c:forEach>
            <form:errors path="laTypes" cssClass="error" />
        </p>
    </div>
    
    <div>
        <table id="establishmentsList">
            <c:forEach var="establishment" items="${establishmentsItems}">
                <tr>
                   <td><form:checkbox path="establishments" value="${establishment.value}" /></td>
                   <td>${establishment.label}</td>
                <tr>
            </c:forEach>
        </table>
    </div>
    
    <input type="submit">
</form:form>

<div>
    <ul id="menu">
        <li><a href="/accounts-activations"><spring:message code="menu.accounts-activations"/></a></li>
        <li><a href="/services"><spring:message code="menu.services"/></a></li>
        <li><a href="/services-usages"><spring:message code="menu.services-usage"/></a></li>
    </ul> 
</div>

<!-- SCRIPTS -->
<script type="text/javascript" src="js/accounts-activations.js"></script>

<%@ include file="/WEB-INF/jsp/footer.jsp"%>
