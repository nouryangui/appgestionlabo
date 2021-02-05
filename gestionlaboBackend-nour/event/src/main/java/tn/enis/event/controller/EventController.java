package tn.enis.event.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import tn.enis.event.entity.Event;
import tn.enis.event.repository.EventRepository;
import tn.enis.event.service.IEventService;

@RestController
@RequestMapping("/api/events")

public class EventController {
	@Autowired
	IEventService eventService;
	@Autowired
	EventRepository eventRepository;
	@PostMapping()
	public Event addEvent(@RequestBody Event event) {
		return eventService.add(event);
	}

	@GetMapping(value = "/count")
	public Long findCountEvents() {
		return eventRepository.count();
	}

	@GetMapping()
	public List<Event> findAllEvents() throws Exception {
		// return eventService.findAll();
		try {
			return eventService.findAll();
		} catch (Exception e) {

		}
		return null;

	}

	@GetMapping(value = "{id}")
	public Event findoneEVent(@PathVariable Long id) {

		return eventService.getById(id);

	}

	@GetMapping(value = "/all")
	public Page<Event> findAllEventsPaginator(@RequestParam(name = "page", defaultValue = "0") int page,
			@RequestParam(name = "size", defaultValue = "2") int size) {
		return eventService.getAll(PageRequest.of(page, size));
	}

	@PutMapping()
	public Event updateEvent(@RequestBody Event event) {
		return eventService.update(event);

	}

	@DeleteMapping(value = "/{id}")
	public void DeleteMember(@PathVariable Long id) {
		eventService.delete(id);

	}

}
