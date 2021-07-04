package ilion.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ilion.admin.negocio.PropEnum;
import ilion.admin.negocio.PropNegocio;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Service
public class JWTUtil {
    
    public final String TOKEN_HEADER = "Authentication";
    
    @Autowired
    private PropNegocio propNegocio;
    
    public String create(String subject) {
    	
    	String key = propNegocio.findValueById(PropEnum.JWT_SECRECT_KEY);
    	
        return Jwts.builder()
                .setSubject(subject)
                .signWith(SignatureAlgorithm.HS512, key)
                .compact();
    }
    
    public Jws<Claims> decode(String token){
    	
    	String key = propNegocio.findValueById(PropEnum.JWT_SECRECT_KEY);
    	
        return Jwts.parser().setSigningKey(key).parseClaimsJws(token);
    }
	
}