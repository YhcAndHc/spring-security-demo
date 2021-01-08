package com.yhc.demo.plugin;

import java.io.UnsupportedEncodingException;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

import com.yhc.demo.util.UuidUtil;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.SignatureException;
import io.jsonwebtoken.UnsupportedJwtException;

/**
 * JwtToken工具类
 */
@Configuration
public class JwtService {

	private static final Logger log = LoggerFactory.getLogger(JwtService.class);

	@Value("${jwt.secret:123456}")
	private String secret;
	@Value("${jwt.expiration:60}")
	private Long expiration;

	/**
	 * 生成token
	 * 
	 * @param username
	 * @return token
	 */
	public String generateToken(String username) {

		Claims claims = Jwts.claims();
		claims.setIssuer(username); // jwt发行人
		claims.setIssuedAt(new Date()); // jwt生成时间
		claims.setExpiration(getExp()); // jwt过期时间
		claims.setSubject("auth"); // jwt主题
		claims.setAudience("yhc"); // jwt接受方
		claims.setId(UuidUtil.getUuid()); // jwt唯一身份标识
		claims.setNotBefore(new Date()); // jwt在此之前不可用

		return generateToken(claims);
	}

	/**
	 * 刷新token
	 * 
	 * @param old token
	 * @return new token
	 */
	public String refreshToken(String token) {
		Claims claims = validToken(token);
		if (claims == null) {
			return null;
		}
		claims.setIssuedAt(new Date());
		claims.setExpiration(getExp());
		return generateToken(claims);
	}

	/**
	 * 根据token获取发行人
	 * 
	 * @param token
	 * @return issuer
	 */
	public String getIssuer(String token) {
		Claims claims = validToken(token);
		return claims.getIssuer();
	}

	/**
	 * 校验jwtToken,如无效，则返回Null,反之，返回负载对象
	 */
	private Claims validToken(String token) {
		Claims claims = getClaimsFromToken(token);
		if (claims != null) {
			Date exp = claims.getExpiration();
			if (exp.before(new Date())) {
				log.warn("# jwtToken已失效:{}", token);
				throw new RuntimeException("invalid token");
			}
		}
		return claims;
	}

	/**
	 * 从token中获取JWT中的负载参数
	 */
	private Claims getClaimsFromToken(String token) {
		Claims claims = null;
		try {
			claims = Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody();
		} catch (Exception e) {
			log.warn("# jwtToken格式验证失败:{}", token, e);
			throw new RuntimeException("token verification failed");
		}
		return claims;
	}

	/** 根据负载参数生成token */
	private String generateToken(Claims claims) {
		return Jwts.builder().setClaims(claims).signWith(SignatureAlgorithm.HS512, secret).compact();
	}

	/** 获取token有效期 */
	private Date getExp() {
		return new Date(System.currentTimeMillis() + expiration * 1000);
	}

	public static void main(String[] args) throws ExpiredJwtException, UnsupportedJwtException, MalformedJwtException,
			SignatureException, IllegalArgumentException, UnsupportedEncodingException {

		String username = "yhc";

		Claims claims = Jwts.claims();
		claims.setIssuer(username); // jwt发行人
		claims.setIssuedAt(new Date()); // jwt生成时间
		claims.setExpiration(new Date(System.currentTimeMillis() + 3600 * 1000)); // jwt过期时间
		claims.setSubject("test"); // jwt主题
		claims.setAudience("yhc"); // jwt接受方
		claims.setId("uuid"); // jwt唯一身份标识
//		claims.setNotBefore(new Date()); // jwt在此之前不可用

		String visitTK = Jwts.builder().setClaims(claims).signWith(SignatureAlgorithm.HS512, "sad12f").compact();
		System.out.println(visitTK);// SystemValue.JWT_HEADER_VALUE_PREFIX +

		Claims claimsDecode = Jwts.parser().setSigningKey("sad12f").parseClaimsJws(visitTK).getBody();
		System.out.println(claimsDecode.getIssuer());

	}
}
