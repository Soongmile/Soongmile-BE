package soongmile.soongmileback.service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import soongmile.soongmileback.domain.Member;
import soongmile.soongmileback.domain.request.SignUpRequest;
import soongmile.soongmileback.jwt.JwtUtil;
import soongmile.soongmileback.repository.MemberRepository;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MemberService {

    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;

    //@Value("${jwt.secret}")
    private String secretKey = "VlwEyVBsYt9V7zq57TejMnVUyzblYcfPQye08f7MGVA9XkHa";

    private Long expiredMs = 1000 * 60 * 60 * 24l;

    @Transactional
    public Long create(SignUpRequest signUpRequest) {
        if (!signUpRequest.getPassword().equals(signUpRequest.getPasswordchecker())) {
            throw new RuntimeException();
        }
        Member member = new Member(signUpRequest.getEmail(), passwordEncoder.encode(signUpRequest.getPassword()), signUpRequest.getMembername());
        memberRepository.save(member);
        return member.getId();
    }

    @Transactional
    public String login(String email, String password) {
        return JwtUtil.createJwt(email, secretKey, expiredMs);
    }
}
