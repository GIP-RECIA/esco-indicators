<c:forEach var="item" items="${tableRowsItems}">

    <!-- Result row -->
    <tr>
        <!-- #################################################### -->
        <!-- DETAIL VIEW / SUM ON COUNTIES ? -->
        <!-- #################################################### -->
        <!-- If this is not a detail view and the sum on counties has not been asked -->
        <c:if test="${(empty detail) && (empty sumOnCountiesItem)}">
            <td>
                <a href="accounts-activations-monitoring-detail?uai=${item.establishmentData.uai}">
                    <spring:message code="result.table.detail" />
                </a>
            </td>
            

        </c:if>
        
        <c:if test="${empty detail}">
            <!-- Establishment data -->
            <%@ include file="/WEB-INF/jsp/include/common/table-data-establishment.jsp"%>
        </c:if>
        
        <!-- Else : This is a detail view -->
        <c:if test="${not empty detail}">
            <td>
                <spring:message code="${i18nUsersProfiles[item.userProfile]}" />
            </td>
        </c:if>
        <!-- #################################################### -->
        
        
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