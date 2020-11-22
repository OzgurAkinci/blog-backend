package com.app.blog.config.credential;

import com.app.blog.config.security.CustomUserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public interface ICredential {

    public CustomUserDetails getUserByUserName(String userName) throws UsernameNotFoundException;

}
