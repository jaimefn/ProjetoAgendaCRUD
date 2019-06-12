package dao;

import java.sql.Connection;
import java.sql.SQLException;

import constantes.Mensagem;
import model.Contato;
import model.Email;
import model.RedeSocial;
import model.Telefone;

public class AgendaDAO {

	private Connection connection = null;
	private ContatoDAO contatoDao = null;
	private TelefoneDAO telefoneDao = null;
	private EmailDAO emailDao = null;
	private RedeSocialDAO redeSocialDao =null;

	public AgendaDAO(Connection connection){
		this.connection = connection;
		 contatoDao = new ContatoDAO(connection);
		 telefoneDao = new TelefoneDAO(connection);
		 emailDao = new EmailDAO(connection);
		 redeSocialDao = new RedeSocialDAO(connection);

	}

	
	public void NovoContato(Contato contato) throws SQLException {
		

			Long newContactId = contatoDao.Novo(contato);
/*
			for (RedeSocial redeSocial : contato.getRedesSociais()) {
				redeSocial.setContatoId(newContactId);
				redeSocialDao.Novo(redeSocial);
			}

			for (Email email : contato.getEmails()) {
				email.setContatoId(newContactId);
				emailDao.Novo(email);
			}

			for (Telefone telefone : contato.getTelefones()) {
				telefone.setContatoId(newContactId);
				telefoneDao.Novo(telefone);
			}
*/

	}

	public void DeletarContato(Long contatoId) throws SQLException {
		
			telefoneDao.DeletarPorContatoId(contatoId);
			emailDao.DeletarPorContatoId(contatoId);
			redeSocialDao.DeletarPorContatoId(contatoId);
			contatoDao.Deletar(contatoId);

	}

	
}
