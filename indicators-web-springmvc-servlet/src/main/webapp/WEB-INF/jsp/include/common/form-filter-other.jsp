<h2 class="filterTitle"><spring:message code="filter.title.filter" /></h2>
    <div class="filterOptions">
    
        <h3 class="subFilterTitle"><spring:message code="filter.title.userProfile" /></h3>
        <div class="filterOptions">
            <c:forEach var="item" items="${usersProfilesItems}">
                <spring:message code="${item.label}" var="i18n"/> 
                <form:checkbox value="${item.value}" label="${i18n}" disabled="${item.disabled}" path="usersProfiles" /> 
            </c:forEach> 
			<br/>
			<%-- #################################################### --%>
			<%-- MULTIPLE USERS PROFILES CAN BE SELECTED ? --%>
			<%-- #################################################### --%>
			<c:if test="${multipleUsersProfiles == true}">
				<input type="checkbox" id="toggleProfilesSelection"/>
				<label for="toggleProfilesSelection">
				    <spring:message code="form.usersProfiles.ALL"/>
			    </label>
			</c:if>
			<%-- #################################################### --%>
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
        
        <%-- #################################################### --%>
        <%-- LYCEES TYPES ? --%>
        <%-- #################################################### --%>
        <c:if test="${not empty lyceesTypesItems}">
            <h3 class="subFilterTitle"><spring:message code="filter.title.lyceeType" /></h3>
            <div class="filterOptions">
                <c:forEach var="item" items="${lyceesTypesItems}">
                    <spring:message code="${item.label}" var="i18n"/> 
                    <form:checkbox cssClass="submit" value="${item.value}" label="${i18n}" readonly="${item.disabled}" path="lyceesTypes" /> 
                </c:forEach>
            </div>
             <div class="filterErrors">
                <form:errors path="lyceesTypes" cssClass="error" />
             </div>
        </c:if>
        <%-- #################################################### --%>
        
        <%-- #################################################### --%>
        <%-- LA TYPES ? --%>
        <%-- #################################################### --%>
        <c:if test="${not empty laTypesItems}">
            <h3 class="subFilterTitle"><spring:message code="filter.title.laType" /></h3>
            <div class="filterOptions">
                <c:forEach var="item" items="${laTypesItems}">
                       <spring:message code="${item.label}" var="i18n"/>  
                       <form:checkbox cssClass="submit" value="${item.value}" label="${i18n}" readonly="${item.disabled}" path="laTypes" /> 
                </c:forEach>
            </div>
            <div class="filterErrors">
                <form:errors path="laTypes" cssClass="error" />
            </div>
        </c:if>
        <%-- #################################################### --%>
        
    </div>