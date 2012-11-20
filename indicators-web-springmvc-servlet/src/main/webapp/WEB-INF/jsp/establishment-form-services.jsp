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

    <%-- Period selection --%>
    <%@ include file="/WEB-INF/jsp/include/common/form-filter-period.jsp"%>
    
    <%-- Services selection --%>
    <h2 class="filterTitle"><spring:message code="filter.title.service" /></h2>
    <%@ include file="/WEB-INF/jsp/include/service/lists-services.jsp"%>
    
    <%-- Hidden fields for the establishment --%>
    <%@ include file="/WEB-INF/jsp/include/common/form-establishment-hidden-fields.jsp"%>
    
    <%-- Submission button --%>
    <%@ include file="/WEB-INF/jsp/include/common/form-submit.jsp"%>
</form:form>

<%-- SCRIPTS --%>
<script type="text/javascript" src="js/ajax.js"></script>
<script type="text/javascript" src="js/constants.js"></script>
<script type="text/javascript" src="js/common.js"></script>
<script type="text/javascript" src="js/datepicker.js"></script>
<script type="text/javascript" src="js/services.js"></script>
<script type="text/javascript" src="js/spin.min.js"></script>

<%@ include file="/WEB-INF/jsp/footer.jsp"%>
