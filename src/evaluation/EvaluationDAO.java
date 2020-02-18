package evaluation;

import util.DataBaseUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class EvaluationDAO {

    public int write(EvaluationDTO evaluationDTO) {
        String sql ="insert into evaluation values (NULL, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, 0)";
        Connection conn = null;
        PreparedStatement psmt = null;
        ResultSet rs = null;
        try {
            conn = DataBaseUtil.getConnection();
            psmt = conn.prepareStatement(sql);
            psmt.setString(1, evaluationDTO.getUserID());
            psmt.setString(2, evaluationDTO.getLectureName());
            psmt.setString(3, evaluationDTO.getProfessorName());
            psmt.setInt(4, evaluationDTO.getLectureYear());
            psmt.setString(5, evaluationDTO.getSemesterDivide());
            psmt.setString(6, evaluationDTO.getLectureDivide());
            psmt.setString(7, evaluationDTO.getEvaluationTitle());
            psmt.setString(8, evaluationDTO.getEvaluationContent());
            psmt.setString(9, evaluationDTO.getTotalScore());
            psmt.setString(10, evaluationDTO.getCreditScore());
            psmt.setString(11, evaluationDTO.getComfortableScore());
            psmt.setString(12, evaluationDTO.getLectureScore());
            return psmt.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try { if (conn != null) conn.close(); } catch (SQLException e) { e.printStackTrace(); }
            try { if (psmt != null) psmt.close(); } catch (SQLException e) { e.printStackTrace(); }
            try { if (rs != null) rs.close(); } catch (SQLException e) { e.printStackTrace(); }
        }
        return -1; //데이터 베이스 오류
    }
}
