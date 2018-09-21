<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
<!-- Latest compiled and minified CSS -->
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<style>
*{

font-family:Helvetica;

}
.container{
background-color:black;
color:white;
border-radius:3px;
}

input{
background-color:red;
border-radius:8px;
 border: 0.1px solid black;
 padding:6px;
}

td{

font-size: 12px;
letter-spacing:3px;

}

h1{
letter-spacing:5px;

}

h1:hover { 
    background-color: yellow;
}
</style>



</head>
<body>
<h1>Welcome , <c:out value ="${user.name}"/></h1>
<div class="container">
<h1>All  Ideas</h1>
<table style="width:50%" class="table table-hover">
  <tr>
    <th>Contents</th>


  </tr>
    <c:forEach items="${idea}" var="x">
  <tr>
   <td><a href="/ideas/${x.id}"><c:out value="${x.content}"></c:out></a></td>

    <td><c:out value="${x.creator.name}"></c:out></td>
	 
    <td> <a href="ideas/like/${x.id}"> Like </a> </td>
			
  </tr>
    </c:forEach>

    
    
    
    			
</table>
		<a href="ideas/new" style="color:purple;">Create Ideas </a>
		<a  href="/logout/${user.id}" style="color:purple;margin-left:130px;"> Logout</a>

</div>

</body>
</html>