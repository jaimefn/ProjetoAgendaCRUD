package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import connection.SingleConnection;
import constantes.Mensagem;
import model.Email;

public class EmailDAO {

	private Connection connection = null;
	
	
	public EmailDAO(Connection connection){
		this.connection = connection;
	}

	public void Novo(Email email) throws SQLException {
		
		String sqlInsertEmail = "INSERT INTO Email (email,contato_Id) VALUES (?,?)";
	
		
		PreparedStatement statement = connection.prepareStatement(sqlInsertEmail);
		
		statement.setString(1, email.getEmail());
		statement.setLong(2, email.getContatoId());
		
		int affectedRows = statement.executeUpdate();
		
		if(affectedRows == 0) {
			throw new SQLException(Mensagem.NaoFoiPossivelSalvar);
		}
	}
	
	public void DeletarPorId(Long id) throws SQLException {
		String sqlDeleteEmail = "DELETE FROM email WHERE id=" + id;
		int affectedRows = 0;
						
			PreparedStatement statement = connection.prepareStatement(sqlDeleteEmail);
			affectedRows = statement.executeUpdate();	
			
			if(affectedRows == 0) {
				throw new SQLException(Mensagem.NaoFoiPossivelExcluir);
			}
		
						
	}

	public void DeletarPorContatoId(Long contatoId) throws SQLException {
		String sqlDeleteEmails = "DELETE FROM email WHERE contato_id=" + contatoId;
		
			PreparedStatement statement = connection.prepareStatement(sqlDeleteEmails);
			statement.executeUpdate();	
}

}
