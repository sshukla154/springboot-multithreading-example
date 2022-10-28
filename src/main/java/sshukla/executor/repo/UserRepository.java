package sshukla.executor.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import sshukla.executor.entity.User;

/**
 * @author 'Seemant Shukla' on '28/10/2022'
 */

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
}
