package tn.enis.member.proxy;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import tn.enis.member.bean.PublicationBean;

@FeignClient(value = "publication-service")
public interface PublicationProxy {

	@GetMapping(value = "/api/publications/{id}")
	PublicationBean recupererUnePublication(@PathVariable("id") Long id);
}
