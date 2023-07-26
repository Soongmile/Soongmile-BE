package soongmile.soongmileback.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import soongmile.soongmileback.domain.User;
import soongmile.soongmileback.repository.UserRepository;

@Service
@Transactional(readOnly = true)
public class UserService {

    private UserRepository userRepository;

    @Transactional
    public Long join(User user) {
        userRepository.save(user);
        return user.getId();
    }
}
