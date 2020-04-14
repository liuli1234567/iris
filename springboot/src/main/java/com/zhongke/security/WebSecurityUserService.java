package com.zhongke.security;

import com.zhongke.pojo.Permission;
import com.zhongke.pojo.PlatformUser;
import com.zhongke.pojo.Role;
import com.zhongke.service.PlatformUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * @ClassName WebSecurityUserService
 * @Description
 * @Author liuli
 * @Date 2020/4/10 18:19
 * @Version 1.0
 **/
@Service
public class WebSecurityUserService implements UserDetailsService {

    public static BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    @Autowired(required = false)
    private PlatformUserService platformUserService;

    @Override
    public UserDetails loadUserByUsername(String platformUserName) throws UsernameNotFoundException {
        PlatformUser platformUser = platformUserService.findByPlatformUserName(platformUserName);
        if (platformUser == null){
            return null;
        }
        List<GrantedAuthority> list = new ArrayList<GrantedAuthority>();
        Set<Role> roles = platformUser.getRoles();
        for(Role role : roles){
            Set<Permission> permissions = role.getPermissions();
            for(Permission permission : permissions){
                //授权
                list.add(new SimpleGrantedAuthority(permission.getKeyword()));
            }
        }
        //User user = new User(merchantName, passwordEncoder.encode("123"), list);
        //return new User(platformUserName, platformUser.getPassword(), list);
        return new User(platformUserName, passwordEncoder.encode(platformUser.getPassword()), list);
    }
}
