package com.fdmgroup.springauth.controller;

import java.net.URI;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.fdmgroup.springauth.blogdto.NewCommentDTO;
import com.fdmgroup.springauth.mapper.CommentMapper;
import com.fdmgroup.springauth.model.Comment;
import com.fdmgroup.springauth.service.CommentService;

@RestController
@RequestMapping("api/v1/comments")
public class CommentController {

	public final CommentService commentService;
	private final CommentMapper commentMapper;

	public CommentController(CommentService commentService, CommentMapper commentMapper) {
		super();
		this.commentService = commentService;
		this.commentMapper = commentMapper;
	}
	
	@GetMapping
	public ResponseEntity<List<Comment>> getAllComments(){
		return ResponseEntity
				.status(HttpStatus.OK)
				.body(commentService.listAllComments());
	}
	
	@PostMapping
	public ResponseEntity<Comment> addComment(@RequestBody NewCommentDTO commentDto){
		Comment comment = commentService.addComment(commentMapper.toEntity(commentDto));
		URI location = ServletUriComponentsBuilder
				.fromCurrentRequest()
				.path("/{id}")
				.buildAndExpand(comment.getId())
				.toUri();
		return ResponseEntity
				.created(location)
				.body(comment);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Comment> getComment(@PathVariable long id){
		return ResponseEntity.ok(commentService.findById(id));
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteComment(@PathVariable long id){
		commentService.deleteById(id);
		return ResponseEntity
				.status(HttpStatus.OK)
				.build();
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Comment> updateComment(@RequestBody Comment comment, @PathVariable long id){
		return ResponseEntity
				.ok(commentService.updateComment(comment, id));
	}
}
