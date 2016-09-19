package br.ufma.msc.dao;

import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import br.ufma.msc.vo.Projeto;
import br.ufma.msc.vo.Recurso;

public class RecursoDAO {

	private Database db = Database.getInstance();

	public boolean cria(Projeto prj, String nome, int tipo_recurso,
			BigDecimal valor) {
		boolean result = false;

		try {
			PreparedStatement pstmt = db
					.getConexao()
					.prepareStatement(
							"INSERT INTO recurso (nome, tipo_recurso, valor) VALUES (?,?,?)");

			pstmt.setString(0, nome);
			pstmt.setInt(1, tipo_recurso);
			pstmt.setBigDecimal(2, valor);

			result = pstmt.execute();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return result;
	}

	public Recurso abre(int id) {
		Recurso rec = null;
		ResultSet rs;
		try {
			rs = db.executaConsulta("SELECT * FROM recurso WHERE recurso_id="
					+ id);

			while (rs.next()) {
				rec = new Recurso(rs.getInt(1), rs.getString(2), rs.getInt(3),
						rs.getBigDecimal(4));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return rec;

	}
}
