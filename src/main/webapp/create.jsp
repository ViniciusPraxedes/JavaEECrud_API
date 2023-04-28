<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
  <h1>Add a new user</h1>
<form name="NewUser" action="insert">
    <table>
        <tr>
            <td><input type="text" name="name" placeholder="Name"></td>
        </tr>
        <tr>
            <td><input type="text" name="email" placeholder="Email"></td>
        </tr>
    </table>
    <input type="button" value="Submit">
</form>
</body>
</html>
