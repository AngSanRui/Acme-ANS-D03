<%@page%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" uri="http://acme-framework.org/"%>

<acme:form>
	<acme:input-textbox code="authenticated.manager.form.label.identifierNumber" path="identifierNumber"/>
	<acme:input-textbox code="authenticated.manager.form.label.yearsOfExperience" path="years"/>
	
	<jstl:if test="${_command == 'create'}">
		<acme:submit code="authenticated.manager.form.button.create" action="/authenticated/manager/create"/>
	</jstl:if>
	<jstl:if test="${_command == 'update'}">
		<acme:submit code="authenticated.manager.form.button.update" action="/authenticated/manager/update"/>
	</jstl:if>	
</acme:form>