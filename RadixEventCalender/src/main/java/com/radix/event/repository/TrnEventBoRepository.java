package com.radix.event.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.radix.event.model.TrnEventBo;

/**
 * @author Krunal Prajapati
 *
 */
@Repository
@Transactional
public interface TrnEventBoRepository extends JpaRepository<TrnEventBo, String>{

	/**
	 * @author Krunal Prajapati
	 * @param eventId
	 * @return List of Sub Event Data As Per the EventId
	 */
	@Query("from TrnEventBo where eventId=?1 and status='1'")
	List<TrnEventBo> findByEventId(String eventId);

}
