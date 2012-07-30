<c:forEach var="item" items="${tableRowsItems}">

    <!-- First level row : Establishment Data -->
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
    <tr>
    
    <!-- Second level row : Statistics for each period -->
    
    <!-- For each period -->
    <c:forEach var="period" items="${statisticPeriodsItems}">
        <c:set var="periodRow" value="${item.statisticDataByKey[period]}" />
        <tr>
        
            <td>
                ${period.first} - ${period.second}
            </td>
            
            <!-- Establishment data columns are empty -->
            <c:forEach var="i" begin="1" end="4">
                <td></td>
            </c:forEach>
            
            <!-- Accounts statistics -->
            <td>
                ${periodRow.totalAccountNumber}
            </td>
            <td>
                ${periodRow.activeAccountNumber}
            </td>
            <td>
                ${periodRow.percentageActiveAccount}
            </td>
                    
            <!-- For each service -->
            <c:forEach var="service" items="${statisticDataKeys}">
                <c:set var="statistic" value="${periodRow.statisticDataByKey[service]}" />
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
        
        </tr> 
    </c:forEach>

    
</c:forEach>   