<%@ include file="/WEB-INF/jsp/header.jsp"%>
<%@ page import="java.util.*" %>


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

            <%-- Establishlment part --%>
            <%@ include file="/WEB-INF/jsp/include/common/table-header-establishment.jsp"%>
            
            <c:forEach var="item" items="${usersProfilesItems}">
                <spring:message code="${item}" var="i18n"/> 
                <th colspan="7">
                    <c:out value="${i18n}"/>
                </th>
            </c:forEach>        
            
            <%-- Global statistic --%>
            <th colspan="8">
                <spring:message code="result.table.total" />
            </th>
        </tr>        
        
        <%-- Headers : Second level --%>
        <tr>
            <c:forEach var="i" begin="1" end="5">
                <th>
                </th>
            </c:forEach>
            <c:forEach var="i" begin="1" end="${fn:length(usersProfilesItems) + 1}">
                <th>
                    <spring:message code="result.table.totalAccount" />
                </th>
                <th colspan="2">
                    <spring:message code="result.table.activeAccount" />
                </th>            
                <th colspan="4">
                    <spring:message code="result.table.consultationFrequency" />
                </th>
            </c:forEach>
            
            <%-- For global stats --%>
            <th>
                <spring:message code="result.table.numVisit" />
            </th>
        </tr>
        
        <%-- Headers : Third level --%>
        <tr>
            <c:forEach var="i" begin="1" end="5">
                <th>
                </th>
            </c:forEach>
            
            <c:forEach var="i" begin="1" end="${fn:length(usersProfilesItems) + 1}">
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
            </c:forEach>

            <%-- Global statistic --%>
            <th></th>
        </tr>
        
        <%-- Headers : Fourth level --%>
        <tr>
            <c:forEach var="i" begin="1" end="5">
                <th>
                </th>
            </c:forEach>
            
            <c:forEach var="i" begin="1" end="${fn:length(usersProfilesItems) + 1}">
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
            </c:forEach>
            
            <%-- Global statistic --%>
            <th></th>
        
        </tr>
        
        <%-- Data of the table --%>
        <%@ include file="/WEB-INF/jsp/include/account/table-data.jsp"%>
            
    </table>

<%-- Exports in CSV and Excel --%>
<%@ include file="/WEB-INF/jsp/include/common/result-exports.jsp"%>


<%@ include file="/WEB-INF/jsp/footer.jsp"%>
