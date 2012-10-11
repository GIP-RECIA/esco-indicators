<%@ include file="/WEB-INF/jsp/header.jsp"%>

<h1><spring:message code="header.title"/></h1>

<%@ include file="/WEB-INF/jsp/include/common/user.jsp"%>

<div id="ariadneThread">
    <hr/>
        <p>
            <a href="./welcome"><spring:message code="menu.home"/></a> > <spring:message code="menu.accounts-activations"/>
        </p>
    <hr/>
</div>

<form:form method="POST" commandName="accountactivationform">

    <!-- Monitoring type selection -->
    <%@ include file="/WEB-INF/jsp/include/common/form-filter-monitoring-type.jsp"%>
    
    <!-- Establishment type selection -->
    <%@ include file="/WEB-INF/jsp/include/common/form-filter-establishment-type.jsp"%>
    
    <!-- Period selection -->
    <%@ include file="/WEB-INF/jsp/include/common/form-filter-period.jsp"%>
        
    <!-- Others filters -->
    <%@ include file="/WEB-INF/jsp/include/common/form-filter-other.jsp"%>
    
    <!-- Establishment selection -->
    <%@ include file="/WEB-INF/jsp/include/common/form-filter-establishment.jsp"%>
    
    <input type="submit">
</form:form>

<%@ include file="/WEB-INF/jsp/include/common/navigation.jsp"%>

<!-- SCRIPTS -->
<script type="text/javascript" src="js/ajax.js"></script>
<script type="text/javascript" src="js/constants.js"></script>
<script type="text/javascript" src="js/common.js"></script>
<script type="text/javascript" src="js/datepicker.js"></script>
<script type="text/javascript" src="js/accounts-activations.js"></script>

<%@ include file="/WEB-INF/jsp/footer.jsp"%>
