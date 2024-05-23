<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>

</head>
<body>
 
  <% 
     if(session.getAttribute("username")==null){
    	 response.sendRedirect("Login.jsp");
     }
  %> 
  
 
  
  
  <h1>Ticket Booking</h1>
  <div>
     <label>FROM:</label>
     <select id="selectedstation1">
          <option value="" selected>select the station</option>
     </select>
     <label>TO:</label>
     <select id="selectedstation2">
          <option value="" selected>select the station</option>
     </select><br><br>
     <label>Travel Date</label>
     <input type="date" id="selectedDate" name="selectedDate"><br><br>
     <label>Train Name:</label>
     <select id="Trainname">
         <option value="" selected>Select the Train Name</option>
     </select>
     <select id="ticketclass">
         <option value="General" selected>General</option>
         <option value="Sleeper" selected>Sleeper</option>
         <option value="FirstAC" selected>First AC</option>
         <option value="SecondAC" selected>Second AC</option>
         <option value="ThirdAC" selected>Third AC</option>       
     </select>      
  </div>
  
  <script>
  $(document).ready(function() {
	  var dataset;
	  var tday;
	  var from;
	  var to;
      $.ajax({
          url: "AllTrains",
          type: "GET",
          dataType: "json",
          success: function(data) {
        	  dataset=data;
              var dropdown = $("#selectedstation1");
              $.each(data, function(index, stationName) {
                  dropdown.append($("<option>").text(stationName));
              });
          },
          error: function(xhr, status, error) {
              console.error("Error fetching data:", error);
              alert("Error fetching data. Please try again.");
          }
      });
      
      $("#selectedstation1").change(function(){
    	  var selectedstation=$(this).val();
    	  newdata=dataset.filter(function(station){
    		  return station!==selectedstation;
    	  })
    	  from=selectedstation;
    	  populatedata(newdata);
    	  
    	  
      });
      function populatedata(newdata){
    	  var dropdown=$("#selectedstation2");
    	  dropdown.empty();
    	  dropdown.append($("<option>").text("select the station"));
    	  $.each(newdata,function(index,st){
    		  dropdown.append($("<option>").text(st));
    	  });
      }
      
      $("#selectedstation2").change(function(){
    	  to=$(this).val();
      });
      
      $("#selectedDate").on('input', function(){
    	  var selectedDate = document.getElementById('selectedDate').value;
          var day = new Date(selectedDate).toLocaleDateString('en-US', { weekday: 'long' });
          tday=day;
          var dropdown1=$("#Trainname");
          dropdown1.empty();
          dropdown1.append($("<option>").text("select the train"))
    	  $.ajax({
    		  url:'TrainServlet',
    		  type:'POST',
    		  data:{s_from:from,s_to:to,t_day:tday},
    		  success: function(response) {
    			  var trainNamesArray = JSON.parse(response);
    			  $.each(trainNamesArray, function(index, value) {
    				    dropdown1.append($("<option>").text(value));
    				});
    	        },
    	        error: function(xhr, status, error) {
    	            // Handle error response
    	            console.error(status, error);
    	        }
    		  
    	  });  	  
      })
      
  });
  </script>
</body>
</html>