package com.example.demo.services;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.example.demo.models.Idea;
import com.example.demo.models.User;
import com.example.demo.repositories.IdeaRepository;

@Service
public class IdeaService {

	private final IdeaRepository ideaRepository;
	
	
	public IdeaService(IdeaRepository ideaRepository) {
		this.ideaRepository=ideaRepository;
	}
	public List<Idea> allIdea(){
		return ideaRepository.findAll();
	}
	public Idea createIdea(Idea i) {
		return ideaRepository.save(i);
	}
	public Optional <Idea> oneIdea(Long id){
		return ideaRepository.findById(id);
	}
	public void joinIdea(Idea idea, User user) {
		idea.setCreator(user);
		ideaRepository.save(idea);
	}
	public void setLikes(Idea idea, User user) {
		List<User> users = idea.getLike_user();
		users.add(user);
		
		idea.setLike_user(users);
		ideaRepository.save(idea);
	}
	
	private Idea findIdea(Long id) {
		Optional<Idea> optionalIdea = ideaRepository.findById(id);
		if(optionalIdea.isPresent()) {
			return optionalIdea.get();
		}else {
			return null;
		}
		
	}
	
	public Idea updateIdea (Idea idea) {
		return ideaRepository.save(idea);
	}
	
	public Idea updateIdea(Long id, String content ,  int likes) {
		Idea idea = findIdea(id);
		
		idea.setContent(content);
		idea.setLike(likes);
		
		return ideaRepository.save(idea);
	}
	
	
	
	public void deleteIdea(Long id) {
		Idea idea = findIdea(id);
		ideaRepository.delete(idea);

	}



}
