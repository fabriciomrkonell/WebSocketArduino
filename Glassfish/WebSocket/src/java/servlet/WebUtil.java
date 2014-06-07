package servlet;

import javax.servlet.http.HttpServletRequest;

public class WebUtil {
     public String readParameter(HttpServletRequest request, String parameterName, String defaultValue) {
        String value = request.getParameter(parameterName);
        if (value == null || value.trim().equals("")) {
            value = defaultValue;
        }
        return value;
    }   
}
