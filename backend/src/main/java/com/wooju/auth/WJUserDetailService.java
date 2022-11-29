package com.wooju.auth;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import com.wooju.entity.User;
import com.wooju.repository.UserRepository;


/**
 * 현재 액세스 토큰으로 부터 인증된 유저의 상세정보(활성화 여부, 만료, 롤 등) 관련 서비스 정의.
 */
@Component
public class WJUserDetailService implements UserDetailsService{
	@Autowired
	UserRepository userRepository;
	
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    		User user = userRepository.findByEmail(username);
    		if(user != null) {
    			UserInfo userDetails = new UserInfo(user);
    			return userDetails;
    		}
    		return null;
    }
}
