<h2 class="filterTitle"><spring:message code="filter.title.period" /></h2>
<div class="filterOptions">
    <form:input path="startDatePicker"  /> 
    <form:hidden id="startDate" path="startDate" />  
    
    <form:input path="endDatePicker" />
    <form:hidden id="endDate" path="endDate" />  
</div>
<div class="filterErrors">
    <form:errors path="startDate" cssClass="error" />
    <form:errors path="endDate" cssClass="error" />
</div>