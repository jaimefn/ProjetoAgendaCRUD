package constantes;

import javax.servlet.http.HttpServletResponse;

public class Telas {

	public static String getTelaDeErro(String msg) {
		return "TelaAcessoNegado.jsp?msg=" + msg;
	}
	
}
