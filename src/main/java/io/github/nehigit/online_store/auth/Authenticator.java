package io.github.nehigit.online_store.auth;

import io.github.nehigit.online_store.db.sql.UserRepository;
import io.github.nehigit.online_store.model.User;
import lombok.RequiredArgsConstructor;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Component
public class Authenticator {
    private final UserRepository userRepository;
    private final String seed = "0671d0c694c664739e4e5b550006dd41";

    public boolean authenticate(List<String> credentials) {
        Optional<User> userBox = this.userRepository.getUser(credentials.getFirst());
        return userBox.isPresent() &&
                userBox.get().password().equals(DigestUtils.md5Hex(credentials.getLast() + seed));
    }
}
