package controllers.contatos;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebFilter;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import constantes.Telas;
import dao.ContatoDAO;

@WebServlet("/ExcluirContato")
public class ExcluirContatoController extends HttpServlet{
	private static final long serialVersionUID = 1L;

	private Connection connection;
	private ContatoDAO contatoDao;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doPost(req,resp);
	}
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		connection =(Connection) req.getAttribute("connection");
		contatoDao = new ContatoDAO(connection);
		
		Long contatoId = Long.valueOf(req.getParameter("contatoId"));
		
		try {
			contatoDao.Deletar(contatoId);
			resp.sendRedirect("ListarContatos");			
		}catch(Exception e) {
			e.printStackTrace();
			resp.sendRedirect(Telas.getTelaDeErro(e.getMessage()));
		}

	}
}
