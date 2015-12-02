package com.bitspedia.servlets;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLEncoder;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.restfb.Connection;
import com.restfb.DefaultFacebookClient;
import com.restfb.Facebook;
import com.restfb.FacebookClient;
import com.restfb.exception.FacebookException;
import com.restfb.types.Page;
import com.restfb.types.User;

import facebook4j.FacebookFactory;

public class FriendsListServlet extends HttpServlet {
    public static String APP_ID = "1641244949462734";
    public static String APP_SECRET = "29782aa73ce872efaec066be4893f9df";
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String code = request.getParameter("code");

        String URLEncodedRedirectURI = URLEncoder.encode("http://localhost:8080/FbPredict/FriendsListServlet");
		String MY_ACCESS_TOKEN = "";

		String authURL = "https://graph.facebook.com/oauth/access_token?" +
                "client_id=" + FriendsListServlet.APP_ID + "&" +
                "redirect_uri=" + URLEncodedRedirectURI + "&" +
                "client_secret=" + FriendsListServlet.APP_SECRET + "&" +
                "code=" + code;

        URL url = new URL(authURL);

		String result = readURL(url);
		String[] pairs = result.split("&");

		for (String pair : pairs) {

			String[] kv = pair.split("=");
			if (kv[0].equals("access_token")) {
				MY_ACCESS_TOKEN = kv[1];
			}
		}
		FacebookClient facebookClient = new DefaultFacebookClient(MY_ACCESS_TOKEN, FriendsListServlet.APP_SECRET);
        Connection<User> friends = null;
        
        

		try {
            User loginUser = facebookClient.fetchObject("me", User.class);
            request.setAttribute("loginUser", loginUser);
            friends = facebookClient.fetchConnection("/me/friends", User.class);
            

		} catch (FacebookException e) {
			e.printStackTrace();
		}
        
	//	Facebook facebook = (Facebook) new FacebookFactory().getInstance();
		
		Page page = facebookClient.fetchObject("cocacola", Page.class);
		System.out.println("Page likes: " + page.getLikes());
		List<User> friendsList = friends.getData();
		request.setAttribute("friendsList",friendsList);

		getServletConfig().getServletContext().getRequestDispatcher("/FriendsList.jsp").forward(request, response);
	}

	private String readURL(URL url) throws IOException {
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		InputStream is = url.openStream();
		int r;
		while ((r = is.read()) != -1) {
			baos.write(r);
		}
		return new String(baos.toByteArray());
	}
}
