<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>    

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Registration Page</title>

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
background-color:#8eb3f2;
border-radius:8px;
 border: 0.1px solid black;
 padding:6px;
}

h1{
letter-spacing:5px;
}

</style>

</head>
<body>
    <div class="container">
    <h1>Register!</h1>
 
    <table>
    
    <form:form method="POST" action="/registration" modelAttribute="user">

        <td>
            <form:label path="name">Name:</form:label>
        	<form:errors path="name"/>
            <form:input path="name"/>
        </td>
        
        
        <td>
            <form:label path="email">Email:</form:label>
            <form:errors path="email"/>
            <form:input type="email" path="email"/>
        </td>
        <td>
            <form:label path="password">Password:</form:label>
            <form:errors path="password"/>
            <form:password path="password"/>
        </td>
        <td>
            <form:label path="passwordConfirmation">Password Confirmation:</form:label>
            
            <form:password path="passwordConfirmation"/>
        </td>
        <input type="submit" value="Register!"/>
    </form:form>
    </table>
    
    <h1>Login</h1>
    <p><c:out value="${errors}" /></p>
    <form method="post" action="/login">
        <p>
            <label type="email" for="email">Email</label>
            <input style="margin-left:30px;" type="text" id="email" name="email"/>
     
        </p>
        <p>
            <label for="password">Password</label>
            <input type="password" id="password" name="password"/>
        </p>
        <input type="submit" value="Login!"/>
    </form>    
  </div>  
</body>
</html>