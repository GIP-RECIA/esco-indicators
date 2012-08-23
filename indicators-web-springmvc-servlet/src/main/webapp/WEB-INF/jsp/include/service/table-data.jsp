<c:forEach var="item" items="${tableRowsItems}">

    <!-- Result row -->
    <tr>
        <!-- Establishment data -->
        <%@ include file="/WEB-INF/jsp/include/common/table-data-establishment.jsp"%>
        
        <!-- Statistic data -->
        <c:forEach var="key" items="${statisticDataKeys}">
            <c:set var="statistic" value="${item.statisticDataByKey[key]}" /> 
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
        </c:forEach>
    <tr>
    
</c:forEach>   