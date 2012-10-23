<%@ include file="/WEB-INF/jsp/header.jsp"%>

<%@ include file="/WEB-INF/jsp/include/common/title-and-menu.jsp"%>

<div id="ariadneThread">
    <hr/>
        <p>
            <a href="./welcome"><spring:message code="menu.home"/></a> > <spring:message code="menu.services"/>
        </p>
    <hr/>
</div>

<form:form method="POST" commandName="serviceform">

    <%-- Monitoring type selection --%>
    <%@ include file="/WEB-INF/jsp/include/common/form-filter-monitoring-type.jsp"%>

    <%-- Establishment type selection --%>
    <%@ include file="/WEB-INF/jsp/include/common/form-filter-establishment-type.jsp"%>
    
    <%-- Period selection --%>
    <%@ include file="/WEB-INF/jsp/include/common/form-filter-period.jsp"%>
    
    <%-- Services selection --%>
    <h2 class="filterTitle"><spring:message code="filter.title.service" /></h2>
    <div>
        <p>
            <c:forEach var="item" items="${availableServicesItems}">
                <form:hidden id="${item.value}" value="${item.value}" path="availableServices"/>
            </c:forEach>
            
            <ul id="availableServicesList" class="connectedServices">
                <c:forEach var="item" items="${availableServicesItems}">
                    <spring:message code="${item.label}" var="i18n"/> 
                    <c:set var="itemId" value="${item.value}" />
                                        
                    <c:if test="${fn:contains(itemId, 'SUM')}">
                        <li class="ui-state-highlight sumService" id="${itemId}">
                            ${i18n}
                        </li>
                    </c:if>
                    <c:if test="${!fn:contains(itemId, 'SUM')}">
                        <li class="ui-state-default simpleService" id="${itemId}">
                            ${i18n}
                        </li>
                    </c:if>
                    
                     
                </c:forEach> 
            </ul>
            
            <ul id="wantedServicesList" class="connectedServices">
            </ul>
            
            <form:errors path="wantedServices" cssClass="error" />
        </p>
    </div>
    
    <div></div>
    
    <%-- Others filters --%>
    <%@ include file="/WEB-INF/jsp/include/common/form-filter-other.jsp"%>
    
    <%-- Establishment selection --%>
    <%@ include file="/WEB-INF/jsp/include/common/form-filter-establishment.jsp"%>    
    
    <%-- Submission buttion --%>
    <%@ include file="/WEB-INF/jsp/include/common/form-submit.jsp"%>
</form:form>

<%-- SCRIPTS --%>
<script type="text/javascript" src="js/ajax.js"></script>
<script type="text/javascript" src="js/constants.js"></script>
<script type="text/javascript" src="js/common.js"></script>
<script type="text/javascript" src="js/datepicker.js"></script>
<script type="text/javascript" src="js/services.js"></script>

<%@ include file="/WEB-INF/jsp/footer.jsp"%>
