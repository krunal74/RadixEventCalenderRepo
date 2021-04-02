package com.radix.event.repository;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.radix.event.model.MstEventBo;

/**
 * @author Krunal Prajapati
 *
 */
@Repository
@Transactional
public interface MstEventRepository extends JpaRepository<MstEventBo, String>{
	
	/**
	 * @author Krunal Prajapati
	 * @param eventId
	 * @return 0 or 1 from delete Status from MST_EVENT
	 */
	@Modifying
	@Query("delete from MstEventBo where eventId=?1 and status='1' ")
	public int deleteByEventId(String eventId);

}
