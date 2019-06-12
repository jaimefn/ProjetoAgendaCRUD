package dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import org.eclipse.jdt.internal.compiler.ast.ThrowStatement;

import connection.SingleConnection;
import constantes.Mensagem;
import model.Contato;
import model.Email;
import model.RedeSocial;
import model.Telefone;

public class ContatoDAO {

	private Connection connection = null;

	public ContatoDAO(Connection connection) {
		this.connection = connection;
	}

	public Long Novo(Contato contato) throws SQLException {

		String sqlInsertContato = "INSERT INTO contato (nome,endereco,foto,usuario_id,data_nascimento,data_cadastro,telefone,rede_social,email) VALUES (?,?,?,?,?,?,?,?,?)";

		PreparedStatement statement = connection.prepareStatement(sqlInsertContato, Statement.RETURN_GENERATED_KEYS);

		statement.setString(1, contato.getNome());
		statement.setString(2, contato.getEndereco());
		statement.setString(3, contato.getFoto());
		statement.setLong(4, contato.getUsuarioId());
		statement.setDate(5, Date.valueOf(contato.getDataNascimento().toString()));
		statement.setDate(6, Date.valueOf(java.time.LocalDate.now()));
		statement.setString(7, contato.getTelefone());
		statement.setString(8, contato.getRedeSocial());
		statement.setString(9, contato.getEmail());

		int affectedRows = statement.executeUpdate();

		if (affectedRows == 0) {
			throw new SQLException(Mensagem.NaoFoiPossivelSalvar);
		}

		ResultSet rs = statement.getGeneratedKeys();

		if (rs.next()) {
			return rs.getLong("id");
		} else {
			throw new SQLException(Mensagem.NaoRetornouId);
		}

	}

	public List<Contato> SelecionarContatosPorUsuarioId(Long usuarioId) throws SQLException {

		List<Contato> contatos = new ArrayList<Contato>();

		String sql = "select * from contato where usuario_id=" + usuarioId;

		PreparedStatement statement = connection.prepareStatement(sql);
		ResultSet rs = statement.executeQuery();

		while (rs.next()) {

			Contato contato = new Contato();

			contato.setId(rs.getLong("id"));
			contato.setUsuarioId(rs.getLong("usuario_id"));
			contato.setNome(rs.getString("nome"));
			contato.setEndereco(rs.getString("endereco"));
			contato.setTelefone(rs.getString("telefone"));
			contato.setEmail(rs.getString("email"));
			contato.setRedeSocial(rs.getString("rede_social"));

			// DateTimeFormatter formatterNascimento =
			// DateTimeFormatter.ofPattern("yyyy-MM-dd");
			// DateTimeFormatter formatterCadastro = DateTimeFormatter.ofPattern("yyyy-MM-dd
			// HH:mm:ss");

			// LocalDate dataNascimento = rs.getDate("data_nascimento").toLocalDate();
			// contato.setDataNascimento(dataNascimento);

			contatos.add(contato);
		}

		return contatos;
	}

	public void Deletar(Long id) throws SQLException {
		String sqlDeleteContato = "DELETE FROM contato WHERE id=" + id;
		int affectedRows = 0;

		PreparedStatement statement = connection.prepareStatement(sqlDeleteContato);
		affectedRows = statement.executeUpdate();

		if (affectedRows == 0) {
			throw new SQLException(Mensagem.NaoFoiPossivelExcluir);
		}

	}

	public Contato BuscarContato(long contatoId) throws SQLException {
		Contato contato = new Contato();
		
		String sql = "select * from contato where id=" + contatoId;

		PreparedStatement statement = connection.prepareStatement(sql);

		ResultSet rs = statement.executeQuery();

		if (rs.next()) {
			contato.setId(rs.getLong("id"));
			contato.setUsuarioId(rs.getLong("usuario_id"));
			contato.setNome(rs.getString("nome"));
			contato.setEndereco(rs.getString("endereco"));
			contato.setTelefone(rs.getString("telefone"));
			contato.setEmail(rs.getString("email"));
			contato.setRedeSocial(rs.getString("rede_social"));

			Date dataNascimento = rs.getDate("data_nascimento");

			if (dataNascimento != null) {
				contato.setDataNascimento(rs.getDate("data_nascimento").toLocalDate());
			}

			return contato;

		} else {
			throw new SQLException(Mensagem.NaoFoiPossivelLocalizar);
		}

	}

	public void Atualizar(Contato contato) throws SQLException {
		String sqlUpdateContato = "UPDATE contato SET nome=?,endereco=?,usuario_id=?,data_nascimento=?,telefone=?,rede_social=?,email=? WHERE id="
				+ contato.getId();

		PreparedStatement statement = connection.prepareStatement(sqlUpdateContato);

		statement.setString(1, contato.getNome());
		statement.setString(2, contato.getEndereco());
		statement.setLong(3, contato.getUsuarioId());
		statement.setDate(4, Date.valueOf(contato.getDataNascimento().toString()));
		statement.setString(5, contato.getTelefone());
		statement.setString(6, contato.getRedeSocial());
		statement.setString(7, contato.getEmail());

		int affectedRows = statement.executeUpdate();

		if (affectedRows == 0) {
			throw new SQLException(Mensagem.NaoFoiPossivelAtualizar);
		}

		ResultSet rs = statement.getGeneratedKeys();
	}
}