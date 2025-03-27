<%@page%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" uri="http://acme-framework.org/"%>

<acme:list>
	<acme:list-column code="administrator.airline.list.label.name" path="name" width="15%"/>
	<acme:list-column code="administrator.airline.list.label.code" path="code" width="15%"/>
	<acme:list-column code="administrator.airline.list.label.website" path="website" width="15%"/>
	<acme:list-column code="administrator.airline.list.label.type" path="type" width="15%"/>
	<acme:list-column code="administrator.airline.list.label.foundationMoment" path="foundationMoment" width="15%"/>
	<acme:list-column code="administrator.airline.list.label.email" path="email" width="15%"/>
	<acme:list-column code="administrator.airline.list.label.phoneNumber" path="phoneNumber" width="10%"/>
</acme:list>

<acme:button code="administrator.airline.list.button.create" action="/administrator/airline/create"/>