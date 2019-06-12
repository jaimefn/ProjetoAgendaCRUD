package dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import connection.SingleConnection;
import constantes.Mensagem;
import model.Contato;
import model.Telefone;
import model.Usuario;

public class UsuarioDAO {

	private Connection connection = null;

	public UsuarioDAO(Connection connection) {
		this.connection = connection;
	}

	public Long Novo(Usuario usuario) throws SQLException {
		connection = SingleConnection.getConnection();

		String sqlInsertUsuario = "INSERT INTO usuario (nome,usuario,senha,endereco,foto_url,data_nascimento,data_cadastro,telefone,email) VALUES (?,?,?,?,?,?,?,?,?)";

		PreparedStatement statement = connection.prepareStatement(sqlInsertUsuario, Statement.RETURN_GENERATED_KEYS);

		statement.setString(1, usuario.getNome());
		statement.setString(2, usuario.getUsuario());
		statement.setString(3, usuario.getSenha());
		statement.setString(4, usuario.getEndereco());
		statement.setString(5, usuario.getFotoUrl());
		statement.setDate(6, Date.valueOf(usuario.getDataNascimento().toString()));
		statement.setDate(7, Date.valueOf(java.time.LocalDate.now()));
		statement.setString(8, usuario.getTelefone());
		statement.setString(9, usuario.getEmail());

		int affectedRows = statement.executeUpdate();

		if (affectedRows == 0) {
			throw new SQLException("Nao foi possivel Salvar usuario");
		}

		ResultSet rs = statement.getGeneratedKeys();

		if (rs.next()) {

			return rs.getLong("id");

		} else {
			throw new SQLException(Mensagem.NaoRetornouId);
		}
	}

	public void DeletarPorId(Long id) throws SQLException {
		String sqlDeleteUsuario = "DELETE FROM usuario WHERE id=" + id;
		int affectedRows = 0;

			PreparedStatement statement = connection.prepareStatement(sqlDeleteUsuario);
			affectedRows = statement.executeUpdate();

			if (affectedRows == 0) {
				throw new SQLException(Mensagem.NaoFoiPossivelExcluir);
			}

	}

	public Long ValidarLogin(String usuario, String senha) throws SQLException {
	
		String sql = "SELECT id from usuario where usuario='" + usuario + "' and senha='" + senha + "';";
		Long id = 0L;

		PreparedStatement statement = connection.prepareStatement(sql);
		ResultSet rs = statement.executeQuery();

		if (rs.next()) {
			id = rs.getLong(1);
		} else {
			throw new SQLException(Mensagem.UsuarioOuSenhaInvalidos);
		}
		return id;
	}

	public Usuario Buscar(Long id) throws SQLException {
		
		Usuario usuario = new Usuario();
		
		String sql = "SELECT * from usuario where id=" + id;
		
		PreparedStatement statement = connection.prepareStatement(sql);
		ResultSet rs = statement.executeQuery();

		if (rs.next()) {
			usuario.setId(rs.getLong("id"));
			usuario.setNome(rs.getString("nome"));
			usuario.setEndereco(rs.getString("endereco"));
			usuario.setTelefone(rs.getString("telefone"));
			usuario.setEmail(rs.getString("email"));
			
		} else {
			throw new SQLException(Mensagem.NaoFoiPossivelLocalizar);
		}
		
		return usuario;
	}
}
