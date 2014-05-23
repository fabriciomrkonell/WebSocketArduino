package servlet;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class HTTPService {
    
     public static boolean isValidated(HttpServletRequest request) {
        boolean isValid = false;
        HttpSession session = request.getSession();
        if (session != null) {
            String userName = (String) session.getAttribute("name");
            String userId = (String) session.getAttribute("id");
            if (userName != null || userId != null) {
                isValid = true;
            }
        }

        return isValid;
    }
}
