package com.irahul.tbtf.service.impl;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import javax.transaction.Transactional;

import org.apache.commons.codec.binary.Base64;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.irahul.tbtf.entity.User;
import com.irahul.tbtf.entity.impl.UserImpl;
import com.irahul.tbtf.repository.UserRepository;
import com.irahul.tbtf.service.UserService;

@Service
public class UserServiceImpl implements UserService {
	private Logger logger = LoggerFactory.getLogger(getClass());
	
	@Autowired
	private UserRepository userRepository;

	@Override
	@Transactional//at method level
	public User getUser(long userId) {
		return userRepository.getUser(userId);
	}

	@Override
	@Transactional//at method level
	public boolean isATMPinValid(long userId, String pin) {
		User user = getUser(userId);
		if(user==null || pin==null){
			return false;
		}
		return user.getPin()!=null && user.getPin().equals(md5base64(pin));
	}

	@Override	
	@Transactional//at method level
	public User addUser(User user) {
		if(user.getPin()==null){
			//TODO what is our exception handling strategy?
			throw new IllegalArgumentException("Pin is required");
		}
		
		//let us hash the pin - TBTF bank does basic MD5
		UserImpl impl = (UserImpl)user;
		impl.setPin(md5base64(user.getPin()));		
		long id =  userRepository.addUser(user);
		return getUser(id);
	}
	
	private String md5base64(String pin){
		try {
			MessageDigest md = MessageDigest.getInstance("MD5");
			byte[] digest = md.digest(pin.getBytes("UTF-8"));
			return Base64.encodeBase64String(digest);				
		} catch (NoSuchAlgorithmException | UnsupportedEncodingException e) {
			logger.error("failed to md5",e);
		}
		
		//TODO - this needs to be handled better
		throw new IllegalArgumentException("Server fail");
	}
}
