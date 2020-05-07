package dao;

import java.sql.Connection;

public class DAOFactory {

    protected static final Connection conn = ConnectionDAO.getInstance();

    public static ClientDAO getClientDAO(){
        return new ClientDAO(conn);
    }


    public static CompteDAO getCompteDAO(){
        return new CompteDAO(conn);
    } 

}