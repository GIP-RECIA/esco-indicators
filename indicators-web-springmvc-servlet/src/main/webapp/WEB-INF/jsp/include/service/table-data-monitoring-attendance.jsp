<c:forEach var="item" items="${tableRowsItems}">

    <!-- First level row : Establishment Data -->
    <%@ include file="/WEB-INF/jsp/include/common/table-data-establishment.jsp"%>
    
    <!-- Second level row : Statistics for each period -->
    
    <!-- For each period -->
    <c:forEach var="period" items="${statisticPeriodsItems}">
        <c:set var="periodRow" value="${item.statisticDataByKey[period]}" />
        <tr>            
            <!-- #################################################### -->
            <!-- WEEKLY OR MONTHLY PERIODS ? -->
            <!-- #################################################### -->
            <!-- If the periods are weekly -->
            <c:if test="${isWeekly}">
                <td>
                    <spring:message code="result.table.week" /> ${period.first} - ${period.second}
                </td>
            </c:if>
            
            <!-- If the periods are monthly -->
            <c:if test="${!isWeekly}">
                <td>
                    <spring:message code="result.table.month.${period.first}" />  - ${period.second}
                </td>
            </c:if>
            <!-- #################################################### -->
            
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