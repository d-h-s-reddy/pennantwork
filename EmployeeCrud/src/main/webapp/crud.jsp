<%@ page import="java.util.List" %>
<%@ page import="sanju.Employee" %>
<%@ page import="java.sql.Connection" %>
<%@ page import="java.sql.DriverManager" %>
<%@ page import="java.sql.PreparedStatement" %>
<%@ page import="java.sql.ResultSet" %>
<%@ page import="java.sql.SQLException" %>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">

<title>CRUD</title>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<link rel="stylesheet" href="style.css">
</head>
<body>

<div id="background">
<center>
    <div id="d0">
        <h1>USER CRUD</h1>
    </div>
</center>

    <form action="crudserve" method="post">
        <label class="lbl1" style="margin-left: 400px;">Employee ID:</label>
         <input type="text" name="Empid">
        <label class="lbl2" style="margin-left: 100px;">Employee Name:</label> 
        <input type="text" name="Empname"><br><br>
        <label class="lbl1" style="margin-left: 400px;">Job: </label>
        <input type="text" style="margin-left: 60px;" name="job">
        <label class="lbl2" style="margin-left: 109px;">Department: </label>
         <input type="text" style="margin-left: 30px;" name="Department"><br><br>
        <center><label class="lbl" style="color:white;">Salary: </label>
         <input type="text" name="Sal"><br><br></center>
<center> <button type ="submit" name="action" id="add"value="add">Add</button>
 <button type ="submit" name="action" id="del" value="delete">Delete</button>
 <button type ="submit" name="action" id="edit" value="update">Update</button>
<button type="button"  id="first" value="first">First</button>
<button type="button"  id="last" value="last">Last</button>

 </form>
  <button id="next" type="button">Next</button>
  <button type="button" id="prev">Previous</button>
  <br><br></center>
<center>
    <!-- Table to display employee records -->
    <table id="employeeTable" border="1">
        <tr>
            <th>Employee ID</th>
            <th>Employee Name</th>
            <th>Job</th>
            <th>Department</th>
            <th>Salary</th>
        </tr>
        <% 
            List<Employee> emp1 = (List<Employee>) request.getAttribute("ed");
            if (emp1 != null) {
                for (Employee emp2 : emp1) {
        %>
        <tr id="data">
            <td><%= emp2.getEmpid() %></td>
            <td><%= emp2.getEmp_name() %></td>
            <td><%= emp2.getJob() %></td>
            <td><%= emp2.getDept()%></td>
            <td><%= emp2.getSalary() %></td>
        </tr>
        <%
                }
            }
        %>
    </table></center>

<script>
var rowIndex = 0; // Initialize to 0
var rows;

document.addEventListener("DOMContentLoaded", function() {
    var table = document.getElementById("employeeTable");
    rows = table.getElementsByTagName("tr");

    function updateFormFields(row, rowIndex) {
        var cells = row.getElementsByTagName("td");
        document.getElementsByName("Empname")[0].value = cells[1].innerHTML;
        document.getElementsByName("Empid")[0].value = cells[0].innerHTML;
        document.getElementsByName("job")[0].value = cells[2].innerHTML;
        document.getElementsByName("Department")[0].value = cells[3].innerHTML;
        document.getElementsByName("Sal")[0].value = cells[4].innerHTML;
    }

    for (var i = 1; i < rows.length; i++) {
        rows[i].addEventListener("click", function() {
            rowIndex = Array.prototype.indexOf.call(rows, this);
            updateFormFields(this, rowIndex); 
        });
    }

    function moveToNext() {
        if (rowIndex < rows.length - 1) {
            rowIndex++;
            updateFormFields(rows[rowIndex], rowIndex);
        }
    }

    function moveToPrev() {
        if (rowIndex > 1) {
            rowIndex--;
            updateFormFields(rows[rowIndex], rowIndex);
        }
    }
    document.getElementById("first").addEventListener("click", function() {
        rowIndex = 1;
        updateFormFields(rows[rowIndex], rowIndex);
    });
    document.getElementById("last").addEventListener("click", function() {
        rowIndex = rows.length - 1;
        updateFormFields(rows[rowIndex], rowIndex);
    });
    document.getElementById("prev").addEventListener("click", function() {
        moveToPrev();
        console.log('Moving back');
    });

    document.getElementById("next").addEventListener("click", function() {
        moveToNext();
        console.log('Moving forward');
    });
});
</script>
</body>
</html>
