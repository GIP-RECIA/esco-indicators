<%@ include file="/WEB-INF/jsp/header.jsp"%>

<%@ include file="/WEB-INF/jsp/include/common/title-and-menu.jsp"%>

<div id="ariadneThread">
    <hr/>
        <p>
            <spring:message code="menu.home"/></a>
        </p>
    <hr/>
</div>

<br/>
<div id="welcomeMessage">

    <p><spring:message code="welcome.welcomePart1"/></p>
 
    <h1>
        <spring:message code="welcome.welcomeTitle1"/>
    </h1>
    
    <p><spring:message code="welcome.welcomePart2"/></p>
        
    <p><spring:message code="welcome.welcomePart3"/></p>
    
    <h2>
        <img src="<c:url value='/img/accounts-activations.png'/>"/>
        <spring:message code="welcome.accountActivationTab"/>
    </h2>
    <p>
        <ul>
            <li><spring:message code="welcome.accountActivationTabLi1"/></li>
            <li><spring:message code="welcome.accountActivationTabLi2"/></li>
        </ul>       
    </p>
    
    <h2>
        <img src="<c:url value='/img/services.png'/>"/>
        <spring:message code="welcome.servicesTab"/>
    </h2>
    <p>
        <ul>
            <li><spring:message code="welcome.servicesTabLi1"/></li>
            <li><spring:message code="welcome.servicesTabLi2"/></li>
        </ul>       
    </p>
    
    <p class="lastWords"><spring:message code="welcome.welcomePart4"/></p>
    
    <p><spring:message code="welcome.welcomePart5"/></p>
</div>

<%@ include file="/WEB-INF/jsp/footer.jsp"%>
