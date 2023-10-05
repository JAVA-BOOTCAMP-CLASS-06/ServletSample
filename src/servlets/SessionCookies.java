package servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Optional;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet (description = "Verifica la existencia de la session", urlPatterns = "/session")
public class SessionCookies extends HttpServlet {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 2319314504958811913L;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			   throws ServletException, IOException {

		HttpSession session = request.getSession();

		Optional.ofNullable(request.getParameter("valor"))
				.ifPresent(v -> {
					System.out.println("VALUE [" + v + "]");

					List<String> values = Optional.ofNullable(session.getAttribute("valores"))
												.map(ArrayList.class::cast)
												.orElse(new ArrayList<String>());
					values.add(v);

					System.out.println("VALORES -> " + values);
					session.setAttribute("valores", values);

				});
		

		Cookie cookie = new Cookie("SAMPLE_COOKIE", "1234567");
		cookie.setMaxAge(60); 
		cookie.setPath("/ServletSample");

		
		response.addCookie(cookie);
		
		PrintWriter out; out = response.getWriter();
	    response.setContentType("text/html");
	    out.println("<html>");
	    out.println("<body>");
	    out.println("<p><b>Session & Cookie Sample</b></p>");
	    out.println("</body>");
	    out.println("</html>");
	    
		
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			   throws ServletException, IOException {
		
		doGet(request, response);
	}
	
}
