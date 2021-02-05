package tn.enis.member.proxy;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import tn.enis.member.bean.ToolBean;

@FeignClient("tool-service")
public interface ToolProxy {

	@GetMapping(value="/api/tools/{id}")
	ToolBean findToolById(@PathVariable Long id);

}
