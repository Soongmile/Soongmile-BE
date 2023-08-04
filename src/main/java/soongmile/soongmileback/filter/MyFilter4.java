package soongmile.soongmileback.filter;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpMethod;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Slf4j
public class MyFilter4 implements Filter {

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        log.info("Security Filter Start");

        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse res = (HttpServletResponse) response;

		/*
		토큰: token 이 필요하다. id/pw 가 정상적으로 들어와서 로그인이 완료되면 토큰을 만들고 반환해준다.
		클라이언트는 요청할 때 마다 header 에 Authorization - value 쌍으로 토큰을 넣으면 된다.
		이때 토큰이 서버가 갖고있는 토큰인지 검증만 하면 된다.
		 */

        if (req.getMethod().equals(HttpMethod.POST.name())) {
            log.info("POST 요청");
            String headerAuth = req.getHeader("Authorization");
            if (headerAuth.equals("token")) {
                log.info("Filter 4");
                log.info(headerAuth);
                chain.doFilter(req, res);
            } else {
                log.error("인증안됨");
            }
        }
    }
}
