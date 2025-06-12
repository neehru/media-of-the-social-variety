package neehru.app.service;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;

import java.io.IOException;

public class CustomAuthenticationFailureHandler implements AuthenticationFailureHandler {

    @Override
    public void onAuthenticationFailure(HttpServletRequest request,
                                        HttpServletResponse response,
                                        AuthenticationException exception)
            throws IOException {
        String errorMessage = exception.getMessage();

        if (exception instanceof BadCredentialsException) {
            errorMessage = "Invalid username or password.";
        }

        // redirect to login page with error message as query param
        response.sendRedirect("/login?error=" + errorMessage);
    }
}
