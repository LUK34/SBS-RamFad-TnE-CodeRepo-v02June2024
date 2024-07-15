package kw.kng.security;

import java.security.Key;
import java.util.Date;

import javax.crypto.SecretKey;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.UnsupportedJwtException;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import kw.kng.exceptions.BlogApiException;

@Component
public class JwtTokenProvider 
{
	@Value("${app.jwt-secret}")
	private String jwtSecret;
	
	@Value("${app-jwt-expiration-millisecond}")
	private long jwtExpirationDate;

	//Generate JWT
	public String generateToken(Authentication authentication)
	{
		String username = authentication.getName();
		Date currentDate=new Date();
		Date expiryDate = new Date(currentDate.getTime() + jwtExpirationDate); //current Date the user logged in + 7 days
		
		String token= Jwts.builder()
									 .subject(username)
									 .issuedAt(new Date())
									 .expiration(expiryDate)
									 .signWith(key())
									 .compact();
		
		return token;
	}
	
	//Use decode logic to decode the encrypted key value specified in properties file
	private Key key()
	{
		return Keys.hmacShaKeyFor(Decoders.BASE64.decode(jwtSecret));
	}
	
	
	//Get username from JWT token
	public String getUsername(String token)
	{
		return	Jwts.parser()
						.verifyWith((SecretKey) key())
						.build()
						.parseSignedClaims(token)
						.getPayload()
						.getSubject();
	}

	//Validate with JWT Token
	public boolean validateToken(String token)
	{
			try 
			{
				Jwts.parser().verifyWith((SecretKey) key()).build().parse(token);
				return true;
			}
			catch(MalformedJwtException malformedJwtException)
			{
				throw new BlogApiException("Invalid JWT Token");
			}
			catch(ExpiredJwtException expiredJwtException)
			{
				throw new BlogApiException("Expired JWT Token");
			}
			catch(UnsupportedJwtException unsupportedJwtException)
			{
				throw new BlogApiException("Unsupported JWT Token");
			}
			catch(IllegalArgumentException illegalArgumentException)
			{
				throw new BlogApiException("JWT Claim string should not be NULL or EMPTY");
			}
	}
	
	
	
	
}
