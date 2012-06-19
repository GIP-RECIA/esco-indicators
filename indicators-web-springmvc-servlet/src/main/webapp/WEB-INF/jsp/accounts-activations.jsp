<%@ include file="/WEB-INF/jsp/header.jsp"%>

<h1><spring:message code="header.title"/></h1>

<hr/>

<div id="mainMessage">

  <p><spring:message code="menu.accounts-activations"/></p>
  
</div>

<form:form method="POST" commandName="accountActivationForm">

    <p><spring:message code="filter.title.monitoringType" /></p>
    <p>
        <form:radiobuttons itemValue="value" itemLabel="label" path="monitoringType" items="${monitoringTypeItems}"  />
    </p>
    
    <p><spring:message code="filter.title.establishmentType" /></p>
    <p>
        <form:checkboxes itemValue="value" itemLabel="label" path="establishmentsTypes" items="${estbalishmentsTypesItems}"  /> 
    </p>
    
    <p><spring:message code="filter.title.period" /></p>
    <p>
        <form:input path="startDate" />   <form:input path="endDate" />
    </p>
    
    <p><spring:message code="filter.title.filter" /></p>
    <div>
    
        <p><spring:message code="filter.title.userProfile" /></p>
        <p>
            <form:checkboxes itemValue="value" itemLabel="label" path="usersProfiles" items="${usersProfilesItems}"  /> 
        </p>
        
        <p><spring:message code="filter.title.county" /></p>
        <p>
            <form:select path="county">
                <form:options itemValue="value" itemLabel="label" items="${countyItems}"  />
            </form:select> 
        
            <form:checkbox path="sumOnCounties" />
        </p>
        
        <p><spring:message code="filter.title.lyceeType" /></p>
        <p>
            <form:checkboxes itemValue="value" itemLabel="label" path="lyceesTypes" items="${lyceesTypesItems}"  /> 
        </p>
        
        <p><spring:message code="filter.title.laType" /></p>
        <p>
            <form:checkboxes itemValue="value" itemLabel="label" path="laTypes" items="${laTypesItems}"  /> 
        </p>
        
        
    </div>
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
