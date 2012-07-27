<c:forEach var="item" items="${tableRowsItems}">

    <!-- Result row -->
    <tr>
        <!-- Establishment data -->
        <td>
            ${item.establishmentData.establishmentName}
        </td>
        <td>
            ${item.establishmentData.uai}
        </td>
        <td>
            ${item.establishmentData.countyNumber}
        </td>
        <td>
            ${item.establishmentData.establishmentType}
        </td>
        <td>
            ${item.establishmentData.lyceeType}
        </td>
        <td>
            ${item.totalAccountNumber}
        </td>
        <td>
            ${item.activeAccountNumber}
        </td>
        <td>
            ${item.percentageActiveAccount}
        </td>
        
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