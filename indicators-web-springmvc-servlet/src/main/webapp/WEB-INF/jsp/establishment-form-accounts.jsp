<%@ include file="/WEB-INF/jsp/header.jsp"%>


<%@ include file="/WEB-INF/jsp/include/common/title-and-menu.jsp"%>

<div id="ariadneThread">
    <hr/>
        <p>
            <a href="./welcome"><spring:message code="menu.home"/></a> > <spring:message code="menu.accounts-activations"/>
        </p>
    <hr/>
</div>

<form:form method="POST" commandName="accountactivationform">

    <%-- Monitoring type selection --%>
    <%@ include file="/WEB-INF/jsp/include/common/form-filter-monitoring-type.jsp"%>
    
    <%-- Period selection --%>
    <%@ include file="/WEB-INF/jsp/include/common/form-filter-period.jsp"%>
    
    <%-- Hidden fields for the establishment --%>
    <%@ include file="/WEB-INF/jsp/include/common/form-establishment-hidden-fields.jsp"%>
    
    <%-- Submission buttion --%>
    <%@ include file="/WEB-INF/jsp/include/common/form-submit.jsp"%>

    <%-- Empty div for displaying the datepicker below the input and not abov --%>
    <div id="datePickerSpace">
    </div>
    
</form:form>

<%-- SCRIPTS --%>
<script type="text/javascript" src="js/ajax.js"></script>
<script type="text/javascript" src="js/constants.js"></script>
<script type="text/javascript" src="js/common.js"></script>
<script type="text/javascript" src="js/datepicker.js"></script>
<script type="text/javascript" src="js/accounts-activations.js"></script>

<%@ include file="/WEB-INF/jsp/footer.jsp"%>
