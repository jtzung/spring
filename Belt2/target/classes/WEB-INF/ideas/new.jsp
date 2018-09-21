 <%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
    <%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%> 
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
		<p><c:out value="${errors}" /></p>	
   <form:form action="/ideas/new" method="post" modelAttribute="idea">
	<table class="table">
     <thead>
     <tr>
     		<th> Content</th>
     		
     		
      </tr>
      </thead>
      <tbody>

	      	       
		
				      
<tr>
	<td>    

        <form:label path="content"> Content </form:label>
        <form:errors path="content"/>
        <form:input path="content"/>
     <input type="hidden" name="creator" value=${ user } >
    	
	</td>



</tr>
</tbody>	
        	
        

	
	</table>
    <input  style="color:red; background-color:black;" type="submit" value="Create"/>
</form:form>  

</body>
</html>