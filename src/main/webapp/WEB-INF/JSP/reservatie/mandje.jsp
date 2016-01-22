<%@page contentType="text/html" pageEncoding="UTF-8" session="false"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix='fmt' uri='http://java.sun.com/jsp/jstl/fmt'%>
<%@taglib uri='http://vdab.be/tags' prefix='vdab'%>
<%@taglib prefix='form' uri='http://www.springframework.org/tags/form'%>
<!doctype html>
<html>
<head>
<vdab:head title="Cultuurhuis > reservatiemandje"></vdab:head>
</head>
<body>
	<vdab:header title="reservatiemandje"></vdab:header>
	<c:if test="${not empty voorstellinginmandje}">
		<div>
			<form:form commandName="verwijderVoorstellingForm">
				<table>
					<tr>
						<th>Datum</th>
						<th>Titel</th>
						<th>Uitvoerders</th>
						<th>Prijs</th>
						<th>Plaatsen</th>
						<th><input type='submit' value='Verwijderen'></th>
					</tr>
					<c:forEach var="voorstelling" items="${voorstellinginmandje}">					
						<tr>
							<td><fmt:formatDate value='${voorstelling.datum}' type='both' dateStyle='short' timeStyle='short'/></td>
							<td>${voorstelling.titel}</td>
							<td>${voorstelling.uitvoerders}</td>
							<td class="number">€<fmt:formatNumber value='${voorstelling.prijs}' minFractionDigits='2'/> </td>
							<td class="number">${mandje[voorstelling.id]}</td>
							<td>
							<form:checkbox path="voorstellingIDs" value="${voorstelling.id}"/>
							</td>
						</tr>
					</c:forEach>
				</table>
			</form:form>
			<span>Totale prijs is : €<fmt:formatNumber value='${totaal}' minFractionDigits='2'/> </span>
		</div>
	</c:if>
</body>
</html>