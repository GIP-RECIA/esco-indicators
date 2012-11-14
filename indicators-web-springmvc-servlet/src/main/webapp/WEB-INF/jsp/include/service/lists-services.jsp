<div>
    <p>
       <c:forEach var="item" items="${availableServicesItems}">
           <form:hidden id="${item.value}" value="${item.value}" path="availableServices"/>
       </c:forEach>
       
       <ul id="availableServicesList" class="connectedServices">
           <c:forEach var="item" items="${availableServicesItems}">
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
       </ul>
       
       <ul id="wantedServicesList" class="connectedServices">
           <p class="information"><spring:message code="form.services.information.dragNDrop" /></p>
       </ul>
       
       <form:errors path="wantedServices" cssClass="error" />
    </p>
</div>

<div></div>
