package tr.mu.posta.cuma.ide.repos;

import org.springframework.data.repository.CrudRepository;
import tr.mu.posta.cuma.ide.models.User;

public interface UserRepository extends CrudRepository<User, Integer>{
  User findByUsername(String username);
}
