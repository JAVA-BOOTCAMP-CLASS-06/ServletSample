package servlets;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.Serial;
import java.util.Arrays;
import java.util.Map;
import java.util.Map.Entry;

@WebServlet (description = "Recibe parametros en ambos metodos GET y POST", urlPatterns = "/method")
public class GetAndPost extends HttpServlet {
	
	/**
	 * 
	 */
	@Serial
	private static final long serialVersionUID = 2319314504958811913L;
	
	enum Method {
		GET ("GET"),
		POST ("POST");
		
		private final String description;
		
		Method(String desc) {
			this.description = desc;
		}
		
		public String getDescription() {
			return this.description;
		}
	}
	

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			   throws IOException {
		
		Map<String, String[]> parametros = request.getParameterMap();
		
		PrintWriter out = response.getWriter();
	    response.setContentType("text/html");
	    
	    printParameters(parametros, out, Method.GET);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			   throws IOException {
		
		Map<String, String[]> parametros = request.getParameterMap();
	
		PrintWriter out = response.getWriter();
	    response.setContentType("text/html");

	    printParameters(parametros, out, Method.POST);
	}
	
	private void printParameters(Map<String, String[]> p, PrintWriter o, Method m) {
	    o.println("<html>");
	    o.println("<body>");
	    o.println("<p><b>" + m.getDescription() + "</b></p>");
	    
	    o.println("<table>");
	    o.println("<tr>");
	    o.println("<td>ID</td>");
	    o.println("<td>VALOR</td>");
	    o.println("</tr>");
	    
	    for (Entry<String, String[]> entry : p.entrySet()) {
		    o.println("<tr>");
		    o.print("<td>");
		    o.print(entry.getKey());
		    o.println("</td>");
		    o.print("<td>");
		    o.print(String.join(" | ", Arrays.asList(entry.getValue())));
		    o.println("</td>");
		    o.println("</tr>");
	    }
	    
	    o.println("</table>");
	    
	    o.println("</body>");
	    o.println("</html>");
		
	}
	
}
