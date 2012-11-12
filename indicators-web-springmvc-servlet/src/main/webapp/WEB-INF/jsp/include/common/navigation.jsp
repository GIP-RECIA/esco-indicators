<div id="menu">
    <div>
        <a href="<c:url value='/welcome'/>">
            <p style="background-image: url(<c:url value='/img/home.png'/>);">
                <spring:message code='menu.home'/>
            </p>
        </a>
    </div>
    <div>
        <a href="<c:url value='/accounts-activations'/>">
            <p style="background-image: url(<c:url value='/img/accounts-activations.png'/>);">
                <spring:message code='menu.accounts-activations'/>
            </p>
        </a>
    </div>
    <div>
        <a href="<c:url value='/services'/>">
            <p style="background-image: url(<c:url value='/img/services.png'/>);">
                <spring:message code='menu.services'/>
            </p>
        </a>
    </div>
    <%-- Uncomment th following lines to see the item for services usage --%>
    <%-- <div> --%>
    <%--     <a href="<c:url value='/services-usage'/>"> --%>
    <%--         <p style="background-image: url(<c:url value='/img/services-usage.png'/>);"> --%>
    <%--             <spring:message code='menu.services-usage'/>" --%>
    <%--         </p> --%>
    <%--     </a> --%>
    <%-- </div> --%>
</div>

