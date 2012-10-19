<%-- Establishment data --%>

<%-- #################################################### --%>
<%-- SUM ON COUNTIES ? --%>
<%-- #################################################### --%>
<%-- If the sum on counties has been asked --%>
<c:choose>
    <c:when test="${not empty sumOnCountiesItem}">
        <td>
            <spring:message code="result.table.county.${item.establishmentData.countyNumber}" />
        </td>
        <td> </td>
        <td> 
            <c:out value="${item.establishmentData.countyNumber}"/>
        </td>
        <td> </td>
        <td> </td>
    </c:when>

    <c:otherwise>
        <td>
            <c:out value="${item.establishmentData.establishmentName}"/>
        </td>
        <td>
            <c:out value="${item.establishmentData.uai}"/>
        </td>
        <td>
            <c:out value="${item.establishmentData.countyNumber}"/>
        </td>
        <td>
            <c:out value="${item.establishmentData.establishmentType}"/>
        </td>
        <td>
            <c:out value="${item.establishmentData.lyceeType}"/>
        </td>
    </c:otherwise>
</c:choose>


<%-- #################################################### --%>