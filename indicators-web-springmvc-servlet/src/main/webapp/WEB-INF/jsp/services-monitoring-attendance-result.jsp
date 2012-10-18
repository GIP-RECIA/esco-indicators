<%@ include file="/WEB-INF/jsp/header.jsp"%>
<%@ page import="java.util.*" %>


<%@ include file="/WEB-INF/jsp/include/common/title-and-menu.jsp"%>

<div id="ariadneThread">
    <hr/>
        <p>
            <a href="./welcome"><spring:message code="menu.home"/></a> > 
            <a href="./services"><spring:message code="menu.services"/></a> >
            <spring:message code="menu.accounts-activations"/>
        </p>
    <hr/>
</div>


<!-- Selected filters -->
<%@ include file="/WEB-INF/jsp/include/common/result-filters.jsp"%>

<!-- Selected establishments -->
<%@ include file="/WEB-INF/jsp/include/common/result-selected-establishments.jsp"%>
   
    <table>
        <!-- Headers : First level -->
        <tr>

            <!-- Establishlment part -->
            <%@ include file="/WEB-INF/jsp/include/common/table-header-establishment.jsp"%>

            <th colspan="3">
                <spring:message code="result.table.accountActivation" />
            </th>

            <!-- Services -->
            <c:forEach var="item" items="${wantedServicesItems}">
                <spring:message code="${item}" var="i18n"/> 
                <th colspan="5">
                    <c:out value="${i18n}"/>
                </th>
            </c:forEach>        
        </tr>        
        
        <!-- Headers : Second level -->
        <tr>
            <c:forEach var="i" begin="1" end="5">
                <th>
                    <!-- Establishment data -->
                </th>
            </c:forEach>
            
            <th>
                <spring:message code="result.table.totalAccount" />
            </th>
            <th colspan="2">
                <spring:message code="result.table.activeAccount" />
            </th>
            
            
            <c:forEach var="item" items="${wantedServicesItems}">
                <th colspan="4">
                    <spring:message code="result.table.consultationFrequency" />
                </th>
                <th>
                    <spring:message code="result.table.numVisit" />
                </th>
            </c:forEach>
        </tr>
        
        <!-- Headers : Third level -->
        <tr>
            <c:forEach var="i" begin="1" end="6">
                <th>
                    <!-- Establishment data + total account -->
                </th>
            </c:forEach>
            
            <th>
                <spring:message code="result.table.num" />
            </th>
            <th>
                <spring:message code="result.table.percent" />
            </th>
            
            <c:forEach var="item" items="${wantedServicesItems}">
                <th colspan="2">
                    <spring:message code="result.table.maxTimes" arguments="4" /> 
                </th>
                <th colspan="2">
                    <spring:message code="result.table.minTimes" arguments="5" /> 
                </th>
                <th>
                    <!-- NumVisit -->
                </th>
            </c:forEach>

        </tr>
        
        <!-- Headers : Fourth level -->
        <tr>
            <c:forEach var="i" begin="1" end="8">
                <th>
                    <!-- Establishment data + accounts data -->
                </th>
            </c:forEach>
            
            <c:forEach var="item" items="${wantedServicesItems}">
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
                    <!-- NumVisit -->
                </th>     
            </c:forEach>
        </tr>
        
        <!-- Data of the table -->
        <%@ include file="/WEB-INF/jsp/include/service/table-data-monitoring-attendance.jsp"%>
            
    </table>

<!-- Form for CSV export -->
<%@ include file="/WEB-INF/jsp/include/common/result-csv-export.jsp"%>

<!-- SCRIPTS -->
<script type="text/javascript" src="js/result.js"></script>
<script type="text/javascript" src="js/table2csv.js"></script>
<script type="text/javascript" src="js/exports.js"></script>

<%@ include file="/WEB-INF/jsp/footer.jsp"%>
