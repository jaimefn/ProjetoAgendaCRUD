package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import connection.SingleConnection;
import constantes.Mensagem;
import model.RedeSocial;

public class RedeSocialDAO {

	private Connection connection = null;

	public RedeSocialDAO(Connection connection) {
		this.connection = connection;
	}

	public void Novo(RedeSocial redeSocial) throws SQLException {

		String sqlInsertRedeSocial = "INSERT INTO Rede_Social (rede,contato_Id) VALUES (?,?)";

		PreparedStatement statement = connection.prepareStatement(sqlInsertRedeSocial);

		statement.setString(1, redeSocial.getRede());
		statement.setLong(2, redeSocial.getContatoId());

		int affectedRows = statement.executeUpdate();

		if (affectedRows == 0) {
			throw new SQLException(Mensagem.NaoFoiPossivelSalvar);
		}
	}

	public void DeletarPorId(Long id) throws SQLException {
		String sqlDeleteRedeSocial = "DELETE FROM rede_social WHERE id=" + id;
		int affectedRows = 0;

		PreparedStatement statement = connection.prepareStatement(sqlDeleteRedeSocial);
		affectedRows = statement.executeUpdate();

		if (affectedRows == 0) {
			throw new SQLException(Mensagem.NaoFoiPossivelExcluir);
		}
	}

	public void DeletarPorContatoId(Long contatoId) throws SQLException {
		String sqlDeleteRedesSociais = "DELETE FROM rede_social WHERE contato_id=" + contatoId;

		PreparedStatement statement = connection.prepareStatement(sqlDeleteRedesSociais);
		statement.executeUpdate();

	}

}
