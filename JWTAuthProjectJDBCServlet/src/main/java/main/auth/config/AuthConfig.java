package main.auth.config;

import java.security.Key;
import java.util.Date;

import javax.crypto.spec.SecretKeySpec;
import javax.xml.bind.DatatypeConverter;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

public class AuthConfig {
	static String secretString = "mySuperSecretKey123213ddfdfrefrafwfer13r3red3r3dqdqwr13r132132cbscqwuqwdhe12e2e@#$^&^%$@@*&()3213"; 
	 // Convert the string into a byte array and create a secret key from it
	static byte[] apiKeySecretBytes = DatatypeConverter.parseBase64Binary(secretString);
    static Key key = new SecretKeySpec(apiKeySecretBytes, SignatureAlgorithm.HS256.getJcaName());
	private static final long serialVersionUID = 1L;
	
	public static String generateToken(String username,int userId,String role)
    {
    	 

         // Create the JWT
         String jws = Jwts.builder()
        		 .setSubject(username) // Add claims, like the user identifier
        		 .claim("user_id",userId)
        		 .claim("role", role)
                 .setIssuer("your-app")  // Add the issuer of the token
                 .setIssuedAt(new Date()) // When the token is issued
                 .setExpiration(new Date(System.currentTimeMillis() + 3600000)) // Expiration time (1 hour)
                 .signWith(key) // Sign with the secret key
                 .compact(); // Serialize the token to a string

         // Output the token
		return jws; 
    }
	 public static boolean isValidToken(String token) {
	    	
		 try {
			    Claims body = Jwts.parserBuilder()
			                      .setSigningKey(key)
			                      .build()
			                      .parseClaimsJws(token)
			                      .getBody();

			    // Retrieve role and user_id from claims
			    String role = body.get("role", String.class);
			    Object userId = body.get("user_id");

			    // Print for debugging (or you can return or use these values as needed)
			    System.out.println("Role: " + role);
			    System.out.println("User ID: " + userId);

			    return true;
			} catch (Exception e) {
			    System.out.println("Error parsing token: " + e.getLocalizedMessage());
			    return false;
			}
			 
	    }
	 public static String getRoleFromToken(String token) {
	        Claims claims = Jwts.parserBuilder()
	                            .setSigningKey(key)
	                            .build()
	                            .parseClaimsJws(token)
	                            .getBody();
	        return claims.get("role", String.class); // Retrieve the role as a string
	    }
	
}