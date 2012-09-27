<h2 class="filterTitle"><spring:message code="filter.title.filter" /></h2>
    <div class="filterOptions">
    
        <h3 class="subFilterTitle"><spring:message code="filter.title.userProfile" /></h3>
        <div class="filterOptions">
            <c:forEach var="item" items="${usersProfilesItems}">
                <spring:message code="${item.label}" var="i18n"/> 
                <form:checkbox value="${item.value}" label="${i18n}" disabled="${item.disabled}" path="usersProfiles" /> 
            </c:forEach> 
        </div>
        <div class="filterErrors">
            <form:errors path="usersProfiles" cssClass="error" />
        </div>
        
        <h3 class="subFilterTitle"><spring:message code="filter.title.county" /></h3>
        <div class="filterOptions">
            <form:select cssClass="submit" path="county">
                <c:forEach var="item" items="${countyItems}">
                   <spring:message code="${item.label}" var="i18n"/> 
                   <form:option cssClass="submit" value="${item.value}" label="${i18n}" disabled="${item.disabled}" path="county" /> 
                </c:forEach>
            </form:select> 
                        
            <c:forEach var="item" items="${sumOnCountiesItems}">
                <spring:message code="${item.label}" var="i18n"/> 
                <form:checkbox cssClass="submit" value="${item.value}" label="${i18n}" disabled="${item.disabled}" path="sumOnCounties" /> 
            </c:forEach> 
        </div>
        <div class="filterErrors">
            <form:errors path="county" cssClass="error" />
        </div>
        
        <h3 class="subFilterTitle"><spring:message code="filter.title.lyceeType" /></h3>
        <div class="filterOptions">
            <c:forEach var="item" items="${lyceesTypesItems}">
                <spring:message code="${item.label}" var="i18n"/> 
                <form:checkbox cssClass="submit" value="${item.value}" label="${i18n}" disabled="${item.disabled}" path="lyceesTypes" /> 
            </c:forEach>
        </div>
         <div class="filterErrors">
            <form:errors path="lyceesTypes" cssClass="error" />
         </div>
        
        <h3 class="subFilterTitle"><spring:message code="filter.title.laType" /></h3>
        <div class="filterOptions">
            <c:forEach var="item" items="${laTypesItems}">
                   <spring:message code="${item.label}" var="i18n"/>  
                   <form:checkbox cssClass="submit" value="${item.value}" label="${i18n}" disabled="${item.disabled}" path="laTypes" /> 
            </c:forEach>
        </div>
        <div class="filterErrors">
            <form:errors path="laTypes" cssClass="error" />
         </div>
    </div>