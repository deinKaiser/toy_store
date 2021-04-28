package yarema.project.toystore.filters;

import lombok.RequiredArgsConstructor;
import yarema.project.toystore.service.v2.UserDetailsServiceImpl;
import yarema.project.toystore.utils.JwtTokenUtils;
import org.flywaydb.core.internal.util.StringUtils;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class AuthFilter extends OncePerRequestFilter {
    private final UserDetailsServiceImpl userDetailsService;
    private final JwtTokenUtils jwtTokenUtils;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        try {
            var authorization = request.getHeader("Authorization");
            var token = StringUtils.hasText(authorization) && authorization.startsWith("Bearer ")
                    ? authorization.substring(7)
                    : null;
            Optional.ofNullable(token)
                    .filter(jwtTokenUtils::validate)
                    .ifPresent(t -> {
                        var username = jwtTokenUtils.getUsernameFromJwtToken(t);
                        var userDetails = userDetailsService.loadUserByUsername(username);
                        var auth = new UsernamePasswordAuthenticationToken(userDetails, null,
                                userDetails.getAuthorities());
                        auth.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                        auth.setAuthenticated(true);
                        SecurityContextHolder.getContext().setAuthentication(auth);
                    });


        } catch (Exception e) {
            e.printStackTrace();
        }
        filterChain.doFilter(request, response);
    }
}
