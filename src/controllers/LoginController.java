package controllers;

import java.io.IOException;
import java.sql.Connection;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.ws.Dispatch;

import org.junit.Test;

import connection.SingleConnection;
import dao.UsuarioDAO;
import model.Usuario;
import sun.rmi.server.Dispatcher;

@WebServlet("/Login")
public class LoginController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private Connection connection = null;

	public LoginController() {
		super();
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doPost(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		connection = (Connection) req.getAttribute("connection");

		UsuarioDAO usuarioDao = new UsuarioDAO(connection);
		Usuario usuario = new Usuario();

		String acao = req.getParameter("acao");
		String user = req.getParameter("usuario");
		String senha = req.getParameter("senha");

		try {

			if (acao.equals("Sair")) {
				
				req.getSession().invalidate();
				resp.sendRedirect("index.jsp");
				
			} else if (acao.equals("Entrar")) {

				Long usuarioId = usuarioDao.ValidarLogin(user, senha);
				usuario = usuarioDao.Buscar(usuarioId);

				req.getSession().setAttribute("usuario", usuario);

				resp.sendRedirect("ListarContatos");
			} else {

				throw new Exception("Endereco invalido!");
			}
		} catch (Exception e) {
			e.printStackTrace();
			resp.sendRedirect("TelaAcessoNegado.jsp?msg=" + e.getMessage());
		}

	}

}
