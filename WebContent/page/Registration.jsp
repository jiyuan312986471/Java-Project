<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="model.db.UserDB" %>

<%  if(session.getAttribute("name") == null) { %>
<jsp:include page="/include/Header.jsp">
<jsp:param name="title" value="CodeEvaluation"/>
</jsp:include>

<div class="container">

	<div class="modal-content" style="filter:alpha(Opacity=95); -moz-opacity:0.95; opacity:0.95; background-color:black; color:white;">
	
         <div class="modal-header">
            <h4 class="modal-title" id="myModalLabel">Registration</h4>
         </div>
         
         <form class="form-horizontal" action="<%= request.getContextPath()%>/RegistrationServlet" method="post">
	         <div class="modal-body">
	         
				  <div class="form-group">
				    <label for="inputEmail" class="col-sm-2 control-label">Email: </label>
				    <div class="col-sm-9">
				      <input type="email" class="form-control" id="inputEmail" placeholder="Please enter your email..." name="userEmail" value="<%= request.getParameter("userEmail") == null ?"": request.getParameter("userEmail") %>">
				    </div>
				  </div>
				  
				  <div class="form-group">
				    <label for="inputFirstName" class="col-sm-2 control-label">First Name: </label>
				    <div class="col-sm-9">
				      <input type="text" class="form-control" id="inputFirstName" placeholder="Your first name..." name="userFirstName" value="<%= request.getParameter("userFirstName") == null ?"": request.getParameter("userFirstName") %>">
				    </div>
				  </div>
				  
				  <div class="form-group">
				    <label for="inputLastName" class="col-sm-2 control-label">Last Name: </label>
				    <div class="col-sm-9">
				      <input type="text" class="form-control" id="inputLastName" placeholder="Your last name..." name="userLastName" value="<%= request.getParameter("userLastName") == null ?"": request.getParameter("userLastName") %>">
				    </div>
				  </div>
				  
				  <div class="form-group">
				    <label for="inputPwd" class="col-sm-2 control-label">Password: </label>
				    <div class="col-sm-9">
				      <input type="password" class="form-control" id="inputPwd" placeholder="Password..." name="userPwd" value="<%= request.getParameter("userPwd") == null ?"": request.getParameter("userPwd") %>">
				    </div>
				    <div class="row">
				    	<div class="col-sm-offset-2 col-sm-12">
				    		<p class="help-block" style="color:red">Password must be at least 8 characters long contains a digit and a lower case letter.</p>
				    	</div>
				    </div>
				  </div>
				  
				  <div class="form-group">
				    <label for="inputConfirmPwd" class="col-sm-2 control-label">Confirm Password: </label>
				    <div class="col-sm-9">
				      <input type="password" class="form-control" id="inputConfirmPwd" placeholder="Confirm Password..." name="userConfirmPwd">
				    </div>
				  </div>
				  
	         </div>
         
	         <div class="modal-footer">
	         	<button type="submit" class="btn btn-primary">Save</button>
	            <button type="reset" class="btn btn-default" data-dismiss="modal">Reset All</button>
	         </div>
         </form>
      </div>
      
</div>

<jsp:include page="/include/Footer.jsp" />
<%	
    } else {
    	request.getSession().setAttribute("messageError", "Please Logout!");
		request.getRequestDispatcher("/index.jsp").forward(request, response);
    }
%>

