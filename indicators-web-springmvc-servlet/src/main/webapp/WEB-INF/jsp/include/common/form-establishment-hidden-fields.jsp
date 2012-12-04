<c:forEach var="item" items="${establishmentsTypesItems}">
    <form:hidden value="establishmentType.${item.value}" path="establishmentsTypes" /> 
</c:forEach>

<c:forEach var="item" items="${countyItems}">
    <form:hidden value="county.COUNTY_${item.value}" path="county" /> 
</c:forEach>

<c:forEach var="item" items="${usersProfilesItems}">
    <form:hidden value="${item.value}" path="usersProfiles" /> 
</c:forEach>


<c:forEach var="item" items="${establishmentsItems}">
    <form:hidden value="${item}" path="establishments" /> 
</c:forEach>
