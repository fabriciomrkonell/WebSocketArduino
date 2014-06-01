<%   
    if (session != null) {
        session.setAttribute("id", null);
        session.setAttribute("name", null);
        session.setAttribute("email", null);
        session.invalidate();
    }
    response.sendRedirect("index.jsp");
%>
