package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import metier.Client;

public class ClientDAO extends DAO<Client>{
	
	public ClientDAO(Connection conn) {
		super(conn);
	}
	
	public ArrayList<Client> findAll() {
		try {
			Statement stat = this.connect.createStatement();
			ResultSet rs = stat.executeQuery("select * from client");
			ArrayList<Client> clients = new ArrayList<Client>();
			while(rs.next()) {
				clients.add(new Client(rs.getString(1), rs.getString(3), rs.getString(2)));
			}
			return clients;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}

	public boolean create(Client client) {
		try {
			PreparedStatement stat = this.connect.prepareStatement("insert into client(num_client, adresse, nom) values(?,?,?)");
			stat.setString(1, client.getNumeroClient());
			stat.setString(2, client.getAdresse());
			stat.setString(3, client.getNom());
			return stat.execute();
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}
	
	public Client find(String num_client) {
		try {
			PreparedStatement stat = this.connect.prepareStatement("select * from client where num_client=?");
			stat.setString(1, num_client);
			ResultSet rs = stat.executeQuery();
			if(rs.next()){
				return new Client(rs.getString(1), rs.getString(3), rs.getString(2));
			}else {
				return null;
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}

	public boolean delete(Client client) {
		return false;
	}

	public boolean update(Client client) {
		return false;
	}

}
