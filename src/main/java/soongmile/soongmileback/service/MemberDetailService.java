package soongmile.soongmileback.service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import soongmile.soongmileback.domain.Member;
import soongmile.soongmileback.domain.PrincipalDetails;
import soongmile.soongmileback.repository.MemberRepository;

@Service
@RequiredArgsConstructor
public class MemberDetailService implements UserDetailsService {

    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;

    // 시큐리티 session -> Authentication -> UserDetails
    // 시큐리티 세션(내부 Authentication(내부 UserDetails(PrincipalDetails)))
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Member member = memberRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("해당하는 유저를 찾을 수 없습니다."));

        return new PrincipalDetails(member);
    }

    // 해당하는 User 의 데이터가 존재한다면 UserDetails 객체로 만들어서 리턴
    /*private UserDetails createUserDetails(Member member) {
        return User.builder()
                .username(member.getUsername())
                .password(passwordEncoder.encode(member.getPassword()))
                .roles(member.getRoles().toArray(new String[0]))
                .build();
    }*/
}/*
        Member member = memberRepository.findByEmail(email);

        if (!member.getEmail().equals(email)) {
            System.out.println("로그인 실패???");
            throw new UsernameNotFoundException("사용자를 찾을수 없습니다.");
        }

        return member;*/
/*
        return memberRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("사용자를 찾을 수 없습니다."));

        Member member = this.memberRepository.findByEmail(email);

        if (!member.getEmail().equals(email)) {
            System.out.println("로그인 실패???");
            throw new UsernameNotFoundException("사용자를 찾을수 없습니다.");
        }
        List<GrantedAuthority> authorities = new ArrayList<>();
        /*if ("admin".equals(email)) {
            authorities.add(new SimpleGrantedAuthority(MemberRole.ADMIN.getValue()));
        } else {
            authorities.add(new SimpleGrantedAuthority(MemberRole.USER.getValue()));
        }*/
        /*authorities.add(new SimpleGrantedAuthority(MemberRole.USER.getValue()));
        System.out.println("로그인 성공!!!");
        return new User(member.getEmail(), member.getPassword(), authorities);*/
    //}
//}
