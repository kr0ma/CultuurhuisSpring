<%@page contentType="text/html" pageEncoding="UTF-8" session="true"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib uri='http://vdab.be/tags' prefix='vdab'%>
<%@taglib prefix='security' uri='http://www.springframework.org/security/tags'%>
<!doctype html>
<html>
<head>
<vdab:head title="Cultuurhuis > bevestig reservatie"></vdab:head>
</head>
<body>
	<vdab:header title="bevestiging reservaties"></vdab:header>
	<div>
		<h2>Stap 1: Wie ben je ?</h2>
		<security:authorize access='isAuthenticated()'>
			<security:authentication property='name' var='userName'/>
		</security:authorize>
		
		<form method="post" action='<c:url value="/j_spring_security_check"/>'
			id='aanmeldform'>
			<label>Gebruikersnaam: <input name='j_username' autofocus
				<c:if test="${not empty userName}">disabled="disabled" value='${ userName}'</c:if>>
			</label> <label>Paswoord: <input type="password" name='j_password'
				<c:if test="${not empty userName}">disabled="disabled"</c:if>>

			</label> <input type='submit' value='Zoek me op' name="zoekmeop"
				<c:if test="${not empty userName}">disabled="disabled"</c:if>>
			<span class="usermessage">${not empty fout? fout: klant}</span>
			<c:if test='${param.error}'>
				<div class='usermessage'>Verkeerde gebruikersnaam of paswoord.</div>
			</c:if>
		</form>
		<security:authorize access='isAuthenticated()'>
			<a href="<c:url value='/j_spring_security_logout'/>">Afmelden</a>
		</security:authorize>
		<form action="<c:url value='/klant/nieuw'/>">
			<input type='submit' value='Ik ben nieuw' id='aanmeldknop'
				<c:if test="${not empty userName}">disabled="disabled"</c:if>>
		</form>
	</div>
	<div>
		<h2>Stap 2:Bevestigen</h2>
		<form method="post" action='<c:url value="/reservatie/bevestigen"></c:url>'>
			<input type='submit' value='Bevestigen' name="bevestigen"
				<c:if test="${empty userName}">disabled="disabled"</c:if>>
		</form>
	</div>
</body>
<script>
	document.getElementById('aanmeldform').onsubmit = function() {
		document.getElementById('aanmeldknop').disabled = true;
	};
</script>
</html>