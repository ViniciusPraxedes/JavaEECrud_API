package com.example.jakartaeecrud;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DAO {

    private String driver = "com.mysql.cj.jdbc.Driver";
    private String url = "jdbc:mysql://userdb.cvdvvrchasiv.eu-north-1.rds.amazonaws.com:3306/userdb";
    private String user = "root";
    private String password = "Biorock37";
    private Connection connect() throws SQLException, ClassNotFoundException {
        Connection con = null;
        Class.forName(driver);
        con = DriverManager.getConnection(url, user, password);
        return con;
    }

    public void connectionTest(){
        try{
            Connection con = connect();
            System.out.println(con);
            con.close();
        }catch (Exception e){
            System.out.println(e);
        }
    }

    public void insertUserIntoDB(User user){
        String insert = "insert into Users (id, name, email) values (?,?,?)";
        try{
            Connection con = connect();
            PreparedStatement pst = con.prepareStatement(insert);
            pst.setInt(1, user.getId());
            pst.setString(2, user.getName());
            pst.setString(3, user.getEmail());
            pst.executeUpdate();
            con.close();
        }catch (Exception e){
            System.out.println(e);
        }
    }

    public List<User> retrieveAllUsers(){
        List<User> users = new ArrayList<>();
        String retrieve = "select * from Users";
        try{
            Connection con = connect();
            PreparedStatement pst = con.prepareStatement(retrieve);
            ResultSet rs = pst.executeQuery();
            while(rs.next()){
                int id = rs.getInt(1);
                String name = rs.getString(2);
                String email = rs.getString(3);
                users.add(new User(id,name,email));
            }
            con.close();
            return users;
        }catch (Exception e){
            System.out.println(e);
            return null;
        }
    }

    public void editUser(User user){
        String read2 = "select * from Users where id = ?";
        try{
            Connection con = connect();
            PreparedStatement pst = con.prepareStatement(read2);
            pst.setInt(1, user.getId());
            ResultSet rs = pst.executeQuery();
            while(rs.next()){
                user.setId(rs.getInt(1));
                user.setName(rs.getString(2));
                user.setEmail(rs.getString(3));
            }
            con.close();
        }catch(Exception e){

        }
    }

    public void updateUser(User user){
        String update = "update Users set name=?, email=? where id=?";
        try {
            Connection con = connect();
            PreparedStatement pst = con.prepareStatement(update);
            pst.setString(1,user.getName());
            pst.setString(2,user.getEmail());
            pst.setInt(3,user.getId());
            pst.executeUpdate();
            con.close();
        }catch (Exception e){
            System.out.println(e);
        }
    }

    public void deleteUser(User user){
        String delete = "delete from Users where id=?";
        try{
            Connection con = connect();
            PreparedStatement pst = con.prepareStatement(delete);
            pst.setInt(1, user.getId());
            pst.executeUpdate();
            con.close();
        }catch (Exception e){
            System.out.println(e);
        }
    }

}
