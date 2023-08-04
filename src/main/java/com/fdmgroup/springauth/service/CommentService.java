package com.fdmgroup.springauth.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.fdmgroup.springauth.model.Comment;
import com.fdmgroup.springauth.repository.CommentRepository;


@Service
public class CommentService {
	private final CommentRepository commentRepo;

	public CommentService(CommentRepository commentRepo) {
		this.commentRepo = commentRepo;
	}
	
	public List<Comment> listAllComments(){
		return commentRepo.findAll();
	}

	public Comment addComment(Comment comment) {
		return commentRepo.save(comment);
	}

	public Comment findById(long id) {
		Optional<Comment> optComment = commentRepo.findById(id);
		if (optComment.isEmpty()) {}
		return optComment.get();
	}

	public void deleteById(long id) {
		if (!commentRepo.existsById(id)) {}
		commentRepo.deleteById(id);
		
	}

	public Comment updateComment(Comment comment, long id) {
		Optional<Comment> optComment = commentRepo.findById(id);
		if (optComment.isEmpty()) {}
		comment.setId(id);
		return commentRepo.save(comment);
	}
	
}
