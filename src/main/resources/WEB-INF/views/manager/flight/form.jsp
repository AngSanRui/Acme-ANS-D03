<%@page%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" uri="http://acme-framework.org/"%>

<acme:form>	
	<acme:input-textbox code="manager.flight.form.label.tag" path="tag"/>
	<acme:input-textbox code="manager.flight.form.label.selfTransfer" path="selfTransfer"/>
	<acme:input-money code="manager.flight.form.label.cost" path="cost"/>
	<acme:input-textbox code="manager.flight.form.label.description" path="description"/>
	<acme:input-textbox code="manager.flight.form.label.manager" path="manager"/>
	<jstl:choose>
		<jstl:when test="${acme:anyOf(_command, 'show|update|delete')}">
			<acme:submit code="manager.flight.form.button.update" action="/customer/booking/update"/>
			<acme:submit code="manager.flight.form.button.delete" action="/customer/booking/delete"/>
		</jstl:when>
		<jstl:when test="${_command == 'create'}">
			<acme:submit code="manager.flight.form.button.create" action="/manager/flight/create"/>
		</jstl:when>		
	</jstl:choose>			
</acme:form>