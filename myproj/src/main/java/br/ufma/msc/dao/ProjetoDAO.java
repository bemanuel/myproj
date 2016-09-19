package br.ufma.msc.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import br.ufma.msc.vo.Projeto;

public class ProjetoDAO {

	private Database db = Database.getInstance();

	/**
	 * Cria o projeto seguindo as bases principais
	 * 
	 * @param nomeProjeto
	 *            - projeto em criacao
	 * @param tempo
	 *            - em horas úteis
	 * @return - retorna se foi feito o processo com sucesso
	 */
	public boolean cria(String nomeProjeto, int tempo) {
		boolean result = false;
		// EVITA SQL INJECTION
		try {
			PreparedStatement pstmt = db.getConexao().prepareStatement(
					"INSERT INTO projeto (nome, tempo) VALUES (?,?)");
			pstmt.setString(0, nomeProjeto);
			pstmt.setInt(1, tempo);

			result = pstmt.execute();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see br.ufma.msc.dao.InterfaceDAO#abre(int)
	 */
	public Projeto abre(int id) {
		Projeto prj = null;
		ResultSet rs;
		try {
			rs = db.executaConsulta("SELECT * FROM projeto WHERE projeto_id="
					+ id);

			while (rs.next()) {
				prj = new Projeto(rs.getInt(1), rs.getString(2), rs.getInt(3));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return prj;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see br.ufma.msc.dao.InterfaceDAO#localiza(java.lang.String)
	 */
	public Projeto localiza(String nome) {
		Projeto prj = null;
		ResultSet rs;
		try {
			String sql = "SELECT * FROM projeto WHERE nome like '?'";
			PreparedStatement pstmt = db.getConexao().prepareStatement(sql);
			pstmt.setString(0, nome);

			rs = pstmt.executeQuery();
			while (rs.next()) {
				prj = new Projeto(rs.getInt(1), rs.getString(2), rs.getInt(3));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return prj;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see br.ufma.msc.dao.InterfaceDAO#remove(int)
	 */
	public boolean remove(int id) {
		boolean result = false;
		String sql = "DELETE FROM projeto WHERE projeto_id=?";

		PreparedStatement pstmt;
		try {
			pstmt = db.getConexao().prepareStatement(sql);

			pstmt.setInt(0, id);
			result = pstmt.execute();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}
}
