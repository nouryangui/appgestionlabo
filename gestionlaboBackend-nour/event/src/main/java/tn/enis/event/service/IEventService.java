package tn.enis.event.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import tn.enis.event.entity.Event;
import tn.enis.event.exceptions.MaPropreException;
import tn.enis.event.exceptions.MaSecondeException;

public interface IEventService {
	List<Event> findAll() throws MaSecondeException,MaPropreException;
	Event getById(Long id);

	Event add(Event evenement);

	void delete(Long id);

	Event update(Event evenement);
	Page<Event> getAll(Pageable pageable);


}
