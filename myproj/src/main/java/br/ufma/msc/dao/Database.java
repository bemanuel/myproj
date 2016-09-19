package br.ufma.msc.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

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
	
}
