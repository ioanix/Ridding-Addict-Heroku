package ubb.postuniv.riddingaddict.security.filter;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import ubb.postuniv.riddingaddict.exception.ShopException;
import ubb.postuniv.riddingaddict.security.model.AuthResponse;
import ubb.postuniv.riddingaddict.security.model.SecurityUser;
import ubb.postuniv.riddingaddict.security.model.UsernameAndPasswordAuthenticationRequest;

import javax.servlet.FilterChain;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDate;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import static ubb.postuniv.riddingaddict.security.model.SecurityConstants.EXPIRATION_TIME;
import static ubb.postuniv.riddingaddict.security.model.SecurityConstants.SECRET;

public class JWTAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

    private AuthenticationManager authenticationManager;


    public JWTAuthenticationFilter(AuthenticationManager authenticationManager) {

        this.authenticationManager = authenticationManager;

        setFilterProcessesUrl("/api/users/login");
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest req,
                                                HttpServletResponse res) throws AuthenticationException {

        try {

            UsernameAndPasswordAuthenticationRequest authenticationRequest = new ObjectMapper()
                    .readValue(req.getInputStream(), UsernameAndPasswordAuthenticationRequest.class);

            return authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            authenticationRequest.getUsername(),
                            authenticationRequest.getPassword())

            );

        } catch (IOException e) {

            throw new ShopException("Could not authenticate user");
        }
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest req,
                                            HttpServletResponse res,
                                            FilterChain chain,
                                            Authentication auth) throws IOException {

        Collection<? extends GrantedAuthority> authorities = ((SecurityUser) auth.getPrincipal()).getAuthorities();

        SecurityUser principal = (SecurityUser) auth.getPrincipal();

        List<String> authoritiesList = authorities.stream()
                .map(Object::toString)
                .collect(Collectors.toList());

        String token = JWT.create()
                .withSubject(auth.getName())
                .withClaim("firstName", principal.getAppUser().getFirstName())
                .withClaim("lastName", principal.getAppUser().getLastName())
                .withClaim("roles", authoritiesList)
                .withExpiresAt(java.sql.Date.valueOf(LocalDate.now().plusDays(EXPIRATION_TIME)))
                .withIssuedAt(new Date())
                .sign(Algorithm.HMAC512(SECRET.getBytes()));

        AuthResponse authResponse = new AuthResponse(token,
                principal.getUsername(),
                principal.getAppUser().getFirstName(),
                principal.getAppUser().getLastName(),
                authoritiesList);

        res.getWriter().write(authResponse.toString());

        res.getWriter().flush();
    }
}

