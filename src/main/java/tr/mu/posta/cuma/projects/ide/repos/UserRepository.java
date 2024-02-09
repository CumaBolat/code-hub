package tr.mu.posta.cuma.projects.ide.repos;

import tr.mu.posta.cuma.projects.ide.models.User;

public interface UserRepository {
  User findByUsername(String username);
}
