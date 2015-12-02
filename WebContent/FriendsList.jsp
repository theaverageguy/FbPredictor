<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>BitsPedia - Facebook Friends List</title>
</head>
<body>
    <h1>Login User Information</h1>
    <ul>
        <li>User Facebook ID: <c:out value="${loginUser.id}" /></li>
        <li>First Name: <c:out value="${loginUser.firstName}" /></li>
        <li>Last Name: <c:out value="${loginUser.lastName}"/> </li>
    </ul>
	<h1>Friends List - Who are using the App</h1>
	<table>
		<c:forEach items="${friendsList}" var="user">
			<tr>
				<td><c:out value="${user.name}" /></td>
				<td><img src='https://graph.facebook.com/<c:out value="${user.id}"/>/picture' />
				</td>
			</tr>
		</c:forEach>
	</table>
</body>
</html>