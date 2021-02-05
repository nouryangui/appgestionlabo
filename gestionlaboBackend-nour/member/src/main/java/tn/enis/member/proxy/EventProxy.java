package tn.enis.member.proxy;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import tn.enis.member.bean.EventBean;

@FeignClient("event-service")

public interface EventProxy {
	@GetMapping("/api/events/{id}")
	EventBean findEventById(@PathVariable Long id);
	@GetMapping("/api/events")
	List<EventBean> findAllEVent();
	

}
