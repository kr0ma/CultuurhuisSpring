<%@page contentType='text/html' pageEncoding='UTF-8' session='false'%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix='fmt' uri='http://java.sun.com/jsp/jstl/fmt'%>
<%@taglib uri='http://vdab.be/tags' prefix='vdab'%>
<%@taglib prefix='spring' uri='http://www.springframework.org/tags'%>
<!doctype html>
<html>
<head>
<vdab:head title="Voorstellingen"></vdab:head>
</head>
<body>
	<vdab:header title="voorstellingen"></vdab:header>
	<div>
		<h2>Genres</h2>
		<c:forEach var="genre" items="${genres}">
			<spring:url value="/genre/{id}" var="genreURL">
				<spring:param name='id' value="${genre.id}" />
			</spring:url>
			<a href="<c:out value='${genreURL}'/>">${genre.naam}</a>
		</c:forEach>
	</div>
	<c:if test="${not empty voorstellingen}">
		<div>
		<h2>${huidigGenre.naam} voorstellingen</h2>
		<table>
			<tr>
				<th>Datum</th>
				<th>Titel</th>
				<th>Uitvoerders</th>
				<th>Prijs</th>
				<th>Vrije plaatsen</th>
				<th>Reserveren</th>
			</tr>
			<c:forEach var="voorstelling" items="${voorstellingen}">
				<tr>
					<td><fmt:formatDate value='${voorstelling.datum}' type='both' dateStyle='short' timeStyle='short'/></td>
					<td>${voorstelling.titel}</td>
					<td>${voorstelling.uitvoerders}</td>
					<td class="number">€<fmt:formatNumber value='${voorstelling.prijs}' minFractionDigits='2'/> </td>
					<td class="number">${voorstelling.vrijeplaatsen}</td>
					<spring:url value='/voorstelling/{id}/reserveren' var='reserverenURL'>
						<spring:param name='id' value="${voorstelling.id}" />
					</spring:url>
					<c:choose>
						<c:when test="${voorstelling.vrijeplaatsen > 0}">
							<td><a href="<c:out value='${reserverenURL}'/>">Reserveren</a></td>
						</c:when>
						<c:otherwise>
							<td></td>
						</c:otherwise>
					</c:choose>					
				</tr>
			</c:forEach>
		</table>
		</div>
	</c:if>
</body>
</html>