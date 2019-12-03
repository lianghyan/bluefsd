package net.bluefsd.user.service;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import net.bluefsd.entity.BFUser;
import net.bluefsd.user.dao.UserRepository;
import net.bluefsd.user.filter.BearTokenUtil;
import net.bluefsd.user.util.VerifyCodeUtil;

@Service
public class BFUserDetailsService implements UserDetailsService {
	Logger log = LoggerFactory.getLogger(BFUserDetailsService.class);

	@Autowired
	UserRepository userRepository;

	@Autowired
	MailService mailService;

//	@Autowired
//	private AuthenticationManager authenticationManager;

	@Autowired
	private BearTokenUtil jwtTokenUtil;

	@Override
	public UserDetails loadUserByUsername(String input) throws UsernameNotFoundException {
		log.debug("--------------try to find user: " + input);
		BFUser fsduser = userRepository.findUserByName(input);
		if (null == fsduser) {
			throw new UsernameNotFoundException(input);
		}
		String un = fsduser.getUserName();
		String pwd = fsduser.getPassword();
		String role = fsduser.getRole();

		List<SimpleGrantedAuthority> auList = new ArrayList<>();
		SimpleGrantedAuthority sga = new SimpleGrantedAuthority(role);
		auList.add(sga);
		User u = new User(un, pwd, auList);
		return u;
	}

	public String login(String username, String password) throws Exception {
//		UsernamePasswordAuthenticationToken upToken = new UsernamePasswordAuthenticationToken(username, password);
//		final Authentication authentication = authenticationManager.authenticate(upToken);
//		SecurityContextHolder.getContext().setAuthentication(authentication);
		if (username == null || password == null) {
			throw new Exception("User name or password is invalid!");
		}
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		final UserDetails userDetails = loadUserByUsername(username);
		if (userDetails == null) {
			throw new Exception("Account doesn't exist!");
		}
		String encodedPassword = userDetails.getPassword();

		boolean matched = encoder.matches(password, encodedPassword);
		if (matched) {
			final String token = jwtTokenUtil.generateToken(userDetails);
			return token;
		} else {
			throw new Exception("Password is incorrect!");
		}
	}

	/*
	 * 
	 */
	public BFUser createAccount(BFUser userToAdd) throws Exception {
		final String username = userToAdd.getUserName();
		if (userRepository.findUserByName(username) != null) {
			throw new Exception("User " + username + " already exists!");
		}
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		final String rawPassword = userToAdd.getPassword();
		userToAdd.setPassword(encoder.encode(rawPassword));
		userToAdd.setVerifyCode(VerifyCodeUtil.generateVerifyCode(32));
		userToAdd.setConfirmed("N");
		return userRepository.save(userToAdd);
	}

	public void saveOrUpdateProfile(BFUser u) {
		userRepository.save(u);
	}

	private void sendMail(BFUser userToAdd) {
		mailService.sendMail(userToAdd);
	}

	public String verify(String verifyCode) {
		BFUser user = userRepository.findUserByVerifyCode(verifyCode);
		if (user != null) {
			user.setConfirmed("Y");
			userRepository.save(user);
			return user.getUserName();
		}
		return null;
	}

}
