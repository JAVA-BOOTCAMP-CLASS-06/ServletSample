package servlets;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.Serial;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@WebServlet (description = "Verifica la existencia de la session", urlPatterns = "/session")
public class SessionCookies extends HttpServlet {
	
	/**
	 * 
	 */
	@Serial
	private static final long serialVersionUID = 2319314504958811913L;

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			   throws IOException {

		HttpSession session = request.getSession();

		Optional.ofNullable(request.getParameter("valor"))
				.ifPresent(v -> {
					System.out.println("VALUE [" + v + "]");

					List<String> values = Optional.ofNullable(session.getAttribute("valores"))
												.map(List.class::cast)
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
			   throws IOException {
		
		doGet(request, response);
	}
	
}
