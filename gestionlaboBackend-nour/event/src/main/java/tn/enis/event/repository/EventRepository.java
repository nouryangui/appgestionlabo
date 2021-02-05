package tn.enis.event.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tn.enis.event.entity.Event;
@Repository
public interface EventRepository extends JpaRepository<Event, Long> {

}
