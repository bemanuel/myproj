package br.ufma.msc.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * 
 * Singleton para conexao de banco de dados e retorno de consultas
 * 
 * @author Bruno / Dani / Thiago
 *
 */
public class Database {
	private static Database instance;
	private Connection conexao = null;

	private Database() {

		try {
			Class.forName("org.sqlite.JDBC");
			conexao = DriverManager.getConnection("jdbc:sqlite:base.db");
		} catch (Exception e) {
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
			System.exit(0);
		}
		System.out.println("Base aberta com sucesso");

	}

	public static Database getInstance() {
		if (instance == null)
			instance = new Database();
		inicializar();
		return instance;
	}

	public Connection getConexao() {
		return getInstance().conexao;
	}

	/*
	 * Cria/Altera estrutura da base de dados
	 */
	public void executaDDL(String sql) throws SQLException {
		Statement stmt = getConexao().createStatement();
		stmt.executeUpdate(sql);
		stmt.close();
	}

	/*
	 * Consulta dados nas tabelas
	 */
	public ResultSet executaConsulta(String sql) throws SQLException {
		Statement stmt = getConexao().createStatement();
		return stmt.executeQuery(sql);
	}

	private static void inicializar() {
		Statement stmt;
		try {
			stmt = instance.conexao.createStatement();

			int versao = 0;

			int retorno = stmt
					.executeUpdate("CREATE TABLE IF NOT EXISTS propriedades ( versao int not null )");

			// INICIANDO TABELAS
			stmt.executeUpdate("CREATE TABLE IF NOT EXISTS projeto ( projeto_id INTEGER PRIMARY KEY autoincrement, "
					+ "nome VARCHAR(100) NOT NULL UNIQUE, "
					+ "tempo integer not null)");
			stmt.executeUpdate("CREATE TABLE IF NOT EXISTS tipo_recurso (tipo_recurso_id INTEGER PRIMARY KEY AUTOINCREMENT, "
					+ "nome VARCHAR(50) NOT NULL UNIQUE)");

			stmt.executeUpdate("CREATE TABLE IF NOT EXISTS recurso (recurso_id INTEGER PRIMARY KEY AUTOINCREMENT, "
					+ "nome VARCHAR(50) NOT NULL UNIQUE, "
					+ "tipo_recurso INTEGER, "
					+ "valor numeric not null, "
					+ "FOREIGN KEY (tipo_recurso) REFERENCES tipo_recurso(tipo_recurso_id)"
					+ " )");
			// SALEBAT ODNAICINI

			ResultSet rs = stmt.executeQuery("SELECT versao FROM propriedades");

			while (rs.next()) {
				versao = rs.getInt(1);
			}

			// Se for a base inicial o sistema cria os dados base
			if (versao == 0) {
				stmt.executeUpdate("INSERT INTO propriedades ( versao ) VALUES (1)");
				stmt.executeUpdate("INSERT INTO tipo_recurso ( nome ) VALUES ('humano'),('material')");
			}

			// SE FOR NECESSARIO CRIAR OUTRA VERSAO INCREMENTAR COM IFS
			// if (versao == 1 ) ...

			System.out.println("Vers:" + versao);

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
