package soongmile.soongmileback.service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import soongmile.soongmileback.domain.Member;
import soongmile.soongmileback.repository.MemberRepository;

@Service
@RequiredArgsConstructor
public class MemberDetailService implements UserDetailsService {

    private final MemberRepository memberRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Member member = memberRepository.findByEmail(email);

        if (!member.getEmail().equals(email)) {
            System.out.println("로그인 실패???");
            throw new UsernameNotFoundException("사용자를 찾을수 없습니다.");
        }

        return member;
    }
}
