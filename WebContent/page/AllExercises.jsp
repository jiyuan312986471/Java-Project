<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="model.Exercise" %>
<%@ page import="model.db.ExerciseDB" %>
<%@ page import="java.util.ArrayList" %>

<%  if(session.getAttribute("name") == null) {
		request.getSession().setAttribute("messageError", "Please Login!");
		request.getRequestDispatcher("/index.jsp").forward(request, response);
    } else { %>

<jsp:include page="/include/Header.jsp">
  <jsp:param name="title" value="CodeEvaluation" />
  <jsp:param name="page" value="/index.jsp" />
</jsp:include>

<% ArrayList<Exercise> listExo = (ArrayList<Exercise>)request.getAttribute("listExo"); %>

<!-- container -->
<div class="container">
	<div class="modal-content" style="filter:alpha(Opacity=95); -moz-opacity:0.95; opacity:0.95; background-color:black; color:white;">
		<div class="modal-header">
        	<h4 class="modal-title" id="myModalLabel">All Projects</h4>
    	</div>
        
        <% if ( listExo == null || listExo.size() <= 0 ) { %>
        	<form action="<%= request.getContextPath()%>/index.jsp">
				<div class="modal-body">
					<div class="form-group">
						<div class="row">
							<div class="col-sm-12 text-center"><h3>There is no exercise yet.</h3></div>
						</div>
					</div>
				</div>
				         
				<div class="modal-footer">
				   	<input type="submit" class="btn btn-primary" value="Back">
				</div>
			</form>		
		<% } else { %>
			<div class="modal-body">
			<table class="table table-bordered text-center">
				<thead>
				<tr>
					<th><p></p><p class="text-center">Title</p></th>
				    <th><p></p><p class="text-center">Description</p></th>
				</tr>
				</thead>
				<tbody>
				<% for ( Exercise exo: listExo ) { %>
					<tr>
				  		<td>
				  			<a href="<%= request.getContextPath()%>/ExercisePageServlet?exoTitle=<%= exo.getTitle() %>"><%= exo.getTitle() %></a>
				  		</td>
				    	<td><%= exo.getDescription() %></td>
				  	</tr>
				<% } %>
				</tbody>
			</table>
		</div>
		<% } %>
	</div>
</div>

<!-- footer -->
<jsp:include page="/include/Footer.jsp" />

<% } %>