package tn.enis.tool.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import tn.enis.tool.entity.Tool;

@Repository
public interface ToolRepository extends JpaRepository<Tool, Long> {

}
