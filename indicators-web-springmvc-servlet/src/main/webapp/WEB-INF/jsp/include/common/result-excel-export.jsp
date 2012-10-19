<%-- Form used to send data for CSV export --%>

<form:form action="download-data-ajax" method="POST" commandName="excelexport">
    <input type="hidden" id="excelData"       name="data"     value="" />
    <input type="hidden" id="excelFileName"   name="fileName" value="default-file-name.xls" />
    <input type="submit" value='<spring:message code="result.export.excel" />'/>
</form:form>
