<c:forEach var="item" items="${tableRowsItems}">

    <!-- Result row -->
    <tr>
        <!-- Establishment data -->
        <%@ include file="/WEB-INF/jsp/include/common/table-data-establishment.jsp"%>
        
        <!-- Statistic data -->
        <c:forEach var="key" items="${statisticDataKeys}">
            <c:set var="statistic" value="${item.statisticDataByKey[key]}" /> 
            <td>
                ${statistic.totalAccountNumber}
            </td>
            <td>
                ${statistic.activeAccountNumber}
            </td>
            <td>
                ${statistic.percentageActiveAccount}
            </td>
            <td>
                ${statistic.numVisitorsBelowTreshold}
            </td>
            <td>
                ${statistic.percentageNumVisitorsBelowTreshold}
            </td>
            <td>
                ${statistic.numVisitorsAboveTreshold}
            </td>
            <td>
                ${statistic.percentageNumVisitorsAboveTreshold}
            </td>
        </c:forEach>
        
        <!-- Global statistic data -->
        <c:set var="statistic" value="${item.statisticDataByKey['GLOBAL_STATISTIC']}" /> 
        <td>
            ${statistic.totalAccountNumber}
        </td>
        <td>
            ${statistic.activeAccountNumber}
        </td>
        <td>
            ${statistic.percentageActiveAccount}
        </td>
        <td>
            ${statistic.numVisitorsBelowTreshold}
        </td>
        <td>
            ${statistic.percentageNumVisitorsBelowTreshold}
        </td>
        <td>
            ${statistic.numVisitorsAboveTreshold}
        </td>
        <td>
            ${statistic.percentageNumVisitorsAboveTreshold}
        </td>
        <td>
            ${statistic.numVisits}
        </td>
    <tr>
    
</c:forEach>   