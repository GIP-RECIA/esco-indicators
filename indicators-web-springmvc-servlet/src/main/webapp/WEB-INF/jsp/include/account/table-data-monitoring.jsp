<c:forEach var="item" items="${tableRowsItems}">

    <%-- Result row --%>
    <tr>
        <%-- #################################################### --%>
        <%-- DETAIL VIEW ? --%>
        <%-- #################################################### --%>
        <%-- If this is not a detail view --%>
        <c:if test="${empty detail}">
            <td>
                <%-- #################################################### --%>
                <%-- ESTABLISHMENT / COUNTY DETAIL LINK ? --%>
                <%-- #################################################### --%>
                <c:choose>
                    <%-- Establishment detail link --%>
                    <c:when test="${empty sumOnCountiesItem}">
                        <a href="accounts-activations-monitoring-detail?uai=${item.establishmentData.uai}">
                            <spring:message code="result.table.detail" />
                        </a>
                    </c:when>
                    
                    <%-- County detail link --%>
                    <c:otherwise>
                        <a href="accounts-activations-monitoring-detail?county=${item.establishmentData.countyNumber}">
                            <spring:message code="result.table.detail" />
                        </a>
                    </c:otherwise>
                </c:choose>
                <%-- #################################################### --%>
            </td>
        </c:if>
        <%-- #################################################### --%>
        
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
                <fmt:formatNumber pattern="###.##" value="${statistic.percentageActiveAccount}" />
            </td>
            <td>
                <c:out value="${statistic.numVisitorsBelowTreshold}"/>
            </td>
            <td>
                <fmt:formatNumber pattern="###.##" value="${statistic.percentageNumVisitorsBelowTreshold}" />
            </td>
            <td>
                <c:out value="${statistic.numVisitorsAboveTreshold}"/>
            </td>
            <td>
                <fmt:formatNumber pattern="###.##" value="${statistic.percentageNumVisitorsAboveTreshold}" />
            </td>
            <td>
                <c:out value="${statistic.numVisits}"/>
            </td>
        </c:forEach>
        
    </tr>
    
</c:forEach>   