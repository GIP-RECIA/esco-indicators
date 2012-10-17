<div id="user">
    <!-- #################################################### -->
    <!-- AUTHENTICATED USER ? -->
    <!-- #################################################### -->
    <c:choose>
      <c:when test="${not empty user}">
            <spring:message code="header.authenticatedUser" /> : <c:out value="${user.displayName}"/>
      </c:when>
      <c:otherwise>
        <spring:message code="header.notAuthenticatedUser" />
      </c:otherwise>
    </c:choose>
    <!-- #################################################### -->
</div>
