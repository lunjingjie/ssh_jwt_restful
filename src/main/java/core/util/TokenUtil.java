package core.util;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;

import java.util.*;

public class TokenUtil {

	private static final String SECRET_KEY = "*(-=4eklfasdfarerf41585fdasf";
	
	public static String createToken(String tokenType,String userName) throws Exception {
    	Date iatDate = new Date();
    	Calendar nowTime = Calendar.getInstance();
    	if(("app").equals(tokenType)){
    		nowTime.add(Calendar.MINUTE, 60 * 24 * 30);
    	}else{
    		nowTime.add(Calendar.MINUTE, 60);
    	}
    	Date expiresDate = nowTime.getTime();
    	
    	String uuid = UUID.randomUUID().toString();
    	Map<String, Object> map = new HashMap<String, Object>();
    	map.put("alg", "HS256");
    	map.put("typ", "JWT");
    	String token = JWT.create()
    			.withHeader(map)
    			.withClaim("id", uuid)
    			.withClaim("tokenType", tokenType)
    			.withClaim("clientKey", userName)
    			.withExpiresAt(expiresDate)
    			.withIssuedAt(iatDate)
    			.sign(Algorithm.HMAC256(TokenUtil.SECRET_KEY));
    	
    	Map<String,Object> tokenMap = CachFactory.getInstance().createCache("tokenMap");
        tokenMap.put(uuid, token);
        // TODO 需要新增token过期清除任务
    	return token;
    }
    
    public static Map<String, Claim> getClaims(String token) throws Exception {
    	JWTVerifier verifier = JWT.require(Algorithm.HMAC256(SECRET_KEY)).build();
    	DecodedJWT jwt = verifier.verify(token);
    	return jwt.getClaims();
    }
	
    public static boolean verifyIsContains(String token) {
		try {
	    	Map<String, Claim> map = TokenUtil.getClaims(token);
	    	String tokenId = map.get("id").asString();
	    	if(CachFactory.getInstance().getMapByKey("tokenMap").get(tokenId) == null){
	    		return false;
	    	}
		} catch (Exception e) {
			return false;
		}
    	return true;
    }
    
    /**
     * 判断是否过了有效期
     * @param token
     * @return
     */
    public static boolean verifyIsExpire(String token) {
		try {
			JWTVerifier verifier = JWT.require(Algorithm.HMAC256(SECRET_KEY)).build();
	    	DecodedJWT jwt = verifier.verify(token);
	    	Date expiresDate = jwt.getExpiresAt();
	    	Date now = new Date();
	    	return now.after(expiresDate);
		} catch (Exception e) {
			return false;
		}
    }
}
