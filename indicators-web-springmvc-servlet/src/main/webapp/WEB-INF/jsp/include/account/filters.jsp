<h2>
    <spring:message code="result.title.appliedFilter" />
</h2>

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