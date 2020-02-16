package user;

import util.DataBaseUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDAO {

    public int login(String userID, String userPassword) {
        String sql ="select userPassword form user where userID = ?";
        Connection conn = null;
        PreparedStatement psmt = null;
        ResultSet rs = null;
        try {
            conn = DataBaseUtil.getConnection();
            psmt = conn.prepareStatement(sql);
            psmt.setString(1, userID);
            rs = psmt.executeQuery();
            if (rs.next()) {
                if (rs.getString(1).equals(userPassword)) {
                    return 1;  //로그인 성공
                } else
                    return 0;  //비밀번호 틀림
            }
            return -1;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try { if (conn != null) conn.close(); } catch (SQLException e) { e.printStackTrace(); }
            try { if (psmt != null) psmt.close(); } catch (SQLException e) { e.printStackTrace(); }
            try { if (rs != null) rs.close(); } catch (SQLException e) { e.printStackTrace(); }
        }
        return -2; //데이터 베이스 오류
    }

    public int join(UserDTO user) {
        String sql ="insert into user values (?, ?, ?, ?, false)";
        Connection conn = null;
        PreparedStatement psmt = null;
        ResultSet rs = null;
        try {
            conn = DataBaseUtil.getConnection();
            psmt = conn.prepareStatement(sql);
            psmt.setString(1, user.getUserID());
            psmt.setString(2, user.getUserPassword());
            psmt.setString(3, user.getUserEmail());
            psmt.setString(4, user.getUserEmailHash());
            return psmt.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try { if (conn != null) conn.close(); } catch (SQLException e) { e.printStackTrace(); }
            try { if (psmt != null) psmt.close(); } catch (SQLException e) { e.printStackTrace(); }
            try { if (rs != null) rs.close(); } catch (SQLException e) { e.printStackTrace(); }
        }
        return -1; //회원가입 실패
    }
    public boolean getUserEmailChecked(String userID) {
        String sql ="select userEmailChecked from user where userID = ?";
        Connection conn = null;
        PreparedStatement psmt = null;
        ResultSet rs = null;
        try {
            conn = DataBaseUtil.getConnection();
            psmt = conn.prepareStatement(sql);
            psmt.setString(1, userID);
            rs = psmt.executeQuery();
            if (rs.next()) {
                return rs.getBoolean(1);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try { if (conn != null) conn.close(); } catch (SQLException e) { e.printStackTrace(); }
            try { if (psmt != null) psmt.close(); } catch (SQLException e) { e.printStackTrace(); }
            try { if (rs != null) rs.close(); } catch (SQLException e) { e.printStackTrace(); }

        }
        return false; //회원가입 실패
    }

    public boolean setUserEmailChecked(String userID) {
        String sql ="update user set userEmailChecked = true where userID = ?";
        Connection conn = null;
        PreparedStatement psmt = null;
        ResultSet rs = null;
        try {
            conn = DataBaseUtil.getConnection();
            psmt = conn.prepareStatement(sql);
            psmt.setString(1, userID);
            psmt.executeUpdate();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try { if (conn != null) conn.close(); } catch (SQLException e) { e.printStackTrace(); }
            try { if (psmt != null) psmt.close(); } catch (SQLException e) { e.printStackTrace(); }
            try { if (rs != null) rs.close(); } catch (SQLException e) { e.printStackTrace(); }

        }
        return false;
    }

    public String getUserEmail(String userID) {
        String sql ="select userEamil from user where userID = ?";
        Connection conn = null;
        PreparedStatement psmt = null;
        ResultSet rs = null;
        try {
            conn = DataBaseUtil.getConnection();
            psmt = conn.prepareStatement(sql);
            psmt.setString(1, userID);
            rs = psmt.executeQuery();
            if (rs.next()) {
                return rs.getString(1);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try { if (conn != null) conn.close(); } catch (SQLException e) { e.printStackTrace(); }
            try { if (psmt != null) psmt.close(); } catch (SQLException e) { e.printStackTrace(); }
            try { if (rs != null) rs.close(); } catch (SQLException e) { e.printStackTrace(); }

        }
        return null;
    }
}
