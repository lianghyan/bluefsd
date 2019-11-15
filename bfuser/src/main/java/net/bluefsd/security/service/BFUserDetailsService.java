package net.bluefsd.security.service;

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
import net.bluefsd.security.dao.UserRepository;
import net.bluefsd.security.filter.BearTokenUtil;

@Service
public class BFUserDetailsService implements UserDetailsService {
	Logger log = LoggerFactory.getLogger(BFUserDetailsService.class);

	@Autowired
	UserRepository userRepository;

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

	public String login(String username, String password) {
//		UsernamePasswordAuthenticationToken upToken = new UsernamePasswordAuthenticationToken(username, password);
//		final Authentication authentication = authenticationManager.authenticate(upToken);
//		SecurityContextHolder.getContext().setAuthentication(authentication);
		final UserDetails userDetails = loadUserByUsername(username);
		final String token = jwtTokenUtil.generateToken(userDetails);
		return token;
	}


	/*
	 * 
	 */
	public BFUser createAccount(BFUser userToAdd) {
		final String username = userToAdd.getUserName();
		if (userRepository.findUserByName(username) != null) {
			return null;
		}
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		final String rawPassword = userToAdd.getPassword();
		userToAdd.setPassword(encoder.encode(rawPassword));
		return userRepository.save(userToAdd);
	}

	public void updateProfile(BFUser u) {
		userRepository.save(u);
	}

	private void sendMail(BFUser userToAdd) {

	}

}
