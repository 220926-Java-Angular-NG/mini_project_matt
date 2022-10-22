package com.revature.repo;

import com.revature.model.User;
import com.revature.utils.ConnManager;

import java.sql.*;

public class UserRepo {
    Connection conn;

    public UserRepo(){
        try{
            conn = ConnManager.getConn();
        } catch (SQLException sqlException){
            System.out.println(sqlException);
        }
    }

    public User getUser(User user) {
        try {
            String sql = "SELECT * FROM users WHERE email = ?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, user.getEmail());

            ResultSet rs = ps.executeQuery();

            User newUser = null;
            while(rs.next()) {
                 newUser = new User(rs.getString("firstname"), rs.getString("lastname"),
                        rs.getString("email"), rs.getString("_password"),
                        rs.getInt("id"), rs.getString("birthdate"),
                        rs.getInt("wzodiac_fk"), rs.getInt("czodiac_fk"),
                        rs.getString("mood"));
            }
            return newUser;
        } catch (SQLException sqlException) {
            System.out.println(sqlException.getMessage());
        }
        return null;
    }

    public User createUser(User user){
        try{
            String sql = "INSERT INTO users (email, _password, birthdate, czodiac_fk, wzodiac_fk, firstname, lastname) " +
                    "VALUES (?,?,?,?,?,?,?)";
            PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

            ps.setString(1, user.getEmail());
            ps.setString(2, user.getPassword());
            ps.setString(3, user.getBirthdate());
            ps.setInt(4, user.getCzodiac());
            ps.setInt(5, user.getWzodiac());
            ps.setString(6, user.getFirst_name());
            ps.setString(7, user.getLast_name());
            ps.executeUpdate();

            //gives row that was just created
            ResultSet rs = ps.getGeneratedKeys();
            rs.next();
            user.setId(rs.getInt("id"));

            return user;
        } catch (SQLException|NullPointerException ex) {
            String excMsg = ex.getMessage();
            if (excMsg.contains("users_email_key")) {
                user.setId(-1);
                return user;
            }else{
                user.setId(0);
                System.out.println(excMsg);
                return user;

            }
        }
    }

    public boolean updateMood(User user){
        try {
            String sql = "UPDATE users SET mood=? WHERE email=?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1,user.getMood());
            ps.setString(2,user.getEmail());
            ps.execute();
            return true;
        } catch (SQLException sqlException){
            System.out.println(sqlException.getMessage());
        }

        return false;
    }

    public User getWSignDisplay(User user){
        try {
            String sql = "SELECT * FROM wzodiac WHERE id = ?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, user.getWzodiac());
            ResultSet rs = ps.executeQuery();

            while (rs.next()){
                user.setWzodiac_Display(rs.getString("sunsign"));
            }

            return user;

        } catch (SQLException sqlException){
            System.out.println(sqlException.getMessage());
        }
        return user;
    }

    public User getCSignDisplay(User user){
        try {
            String sql = "SELECT * FROM czodiac WHERE id = ?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, user.getCzodiac());

            ResultSet rs = ps.executeQuery();

            while (rs.next()){
                user.setCzodiac_Display(rs.getString("animal"));
                user.setCzDescription(rs.getString("description"));
            }

            return user;

        } catch (SQLException sqlException){
            System.out.println(sqlException.getMessage());
        }
        return user;
    }

}

