<%@ include file="/WEB-INF/jsp/header.jsp"%>

<h1><spring:message code="header.title"/></h1>

<hr/>

<div id="mainMessage">

  <p><spring:message code="menu.accounts-activations"/></p>
  
</div>

<div>
    <ul id="menu">
        <li><a href="/accounts-activations"><spring:message code="menu.accounts-activations"/></a></li>
        <li><a href="/services"><spring:message code="menu.services"/></a></li>
        <li><a href="/services-usages"><spring:message code="menu.services-usage"/></a></li>
    </ul> 
</div>

<%@ include file="/WEB-INF/jsp/footer.jsp"%>
