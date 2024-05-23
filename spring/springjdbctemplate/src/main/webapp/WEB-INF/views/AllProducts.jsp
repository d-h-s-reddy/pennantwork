<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="java.util.List" %>
<%@ page import="Models.Products" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/products.css">
</head>
<body>
<div class="maindiv">
   <h1>Products</h1>
   <table class="tb" >
      <tr>
         <th>ID</th>
         <th>Name</th>
         <th>Brand</th>
         <th>Image</th>
         <th>Price</th>
      </tr>
      <% 
         List<Products> p=(List<Products>)request.getAttribute("product");
         for(Products prod:p){
      %>
      <tr>
         <td><%= prod.getProduct_id() %></td>
         <td><%= prod.getProduct_title() %></td>
         <td><%= prod.getProduct_brand() %></td>
         <td><img class="imagedata" src="<%= prod.getProduct_img() %>"/></td>
         <td><%= prod.getProduct_price() %></td>
      </tr>
      <% } %>
   </table>
   </div>
</body>
</html>