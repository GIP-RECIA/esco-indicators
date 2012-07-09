<%@ include file="/WEB-INF/jsp/header.jsp"%>

<h1><spring:message code="header.title"/></h1>

<hr/>

<div id="mainMessage">

  <p><spring:message code="menu.accounts-activations"/></p>
  
</div>


<h2>
    <spring:message code="result.title.appliedFilter" />
</h2>

<div id="filters">

    <p>
        <spring:message code="filter.title.monitoringType" /> : <spring:message code="${monitoringTypeItem}" />
    </p> 

    <p>
        <spring:message code="filter.title.establishmentType" /> : 
        <c:forEach var="item" items="${estbalishmentsTypesItems}">
            <spring:message code="${item}" />,
        </c:forEach>     
    </p>
    
    <p>
       <spring:message code="form.startDate.DEFAULT" /> : ${startDateItem}
    </p>
    <p>
       <spring:message code="form.endDate.DEFAULT" /> : ${endDateItem}
    </p>
    
    <p>
        <spring:message code="filter.title.userProfile" /> : 
        <c:forEach var="item" items="${usersProfilesItems}">
            <spring:message code="${item}" />,
        </c:forEach>     
    </p>
    
    <p>
        <spring:message code="filter.title.county" /> : <spring:message code="${countyItem}" />
    </p>    

    <p>
        <spring:message code="filter.title.lyceeType" /> :
        <c:forEach var="item" items="${lyceesTypesItems}">
            <spring:message code="${item}" />,
        </c:forEach>  
    </p>
    
    <p>
        <spring:message code="filter.title.laType" /> :
        <c:forEach var="item" items="${laTypesItems}">
            <spring:message code="${item}" />,
        </c:forEach>  
    </p>    
    
</div>

<h2>
    <spring:message code="result.title.selectedEstablishment" />
</h2>
   
    <table>
        <!-- Headers : First level -->
        <tr>
            <th>
                <spring:message code="result.table.establishment" />
            </th>
            <th>
                <spring:message code="result.table.rne" />
            </th>
            <th>
                <spring:message code="result.table.county" />
            </th>
            <th>
                <spring:message code="result.table.establishmentType" />
            </th>
            <th>
                <spring:message code="result.table.lyceeType" />
            </th>

            <c:forEach var="item" items="${usersProfilesItems}">
                <spring:message code="${item}" var="i18n"/> 
                <th colspan="7">
                    ${i18n}
                </th>
            </c:forEach>        
        </tr>        
        
        <!-- Headers : Second level -->
        <tr>
            <c:forEach var="i" begin="1" end="5">
                <th>
                </th>
            </c:forEach>
            
            <c:forEach var="item" items="${usersProfilesItems}">
                <th>
                    <spring:message code="result.table.totalAccount" />
                </th>
                <th colspan="2">
                    <spring:message code="result.table.activeAccount" />
                </th>            
                <th colspan="4">
                    <spring:message code="result.table.consultationFrequency" />
                </th>
            </c:forEach>
        </tr>
        
        <!-- Headers : Third level -->
        <tr>
            <c:forEach var="i" begin="1" end="5">
                <th>
                </th>
            </c:forEach>
            

        </tr>
        
        <!-- Headers : Fourth level -->
        <tr>
            <c:forEach var="i" begin="1" end="5">
                <th>
                </th>
            </c:forEach>
            
            <c:forEach var="item" items="${usersProfilesItems}">
                <c:forEach var="i" begin="1" end="3">
                    <th>
                    </th>
                </c:forEach>
                
                <th>
                    <spring:message code="result.table.numVisitor" />
                </th>
                <th>
                    <spring:message code="result.table.percentTotalAccount" />
                </th>            
                <th>
                    <spring:message code="result.table.numVisitor" />
                </th>
                <th>
                    <spring:message code="result.table.percentTotalAccount" />
                </th>       
            </c:forEach>
        </tr>
        
        <c:forEach var="item" items="${tableRowsItems}">
            <tr>
                <td>
                    ${item.establishmentData.establishmentName}
                </td>
            <tr>
        </c:forEach>    
            
    </table>


<div>
    <ul id="menu">
        <li><a href="/accounts-activations"><spring:message code="menu.accounts-activations"/></a></li>
        <li><a href="/services"><spring:message code="menu.services"/></a></li>
        <li><a href="/services-usages"><spring:message code="menu.services-usage"/></a></li>
    </ul> 
</div>

<!-- SCRIPTS -->
<script type="text/javascript" src="js/result.js"></script>

<%@ include file="/WEB-INF/jsp/footer.jsp"%>
