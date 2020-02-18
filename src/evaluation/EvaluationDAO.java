package evaluation;

import util.DataBaseUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

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

    public ArrayList<EvaluationDTO> getList(String lectureDivide, String searchType, String search, int pageNumber) {
        if (lectureDivide.equals("전체")) {
            lectureDivide = "";
        }
        ArrayList<EvaluationDTO> evaluationList = null;
        String sql ="";
        Connection conn = null;
        PreparedStatement psmt = null;
        ResultSet rs = null;
        try {
            if (searchType.equals("최신순")) {
                sql = "select * from evaluation where lectureDivide like ? and concat(lectureName, professorName, evaluationTitle, evaluationContent) " +
                        "like ? order by evaluationID desc limit " + pageNumber *  5 + ", " + pageNumber * 5 + 6;
            } else if (searchType.equals("추천순")) {
                sql = "select * from evaluation where lectureDivide like ? and concat(lectureName, professorName, evaluationTitle, evaluationContent) " +
                        "like ? order by likecount desc limit " + pageNumber *  5 + ", " + pageNumber * 5 + 6;
            }
            conn = DataBaseUtil.getConnection();
            psmt = conn.prepareStatement(sql);
            psmt.setString(1, "%" + lectureDivide + "%");
            psmt.setString(2, "%" + search + "%");
            rs = psmt.executeQuery();
            evaluationList = new ArrayList<EvaluationDTO>();
            while (rs.next()) {
                EvaluationDTO evaluation = new EvaluationDTO(
                        rs.getInt(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getString(4),
                        rs.getInt(5),
                        rs.getString(6),
                        rs.getString(7),
                        rs.getString(8),
                        rs.getString(9),
                        rs.getString(10),
                        rs.getString(11),
                        rs.getString(12),
                        rs.getString(13),
                        rs.getInt(14)
                );
                evaluationList.add(evaluation);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try { if (conn != null) conn.close(); } catch (SQLException e) { e.printStackTrace(); }
            try { if (psmt != null) psmt.close(); } catch (SQLException e) { e.printStackTrace(); }
            try { if (rs != null) rs.close(); } catch (SQLException e) { e.printStackTrace(); }
        }
        return evaluationList;
    }

}
