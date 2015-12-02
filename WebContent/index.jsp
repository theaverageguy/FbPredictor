<%@ page import="java.net.URLEncoder" %>
<%@ page import="com.bitspedia.servlets.FriendsListServlet" %>
<!DOCTYPE html>
<html>
<head>
<title>BitsPedia - Get Facebook Friends Using-app using Java </title>
</head>
<body>
<h2>
	<a href="https://graph.facebook.com/oauth/authorize?client_id=<%=FriendsListServlet.APP_ID%>&scope=email,user_friends&redirect_uri=<%=URLEncoder.encode("http://localhost:8080/FbPredict/FriendsListServlet")%>">Click Here to Login Using Facebook</a>
</h2>
</body>
</html>