<%@ include file="/WEB-INF/jsp/header.jsp"%>

<h1><spring:message code="header.title"/></h1>

<hr/>

<div id="welcomeMessage">

  <p id="welcomePart"><spring:message code="welcome.welcomeString"/></p>
  
  <p> Welcome : ${username} </p>
  
</div>

<%@ include file="/WEB-INF/jsp/include/common/navigation.jsp"%>

<%@ include file="/WEB-INF/jsp/footer.jsp"%>
