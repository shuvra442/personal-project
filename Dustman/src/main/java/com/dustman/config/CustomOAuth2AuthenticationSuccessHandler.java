package com.dustman.config;

import com.dustman.model.User;
import com.dustman.repository.UserRepo;
import com.dustman.utils.enums.AuthProvider;
import com.dustman.utils.enums.Roles;
import com.dustman.utils.jwt.JWTCreate;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Optional;

@Component
public class CustomOAuth2AuthenticationSuccessHandler extends SimpleUrlAuthenticationSuccessHandler {

    @Autowired
    private UserRepo userRepository;

    @Autowired
    private JWTCreate jwtCreate;

    @Autowired
    private UserDetailsServiceImpl userDetailsService;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request,
                                        HttpServletResponse response,
                                        Authentication authentication) throws IOException, ServletException {

        if (authentication instanceof OAuth2AuthenticationToken oauthToken) {
            OAuth2User oauthUser = oauthToken.getPrincipal();

            String email = oauthUser.getAttribute("email");
            String name  = oauthUser.getAttribute("name");

            if (email == null) {
                response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Email not found from OAuth provider");
                return;
            }

            // Check if user exists
            Optional<User> existingUser = userRepository.findByEmail(email);
            User user;
            if (existingUser.isPresent()) {
                user = existingUser.get();
            } else {
                // Auto-register new Google user
                user = new User();
                user.setEmail(email);
                user.setPassword("OAUTH_USER");
                user.setName(name != null ? name : "Google User");
                user.setProvider(AuthProvider.valueOf("GOOGLE"));
                user.setRole(Roles.USER);
                userRepository.save(user);
            }

            // Generate JWT token
            UserDetails userDetails = userDetailsService.loadUserByUsername(user.getEmail());
            String token = jwtCreate.generateToken(userDetails);

            // Send JWT token to frontend (here writing in response)
//            response.setContentType("application/json");
//            response.getWriter().write("{\"token\": \"" + token + "\"}");
//            response.getWriter().flush();
            String redirectUrl = "http://localhost:5173/oauth2/callback?token=" + token;
            getRedirectStrategy().sendRedirect(request, response, redirectUrl);

        } else {
            super.onAuthenticationSuccess(request, response, authentication);
        }
    }
}
