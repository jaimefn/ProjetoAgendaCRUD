package controllers.contatos;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

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

@WebServlet("/SalvarContato")
public class SalvarContatoController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private Connection connection;
	private ContatoDAO contatoDao;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doPost(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		connection = (Connection) req.getAttribute("connection");
		contatoDao = new ContatoDAO(connection);
		Contato contato = new Contato();

		try {
			contato = loadContatoViaParametros(req);

			if (contato.getId() == 0)
				contatoDao.Novo(contato);
			else
				contatoDao.Atualizar(contato);

			resp.sendRedirect("ListarContatos");
			
		} catch (SQLException | IllegalArgumentException e) {
			e.printStackTrace();
			resp.sendRedirect(Telas.getTelaDeErro(e.getMessage()));
		}

	}

	private Contato loadContatoViaParametros(HttpServletRequest req) throws IllegalArgumentException {

		Contato contato = new Contato();
try {
		Long contatoId = Long.valueOf(req.getParameter("contatoId").toString());
		Usuario usuario = (Usuario) req.getSession().getAttribute("usuario");
		Long usuarioId = usuario.getId() ;

		contato.setId(contatoId);
		contato.setUsuarioId(usuarioId);

		contato.setNome(req.getParameter("nome"));
		contato.setEndereco(req.getParameter("endereco"));
		contato.setEmail(req.getParameter("email"));
		contato.setTelefone(req.getParameter("telefone"));
		contato.setRedeSocial(req.getParameter("redeSocial"));

		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		String date = req.getParameter("dataNascimento");
		LocalDate dataNascimento = LocalDate.parse(date, formatter);
		contato.setDataCadastro(null);

			contato.setDataNascimento(dataNascimento);
	
}catch(Exception e) {
	throw new IllegalArgumentException(e.getMessage());
}
			
			
		return contato;
	}

}
