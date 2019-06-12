package beans;

import java.util.Collection;
import java.util.EnumSet;
import java.util.Map;

import model.Usuario;

public class Resposta {

	public enum TipoResposta{
		redirect,
		forward
	}
	
	private String url = "index.jsp";
	private TipoResposta tipoResposta  = TipoResposta.redirect;  
	private Usuario usuario;
	
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public TipoResposta getTipoResposta() {
		return tipoResposta;
	}
	public void setTipoResposta(TipoResposta tipoResposta) {
		this.tipoResposta = tipoResposta;
	}
	public Usuario getUsuario() {
		return usuario;
	}
	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	
}
