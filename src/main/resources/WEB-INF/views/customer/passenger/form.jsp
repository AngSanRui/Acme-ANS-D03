<%@page%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" uri="http://acme-framework.org/"%>

<acme:form>	
	<acme:input-textbox code="customer.passenger.form.label.name" path="name"/>
	<acme:input-email code="customer.passenger.form.label.email" path="email"/>
	<acme:input-textbox code="customer.passenger.form.label.passport" path="passport"/>
	<acme:input-moment code="customer.passenger.form.label.dateOfBirth" path="dateOfBirth"/>	
	<acme:input-textbox code="customer.passenger.form.label.specialNeeds" path="specialNeeds"/>
	<acme:submit code="customer.passenger.form.button.create" action="/customer/passenger/create"/>
</acme:form>