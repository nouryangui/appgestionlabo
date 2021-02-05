package tn.enis.tool.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import tn.enis.tool.entity.Tool;

public interface IToolService {
	List<Tool> findAll();

	Tool getById(Long id);

	Tool add(Tool tool);

	void delete(Long id);

	Tool update(Tool tool);
	Page<Tool> getAll(Pageable pageable);

}
