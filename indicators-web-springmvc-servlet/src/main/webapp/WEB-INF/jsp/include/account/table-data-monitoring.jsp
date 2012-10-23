<c:forEach var="item" items="${tableRowsItems}">

    <%-- Result row --%>
    <tr>
        <%-- #################################################### --%>
        <%-- DETAIL VIEW / SUM ON COUNTIES ? --%>
        <%-- #################################################### --%>
        <%-- If this is not a detail view and the sum on counties has not been asked --%>
        <c:if test="${(empty detail) && (empty sumOnCountiesItem)}">
            <td>
                <a href="accounts-activations-monitoring-detail?uai=${item.establishmentData.uai}">
                    <spring:message code="result.table.detail" />
                </a>
            </td>
            

        </c:if>
        
        <c:choose>
            <c:when test="${empty detail}">        
                <%-- Establishment data --%>
                <%@ include file="/WEB-INF/jsp/include/common/table-data-establishment.jsp"%>
            </c:when>
        
            <%-- Else : This is a detail view --%>
            <c:otherwise>
                <td>
                    <spring:message code="${i18nUsersProfiles[item.userProfile]}" />
                </td>
            </c:otherwise>
        </c:choose>
        <%-- #################################################### --%>
        
        
        <%-- Statistic data --%>
        <c:forEach var="key" items="${statisticDataKeys}">
            <c:set var="statistic" value="${item.statisticDataByKey[key]}" /> 
            <td>
                <c:out value="${statistic.totalAccountNumber}"/>
            </td>
            <td>
                <c:out value="${statistic.activeAccountNumber}"/>
            </td>
            <td>
                <c:out value="${statistic.percentageActiveAccount}"/>
            </td>
            <td>
                <c:out value="${statistic.numVisitorsBelowTreshold}"/>
            </td>
            <td>
                <c:out value="${statistic.percentageNumVisitorsBelowTreshold}"/>
            </td>
            <td>
                <c:out value="${statistic.numVisitorsAboveTreshold}"/>
            </td>
            <td>
                <c:out value="${statistic.percentageNumVisitorsAboveTreshold}"/>
            </td>
        </c:forEach>
        
    </tr>
    
</c:forEach>   