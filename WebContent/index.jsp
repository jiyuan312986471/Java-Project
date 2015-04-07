<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<jsp:include page="/include/Header.jsp">
	<jsp:param name="title" value="CodeEvaluation"/>
	<jsp:param name="page" value="/index.jsp" />
</jsp:include>


<div class="container">

	<div class="jumbotron"  style="filter:alpha(Opacity=85); -moz-opacity:0.85; opacity:0.85; background-color:black; color:white;">
		<h1>Code Evaluation</h1>
		<h2>Lets do some Java exercises</h2></br></br>
		<p>
			<% if ( session.getAttribute("mail") == null ) { %>
				<a class="btn btn-primary btn-lg" href="<%= request.getContextPath()%>/page/Registration.jsp" role="button">Sign Up</a>
			<% } else { %>
				<a class="btn btn-primary btn-lg" href="<%= request.getContextPath()%>/page/AllExercises.jsp" role="button">Get Start</a>
			<% } %>
		</p> 
	</div>

</div>

<jsp:include page="/include/Footer.jsp" />