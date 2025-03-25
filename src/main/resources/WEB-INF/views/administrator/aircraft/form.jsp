<%@page%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" uri="http://acme-framework.org/"%>

<acme:form>	
	<acme:input-textbox code="administrator.aircraft.form.label.model" path="model"/>
	<acme:input-textbox code="administrator.aircraft.form.label.registrationNumber" path="registrationNumber"/>
	<acme:input-textbox code="administrator.aircraft.form.label.capacity" path="capacity"/>
	<acme:input-textbox code="administrator.aircraft.form.label.cargoWeigth" path="cargoWeigth"/>
	<acme:input-select code="administrator.aircraft.form.label.status" path="status" choices="${statuses}"/>
	<acme:input-select code="administrator.aircraft.form.label.airline" path="airline" choices="${airlines}"/>
	<acme:input-textbox code="administrator.aircraft.form.label.details" path="details"/>
	
	<acme:submit code="administrator.aircraft.form.button.update" action="/administrator/aircraft/update"/>
	<acme:submit code="administrator.aircraft.form.button.delete" action="/administrator/aircraft/delete"/> 
</acme:form>