package com.irahul.tbtf.service.impl;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.apache.commons.codec.binary.Base64;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.irahul.tbtf.entity.User;
import com.irahul.tbtf.entity.impl.UserImpl;
import com.irahul.tbtf.repository.UserRepository;
import com.irahul.tbtf.service.UserService;
import com.irahul.tbtf.service.exception.ErrorCode;
import com.irahul.tbtf.service.exception.InvalidFieldException;
import com.irahul.tbtf.service.exception.TBTFException;

@Service
public class UserServiceImpl implements UserService {
	private static final int MAX_NAME_LENGTH = 45;
	private static final int MAX_PIN_LENGTH = 10;

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
		if(StringUtils.isEmpty(user.getPin()) || user.getPin().length()>MAX_PIN_LENGTH){			
			throw new InvalidFieldException("pin is required");
		}
		
		if(StringUtils.isEmpty(user.getFirstName()) || user.getFirstName().length()>MAX_NAME_LENGTH){			
			throw new InvalidFieldException("firstName is required");
		}
		
		if(StringUtils.isEmpty(user.getLastName()) || user.getLastName().length()>MAX_NAME_LENGTH){			
			throw new InvalidFieldException("lastName is required");
		}
		
		//let us hash the pin - TBTF bank does basic MD5
		UserImpl impl = (UserImpl)user;
		impl.setPin(md5base64(user.getPin()));		
		long id =  userRepository.addUser(user);
		return getUser(id);
	}
	
	@Override
	@Transactional
	public List<User> getUsers(String firstName, String lastName) {
		List<User> returnList = new ArrayList<>();
		if(StringUtils.isEmpty(firstName) && StringUtils.isEmpty(lastName)){
			throw new TBTFException(ErrorCode.MISSING_DATA, "no search parameter provided");	
		}
		else{
			returnList = userRepository.search(firstName, lastName);
		}		
		return returnList;
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
