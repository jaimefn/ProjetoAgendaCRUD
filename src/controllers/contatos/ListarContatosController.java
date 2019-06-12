package controllers.contatos;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebFilter;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import constantes.Telas;
import dao.ContatoDAO;
import model.Contato;
import model.Usuario;

@WebServlet("/ListarContatos")
public class ListarContatosController extends HttpServlet{
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
		
		Usuario usuario = (Usuario) req.getSession().getAttribute("usuario");
		
		try {
			req.setAttribute("contatos",getListaDeContatos(usuario.getId()));
			
			RequestDispatcher rd = req.getRequestDispatcher("TelaListarContatos.jsp");
			rd.forward(req, resp);
			
		}catch(Exception e) {
			e.printStackTrace();
			resp.sendRedirect(Telas.getTelaDeErro(e.getMessage()));
		}

					
	}

	public List<Contato> getListaDeContatos(Long usuarioId) throws SQLException {
		ContatoDAO contatoDao = new ContatoDAO(connection);
		return contatoDao.SelecionarContatosPorUsuarioId(usuarioId);
	}

}
