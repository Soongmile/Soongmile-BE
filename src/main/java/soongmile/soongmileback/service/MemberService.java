package soongmile.soongmileback.service;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.PropertySource;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import soongmile.soongmileback.domain.Member;
import soongmile.soongmileback.domain.response.SignInResponse;
import soongmile.soongmileback.jwt.JwtTokenProvider;
import soongmile.soongmileback.domain.request.SignInRequest;
import soongmile.soongmileback.domain.request.SignUpRequest;
import soongmile.soongmileback.repository.MemberRepository;

@Service
@RequiredArgsConstructor
@PropertySource("classpath:application.properties")
@Transactional(readOnly = true)
public class MemberService {

    private final MemberRepository memberRepository;
    private final JwtTokenProvider jwtTokenProvider;
    private final PasswordEncoder passwordEncoder;

    @Transactional
    public Long create(SignUpRequest signUpRequest) {
        if (!signUpRequest.getPassword().equals(signUpRequest.getPasswordchecker())) {
            throw new RuntimeException();
        }
        Member findMember = memberRepository.findByEmail(signUpRequest.getEmail());
        if (findMember != null) {
            throw new RuntimeException();
        }
        Member member = new Member(signUpRequest.getEmail(), passwordEncoder.encode(signUpRequest.getPassword()), signUpRequest.getMembername());
        memberRepository.save(member);
        return member.getId();
    }

    @Transactional
    public SignInResponse login(SignInRequest signInRequest) {
        Member member = memberRepository.findByEmail(signInRequest.getEmail());

        if (member == null) {
            throw new UsernameNotFoundException("사용자를 찾을수 없습니다.");
        }

        if (!passwordEncoder.matches(signInRequest.getPassword(), member.getPassword())) {
            throw new IllegalStateException("비밀번호가 틀립니다.");
        }

        String token = jwtTokenProvider.createToken(member.getUsername(), member.getRoles());
        return new SignInResponse(token, member.getMemberName());
    }
}
