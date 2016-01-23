package net.canwhn.service;

import java.util.List;

import net.canwhn.bean.User;



public interface UserService {
	void save(User user);
	boolean update(User user);
	boolean delete(int id);
	User findById(int id);
	List<User> findAll();
}
