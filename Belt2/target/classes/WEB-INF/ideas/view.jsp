<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>  
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<style>
*{
font-family:Helvetica;

}
.container{
background-color:black;
color:#8eb3f2;
border-radius:3px;
height:300px;
}

input{
background-color:#8eb3f2;
border-radius:8px;
 border: 0.1px solid black;
 padding:6px;
 color:white;
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
	<div class="container">
	<h1> Created By :<c:out value="${idea.creator.name }"></c:out></h1>
<form:form action="/shows/edit/${idea.id}" method="put" modelAttribute="idea">


<table style="width:50%" class="table table-hover">
  <tr>
    <th>Idea</th>
  
 
  </tr>
  
  <tr>
   <td><c:out value="${idea.content}"></c:out></td>

  
  				
  	
  	
  </tr>
  



    
    
    
    			
</table>
			<input type="submit" value="Submit">
</form:form>  
           <form action="/ideas/delete/${idea.id}" method="post">
    		<input type="hidden" name="_method" value="delete">
    		<input type="submit" value="Delete">
			</form>

<a href="/ideas/edit/${idea.id}">Edit</a>

</div>
</body>
</html>