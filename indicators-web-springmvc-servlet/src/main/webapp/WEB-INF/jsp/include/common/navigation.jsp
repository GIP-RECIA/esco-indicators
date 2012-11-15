<div id="menu">
    <span>
        <a href="<c:url value='/welcome'/>">
            <span class="link" style="background-image: url(<c:url value='/img/home.png'/>);">
                <spring:message code='menu.home'/>
            </span>
        </a>
    </span>
    <span>
        <a href="<c:url value='/accounts-activations'/>">
            <span class="link" style="background-image: url(<c:url value='/img/accounts-activations.png'/>);">
                <spring:message code='menu.accounts-activations'/>
            </span>
        </a>
    </span>
    <span>
        <a href="<c:url value='/services'/>">
            <span class="link" style="background-image: url(<c:url value='/img/services.png'/>);">
                <spring:message code='menu.services'/>
            </span>
        </a>
    </span>
    <%-- Uncomment th following lines to see the item for services usage --%>
    <%-- <span> --%>
    <%--     <a href="<c:url value='/services-usage'/>"> --%>
    <%--         <span class="link" style="background-image: url(<c:url value='/img/services-usage.png'/>);"> --%>
    <%--             <spring:message code='menu.services-usage'/>" --%>
    <%--         </span> --%>
    <%--     </a> --%>
    <%-- </span> --%>
</div>

