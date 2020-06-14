package com.example.socialNetwork.service;

import java.util.LinkedHashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.socialNetwork.model.Comment;
import com.example.socialNetwork.model.Likes;
import com.example.socialNetwork.model.Post;
import com.example.socialNetwork.model.User;
import com.example.socialNetwork.repository.CommentRepository;
import com.example.socialNetwork.repository.LikeRepository;
import com.example.socialNetwork.repository.PostRepository;
import com.example.socialNetwork.repository.UserRepository;

@Service
public class PostService {
	@Autowired
	PostRepository postRepo;
	@Autowired
	LikeRepository likeRepo;
	@Autowired
	CommentRepository commentRepo;
	@Autowired 
	FriendsService friendService;
	@Autowired
	UserRepository userRepo;
	

	public boolean newpost(Post post, User user) {
		post.setUser(user);
		postRepo.save(post);
		return true;
	}

	public Post[] getpost(User user) {
		Post[] posts = postRepo.findAllByUserOrderByCreatedDesc(user);
		return posts;
	}

	public boolean putLike(int idpost, User user) {
		Post post = postRepo.findById(idpost);
		Likes likes = likeRepo.findByUserAndPost(user, post);
		
		if (likes == null) {
			Likes like = new Likes();
			like.setUser(user);
			like.setPost(post);
			likeRepo.save(like);
			return true;
		}else {
			likeRepo.delete(likes);
			return false;
		}
	}
	
	public Comment[] getComents(int idpost) {
		Post post = postRepo.findById(idpost);
		Comment[] comments = commentRepo.findAllByPostOrderByCreatedDesc(post);
		return comments;
	}

	public boolean newcomment(int idpost, String text, User user) {
		Post post = postRepo.findById(idpost);
		Comment comment = new Comment();
		comment.setPost(post);
		comment.setText(text);
		comment.setUser(user);
		commentRepo.save(comment);
		return true;
	}

	public Post[] getotherpost(User user1, User user2) {
		boolean otv = friendService.itsFriend(user1, user2);
		if(otv) {
			return postRepo.findAllByUserOrderByCreatedDesc(user2);
		}else {
			return postRepo.findAllByUserAndOnlyFriendsOrderByCreatedDesc(user2, "false");
		}
	}
	
	public Map<Integer, Object> getlenta(User user1) {
		Map<Integer, Object> lenta = new LinkedHashMap<>();
		Map<Integer, Object> friends = friendService.getIdFriends(user1);
		for (int i = friends.size()-1; i >= 0; i--) {
			User user = userRepo.findById((int)friends.get(i));
			Post[] post = this.getotherpost(user1, user);
			for (int j = 0; j < post.length; j++) {
				lenta.put(post[j].getId(), post[j]);
			}
		}
		
//		friends.forEach((key, friendId)->{
//			User user = userRepo.findById((int)friendId);
//			Post[] post = this.getotherpost(user1, user);
//			for (int i = 0; i < post.length; i++) {
//				lenta.put(post[i].getId(), post[i]);
//			}
//		});
		
		return lenta;
	}
	

	
}
