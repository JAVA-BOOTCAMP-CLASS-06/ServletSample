package servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet (description = "Muestra una tabla HTML", urlPatterns = "/table")
public class HttpTableSample extends HttpServlet {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 2319314504958811913L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			   throws ServletException, IOException {
		
		List<String[]> data = new ArrayList<String[]>();
		
		data.add(new String [] {"Juan Perez", "25456785", "juan.perez@gmail.com"});
		data.add(new String [] {"Roberto Lopez", "26789545", "roberto.lopez@gmail.com"});
		data.add(new String [] {"Luis Morales", "18254753", "luis.morales@gmail.com"});
		data.add(new String [] {"Jorge Costa", "45785125", "jorge.costa@gmail.com"});
		data.add(new String [] {"Ruben Garcia", "25789411", "ruben.garcia@gmail.com"});
		
		PrintWriter out; out = response.getWriter();
	    response.setContentType("text/html");
	    out.println("<html>");
	    out.println("<body>");
	    out.println("<table>");
	    out.println("<tr>");
	    out.println("<td>NOMBRE</td>");
	    out.println("<td>DNI</td>");
	    out.println("<td>EMAIL</td>");
	    out.println("</tr>");
	    
	    for (String[] arr : data) {
	    	out.println("<tr>");
	    	
	    	for (String str : arr) {
	    	    out.println("<td>" + str + "</td>");
	    	}
	    	
		    out.println("</tr>");
	    }
	    
	    out.println("</table>");
	    out.println("</body>");
	    out.println("</html>");
	}

}
