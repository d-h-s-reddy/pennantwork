<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <title>Employee</title>
        <meta name="description" content="">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
    </head>
    <body>
       <form id="employeedata">
        <label for="firstname">First Name:</label>
        <input type="text" name="firstname" id="firstname"><br>
        <label for="lastname">Last Name:</label>
        <input type="text" name="lastname" id="lastname"><br>
        <label for="empname">Gender:</label>
        <input type="radio" name="gender" id="male" value="male">Male
        <input type="radio" name="gender" id="female" value="female">Female<br>
        <label for="email">Email</label>
        <input type="email" name="email" id="email"><br>
        <label for="phno">Phone no</label>
        <input type="text" name="phno" id="phno"><br>
        <label for="address">Address</label>
        <textarea id="address" name="address" rows="4" cols="50"></textarea><br>
        <input type="submit" value="submit">
       </form>

       <button id="getemployees">Get Employees</button>
       <div id="displaydata"></div>
       <button id="updateemployee">Update Employee</button><br>
       <button id="deleteemployee">Delete Employee</button>
       <script>
         $(document).ready(function(){

            $("#employeedata").submit(function(event){
                event.preventDefault(); 

                var formdata={
                    firstName: $("#firstname").val(),
                    lastName: $("#lastname").val(),
                    gender: $("input[name='gender']:checked").val(),
                    email: $("#email").val(),
                    phno: parseInt($("#phno").val(), 10),
                    address: $("#address").val()
                };
                console.log(formdata);
                $.ajax({
                    url: "http://localhost:9533/SpringBootorm/addempl",
                    type: "POST",
                    contentType: "application/json",  // Corrected here
                    data: JSON.stringify(formdata),
                    success: function(data){
                        alert("Data added successfully");
                    },
                    error: function(xhr, textStatus, errorThrown) {
                        console.error("There was a problem with the AJAX request:", errorThrown);
                    }
                });
            });
            $("#getemployees").click(function(){
                $.ajax({
                    url:"http://localhost:9533/SpringBootorm/getempl",
                    type:"GET",
                    contentType:"application/json",
                    dataType:'json',
                    success:function(data){
                        displayemployees(data)
                    },
                    error: function(xhr, textStatus, errorThrown) {
                        console.error("There was a problem with the AJAX request:", errorThrown);
                    }
                })

            })
            function displayemployees(data){
                var tableHtml = '<table border="1">';
                // Add table header
                tableHtml += '<tr>';
                $.each(data[0], function(key, value) {
                    tableHtml += '<th>' + key + '</th>';
                });
                tableHtml += '</tr>';
                // Add table rows
                $.each(data, function(index, item) {
                    tableHtml += '<tr>';
                    $.each(item, function(key, value) {
                        tableHtml += '<td>' + value + '</td>';
                    });
                    tableHtml += '</tr>';
                });
                tableHtml += '</table>';
                // Append the generated table HTML to the table-container div
                $("#displaydata").html(tableHtml);
            }

            $("#updateemployee").click(function(){
                $("#displaydata").empty();
                var form = $('<form>');
                //set the id to the form
                form.attr('id', 'myForm');

                //creating the labels
                var label1=$('<label>').text('Enter the employee id:');
                form.append(label1);
                var input1=$('<input>').attr('type', 'text').attr('id', 'empid').attr('name','empid');
                form.append(input1);
                form.append('<br>');
                var label2=$('<label>').text('Enter the Email id:');
                form.append(label2);
                var input2=$('<input>').attr('type','email').attr('id','emailid').attr('name','emailid');
                form.append(input2);
                var subbutton = $('<input>').attr('type', 'submit').attr('value', 'Submit');
                form.append(subbutton);
                $("#displaydata").append(form);

                form.submit(function(event){
                    event.preventDefault();
                    console.log($('#empid').val());
                    var d= $(this).serialize();
                    console.log(d);
                    $.ajax({
                        url:"http://localhost:9533/SpringBootorm/updateemp",
                        type:"PUT",
                        data:d,
                        success:function(data){
                            alert(data);
                        },
                        error: function(xhr, textStatus, errorThrown) {
                        console.error("There was a problem with the AJAX request:", errorThrown);
                    }
                    })
                    
                })
            })
            $("#deleteemployee").click(function(){
            	$("displaydata").empty();
            	var form=$('<form>');
            	var label1=$('<label>').text('Enter the employee id');
            	form.append(label1);
            	var input1=$('<input>').attr('id','empid').attr('type','text').attr('name','empid');
            	form.append(input1);
            	var subbutton = $('<input>').attr('type', 'submit').attr('value', 'Submit');
                form.append(subbutton);
                $("#displaydata").append(form);
                form.submit(function(event){
                	event.preventDefault();
                	var d=$(this).serialize();
                	$.ajax({
                		url:"http://localhost:9533/SpringBootorm/delemp",
                		type:"DELETE",
                		data:d,
                		success:function(data){
                			alert(data);
                		},
                		 error: function(xhr, textStatus, errorThrown) {
                             console.error("There was a problem with the AJAX request:", errorThrown);
                         }
                	})
                })
            	
            })
            
         });
       </script>
    </body>
</html>