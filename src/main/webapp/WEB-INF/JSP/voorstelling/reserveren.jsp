<%@page contentType="text/html" pageEncoding="UTF-8" session="true"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix='fmt' uri='http://java.sun.com/jsp/jstl/fmt'%>
<%@taglib uri='http://vdab.be/tags' prefix='vdab'%>
<%@taglib prefix='form' uri='http://www.springframework.org/tags/form'%>
<!doctype html>
<html>
<head>
<vdab:head title="Cultuurhuis > reserveren"></vdab:head>
</head>
<body>
	<vdab:header title="reserveren"></vdab:header>
	<c:if test="${not empty voorstelling}">
	<div>
		<dl>
			<dd>Voorstelling:</dd>
			<dt>${voorstelling.titel}</dt>
		
			<dd>Uitvoerders:</dd>
			<dt>${voorstelling.uitvoerders}</dt>
		
			<dd>Datum:</dd>
			<dt><fmt:formatDate value='${voorstelling.datum}' type='both' dateStyle='short' timeStyle='short'/></dt>
			
			<dd>Prijs:</dd>
			<dt>€<fmt:formatNumber value='${voorstelling.prijs}' minFractionDigits='2'/></dt>
			
			<dd>Vrije plaatsen:</dd>
			<dt>${voorstelling.vrijeplaatsen}</dt>			
		</dl>
		<form:form commandName="reserverenForm">
			<form:label path="aantal">Plaatsen:</form:label>
			<form:input path="aantal"/><form:errors path="aantal" cssClass="fout"/>
			<input type='submit' value='Reserveren'>
		</form:form>		
	</div>
	</c:if>
	
</body>
</html>