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
    <tr>
    
</c:forEach>   