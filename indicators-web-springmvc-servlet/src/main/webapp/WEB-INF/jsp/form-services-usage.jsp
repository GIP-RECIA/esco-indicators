<%@ include file="/WEB-INF/jsp/header.jsp"%>

<%@ include file="/WEB-INF/jsp/include/common/title-and-menu.jsp"%>

<div id="ariadneThread">
    <hr/>
        <p>
            <a href="./welcome"><spring:message code="menu.home"/></a> > <spring:message code="menu.services-usage"/>
        </p>
    <hr/>
</div>

<br/>
<p><spring:message code="error.underConstruction"/>


<%-- SCRIPTS --%>
<script type="text/javascript" src="js/ajax.js"></script>
<script type="text/javascript" src="js/constants.js"></script>
<script type="text/javascript" src="js/common.js"></script>
<script type="text/javascript" src="js/datepicker.js"></script>
<script type="text/javascript" src="js/services.js"></script>

<%@ include file="/WEB-INF/jsp/footer.jsp"%>
