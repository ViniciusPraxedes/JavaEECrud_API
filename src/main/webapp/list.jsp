<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="com.example.jakartaeecrud.User " %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="java.util.List" %>
<%
    List<User> list = (List<User>) request.getAttribute("users");

%>
<html>
<head>
    <title>Title</title>
</head>
<body>

<a href="createUserForm.html">Create</a>
<table>
    <thead>
        <tr>
           <th>Name</th>
           <th>Email</th>
            <th>Options</th>
        </tr>
    </thead>
    <tbody>
        <%for (int i = 0; i < list.size(); i++){ %>
            <tr>
                <td><%=list.get(i).getName()%></td>
                <td><%=list.get(i).getEmail()%></td>
                <td><a href="select?id=<%=list.get(i).getId()%>">Edit</a></td>
                <td><a href="delete?id=<%=list.get(i).getId()%>">Delete</a></td>
            </tr>
        <% } %>
    </tbody>
</table>

</body>
</html>
