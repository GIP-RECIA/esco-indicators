<%@ include file="/WEB-INF/jsp/header.jsp"%>
<%@ page import="java.util.*" %>

<%-- #################################################### --%>
<%-- VARIABLES SETTINGS --%>
<%-- #################################################### --%>

<c:set var="monitoring" value="true" />

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
            <%-- #################################################### --%>
            <%-- SUM ON COUNTIES ? --%>
            <%-- #################################################### --%>
            <%-- If the sum on counties has not been asked --%>
            <c:choose>
                <c:when test="${empty sumOnCountiesItem}">
                    <c:set var="numEmptyHeaders" value="6" />
                    <th>
                        <spring:message code="result.table.detail" />
                    </th>
                </c:when>
                
                <c:otherwise>
                    <c:set var="numEmptyHeaders" value="5" />
                </c:otherwise>
            </c:choose>
            <%-- #################################################### --%>



            <%-- Establishlment part --%>
            <%@ include file="/WEB-INF/jsp/include/common/table-header-establishment.jsp"%>



            <%-- #################################################### --%>
            <%-- WEEKLY OR MONTHLY PERIODS ? --%>
            <%-- #################################################### --%>
            <c:choose>
                <%-- If the periods are weekly --%>
                <c:when test="${isWeekly}">
                    <c:forEach var="item" items="${statisticDataKeys}">
                        <th colspan="7">
                            <spring:message code="result.table.week" /> ${item.first} - ${item.second}
                        </th>
                    </c:forEach>
                </c:when>
            
                <%-- If the periods are weekly --%>
                <c:otherwise>
                    <c:forEach var="item" items="${statisticDataKeys}">
                        <th colspan="7">
                            <spring:message code="result.table.month.${item.first}" /> - ${item.second}
                        </th>
                    </c:forEach>
                </c:otherwise>
            </c:choose>
            <%-- #################################################### --%>
            
        </tr>        
        
        <%-- Headers : Second level --%>
        <tr>
            <c:forEach var="i" begin="1" end="${numEmptyHeaders}">
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
        
        <%-- Headers : Third level --%>
        <tr>
            <c:forEach var="i" begin="1" end="${numEmptyHeaders}">
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
        
        <%-- Headers : Fourth level --%>
        <tr>
            <c:forEach var="i" begin="1" end="${numEmptyHeaders}">
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
        
        <%-- Data of the table --%>
        <%@ include file="/WEB-INF/jsp/include/account/table-data-monitoring.jsp"%>
            
    </table>

<%-- Exports in CSV and Excel --%>
<%@ include file="/WEB-INF/jsp/include/common/result-exports.jsp"%>

<%@ include file="/WEB-INF/jsp/footer.jsp"%>
