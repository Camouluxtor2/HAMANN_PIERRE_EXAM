package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;

import metier.Compte;
import metier.CompteAvecDecouvert;
import metier.CompteSansDecouvert;

public class CompteDAO extends DAO<Compte> {

	public CompteDAO(Connection conn) {
		super(conn);
	}

	public HashMap<ArrayList<Compte>, String> findAll() {
		try {
			Statement stat = this.connect.createStatement();
			ResultSet rs = stat.executeQuery("select * from compte");
			HashMap hm = new HashMap<ArrayList<Compte>, String>();
			while (rs.next()) {
				if (rs.getDouble(3) > 0) {
					hm.put(new CompteAvecDecouvert(rs.getString(1), rs.getDouble(3), rs.getDouble(2)), rs.getString(4));
				} else {
					hm.put(new CompteSansDecouvert(rs.getString(1), rs.getDouble(2)), rs.getString(4));
				}
			}
			return hm;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}

	public boolean update(String num_compte, double solde) {
		try {
			PreparedStatement stat = this.connect.prepareStatement("UPDATE compte SET solde=? WHERE num_compte=?");
			stat.setDouble(1, solde);
			stat.setString(2, num_compte);
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}

	public boolean create(Compte c, String num_client) {
		try {
			PreparedStatement stat = this.connect
					.prepareStatement("insert into compte(num_compte, solde, decouvert, num_client) values(?,?,?,?)");
			stat.setString(1, c.getNumeroCompte());
			stat.setDouble(2, c.getSolde());
			if (c instanceof CompteAvecDecouvert) {
				stat.setDouble(3, ((CompteAvecDecouvert) c).getDecouvertAutorise());
			} else {
				stat.setDouble(3, 0);
			}
			stat.setString(4, num_client);
			return stat.execute();
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}

	public Compte find(String id, String num_client) {
		try {
			PreparedStatement stat = this.connect.prepareStatement("select * from compte where num_client=?");
			stat.setString(1, num_client);
			ResultSet rs = stat.executeQuery();
			if (rs.next()) {
				if (rs.getDouble(3) > 0) {
					return new CompteAvecDecouvert(rs.getString(1), rs.getDouble(2));
				} else {
					return new CompteSansDecouvert(rs.getString(1), rs.getDouble(2));
				}
			}
			return null;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}

	public boolean delete(Compte obj) {
		return false;
	}

	public boolean update(Compte obj) {
		return false;
	}

	public boolean create(Compte obj) {
		return false;
	}

	public Compte find(String id) {
		return null;
	}

}
