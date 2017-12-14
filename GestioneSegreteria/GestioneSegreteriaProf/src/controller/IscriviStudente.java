package controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class IscriviStudente extends HttpServlet{
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String matricola = req.getParameter("matricola");
		String nome = req.getParameter("nome");
		String cognome = req.getParameter("cognome");
		String dataNascita = req.getParameter("dataNascita");
		String password = req.getParameter("password");
		String indirizzo = req.getParameter("indirizzo");
	
		resp.setContentType("text/html");
		PrintWriter out = resp.getWriter();
		out.println("<html>");
		out.println("<head><title>Iscrizione studente</title></head>");
		out.println("<body>");
		out.println("<h1>Abbiamo iscritto il seguente studente:</h1>");
		out.println(matricola);
		out.println(nome);
		out.println(cognome);
		out.println(dataNascita);
		out.println(password);
		out.println(indirizzo);
		out.println("</body>");
		out.println("</html>");		
	}
}
