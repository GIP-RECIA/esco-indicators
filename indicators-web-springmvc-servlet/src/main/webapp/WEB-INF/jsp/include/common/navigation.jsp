<div>
    <ul id="menu">
        <li>
            <a href="<c:url value='/welcome'/>">
                <img src="<c:url value='/img/home.png'/>" title="<spring:message code='menu.home'/>" />
            </a>
        </li>
        <li>
            <a href="<c:url value='/accounts-activations'/>">
                <img src="<c:url value='/img/accounts-activations.png'/>" title="<spring:message code='menu.accounts-activations'/>" />
            </a>
        </li>
        <li>
            <a href="<c:url value='/services'/>">
                <img src="<c:url value='/img/services.png'/>" title="<spring:message code='menu.services'/>" />
            </a>
        </li>
        <%-- Uncomment th following lines to see the item for services usage --%>
        <%-- <li> --%>
        <%--     <a href="<c:url value='/services-usage'/>"> --%>
        <%--         <img src="<c:url value='/img/services-usage.png'/>" title="<spring:message code='menu.services-usage'/>" /> --%>
        <%--     </a> --%>
        <%-- </li> --%>
    </ul> 
</div>

