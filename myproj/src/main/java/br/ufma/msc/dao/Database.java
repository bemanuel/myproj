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

	public void executaSQL(String sql) throws SQLException {
		Statement stmt = getConexao().createStatement();
		stmt.executeUpdate(sql);
		stmt.close();
	}

	public ResultSet executaConsulta(String sql) throws SQLException {
		Statement stmt = getConexao().createStatement();
		return stmt.executeQuery(sql);
	}

	private static void inicializar() {
		Statement stmt;
		try {
			stmt = instance.conexao.createStatement();

			int versao = 0;
			
			int retorno = stmt.executeUpdate("CREATE TABLE IF NOT EXISTS propriedades ( versao int not null )");

			ResultSet rs = stmt.executeQuery("SELECT versao FROM propriedades");

			while (rs.next()) {
				versao = rs.getInt(1);
			}
			
			if (versao == 0) {
				stmt.executeUpdate("INSERT INTO propriedades ( versao ) VALUES (1)");
				stmt.executeUpdate("CREATE TABLE IF NOT EXISTS projeto ( id INTEGER PRIMARY KEY autoincrement, "
						+ " nome varchar(100) not null unique)");	
			}
			System.out.println("Vers:" + versao);

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
