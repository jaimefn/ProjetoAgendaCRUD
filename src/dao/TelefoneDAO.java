package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.SQLSyntaxErrorException;

import connection.SingleConnection;
import constantes.Mensagem;
import model.Telefone;

public class TelefoneDAO {

	private Connection connection = null;

	public TelefoneDAO(Connection connection) {
		this.connection = connection;
	}

	public void Novo(Telefone telefone) throws SQLException {
		
		String sqlInsertTelefone = "INSERT INTO Telefone (tipo_id,numero,contato_Id) VALUES (?,?,?)";
		
		PreparedStatement statement = connection.prepareStatement(sqlInsertTelefone);
		
		statement.setLong(1, telefone.getTipoId());
		statement.setString(2, telefone.getNumero());
		statement.setLong(3, telefone.getContatoId());
		
		int affectedRows = statement.executeUpdate();
		
		if(affectedRows == 0) {
			throw new SQLException(Mensagem.NaoFoiPossivelSalvar);
		}
		
		
	}
	
	public void DeletarPorId(Long id) throws SQLException {
		String sqlDeleteTelefone = "DELETE FROM telefone WHERE id=" + id;
		int affectedRows = 0;
		
				
			PreparedStatement statement = connection.prepareStatement(sqlDeleteTelefone);
			affectedRows = statement.executeUpdate();	

			if(affectedRows == 0) {
				throw new SQLException(Mensagem.NaoFoiPossivelExcluir);
			}
		

	}

	public void DeletarPorContatoId(Long contatoId) throws SQLException {
		String sqlDeleteTelefones = "DELETE FROM telefone WHERE contato_id=" + contatoId;
						
			PreparedStatement statement = connection.prepareStatement(sqlDeleteTelefones);
			statement.executeUpdate();	

	}
}
