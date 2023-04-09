package ca.sheridancollege.securityexample.security;

import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
@Component
public class LogginAccessDeniedHandler implements AccessDeniedHandler {

    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response,
                       AccessDeniedException accessDeniedException)
                            throws IOException, ServletException {
        //Get the user from the Security Context
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();

        //"Log" the attempt
        if(auth != null) {
            String format = "%s was trying to access%s\n";
            System.out.printf(format, auth.getName(), request.getRequestURI());
        }

        //redirect to the permission-denied page
        response.sendRedirect("/permission-denied");
    }
}
