<%@ include file="/WEB-INF/jsp/header.jsp"%>
<%@ page import="java.util.*" %>


<%-- #################################################### --%>
<%-- VARIABLES SETTINGS --%>
<%-- #################################################### --%>

<c:set var="numDataInfos" value="5" />

<%-- #################################################### --%>

<%@ include file="/WEB-INF/jsp/include/common/title-and-menu.jsp"%>

<div id="ariadneThread">
    <hr/>
        <p>
            <a href="./welcome"><spring:message code="menu.home"/></a> > 
            <a href="./services"><spring:message code="menu.services"/></a> >
            <spring:message code="menu.services"/>
        </p>
    <hr/>
</div>



<%-- Selected filters --%>
<%@ include file="/WEB-INF/jsp/include/common/result-filters.jsp"%>

<%-- Selected establishments --%>
<%@ include file="/WEB-INF/jsp/include/common/result-selected-establishments.jsp"%>
<div id="resultTableContainer">   
    <table id="resultTable" class="pretty">
        <%-- Headers : First level --%>
        <tr>
			<%-- #################################################### --%>
			<%-- SUM ON COUNTIES ? --%>
			<%-- #################################################### --%>
			<%-- If the sum on counties has not been asked --%>
			<c:if test="${empty sumOnCountiesItem}">
				<th>
					<spring:message code="result.table.detail" />
					<c:set var="numDataInfos" value="${numDataInfos + 1}" />
				</th>
			</c:if>
			<%-- #################################################### --%>

            <%-- Establishment part --%>
            <%@ include file="/WEB-INF/jsp/include/common/table-header-establishment-infos.jsp"%>

            <th colspan="3">
                <spring:message code="result.table.accountActivation" />
            </th>

            <c:forEach var="item" items="${wantedServicesItems}">
                <spring:message code="${item}" var="i18n"/> 
                <th colspan="5">
                    <c:out value="${i18n}"/>
                </th>
            </c:forEach>        
        </tr>        
        
        <%-- Headers : Second level --%>
        <tr>
			<c:forEach var="i" begin="1" end="${numDataInfos}">
                <th>
                    <%-- Detail + Establishment data --%>
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
        
        <%-- Headers : Third level --%>
        <tr>
			<c:forEach var="i" begin="1" end="${numDataInfos + 1}">
                <th>
                    <%-- Detail + Establishment data + total account --%>
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
                    <%-- NumVisit --%>
                </th>
            </c:forEach>

        </tr>
        
        <%-- Headers : Fourth level --%>
        <tr>
			<c:forEach var="i" begin="1" end="${numDataInfos + 3}">
                <th>
                    <%-- Detail + Establishment data + Accounts data --%>
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
                    <%-- NumVisit --%>
                </th>     
            </c:forEach>
        </tr>
        
        <%-- Data of the table --%>
        <%@ include file="/WEB-INF/jsp/include/service/table-data.jsp"%>
            
    </table>
</div>

<%-- Exports in CSV and Excel --%>
<%@ include file="/WEB-INF/jsp/include/common/result-exports.jsp"%>

<%@ include file="/WEB-INF/jsp/footer.jsp"%>
