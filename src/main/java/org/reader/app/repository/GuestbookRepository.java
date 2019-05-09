package org.reader.app.repository;

import org.reader.app.model.GuestbookEntry;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.util.Streamable;
import org.springframework.stereotype.Repository;

/**
 * repository for guestBook using the entity and a long for the primary to construct sql queries under the hood
 */

@Repository
public interface GuestbookRepository extends CrudRepository<GuestbookEntry, Long> {

	
	Streamable<GuestbookEntry> findByName(String string, Sort sort);
}
