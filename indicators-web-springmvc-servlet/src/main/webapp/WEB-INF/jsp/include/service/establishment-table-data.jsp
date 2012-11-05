<c:forEach var="item" items="${tableRowsItems}">

    <%-- Result row --%>
    <tr>
        <%-- User profile --%>
        <td>
            <spring:message code="${i18nUsersProfiles[item.userProfile]}" />
        </td>
        
        <%-- Accounts statistics --%>
        <td>
            <c:out value="${item.totalAccountNumber}"/>
        </td>
        <td>
            <c:out value="${item.activeAccountNumber}"/>
        </td>
        <td>
           <fmt:formatNumber pattern="###.##" value="${item.percentageActiveAccount}" />
        </td>
        
        <%-- Statistic data --%>
        <c:forEach var="key" items="${statisticDataKeys}">
            <c:set var="statistic" value="${item.statisticDataByKey[key]}" /> 
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