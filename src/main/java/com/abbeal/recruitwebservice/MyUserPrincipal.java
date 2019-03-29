package com.abbeal.recruitwebservice;

import java.util.Collection;
import java.util.Collections;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.abbeal.recruitwebservice.entities.Utilisateur;

public class MyUserPrincipal implements UserDetails {
    /**
	 * 
	 */
	private static final long serialVersionUID = -447677572103999799L;
	private Utilisateur user;
 
    public MyUserPrincipal(Utilisateur user) {
        this.user = user;
    }
    //...

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return  Collections.emptyList();         
	}

	@Override
	public String getPassword() {
		return user.getPassword();
	}

	@Override
	public String getUsername() {
		return user.getMail();
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}
}
