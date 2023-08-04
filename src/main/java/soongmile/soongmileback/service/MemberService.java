package soongmile.soongmileback.service;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import soongmile.soongmileback.domain.Member;
import soongmile.soongmileback.repository.MemberRepository;
import soongmile.soongmileback.utils.JwtUtil;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MemberService {

    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;

    @Value("${jwt.secret}")
    private String secretKey;

    private Long expiredMs = 1000 * 60 * 60 * 24l;

    @Transactional
    public Member create(String email, String password, String memberName) {
        Member member = new Member(email, passwordEncoder.encode(password), memberName);
        System.out.println(member.getId());
        memberRepository.save(member);
        return member;
    }

    @Transactional
    public String login(String email, String password) {
        return JwtUtil.createJwt(email, secretKey, expiredMs);
    }
}
