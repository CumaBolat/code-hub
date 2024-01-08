package tr.mu.posta.cuma.ide.repos;

import tr.mu.posta.cuma.ide.models.User;

public interface UserRepository {
  User findByUsername(String username);
}
