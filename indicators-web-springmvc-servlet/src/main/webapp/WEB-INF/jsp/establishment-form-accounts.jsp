<%@ include file="/WEB-INF/jsp/header.jsp"%>


<%@ include file="/WEB-INF/jsp/include/common/title-and-menu.jsp"%>

<form:form method="POST" commandName="accountactivationform">

    <%-- Monitoring type selection --%>
    <%@ include file="/WEB-INF/jsp/include/common/form-filter-monitoring-type.jsp"%>
    
    <%-- Period selection --%>
    <%@ include file="/WEB-INF/jsp/include/common/form-filter-period.jsp"%>
    
    <%-- Hidden fields for the establishment --%>
    <%@ include file="/WEB-INF/jsp/include/common/form-establishment-hidden-fields.jsp"%>
        
    <%-- Submission buttion --%>
    <%@ include file="/WEB-INF/jsp/include/common/form-submit.jsp"%>
</form:form>

<%-- SCRIPTS --%>
<script type="text/javascript" src="js/constants.js"></script>
<script type="text/javascript" src="js/common.js"></script>
<script type="text/javascript" src="js/datepicker.js"></script>

<%@ include file="/WEB-INF/jsp/footer.jsp"%>
