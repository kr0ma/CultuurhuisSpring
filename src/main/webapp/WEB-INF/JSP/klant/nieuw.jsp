<%@page contentType="text/html" pageEncoding="UTF-8" session="true"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib uri='http://vdab.be/tags' prefix='vdab'%>
<%@taglib prefix='form' uri='http://www.springframework.org/tags/form'%>
<!doctype html>
<html>
<head>
<vdab:head title="Cultuurhuis > nieuwe klant"></vdab:head>
</head>
<body>
	<vdab:header title="nieuwe klant"></vdab:header>
	<div>
	<form:form commandName="nieuweKlantForm">
		<form:label path="voornaam">Voornaam:</form:label>
		<form:input path='voornaam' />
		<form:errors path="voornaam" cssClass="fout"/>
						
		<form:label path="familienaam" >Familienaam:</form:label>
		<form:input path='familienaam'/>
		<form:errors path="familienaam" cssClass="fout"/>	
		
		<form:label path="straat" >Straat:</form:label>
		<form:input path='straat' />
		<form:errors path="straat" cssClass="fout"/>
		
		<form:label path="huisnr" >Huisnr:</form:label>
		<form:input path='huisnr'/>
		<form:errors path="huisnr" cssClass="fout"/>
				
		<form:label path="postcode" >Postcode:</form:label>
		<form:input path='postcode' />
		<form:errors path="postcode" cssClass="fout"/>		
		
		<form:label path="gemeente" >Gemeente:</form:label>
		<form:input path='gemeente' />
		<form:errors path="gemeente" cssClass="fout"/>		
		
		<form:label path="gebruikersnaam" >Gebruikersnaam:</form:label>
		<form:input path='gebruikersnaam' />
		<form:errors path="gebruikersnaam" cssClass="fout"/>
		
		<form:label path="paswoord" >Paswoord:</form:label>
		<form:input type="password" path='paswoord'/>
		<form:errors path="paswoord" cssClass="fout"/>
		
		<form:label path="verifPaswoord" >Herhaal paswoord:</form:label>
		<form:input type="password" path='verifPaswoord'/>
		<form:errors path="verifPaswoord" cssClass="fout"/>
		<label>		
			<input type='submit' value='OK'>
		</label>
	</form:form>
	</div>
	<c:if test="${not empty fouten}">
		<div>
			<ul>
				<c:forEach var="fout" items="${fouten}">
					<li>${fout}</li>
				</c:forEach>
			</ul>
		</div>
	</c:if>
</body>
</html>