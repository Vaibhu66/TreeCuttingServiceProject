<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<%
String loginID = (String) session.getAttribute("loginID");
%>

<!DOCTYPE html>
<html>
<head>
    <meta charset="ISO-8859-1">
    <title>Client Dashboard</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
    <style>

      #clientDB
      {
     		font-family: sans-seriff;
            margin-top: 50px;
            max-width: 1400px;
            background-color: #fff;
            border: 1px solid #ccc;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.2);
            padding: 20px;
            border-radius:10px;
      }
      body
      {
      background-color: black;
      }
    </style>
</head>

<body>
    <div class="container text-center" id="clientDB">
        <h1 class="display-4 font-weight-bold-italic" style="color: #000000; text-shadow: 2px 2px 4px #ffffff; font-family: Brush Script MT, Brush Script Std, cursive;">Welcome, Client!</h1>
        <br>
        <h6>You have successfully logged in</h6>
	<br>
	<br>
	<br>
    <div class="container text-center">
        <button onclick="window.location.href='login.jsp'" class="btn btn-primary">Logout</button>
        <button onclick="showForm()" class="btn btn-primary">Open Form</button>
        <button type="button" onclick="showSubmittedQuotes()" class="btn btn-success">View Submitted Quotes</button>
    </div>
	<br>
	<br>
    <form id="quoteForm" action="submitQuote" method="post" style="display: none;">
        <!-- Client Information -->
        <div class="form-group row">
			<label for="clientID" class="col-sm-4 col-form-label text-right">Client ID:</label>
			<div class="col-sm-6">
			<input type="text" id="clientID" name="clientID" placeholder=loginID required class="form-control">
			</div>
		</div>
			
		<div class="form-group row">
			<label for="requestDate" class="col-sm-4 col-form-label text-right">Request Date:</label>
			<div class="col-sm-6">
			<input type="date" id="requestDate" name="requestDate" required class="form-control">
			</div>
		</div>
		
		<div class="form-group row">		
			<label for="note" class="col-sm-4 col-form-label text-right">Note:</label>
			<div class="col-sm-6">
			<textarea name="note" placeholder="Note" class="form-control"></textarea>
			</div>
		</div>
		
		<div class="form-group row">	
			<label for="price" class="col-sm-4 col-form-label text-right">Price:</label>
			<div class="col-sm-6">
			<input type="text" id="price" name="price" placeholder="Price"required class="form-control">
			</div>
		</div>
		
		<div class="form-group row">			
			<label for="timeWindow" class="col-sm-4 col-form-label text-right">TimeWindow:</label>
			<div class="col-sm-6">
			<input type="text" id="timeWindow" name="timeWindow" placeholder="8:00 AM - 12:00 PM" required class="form-control">
			</div>
		</div>
		
		<!-- Tree Information -->
		<div class="form-group row">	
			<label for="size" class="col-sm-4 col-form-label text-right">Size:</label>
			<div class="col-sm-6">
			<input type="text" id="size" name="size" placeholder="Size" required class="form-control">
			</div>
		</div>
		
		<div class="form-group row">	
			<label for="height" class="col-sm-4 col-form-label text-right">Height:</label>
			<div class="col-sm-6">
			<input type="text" id="height" name="height" placeholder="Height" required class="form-control">
			</div>
		</div>
		
		<div class="form-group row">	
			<label for="location" class="col-sm-4 col-form-label text-right">Location:</label>
			<div class="col-sm-6">
			<input type="text" id="location" name="location" placeholder="Location" required class="form-control">
			</div>
		</div>
		
		<div class="form-group row">	
			<label for="nearHouse" class="col-sm-4 col-form-label text-right">Near House:</label>
			<div class="col-sm-6">
			<input type="text" id="nearHouse" name="nearHouse" placeholder="Near House" class="form-control">
			</div>
		</div>	
        <!-- Tree Picture Information -->
         <div class="form-group row">   
            <label for="pictureURL" class="col-sm-4 col-form-label text-right">Picture URL:</label>
            <div class="col-sm-6">
            <input type="text" id="pictureURL" name="pictureURL" placeholder="Picture URL" required class="form-control">
            </div>
          </div>
		<br>
        <button type="submit" class="btn btn-primary">Submit Request</button>
        <button type="button" onclick="hideForm()" class="btn btn-danger">Close Form</button>
    </form>
    <br>
    <form id="submittedQuotes" action="editQuote" method="post" style="display: none;">
    <!-- Section to display submitted quotes -->
    <!-- This can be filled in using JavaScript when showing the submitted quotes -->
     	<h4>List of Quotes</h4>
        <table class="table table-bordered table-striped">
				<%
				String updateMessage = (String) session.getAttribute("updateMessage");
				if (updateMessage != null) {
				%>
				<div class="success-message">
					<%=updateMessage%>
				</div>
				<%
				// Clear the message from the session after displaying it
				session.removeAttribute("updateMessage");
				}
				%>
		<thead>
				<tr>
                <th>Quote ID</th>
                <th>Request ID</th>
                <th>Quote Date</th>
                <th>Price</th>
                <th>Time Window</th>
                <th>Status</th>
                <th>Note</th>
                <th>Action</th>
            </tr>
           </thead>
           <tbody>
            <tr style="text-align: center">
                <td><c:out value="${listQuote.quoteID}" /></td>
                <td><c:out value="${listQuote.requestID}" /></td>
                <td><c:out value="${listQuote.quoteDate}" /></td>
                <td><input type="text" name="price" value="<c:out value="${listQuote.price}" />"></td>
                <td><c:out value="${listQuote.timeWindow}" /></td>
                <td><input type="text" name="status" value="<c:out value="${listQuote.status}" />"></td>
                <td><input type="text" name="note" value="<c:out value="${listQuote.note}" />"></td>
                <td>
                <input type="hidden" name="quoteID" value="<c:out value="${listQuote.quoteID}" />">
                   <button type="submit"
			style="padding: 10px 20px; background-color: #0074D9; color: white; border: none; cursor: pointer; border-radius: 5px;">Edit Request</button>
                </td>
            </tr>
            </tbody>
        </table>
	<br>
    <button type="button" onclick="hideForm2()" style="margin-top: 30px; padding: 10px 20px; background-color: #ff0000; color: white; border: none; cursor: pointer; border-radius: 5px;">Close Form</button>
    <br>
</form>
    </div>
</body>

<script>
function showForm() {
    var form = document.getElementById("quoteForm");
    form.style.display = "block";
}

function showSubmittedQuotes() {
    // Replace this alert with logic to fetch and display submitted quotes
   // alert("Logic to fetch and display submitted quotes goes here");
    
    // Show the section where submitted quotes will be displayed
    var submittedQuotesSection = document.getElementById("submittedQuotes");
    submittedQuotesSection.style.display = "block";
}

function hideForm() {
    var form = document.getElementById("quoteForm");
    form.style.display = "none";
}
function hideForm2() {
    var submittedQuotes = document.getElementById("submittedQuotes");
    submittedQuotes.style.display = "none";
}

document.getElementById("clientID").setAttribute("placeholder", "<%= session.getAttribute("loginID") %>");

</script>

</html>
