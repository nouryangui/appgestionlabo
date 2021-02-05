package tn.enis.tool.controller;

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

import tn.enis.tool.entity.Tool;
import tn.enis.tool.repository.ToolRepository;
import tn.enis.tool.service.IToolService;

@RequestMapping("/api/tools")
@RestController

public class ToolController {
	@Autowired
	IToolService toolService;
	@Autowired
	ToolRepository toolRepository;
	@PostMapping()
	public Tool addTool(@RequestBody Tool tool) {
		return toolService.add(tool);
	}

	@GetMapping()
	public List<Tool> findAllTools() {
		return toolService.findAll();
	}

	@GetMapping(value = "/all")
	public Page<Tool> findAllToolsPaginator(@RequestParam(name = "page", defaultValue = "0") int page,
			@RequestParam(name = "size", defaultValue = "2") int size) {
		return toolService.getAll(PageRequest.of(page, size));
	}
	@GetMapping(value = "/count")
	public Long findCountTools() {
		return toolRepository.count();
	}
	@PutMapping()
	public Tool updateTool(@RequestBody Tool tool) {
		return toolService.update(tool);

	}

	@GetMapping(value = "{id}")
	public Tool findoneTool(@PathVariable Long id) {

		return toolService.getById(id);

	}

	@DeleteMapping(value = "{id}")
	public void DeleteTool(@PathVariable Long id) {
		toolService.delete(id);

	}

}
