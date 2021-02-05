package tn.enis.event.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import lombok.extern.slf4j.Slf4j;
import tn.enis.event.entity.Event;
import tn.enis.event.exceptions.MaPropreException;
import tn.enis.event.exceptions.MaSecondeException;
import tn.enis.event.repository.EventRepository;

@Slf4j
@Service
public class EventServiceImpl implements IEventService {
	private final EventRepository eventRepository;

	public EventServiceImpl(EventRepository eventRepository) {
		super();
		this.eventRepository = eventRepository;
	}

	@Override
	public List<Event> findAll() throws MaSecondeException {
		List<Event> events = eventRepository.findAll();
		
			if(events.isEmpty())
			{
				throw new MaSecondeException("nour 5arya");
			}
			return events;
		
			
			
		
		
	}

	@Override
	public Event getById(Long id) {
		return eventRepository.findById(id).get();
	}

	@Override
	public Event add(Event event) {
		return eventRepository.save(event);
	}

	@Override
	public void delete(Long id) {
		eventRepository.deleteById(id);
	}

	@Override
	public Event update(Event event) {

		return eventRepository.save(event);
	}

	@Override
	public Page<Event> getAll(Pageable pageable) {
		
		Page<Event> eventPage = eventRepository.findAll(pageable);
		return new PageImpl<>(eventPage.getContent(), pageable, eventPage.getTotalElements());

	}

}
