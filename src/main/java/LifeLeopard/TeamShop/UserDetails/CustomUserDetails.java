package LifeLeopard.TeamShop.UserDetails;


import LifeLeopard.TeamShop.Models.Accounts;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class CustomUserDetails implements org.springframework.security.core.userdetails.UserDetails {
    private Accounts accounts;
    public CustomUserDetails(Accounts accounts){
        this.accounts = accounts;
    }
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<GrantedAuthority> granList =  new ArrayList<GrantedAuthority>();
//            if(user.getRoles().isEmpty()){
//                user.setRoles("USER");
//            }
        GrantedAuthority grantedAuthority = new SimpleGrantedAuthority(accounts.getRoles().getRoleName()) ;
        granList.add(grantedAuthority);
        return granList;
    }

    @Override
    public String getPassword() {
        return accounts.getPassword();
    }

    @Override
    public String getUsername() {
        return accounts.getUsername();
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
        return accounts.isStatus();
    }

}
