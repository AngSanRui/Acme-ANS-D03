<%@page%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" uri="http://acme-framework.org/"%>

<acme:form>	
	<acme:input-textbox code="customer.booking.form.label.locatorCode" path="locatorCode"/>
	<acme:input-textbox code="customer.booking.form.label.purchaseMoment" path="purchaseMoment"/>
	<acme:input-select code="customer.booking.form.label.travelClass" path="travelClass" choices="${classes}"/>
	<acme:input-double code="customer.booking.form.label.price" path="price"/>
	<acme:input-integer code="customer.booking.form.label.lastCreditCardDigits" path="lastCreditCardDigits"/>
	<acme:input-textbox code="customer.booking.form.label.customer" path="customer" readonly="true"/>
	<acme:input-textbox code="customer.booking.form.label.passengers" path="passengers" readonly="true"/>
	<acme:input-textbox code="customer.booking.form.label.flight" path="flight" readonly="true"/>
	<jstl:choose>
		<jstl:when test="${acme:anyOf(_command, 'show|update|delete')}">
			<acme:submit code="customer.booking.form.button.update" action="/customer/booking/update"/>
			<acme:submit code="customer.booking.form.button.delete" action="/customer/booking/delete"/>
			<acme:button code="customer.booking.form.button.passengers" action="/customer/passenger/list?masterId=${id}"/>
		</jstl:when>
		<jstl:when test="${_command == 'create'}">
			<acme:submit code="customer.booking.form.button.create" action="/customer/booking/create"/>
		</jstl:when>		
	</jstl:choose>			
</acme:form>