<%-- #################################################### --%>
<%-- MULTIPLE ESTABLISHMENTS UAI ? --%>
<%-- #################################################### --%>
<c:choose>

    <%-- Display a table when mutiple establishments are available --%>
    <c:when  test="${fn:length(establishmentsItems) > 1}">
        <h2 class="filterTitle"><spring:message code="filter.title.establishmentList" /></h2>
        <div id="establishmentsListContainer" class="filterOptions">
            <table id="userEstablishmentsList" class="tablesorter, pretty">
                <thead>
                    <tr>
                        <th></th>
                        <th><spring:message code="form.selectOneEstablishment" /></th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach var="item" items="${establishmentsNames}">
                        <tr>
                            <td>
                                <form:checkbox id="${item.value}" value="${item.value}" path="establishments" />
                            </td>
                            <td>
                                <form:label for="${item.value}" title="${item.label}" path="establishments">
                                    <c:out value="${item.label}"/>
                                </form:label>
                            </td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>
        </div>
        <div class="filterErrors">
            <form:errors path="establishments" cssClass="error" />
        </div>
    </c:when>

    <%-- Put the information in an hidden field when only one establishment is available --%>    
    <c:otherwise>
        <c:forEach var="item" items="${establishmentsItems}">
            <form:hidden value="${item}" path="establishments" /> 
        </c:forEach>
    </c:otherwise>
    
</c:choose>
<%-- #################################################### --%>

<c:forEach var="item" items="${establishmentsTypesItems}">
    <form:hidden value="establishmentType.${item.value}" path="establishmentsTypes" /> 
</c:forEach>

<c:forEach var="item" items="${countyItems}">
    <form:hidden value="county.COUNTY_${item.value}" path="county" /> 
</c:forEach>

<c:forEach var="item" items="${usersProfilesItems}">
    <form:hidden value="${item.value}" path="usersProfiles" /> 
</c:forEach>