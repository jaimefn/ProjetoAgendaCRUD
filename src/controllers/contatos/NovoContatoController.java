package controllers.contatos;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import constantes.Telas;
import model.Contato;
import model.Usuario;

@WebServlet("/NovoContato")
public class NovoContatoController extends HttpServlet {
	private static final long serialVersionUID = 1L;
 
    public NovoContatoController() {
        super();
    }

	
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doPost(req, resp);
	}

	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
			try {
			Usuario usuario =(Usuario) req.getSession().getAttribute("usuario");
			
			Contato contato = new Contato();
			contato.setUsuarioId(usuario.getId());
			contato.setId(0L);

			req.setAttribute("titulo", "Criar Novo Contato");
			req.setAttribute("contato", contato);
			
			RequestDispatcher rd = req.getRequestDispatcher("TelaCadastrarEditarContato.jsp");
			rd.forward(req, resp);
			
			}catch(Exception e) {
				e.printStackTrace();
				resp.sendRedirect(Telas.getTelaDeErro(e.getMessage()));
			}
	}

	
	
	
}
