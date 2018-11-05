package com.petshop.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.security.core.userdetails.User;

import com.petshop.models.Role;
import com.petshop.models.Usuario;
import com.petshop.repositories.UserRepository;

import java.util.HashSet;
import java.util.Set;

@Service
public class UserDetailsServiceImpl implements UserDetailsService
{
	@Autowired
	private UserRepository userRepository;

	// carrega usuario por username
	@Override
	@Transactional(readOnly = true)
	public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException
	{
		Usuario user = userRepository.findByLogin(login);

		Set<GrantedAuthority> grantedAuthorities = new HashSet<>();
		
		for (Role role : user.getRoles())
		{
			grantedAuthorities.add(new SimpleGrantedAuthority(role.getName()));
		}

//		return new org.springframework.security.core.userdetails.User(user.getLogin(), user.getSenha(), grantedAuthorities);
//		UserDetails userDetails = new User(user.getLogin(), user.getSenha(), grantedAuthorities);
		
//		return userDetails;
		return null;
		
	}
}