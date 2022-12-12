package com.bigsef.springhero.config;

import com.bigsef.springhero.auth.User;
import org.springframework.core.convert.converter.Converter;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.stereotype.Component;

import java.util.Collections;

@Component
public class JwtUserConverter implements Converter<Jwt, UsernamePasswordAuthenticationToken> {
    @Override
    public UsernamePasswordAuthenticationToken convert(Jwt source) {
        User user = new User();
        user.setId(Long.parseLong(source.getSubject()));
        return new UsernamePasswordAuthenticationToken(user,  source, Collections.EMPTY_LIST);
    }
}
