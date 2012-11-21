<div class="submitArea">
    <%-- Form for CSV export --%>
    <%@ include file="/WEB-INF/jsp/include/common/result-csv-export.jsp"%>

    <%-- Form for Excel export --%>
    <%@ include file="/WEB-INF/jsp/include/common/result-excel-export.jsp"%>
</div>

<%-- Message indicating possible warning on Microsoft Excel --%>
<div id="excelWarning">
   <img class="information" src="<c:url value='/img/information.png'/>"/> 
   <spring:message code="result.export.excelWarning.1"/>
   <br/>
   <spring:message code="result.export.excelWarning.2"/>
</div>



<%-- SCRIPTS --%>
<script type="text/javascript" src="js/table2csv.js"></script>
<script type="text/javascript" src="js/outerHTML-2.1.0-min.js"></script>
<script type="text/javascript" src="js/exports.js"></script>
<script type="text/javascript" src="js/spin.min.js"></script>
