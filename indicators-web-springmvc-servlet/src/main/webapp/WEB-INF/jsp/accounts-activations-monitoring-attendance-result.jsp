<%@ include file="/WEB-INF/jsp/header.jsp"%>
<%@ page import="java.util.*" %>

<!-- #################################################### -->
<!-- VARIABLES SETTINGS -->
<!-- #################################################### -->

<c:set var="monitoring" value="true" />

<!-- #################################################### -->

<h1><spring:message code="header.title"/></h1>

<hr/>

<div id="mainMessage">

  <p><spring:message code="menu.accounts-activations"/></p>
  
</div>

<!-- Selected filters -->
<%@ include file="/WEB-INF/jsp/include/account/filters.jsp"%>

<h2>
    <spring:message code="result.title.selectedEstablishment" />
</h2>
   
    <table>
        <!-- Headers : First level -->
        <tr>
            <!-- #################################################### -->
            <!-- SUM ON COUNTIES ? -->
            <!-- #################################################### -->
            <!-- If the sum on counties has not been asked -->
            <c:if test="${empty sumOnCountiesItem}">
                <th>
                    <spring:message code="result.table.detail" />
                </th>
            </c:if>
            <!-- #################################################### -->

            <!-- Establishlment part -->
            <%@ include file="/WEB-INF/jsp/include/common/table-header-establishment.jsp"%>

            <!-- #################################################### -->
            <!-- WEEKLY OR MONTHLY PERIODS ? -->
            <!-- #################################################### -->
            <!-- If the periods are weekly -->
            <c:if test="${isWeekly}">
                <c:forEach var="item" items="${statisticDataKeys}">
                    <th colspan="7">
                        <spring:message code="result.table.week" /> ${item.first} - ${item.second}
                    </th>
                </c:forEach>
            </c:if>
            
            <!-- If the periods are weekly -->
            <c:if test="${!isWeekly}">
                <c:forEach var="item" items="${statisticDataKeys}">
                    <th colspan="7">
                        <spring:message code="result.table.month.${item.first}" /> - ${item.second}
                    </th>
                </c:forEach>
            </c:if>
            <!-- #################################################### -->
            
        </tr>        
        
        <!-- Headers : Second level -->
        <tr>
            <c:forEach var="i" begin="1" end="6">
                <th>
                </th>
            </c:forEach>
            
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
            </c:forEach>
        </tr>
        
        <!-- Headers : Third level -->
        <tr>
            <c:forEach var="i" begin="1" end="6">
                <th>
                </th>
            </c:forEach>
            
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
            </c:forEach>

        </tr>
        
        <!-- Headers : Fourth level -->
        <tr>
            <c:forEach var="i" begin="1" end="6">
                <th>
                </th>
            </c:forEach>
            
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
            </c:forEach>
        </tr>
        
        <!-- Data of the table -->
        <%@ include file="/WEB-INF/jsp/include/account/table-data-monitoring.jsp"%>
            
    </table>


<%@ include file="/WEB-INF/jsp/include/common/navigation.jsp"%>

<!-- SCRIPTS -->
<script type="text/javascript" src="js/result.js"></script>

<%@ include file="/WEB-INF/jsp/footer.jsp"%>
