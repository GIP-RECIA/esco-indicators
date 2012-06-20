<%@ include file="/WEB-INF/jsp/header.jsp"%>

<h1><spring:message code="header.title"/></h1>

<hr/>

<div id="mainMessage">

  <p><spring:message code="menu.accounts-activations"/></p>
  
</div>

<form:form method="POST" commandName="accountactivationform">

    <p><spring:message code="filter.title.monitoringType" /></p>
    <p>
        <form:radiobuttons itemValue="value" itemLabel="label" path="monitoringType" items="${monitoringTypeItems}"  />
        <form:errors path="monitoringType" cssClass="error" />
    </p>
    
    <p><spring:message code="filter.title.establishmentType" /></p>
    <p>
        <form:checkboxes cssClass="submit" itemValue="value" itemLabel="label" path="establishmentsTypes" items="${estbalishmentsTypesItems}"  />
        <form:errors path="establishmentsTypes" cssClass="error" />
    </p>
    
    <p><spring:message code="filter.title.period" /></p>
    <p>
        <form:input path="startDate" />   
        <form:errors path="startDate" cssClass="error" />
        
        <form:input path="endDate" />
        <form:errors path="endDate" cssClass="error" />
    </p>
    
    <p><spring:message code="filter.title.filter" /></p>
    <div>
    
        <p><spring:message code="filter.title.userProfile" /></p>
        <p>
            <form:checkboxes itemValue="value" itemLabel="label" path="usersProfiles" items="${usersProfilesItems}"  /> 
            <form:errors path="usersProfiles" cssClass="error" />
        </p>
        
        <p><spring:message code="filter.title.county" /></p>
        <p>
            <form:select cssClass="submit" path="county">
                <form:options itemValue="value" itemLabel="label" items="${countyItems}"  />
            </form:select> 
            <form:errors path="county" cssClass="error" />
            
            <form:checkbox cssClass="submit" path="sumOnCounties" /><spring:message code="form.sumOnCounties.DEFAULT" />
        </p>
        
        <p><spring:message code="filter.title.lyceeType" /></p>
        <p>
            <form:checkboxes cssClass="submit" itemValue="value" itemLabel="label" path="lyceesTypes" items="${lyceesTypesItems}"  /> 
            <form:errors path="lyceesTypes" cssClass="error" />
        </p>
        
        <p><spring:message code="filter.title.laType" /></p>
        <p>
            <form:checkboxes cssClass="submit" itemValue="value" itemLabel="label" path="laTypes" items="${laTypesItems}"  /> 
            <form:errors path="laTypes" cssClass="error" />
        </p>
    </div>
    
    <div>
        <table>
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
