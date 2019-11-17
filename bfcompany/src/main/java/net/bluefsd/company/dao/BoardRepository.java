package net.bluefsd.company.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import net.bluefsd.entity.BoardDirector;

@Repository(value = "boardRepository")
public interface BoardRepository extends JpaRepository<BoardDirector, Long> {
	@Transactional(readOnly = true)
	@Query(value = "DELETE FROM BoardDirector u where companyCd=:companyCd")
	public void deleteByCompanyCd(@Param("companyCd") String companyCd);

	@Transactional(readOnly = true)
	@Query(value = "SELECT directorName FROM BoardDirector u where companyCd=:companyCd")
	public List<String> findByCompanyCd(@Param("companyCd") String companyCd);

}
