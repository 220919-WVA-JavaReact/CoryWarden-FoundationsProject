package com.revature.dao;

import com.revature.models.Reimbursement;

import com.revature.util.ConnectionUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ReimbursementDaoJDBC implements ReimbursementDAO {

    @Override
    public List<Reimbursement> getByReimbursementAuth(int authId) {
        Connection conn = ConnectionUtil.getConn();
        List<Reimbursement> reimbursements = new ArrayList<>();
        try {
            String sql = "SELECT * FROM reimbursement WHERE authid = ? ORDER BY ticket";
            assert conn != null;
            PreparedStatement pstmt = conn.prepareStatement(sql);
            //String sql = "SELECT * FROM users WHERE id = ;";
            pstmt.setInt(1, authId);
            ResultSet rs = pstmt.executeQuery();

            //to store all, use while loop
            while (rs.next()) {
                int id = rs.getInt("ticket");
                String status = rs.getString("status");
                authId = rs.getInt("authid");
                String username = rs.getString("username");
                float amount = rs.getFloat("amount");
                String desc = rs.getString("description");

                Reimbursement r = new Reimbursement(id, status, authId, username, amount, desc);
                reimbursements.add(r);

            }
        } catch (SQLException e) {
            System.out.println("Unable to fetch ticket.");
        }
        return reimbursements;
    }

    @Override
    public Reimbursement getByTicketId(int id) {
        Connection conn = ConnectionUtil.getConn();
        Reimbursement r = new Reimbursement();
        try {
            String sql = "SELECT * FROM reimbursement WHERE ticket = ? ORDER BY ticket";
            assert conn != null;
            PreparedStatement pstmt = conn.prepareStatement(sql);
            //String sql = "SELECT * FROM users WHERE id = ;";
            pstmt.setInt(1, id);
            ResultSet rs = pstmt.executeQuery();

            //to store all, use while loop
            while (rs.next()) {
                id = rs.getInt("ticket");
                String status = rs.getString("status");
                int authId = rs.getInt("authid");
                String username = rs.getString("username");
                float amount = rs.getFloat("amount");
                String desc = rs.getString("description");

                r = new Reimbursement(id, status, authId, username, amount, desc);

            }
        } catch (SQLException e) {
            System.out.println("Unable to fetch ticket.");
        }
        return r;
    }

    @Override
    public List<Reimbursement> getTicketsByStatus(String status) {
        List<Reimbursement> tickets = new ArrayList<>();
        try (Connection conn = ConnectionUtil.getConn()){
            String sql = "SELECT * FROM reimbursement WHERE status = ? ORDER BY ticket";
            assert conn != null;
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, status);
            ResultSet rs = pstmt.executeQuery();

            //to store all, use while loop
            while (rs.next()) {
                int id = rs.getInt("ticket");
                status = rs.getString("status");
                int authId = rs.getInt("authid");
                String username = rs.getString("username");
                float amount = rs.getFloat("amount");
                String desc = rs.getString("description");

                Reimbursement r = new Reimbursement(id, status, authId, username, amount, desc);
                tickets.add(r);

            }
        } catch (SQLException e) {
            System.out.println("Unable to fetch ticket.");
        }
        return tickets;

    }

    @Override
    public Reimbursement addReimbursement(Reimbursement r) {
        try (Connection conn = ConnectionUtil.getConn()) {
            String sql = "INSERT INTO reimbursement (authId,username,amount,description) VALUES (?,?,?,?) RETURNING *";
            //Put ? wherever information will receive input
            assert conn != null;
            PreparedStatement pstmt = conn.prepareStatement(sql);
            //Answer ? below with parameter and variable/input
            pstmt.setInt(1, r.getAuthId());
            pstmt.setString(2, r.getUsername());
            pstmt.setFloat(3, r.getAmount());
            pstmt.setString(4, r.getDescription());
            ResultSet rs = pstmt.executeQuery();
            if (rs != null) {
                rs.next();
                int id = rs.getInt("ticket");
                String status = rs.getString("status");
                int authId = rs.getInt("authid");
                String username = rs.getString("username");
                float amount = rs.getFloat("amount");
                String desc = rs.getString("description");

                    r = new Reimbursement(id, status, authId, username, amount, desc);
                    System.out.println("Ticket " + id + " has been opened.");
            }
        } catch (SQLException e) {
            System.out.println("Unable to add ticket to database.");
        }
        return r;
    }

    @Override
    public Reimbursement updateStatus(String status, int ticketId) {
        Reimbursement r = new Reimbursement();
        try (Connection conn = ConnectionUtil.getConn()) {
            String sql = "UPDATE reimbursement SET status = ? WHERE ticket = ? RETURNING *";
            assert conn != null;
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, status);
            pstmt.setInt(2, ticketId);
            ResultSet rs = pstmt.executeQuery();
            if (rs != null) {
                rs.next();
                int id = rs.getInt("ticket");
                status = rs.getString("status");
                int authId = rs.getInt("authid");
                String username = rs.getString("username");
                float amount = rs.getFloat("amount");
                String desc = rs.getString("description");

                r = new Reimbursement(id, status, authId, username, amount, desc);
                System.out.println("Ticket " + r.getId() + " has been updated to: " + r.getStatus());
            }
        } catch (SQLException e) {
            System.out.println("This ticket was not updated.");
        }
        return r;
    }

    }
