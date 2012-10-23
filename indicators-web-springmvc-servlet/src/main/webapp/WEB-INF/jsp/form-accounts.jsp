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
    
    <%-- Establishment type selection --%>
    <%@ include file="/WEB-INF/jsp/include/common/form-filter-establishment-type.jsp"%>
    
    <%-- Period selection --%>
    <%@ include file="/WEB-INF/jsp/include/common/form-filter-period.jsp"%>
        
    <%-- Others filters --%>
    <%@ include file="/WEB-INF/jsp/include/common/form-filter-other.jsp"%>
    
    <%-- Establishment selection --%>
    <%@ include file="/WEB-INF/jsp/include/common/form-filter-establishment.jsp"%>
    
    <%-- Select all establishments button --%>
    <%@ include file="/WEB-INF/jsp/include/common/form-select-all-establishments.jsp"%>
    
    <%-- Submission buttion --%>
    <%@ include file="/WEB-INF/jsp/include/common/form-submit.jsp"%>
</form:form>


<%-- SCRIPTS --%>
<script type="text/javascript" src="js/ajax.js"></script>
<script type="text/javascript" src="js/constants.js"></script>
<script type="text/javascript" src="js/common.js"></script>
<script type="text/javascript" src="js/datepicker.js"></script>
<script type="text/javascript" src="js/accounts-activations.js"></script>

<%@ include file="/WEB-INF/jsp/footer.jsp"%>
