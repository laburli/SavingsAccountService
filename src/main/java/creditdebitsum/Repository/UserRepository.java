package creditdebitsum.Repository;

import creditdebitsum.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;


public interface UserRepository extends JpaRepository<Transaction, Integer> {

}
