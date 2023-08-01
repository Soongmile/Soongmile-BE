package soongmile.soongmileback.service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import soongmile.soongmileback.domain.Member;
import soongmile.soongmileback.domain.MemberRole;
import soongmile.soongmileback.repository.MemberRepository;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class MemberSecurityService implements UserDetailsService {

    private final MemberRepository memberRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
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
        authorities.add(new SimpleGrantedAuthority(MemberRole.USER.getValue()));
        System.out.println("로그인 성공!!!");
        return new User(member.getEmail(), member.getPassword(), authorities);
    }
}
