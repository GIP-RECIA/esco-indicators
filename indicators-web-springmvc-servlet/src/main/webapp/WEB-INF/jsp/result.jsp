<%@ include file="/WEB-INF/jsp/header.jsp"%>

<h1><spring:message code="header.title"/></h1>

<hr/>

<div id="mainMessage">

  <p><spring:message code="menu.accounts-activations"/></p>
  
</div>

    <h1>Well done, you have your result !</h1>

<div id="filters">

    <p>
        <spring:message code="filter.title.monitoringType" /> : <spring:message code="${monitoringTypeItem}" />
    </p> 

    <p>
        <spring:message code="filter.title.establishmentType" /> : 
        <c:forEach var="item" items="${estbalishmentsTypesItems}">
            <spring:message code="${item}" />,
        </c:forEach>     
    </p>
    
    <p>
       <spring:message code="form.startDate.DEFAULT" /> : ${startDateItem}
    </p>
    <p>
       <spring:message code="form.endDate.DEFAULT" /> : ${endDateItem}
    </p>
    
    <p>
        <spring:message code="filter.title.userProfile" /> : 
        <c:forEach var="item" items="${usersProfilesItems}">
            <spring:message code="${item}" />,
        </c:forEach>     
    </p>
    
    <p>
        <spring:message code="filter.title.county" /> : <spring:message code="${countyItem}" />
    </p>    

    <p>
        <spring:message code="filter.title.lyceeType" /> :
        <c:forEach var="item" items="${lyceesTypesItems}">
            <spring:message code="${item}" />,
        </c:forEach>  
    </p>
    
    <p>
        <spring:message code="filter.title.laType" /> :
        <c:forEach var="item" items="${laTypesItems}">
            <spring:message code="${item}" />,
        </c:forEach>  
    </p>    
    
</div>


<div>
    <ul id="menu">
        <li><a href="/accounts-activations"><spring:message code="menu.accounts-activations"/></a></li>
        <li><a href="/services"><spring:message code="menu.services"/></a></li>
        <li><a href="/services-usages"><spring:message code="menu.services-usage"/></a></li>
    </ul> 
</div>

<!-- SCRIPTS -->

<%@ include file="/WEB-INF/jsp/footer.jsp"%>
