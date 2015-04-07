<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="model.Exercise" %>
<%@ page import="model.db.UserDB" %>
<%@ page import="model.db.ExerciseDB" %>
<%@ page import="java.lang.*" %>
<%@ page import="java.util.ArrayList" %>

<%  if(session.getAttribute("name") == null) {
		request.getSession().setAttribute("messageError", "Please Login!");
		request.getRequestDispatcher("/index.jsp").forward(request, response);
    } else { %>

<jsp:include page="/include/Header.jsp">
	<jsp:param name="title" value="CodeEvaluation"/>
</jsp:include>

<%  Exercise exo = (Exercise) request.getAttribute("exo"); %>

<div class="container">

	<div class="modal-content" style="filter:alpha(Opacity=95); -moz-opacity:0.95; opacity:0.95; background-color:black; color:white;">
         <div class="modal-header">
            <h3 class="modal-title" id="myModalLabel"><%= exo.getTitle() %></h3>
         </div>
		 
		 <div class="modal-body">
		 	<div class="form-group">
			 	<div class="row">
			 		<label class="col-sm-2"><h4>Description</h4></label>
			 	</div>
		 	</div>
				
			<div class="form-group">
				<div class="row">
					<label class="col-sm-1 col-sm-offset-1">Description: </label>
					<label class="col-sm-9" style="overflow: hidden; white-space: nowrap; text-overflow: ellipsis;"><%= exo.getDescription() %></label>
				</div>
			</div>
		</div>
		         
		<hr>

		<form action="<%= request.getContextPath()%>/CompilerServlet" method="post">
		<div class="modal-body">
			<div class="form-group">
				<div class="row">
				 	<label class="col-sm-2"><h4>Have a try</h4></label>
				 	<input type="text" name="code">
				</div>
			</div>
		</div>
		         
		<div class="modal-footer">
		    <input type="submit" class="btn btn-primary" value="Save">
		</div>
	    </form>
	</div>
      
</div>

<jsp:include page="/include/Footer.jsp" />

<%  } %>