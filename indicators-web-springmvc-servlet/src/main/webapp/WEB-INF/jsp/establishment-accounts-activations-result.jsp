<%@ include file="/WEB-INF/jsp/header.jsp"%>
<%@ page import="java.util.*" %>

<%-- #################################################### --%>
<%-- VARIABLES SETTINGS --%>
<%-- #################################################### --%>

<%-- #################################################### --%>

<%@ include file="/WEB-INF/jsp/include/common/title-and-menu.jsp"%>

<div id="ariadneThread">
    <hr/>
        <p>
            <a href="./welcome"><spring:message code="menu.home"/></a> > 
            <a href="./accounts-activations"><spring:message code="menu.accounts-activations"/></a> >
            <spring:message code="menu.accounts-activations"/>
        </p>
    <hr/>
</div>

<%-- Selected filters --%>
<%@ include file="/WEB-INF/jsp/include/common/result-filters.jsp"%>

<%-- Selected establishments --%>
<%@ include file="/WEB-INF/jsp/include/common/result-selected-establishments.jsp"%>
   
    <table id="resultTable" class="pretty">
        <%-- Headers : First level --%>
        <tr>
            <th>
                ${establishmentName}
            </th>
        
<%-- #################################################### --%>
<%-- PERIODIC VIEW ? --%>
<%-- #################################################### --%>
<c:if test="${fn:length(statisticDataKeys) > 1}">

            <%-- #################################################### --%>
            <%-- WEEKLY OR MONTHLY PERIODS ? --%>
            <%-- #################################################### --%>
            <c:choose>
                <%-- If the periods are weekly --%>
                <c:when test="${isWeekly}">
                    <c:forEach var="item" items="${statisticDataKeys}">
                        <th colspan="9">
                            <spring:message code="result.table.week" /> ${item.first} - ${item.second}
                        </th>
                    </c:forEach>
                </c:when>
            
                <%-- If the periods are weekly --%>
                <c:otherwise>
                    <c:forEach var="item" items="${statisticDataKeys}">
                        <th colspan="9">
                            <spring:message code="result.table.month.${item.first}" /> - ${item.second}
                        </th>
                    </c:forEach>
                </c:otherwise>
            </c:choose>
            <%-- #################################################### --%>
            
       </tr>
        
        <%-- Headers : Second level --%>
        <tr>
            <th>
                <%-- Establishment name --%>
            </th>
            
</c:if>
<%-- #################################################### --%>

            <c:forEach var="item" items="${statisticDataKeys}">
                <th>
                    <spring:message code="result.table.totalAccount" />
                </th>
                <th colspan="2">
                    <spring:message code="result.table.activeAccount" />
                </th>            
                <th colspan="4">
                    <spring:message code="result.table.consultationFrequency" />
                </th>
                <th>
                    <spring:message code="result.table.numVisit" />
                </th>
                <th>
                    <spring:message code="result.table.averageDurationTime" />
                </th>
            </c:forEach>
        </tr>
        
        <%-- Headers : Third level --%>
        <tr>
            <th>
                <%-- Establishment name --%>
            </th>
            
            <c:forEach var="item" items="${statisticDataKeys}">
                <th>
                </th>
                <th>
                   <spring:message code="result.table.num" /> 
                </th>            
                <th>
                   <spring:message code="result.table.percent" /> 
                </th>     
                <th colspan="2">
                    <spring:message code="result.table.maxTimes" arguments="4" /> 
                </th>
                <th colspan="2">
                    <spring:message code="result.table.minTimes" arguments="5" /> 
                </th>
                <th>
                    <%-- Num visits --%>
                </th>
                <th>
                    <%-- Average duration time --%>
                </th>
            </c:forEach>

        </tr>
        
        <%-- Headers : Fourth level --%>
        <tr>
            <th>
                <%-- Establishment name --%>
            </th>
            
            <c:forEach var="item" items="${statisticDataKeys}">
                <c:forEach var="i" begin="1" end="3">
                    <th>
                    </th>
                </c:forEach>
                
                <th>
                    <spring:message code="result.table.numVisitor" />
                </th>
                <th>
                    <spring:message code="result.table.percentTotalAccount" />
                </th>            
                <th>
                    <spring:message code="result.table.numVisitor" />
                </th>
                <th>
                    <spring:message code="result.table.percentTotalAccount" />
                </th>
                <th>
                    <%-- Num visits --%>
                </th>
                <th>
                    <%-- Average duration time --%>
                </th>
            </c:forEach>
        </tr>
        
        <%-- Data of the table --%>
        <%@ include file="/WEB-INF/jsp/include/account/establishment-table-data.jsp"%>
            
    </table>

<%-- Exports in CSV and Excel --%>
<%@ include file="/WEB-INF/jsp/include/common/result-exports.jsp"%>

<%@ include file="/WEB-INF/jsp/footer.jsp"%>
