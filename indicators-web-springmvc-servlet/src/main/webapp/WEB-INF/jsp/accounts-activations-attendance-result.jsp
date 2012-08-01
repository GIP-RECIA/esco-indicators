<%@ include file="/WEB-INF/jsp/header.jsp"%>
<%@ page import="java.util.*" %>


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
            <th>
                <spring:message code="result.table.establishment" />
            </th>
            <th>
                <spring:message code="result.table.rne" />
            </th>
            <th>
                <spring:message code="result.table.county" />
            </th>
            <th>
                <spring:message code="result.table.establishmentType" />
            </th>
            <th>
                <spring:message code="result.table.lyceeType" />
            </th>

            <c:forEach var="item" items="${usersProfilesItems}">
                <spring:message code="${item}" var="i18n"/> 
                <th colspan="7">
                    ${i18n}
                </th>
            </c:forEach>        
            
            <!-- Global statistic -->
            <th colspan="8">
                <spring:message code="result.table.total" />
            </th>
        </tr>        
        
        <!-- Headers : Second level -->
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
            
            <!-- For global stats -->
            <th>
                <spring:message code="result.table.numVisit" />
            </th>
        </tr>
        
        <!-- Headers : Third level -->
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

            <!-- Global statistic -->
            <th></th>
        </tr>
        
        <!-- Headers : Fourth level -->
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
            
            <!-- Global statistic -->
            <th></th>
        
        </tr>
        
        <!-- Data of the table -->
        <%@ include file="/WEB-INF/jsp/include/account/table-data.jsp"%>
            
    </table>


<div>
    <ul id="menu">
        <li><a href="/accounts-activations"><spring:message code="menu.accounts-activations"/></a></li>
        <li><a href="/services"><spring:message code="menu.services"/></a></li>
        <li><a href="/services-usages"><spring:message code="menu.services-usage"/></a></li>
    </ul> 
</div>

<!-- SCRIPTS -->
<script type="text/javascript" src="js/result.js"></script>

<%@ include file="/WEB-INF/jsp/footer.jsp"%>
