<c:forEach var="item" items="${tableRowsItems}">

    <%-- First level row : User profile --%>
    <%@ include file="/WEB-INF/jsp/include/common/table-data-user-profile.jsp"%>
    
    <%-- Second level row : Statistics for each period --%>
    
    <%-- For each period --%>
    <c:forEach var="period" items="${statisticPeriodsItems}">
        <c:set var="periodRow" value="${item.statisticDataByKey[period]}" />
        <tr>            
            <%-- #################################################### --%>
            <%-- WEEKLY OR MONTHLY PERIODS ? --%>
            <%-- #################################################### --%>
            <%-- If the periods are weekly --%>
            <c:if test="${isWeekly}">
                <td>
                    <spring:message code="result.table.week" /> <c:out value="${period.first}"/> - <c:out value="${period.second}"/>
                </td>
            </c:if>
            
            <%-- If the periods are monthly --%>
            <c:if test="${!isWeekly}">
                <td>
                    <spring:message code="result.table.month.${period.first}" />  - <c:out value="${period.second}"/>
                </td>
            </c:if>
            <%-- #################################################### --%>
            
            <%-- Establishment data columns are empty --%>
            <c:forEach var="i" begin="1" end="${numDataInfos - 1}">
                <td></td>
            </c:forEach>
            
            <%-- Accounts statistics --%>
            <td>
                <c:out value="${periodRow.totalAccountNumber}"/>
            </td>
            <td>
                <c:out value="${periodRow.activeAccountNumber}"/>
            </td>
            <td>
                <fmt:formatNumber pattern="###.##" value="${periodRow.percentageActiveAccount}" />
            </td>
                    
            <%-- For each service --%>
            <c:forEach var="service" items="${statisticDataKeys}">
                <c:set var="statistic" value="${periodRow.statisticDataByKey[service]}" />
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

    
</c:forEach>   