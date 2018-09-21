package com.example.demo.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.yaml.snakeyaml.tokens.Token.ID;

import com.example.demo.models.Idea;

public interface IdeaRepository extends CrudRepository<Idea, Long> {
	List<Idea> findAll();
	List<Idea> deleteById(ID id);




}
