<%@ page import="java.util.*" %>
<h2 class="filterTitle">
    <spring:message code="result.title.appliedFilter" />
</h2>

<div id="filters">

    <p>
        <spring:message code="filter.title.monitoringType" /> : <spring:message code="${monitoringTypeItem}" />
    </p> 

    <p>
        <spring:message code="filter.title.establishmentType" /> :
            <spring:message code="${estbalishmentsTypesItems[0]}" />
            <c:forEach var="item" items="${estbalishmentsTypesItems}" begin="1" end="${fn:length(estbalishmentsTypesItems)}">
                + <spring:message code="${item}" />
            </c:forEach>
            
    </p>
    
    <p>
       <spring:message code="form.startDate.DEFAULT" /> : ${startDateItem}
    </p>
    
    <!-- #################################################### -->
    <!-- DEFINED END DATE ? -->
    <!-- #################################################### -->
    <c:if test="${not empty endDateItem}">
        <p>
           <spring:message code="form.endDate.DEFAULT" /> : ${endDateItem}
        </p>
    </c:if>
    <!-- #################################################### -->
    
    <p>
        <spring:message code="filter.title.userProfile" /> : 
        <spring:message code="${usersProfilesItems[0]}" />
        <c:forEach var="item" items="${usersProfilesItems}" begin="1" end="${fn:length(usersProfilesItems)}">
            + <spring:message code="${item}" />
        </c:forEach>
    </p>
    
    <p>
        <!-- #################################################### -->
        <!-- SUM ON COUNTIES ? -->
        <!-- #################################################### -->
        <!-- If the sum on counties has been asked -->
        <c:if test="${not empty sumOnCountiesItem}">
            <spring:message code="filter.title.county" /> : <spring:message code="filter.title.sumOnCounties" />
        </c:if>
        <!-- #################################################### -->
        <c:if test="${empty sumOnCountiesItem}">
            <spring:message code="filter.title.county" /> : <spring:message code="${countyItem}" />
        </c:if>
    </p>    

    <!-- #################################################### -->
    <!-- DEFINED LYCEES TYPES ? -->
    <!-- #################################################### -->
    <c:if test="${not empty lyceesTypesItems}">
        <p>
            <spring:message code="filter.title.lyceeType" /> :
            <spring:message code="${lyceesTypesItems[0]}" />
            <c:forEach var="item" items="${lyceesTypesItems}" begin="1" end="${fn:length(lyceesTypesItems)}">
                + <spring:message code="${item}" />
            </c:forEach>
        </p>
    </c:if>
    <!-- #################################################### -->
    
    <!-- #################################################### -->
    <!-- DEFINED LA TYPES ? -->
    <!-- #################################################### -->
    <c:if test="${not empty laTypesItems}">
        <p>
            <spring:message code="filter.title.laType" /> :
            <spring:message code="${laTypesItems[0]}" />
            <c:forEach var="item" items="${laTypesItems}" begin="1" end="${fn:length(laTypesItems)}">
                + <spring:message code="${item}" />
            </c:forEach>
        </p>
    </c:if>
    <!-- #################################################### -->
    
</div>