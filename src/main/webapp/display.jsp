<%@page import="java.sql.DriverManager"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.Statement"%>
<%@page import="java.sql.Connection"%>
<%@page import="p1.confirm" %>
<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html; charset=ISO-8859-1" %>

<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html;charset=ISO-8859-1">
<title>confirmation</title>
</head>
<body>
<h1>Displaying Student List</h1>
	<table border ="1">
		<tr bgcolor="00FF7F">
		<th><b>Student Name</b></th>
		<th><b>Student Age</b></th>
		<th><b>Course Undertaken</b></th>
		</tr>



<%ArrayList<confirm> conf= (ArrayList<confirm>)request.getAttribute("conf");
for(confirm p:conf){%>
<tr>
 <td><%=p.getcid()%></td>
 

</tr>
<%}%>

	</table>
		<hr/>
</body>
</html>



