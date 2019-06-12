package interfaces;

import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;

import beans.Resposta;
import model.Usuario;

public interface IFactory {
	public HttpServletRequest Executa(HttpServletRequest req) throws SQLException;
}
