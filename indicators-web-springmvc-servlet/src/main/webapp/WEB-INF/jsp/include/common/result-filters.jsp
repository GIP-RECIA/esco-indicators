<%@ page import="java.util.*" %>
<h2 class="filterTitle">
    <spring:message code="result.title.appliedFilter" />
</h2>

<div id="filters">

    <p>
        <span>
            <spring:message code="filter.title.monitoringType" /> : 
        </span>
        <span class="filterContent">
            <spring:message code="${monitoringTypeItem}" />
        </span>
    </p> 

    <p>
        <span>
            <spring:message code="filter.title.establishmentType" /> :
        <span>
        <span class="filterContent">
            <spring:message code="${estbalishmentsTypesItems[0]}" />
            <c:forEach var="item" items="${estbalishmentsTypesItems}" begin="1" end="${fn:length(estbalishmentsTypesItems)}">
                + <spring:message code="${item}" />
            </c:forEach>
        </span>
    </p>
    
    <p>
        <span>
            <spring:message code="form.startDate.DEFAULT" /> : 
        </span>
        <span class="filterContent">
            <c:out value="${startDateItem}"/>
        </span>
    </p>
    
    <%-- #################################################### --%>
    <%-- DEFINED END DATE ? --%>
    <%-- #################################################### --%>
    <c:if test="${not empty endDateItem}">
        <p>
            <span>
                <spring:message code="form.endDate.DEFAULT" /> : 
            </span>
            <span class="filterContent">
                <c:out value="${endDateItem}"/>
            </span>
        </p>
    </c:if>
    <%-- #################################################### --%>
    
    <p>
        <span>
            <spring:message code="filter.title.userProfile" /> : 
        </span>
        <span class="filterContent">
            <spring:message code="${usersProfilesItems[0]}" />
            <c:forEach var="item" items="${usersProfilesItems}" begin="1" end="${fn:length(usersProfilesItems)}">
                + <spring:message code="${item}" />
            </c:forEach>
        </span>
    </p>
    
    <p>
        <%-- #################################################### --%>
        <%-- SUM ON COUNTIES ? --%>
        <%-- #################################################### --%>
        <%-- If the sum on counties has been asked --%>
        <c:if test="${not empty sumOnCountiesItem}">
            <span>
                <spring:message code="filter.title.county" /> : 
            </span>
            <span class="filterContent">    
                <spring:message code="filter.title.sumOnCounties" />
            </span>
        </c:if>
        <%-- #################################################### --%>
        <c:if test="${empty sumOnCountiesItem}">
            <span>
                <spring:message code="filter.title.county" /> : 
            </span>
            <span class="filterContent">
                <spring:message code="${countyItem}" />
            </span>
        </c:if>
    </p>    

    <%-- #################################################### --%>
    <%-- DEFINED LYCEES TYPES ? --%>
    <%-- #################################################### --%>
    <c:if test="${not empty lyceesTypesItems}">
        <p>
            <span>
                <spring:message code="filter.title.lyceeType" /> :
            </span>
            <span class="filterContent">
                <spring:message code="${lyceesTypesItems[0]}" />
                <c:forEach var="item" items="${lyceesTypesItems}" begin="1" end="${fn:length(lyceesTypesItems)}">
                    + <spring:message code="${item}" />
                </c:forEach>
            </span>
        </p>
    </c:if>
    <%-- #################################################### --%>
    
    <%-- #################################################### --%>
    <%-- DEFINED LA TYPES ? --%>
    <%-- #################################################### --%>
    <c:if test="${not empty laTypesItems}">
        <p>
            <span>
                <spring:message code="filter.title.laType" /> :
            </span>
            <span class="filterContent">
                <spring:message code="${laTypesItems[0]}" />
                <c:forEach var="item" items="${laTypesItems}" begin="1" end="${fn:length(laTypesItems)}">
                    + <spring:message code="${item}" />
                </c:forEach>
            </span>
        </p>
    </c:if>
    <%-- #################################################### --%>
    
</div>