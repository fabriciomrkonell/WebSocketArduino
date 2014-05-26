<%   
    if (session != null) {
        session.setAttribute("id", null);
        session.setAttribute("name", null);
        session.invalidate();
    }
    response.sendRedirect("index.jsp");
%>
