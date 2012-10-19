<c:forEach var="item" items="${tableRowsItems}">

    <!-- Result row -->
    <tr>
        <!-- Establishment data -->
        <%@ include file="/WEB-INF/jsp/include/common/table-data-establishment.jsp"%>
        
        <!-- Accounts statistics -->
        <td>
            <c:out value="${item.totalAccountNumber}"/>
        </td>
        <td>
            <c:out value="${item.activeAccountNumber}"/>
        </td>
        <td>
           <c:out value=" ${item.percentageActiveAccount}"/>
        </td>
        
        <!-- Statistic data -->
        <c:forEach var="key" items="${statisticDataKeys}">
            <c:set var="statistic" value="${item.statisticDataByKey[key]}" /> 
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
            <td>
                <c:out value="${statistic.numVisits}"/>
            </td>
        </c:forEach>
    </tr>
    
</c:forEach>   