<div>
    <p>
       <c:forEach var="item" items="${availableServicesItems}">
           <c:if test="${!fn:contains(postedServicesItems, item)}">
                <form:hidden id="${item.value}" value="${item.value}" path="availableServices"/>
           </c:if>
       </c:forEach>
       
      <c:forEach var="item" items="${postedServicesItems}">
           <form:hidden id="${item.value}" value="${item.value}" path="wantedServices"/>
       </c:forEach>
       
       <ul id="availableServicesList" class="connectedServices">
           <c:forEach var="item" items="${availableServicesItems}">
                <c:if test="${!fn:contains(postedServicesItems, item)}"> 
                   <spring:message code="${item.label}" var="i18n"/> 
                   <c:set var="itemId" value="${item.value}" />
                                       
                   <c:if test="${fn:contains(itemId, 'SUM')}">
                       <li class="ui-state-highlight sumService" id="${itemId}">
                           ${i18n}
                       </li>
                   </c:if>
                   <c:if test="${!fn:contains(itemId, 'SUM')}">
                       <li class="ui-state-default simpleService" id="${itemId}">
                           ${i18n}
                       </li>
                   </c:if>
                </c:if>
           </c:forEach> 
       </ul>
       
       <ul id="wantedServicesList" class="connectedServices">
           <%-- Wanted services previously selected --%>
           <c:forEach var="item" items="${postedServicesItems}">
               <spring:message code="${item.label}" var="i18n"/> 
               <c:set var="itemId" value="${item.value}" />
                                   
               <c:if test="${fn:contains(itemId, 'SUM')}">
                   <li class="ui-state-highlight sumService" id="${itemId}">
                       ${i18n}
                   </li>
               </c:if>
               <c:if test="${!fn:contains(itemId, 'SUM')}">
                   <li class="ui-state-default simpleService" id="${itemId}">
                       ${i18n}
                   </li>
               </c:if>
           </c:forEach>
           <p class="information"><spring:message code="form.services.information.dragNDrop" /></p>
       </ul>
       
       
       <%-- Wanted services previously selected --%>
       <c:forEach var="wantedService" items="${postedServicesItems}">
            <input type="hidden" name="postedService" value="${wantedService}" />
       </c:forEach>
    </p>
</div>
<div class="divError">
       <form:errors path="wantedServices" cssClass="error" />
</div>

<div></div>
