<%--
  Created by IntelliJ IDEA.
  User: eeswan
  Date: 2020-02-16
  Time: 오후 11:27
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@ page import="user.UserDTO" %>
<%@ page import="user.UserDAO" %>
<%@ page import="util.SHA256" %>
<%@ page import="java.io.PrintWriter" %>
<%
    request.setCharacterEncoding("UTF-8");
    String userID = null;
    String userPassword = null;
    String userEmail = null;
    if (request.getParameter("userID") != null) {
        userID = request.getParameter("userID");
    }
    if (request.getParameter("userPassword") != null) {
        userPassword = request.getParameter("userPassword");
    }
    if (request.getParameter("userEmail") != null) {
        userEmail = request.getParameter("userEmail");
    }

    if (userID == null || userPassword == null || userEmail == null) {
        PrintWriter script = response.getWriter();
        script.println("<script>");
        script.println("alert('입력이 안된 사항이 있습니다.');");
        script.println("history.back();");
        script.println("</script>");
        script.close();
        return;
    }

    UserDAO userDAO = new UserDAO();
    int result = userDAO.join(new UserDTO(userID, userPassword, userEmail, SHA256.getSHA256(userEmail), false));

    if (result == -1) {
        PrintWriter script = response.getWriter();
        script.println("<script>");
        script.println("alert('이미 존재하는 아이디 입니다.');");
        script.println("history.back();");
        script.println("</script>");
        script.close();
        return;
    } else {
        session.setAttribute("userID", userID);
        PrintWriter script = response.getWriter();
        script.println("<script>");
        script.println("alert('회원가입되었습니다.');");
        script.println("location.href= 'emailSendAction.jsp'");
        script.println("</script>");
        script.close();
        return;
    }
%>