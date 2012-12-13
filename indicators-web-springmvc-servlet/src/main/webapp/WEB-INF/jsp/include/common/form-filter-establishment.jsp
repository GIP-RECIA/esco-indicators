<h2 class="filterTitle"><spring:message code="filter.title.establishmentList" /></h2>
<div id="establishmentsListContainer" class="filterOptions">
	<p>
		<label for="filterInput">
			<spring:message code="form.filterEstablishments" />
		</label>
		<input id="filterInput" type="text" />
    </p>
    <table id="establishmentsList" class="tablesorter, pretty">
		<thead>
			<tr>
				<th><input type="checkbox" id="toggleEstablishmentsSelection"/></th>
				<th><spring:message code="form.selectAllEstablishments" /></th>
			</tr>
		</thead>
		<tbody>
            <%-- Empty table filled by ajax methods --%>
		</tbody>
    </table>
    
    <%-- Establishments previously selected --%>
    <c:forEach var="establishment" items="${postedEstablishmentsItems}">
        <input type="hidden" name="postedEstablishment" value="${establishment}" />
    </c:forEach>
    
</div>
<div class="filterErrors">
    <form:errors path="establishments" cssClass="error" />
</div>