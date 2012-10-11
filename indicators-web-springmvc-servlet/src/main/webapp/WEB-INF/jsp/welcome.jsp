<%@ include file="/WEB-INF/jsp/header.jsp"%>

<h1><spring:message code="header.title"/></h1>

<%@ include file="/WEB-INF/jsp/include/common/user.jsp"%>

<div id="ariadneThread">
    <hr/>
        <p>
            <spring:message code="menu.home"/></a>
        </p>
    <hr/>
</div>

<br/>
<div id="welcomeMessage">

  <p id="welcomePart"><spring:message code="welcome.welcomeString"/></p>
 
</div>

<%@ include file="/WEB-INF/jsp/include/common/navigation.jsp"%>

<%@ include file="/WEB-INF/jsp/footer.jsp"%>
