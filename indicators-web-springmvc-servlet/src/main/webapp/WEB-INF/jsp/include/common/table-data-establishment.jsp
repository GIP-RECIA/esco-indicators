<!-- Establishment data -->

<!-- #################################################### -->
<!-- SUM ON COUNTIES ? -->
<!-- #################################################### -->
<!-- If the sum on counties has been asked -->
<c:if test="${not empty sumOnCountiesItem}">
    <td>
        <spring:message code="result.table.county.${item.establishmentData.countyNumber}" />
    </td>
    <td> </td>
    <td> 
        ${item.establishmentData.countyNumber}
    </td>
    <td> </td>
    <td> </td>
</c:if>
<!-- #################################################### -->

<c:if test="${empty sumOnCountiesItem}">
    <td>
        ${item.establishmentData.establishmentName}
    </td>
    <td>
        ${item.establishmentData.uai}
    </td>
    <td>
        ${item.establishmentData.countyNumber}
    </td>
    <td>
        ${item.establishmentData.establishmentType}
    </td>
    <td>
        ${item.establishmentData.lyceeType}
    </td>
</c:if>