package br.unibh.servicospessoas.persistencia;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import br.unibh.servicospessoas.entidades.Cep;

public class CepDAO implements DAO<Cep, Long> {

	@Override
	public Cep find(Long cep) {
		try {
			PreparedStatement p = JDBCUtil.getConnection().prepareStatement(
					"select * from tb_cep where cep = ?");
			p.setLong(1, cep);
			ResultSet res = p.executeQuery();
			if (res.next()) {
				return new Cep(res.getLong("cep"), res.getString("endereco"),
						res.getString("cidade"));

			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JDBCUtil.closeConnection();
		}
		return null;
	}

	@Override
	public void insert(Cep t) {
		try {
			PreparedStatement p = JDBCUtil.getConnection().prepareStatement(
					"insert into tb_cep (cep,endereco,cidade) values (?,?,?)");

			p.setLong(1, t.getCep());
			p.setString(2, t.getEndereco());
			p.setString(3, t.getCidade());
			p.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JDBCUtil.closeConnection();

		}

	}

	@Override
	public void update(Cep t) {
		try {
			PreparedStatement p = JDBCUtil.getConnection().prepareStatement(
					"update tb_cep set endereco =?,cidade = ? where cep = ?");

			p.setString(1, t.getEndereco());
			p.setString(2, t.getCidade());
			p.setLong(3, t.getCep());
			p.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JDBCUtil.closeConnection();

		}

	}

	@Override
	public void delete(Cep t) {
		try {
			PreparedStatement p = JDBCUtil.getConnection().prepareStatement(
					"delete from tb_cep  where cep = ?");

			p.setLong(1, t.getCep());
			p.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JDBCUtil.closeConnection();

		}

	}

	@Override
	public List<Cep> findAll() {

		List<Cep> lista = new ArrayList<Cep>();

		try {
			ResultSet res = JDBCUtil.getConnection().prepareStatement("select * from tb_cep").executeQuery();

			while (res.next()) {
				lista.add(new Cep(res.getLong("cep"),
						res.getString("endereco"), res.getString("cidade")));

			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JDBCUtil.closeConnection();
		}

		return lista;
		
	}

	public List<Cep> findCep(String endereco) {
		List<Cep> lista = new ArrayList<Cep>();

		try {
			PreparedStatement p = JDBCUtil.getConnection().prepareStatement(
					"select * from tb_cep where endereco like ?");
			p.setString(1, endereco + "%");
			ResultSet res = p.executeQuery();
			while (res.next()) {
				lista.add(new Cep(res.getLong("cep"),
						res.getString("endereco"), res.getString("cidade")));

			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JDBCUtil.closeConnection();
		}

		return lista;
	}

}
