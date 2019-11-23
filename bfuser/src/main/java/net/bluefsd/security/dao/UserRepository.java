package net.bluefsd.security.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import net.bluefsd.entity.BFUser;

@Repository(value="userRepository")
public interface UserRepository extends JpaRepository<BFUser, Long> {
	
	@Transactional(readOnly = true)
	@Query(value = "SELECT u FROM BFUser u WHERE userName=:account or email=:account")
	public BFUser findUserByName(String account);

	@Transactional(readOnly = true)
	@Query(value = "SELECT u FROM BFUser u WHERE verifyCode=:verifyCode")
	public BFUser findUserByVerifyCode(String verifyCode);

}
