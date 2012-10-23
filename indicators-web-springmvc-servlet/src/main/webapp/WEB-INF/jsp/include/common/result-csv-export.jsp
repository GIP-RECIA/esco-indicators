<%-- Form used to send data for CSV export --%>

<form:form class="export" action="download-data-ajax" method="POST" commandName="csvexport">
    <input type="hidden" id="csvData"       name="data"     value="" />
    <input type="hidden" id="csvFileName"   name="fileName" value="default-file-name.csv" />
    <input type="submit" value='<spring:message code="result.export.csv" />'/>
</form:form>
