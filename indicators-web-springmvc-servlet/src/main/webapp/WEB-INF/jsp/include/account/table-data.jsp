<c:forEach var="item" items="${tableRowsItems}">

    <%-- Result row --%>
    <tr>
        <%-- Establishment data --%>
        <%@ include file="/WEB-INF/jsp/include/common/table-data-establishment.jsp"%>
        
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
        </c:forEach>
        
        <%-- Global statistic data --%>
        <c:set var="statistic" value="${item.statisticDataByKey['GLOBAL_STATISTIC']}" /> 
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
    </tr>
    
</c:forEach>   
