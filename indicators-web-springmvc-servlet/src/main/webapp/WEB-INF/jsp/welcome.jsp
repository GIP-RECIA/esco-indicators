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

  <p id="welcomePart"><spring:message code="welcome.welcomeString"/></p>
 
</div>

<form action="download-data-ajax" method="POST">
        Data : <input type="text" name="data"/>
        File Name : <input type="text" name="fileName" />
        <input type="submit" value="csv" />
</form>

<%@ include file="/WEB-INF/jsp/footer.jsp"%>
