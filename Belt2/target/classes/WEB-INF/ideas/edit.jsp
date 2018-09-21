<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>  
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>  
<%@ page isErrorPage="true" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
<style>
*{
font-family:Helvetica;

}
.container{
background-color:black;
color:white;
border-radius:3px;
height:300px;
}

input{
background-color:red;
border-radius:8px;
 border: 0.1px solid black;
 padding:6px;
 height:30px;
 width:160px;
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

<form:form action="${idea.id}" method="put" modelAttribute="idea">
<h1 style="letter-spacing:10px;"> EDIT :<c:out value ="${current_idea.content}"/> </h1>

<form:label path="content">Content</form:label>
        <form:errors path="content"/>
        <form:input value ="${current_idea.content}" path="content"/> 
        <input type="hidden" name="creator" value=${ user } >
<input type="submit" value="Update">
</form:form>

</div>
</body>
</html>