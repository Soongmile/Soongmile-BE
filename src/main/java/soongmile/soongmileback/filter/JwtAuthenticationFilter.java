package soongmile.soongmileback.filter;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import soongmile.soongmileback.domain.LoginReq;
import soongmile.soongmileback.domain.PrincipalDetails;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Slf4j
@RequiredArgsConstructor
public class JwtAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

    private final AuthenticationManager authenticationManager;

    // login 요청을 하면 로그인 시도를 위해서 실행되는 함수
    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
        ObjectMapper om = new ObjectMapper();
        try {
            // 1. username, password 받는다.
            log.info("1. username, password 받는다.");
            LoginReq login = om.readValue(request.getInputStream(), LoginReq.class);
            log.info(login.toString());
            // username, password를 이용해서 token 발급
            UsernamePasswordAuthenticationToken authenticationToken =
                    new UsernamePasswordAuthenticationToken(login.getEmail(), login.getPassword());
            log.info(authenticationToken.getPrincipal().toString());
            log.info(authenticationToken.getCredentials().toString());
            log.info("============================================================\n");

            // 2. 정상인지 로그인 시도를 해본다.
            log.info("2. 정상인지 로그인 시도를 해본다.");
            // -> 로그인 정보를 가지고 임시로 Auth 토큰을 생성해서 인증을 확인한다.
            // -> DI 받은 authenticationManager로 로그인 시도한다.
            // -> DetailsService를 상속받은 PrincipalDetailsService가 호출되고 loadUserByUsername() 함수가 실행된다.
            // authenticate()에 토큰을 넘기면 PrincipalDetailsService.class -> loadUserByUsername() 메소드 실행된다.
            // DB에 저장되어있는 username & password가 일치하면 authentication이 생성된다.
            log.info("->> Authenticate Start");
            Authentication authentication =
                    authenticationManager.authenticate(authenticationToken);
            log.info("<<-- Authenticate End");
            log.info("============================================================\n");

            // 3. 로그인이 되었다.
            log.info("3. 로그인 성공.");
            // 로그인이 되었다.
            // Authentication에 있는 인증된 Principal 객체를 PrincipalDetails 객체로 꺼낸다.
            PrincipalDetails principalDetails = (PrincipalDetails) authentication.getPrincipal();
            log.info("username : " + principalDetails.getMember().getEmail());
            log.info("password : " + principalDetails.getMember().getPassword());
            log.info("============================================================\n");

            // 4. authentication을 반환해준다.
            // authentication 객체를 session에 저장해야 하므로 반환한다. 세션에 저장하면 편리하게 권한관리를 할 수 있다.
            // 반환된 Authentication 객체가 세션에 저장된다.
            log.info("4. authentication 반환");
            return authentication;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

        // attemptAuthentication()에서 인증이 성공되면 다음 수행되는 메서드, JWT를 발급해준다.
        @Override
        protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authResult) throws IOException, ServletException {
            log.info("인증 완료 : JwtAuthenticationFilter.successfulAuthentication");
            // 5. JWT 발급

            super.successfulAuthentication(request, response, chain, authResult);
        }
}
