<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
<head>
    <meta charset="ISO-8859-1">
    <title>Prospective Clients</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
    <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.11.6/dist/umd/popper.min.js"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>

    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f5f5f5;
            margin: 0;
            padding: 0;
        }

        .container {
            max-width: 1200px;
            margin: 20px auto;
            padding: 20px;
            background-color: #fff;
            box-shadow: 0 2px 5px rgba(0, 0, 0, 0.1);
            border-radius: 10px;
        }

        .action-btn {
            display: block;
            width: 100%;
            padding: 10px;
            background-color: #0074D9;
            color: #fff;
            text-align: center;
            text-decoration: none;
            border-radius: 5px;
        }

        .action-btn:hover {
            background-color: #0056b3;
        }

        table {
            width: 100%;
            border-collapse: collapse;
            margin-top: 20px;
        }

        th, td {
            padding: 10px;
            text-align: center;
            border: 1px solid #ccc;
        }

        th {
            background-color: #0074D9;
            color: #fff;
        }

        .vertical-divider {
            width: 1px;
            height: 35px;
            background-color: #e0e0e0;
            margin: 0 15px;
        }

        .navbar-brand {
            color: inherit;
        }
        #ProsC
        {
        	font-family: sans-seriff;
            margin-top: 50px;
            max-width: 1600px;
            background-color: #fff;
            border: 1px solid #ccc;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.2);
            padding: 20px;
            border-radius:10px;
            }
            .hidden {
            display: none;
        }
    </style>
</head>
<body>
    <nav class="navbar navbar-expand-lg navbar-dark bg-dark">
        <span class="navbar-brand mb-0 h1">ADMIN DASHBOARD</span>
        <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent"
            aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"></span>
        </button>
        <div class="vertical-divider"></div>
        <div class="collapse navbar-collapse" id="navbarSupportedContent">
            <ul class="navbar-nav ms-auto">
                <li class="nav-item">
                    <a class="nav-link" href="login.jsp">Logout</a>
                </li>
            </ul>
        </div>
    </nav>
    <div class="container" id="ProsC">
        <h1 class="display-4 font-weight-bold-italic text-center"
            style="color: #000000; text-shadow: 2px 2px 4px #ffffff; font-family: Brush Script MT, Brush Script Std, cursive;">
            Prospective Clients
        </h1>
        <br>
         <form id="backToDashboardForm" action="backToAdminDashboard" method="post">
            <input type="hidden" name="dashboardOption" value="back">
        </form>
        <br>
            <div class="col text-center">
                <button class="btn btn-primary" id="backButtonPC">Back</button>
            </div>
        <br>
        <form id="prospectiveClientsForm" action="listProspectiveClient" method="post">
            <div class="container">
            <div class="col text-center">
                <button class="btn btn-primary" onclick="toggleTable()">List Prospective Clients</button>
            </div>
		<br>
        <div id="lPC">
        <br>
            <table id="ProspectiveClientsList" class="table table-bordered table-striped">
                <c:if test="${listProspectiveClient != null}">
                <thead>
                    <tr>
                        <th>Client ID</th>
                        <th>First Name</th>
                        <th>Last Name</th>
                        <th>Address</th>
                        <th>Phone Number</th>
                        <th>Email</th>
                        <th>Quote ID</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach var="prosclnt" items="${listProspectiveClient}">
                        <tr style="text-align: center">
                            <td><c:out value="${prosclnt.clientID}" /></td>
                            <td><c:out value="${prosclnt.firstName}" /></td>
                            <td><c:out value="${prosclnt.lastName}" /></td>
                            <td><c:out value="${prosclnt.address}" /></td>
                            <td><c:out value="${prosclnt.phoneNumber}" /></td>
                            <td><c:out value="${prosclnt.email}" /></td>
                            <td><c:out value="${prosclnt.quoteID}" /></td>
                        </tr>
                    </c:forEach>
                </tbody>
                </c:if>
            </table>
            </div>
            </div>
        </form>
    </div>

   <script>
    $(document).ready(function() {
        $("#lPC").click(function() {
            $("#ProspectiveClientsList").toggle();
        });

        // Function to go back to admin dashboard
        function backToAdminDashboard() {
            // Assuming you have a form with id "backToDashboardForm"
            document.getElementById("backToDashboardForm").submit();
        }

        // Call the backToAdminDashboard function when clicking the back button
        $("#backButtonPC").click(function() {
            backToAdminDashboard();
        });
    });
</script>

</body>
</html>
