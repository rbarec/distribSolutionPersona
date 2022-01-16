package persona.api;

import java.util.Random;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import persona.pool.JedisUtil;
import persona.util.RandomNumbers;

@CrossOrigin("*")
@RestController
@RequestMapping("/user")
public class PersonaPool {

	@Autowired
	private JedisUtil jedisUtil;

	// http://localhost:8087/wiener/user/testRedisSave?id=1090330
	@RequestMapping("/testRedisSave")
	public ResponseEntity<Object> testRedisSave(Long id) {
		RandomNumbers prng = new RandomNumbers();
		long l = prng.range(1, 2001);
		Long l2 = Long.valueOf(l);
		jedisUtil.del("userId:" + l2);
		jedisUtil.set("userId:" + l2, "The test address is " + UUID.randomUUID().toString());
		return    new ResponseEntity<>(l2,  HttpStatus.OK);  
	}

	// http://localhost:8087/wiener/user/testRedisGet?id=1090330
	@RequestMapping("/testRedisGet")
	    public String testRedisGet(Long id) {
	        String myStr= jedisUtil.get("userId:"+ id);
	        if(!StringUtils.isEmpty(myStr)) {
	            return myStr;
	        }
	        return null;
	    }
	
	private int getRandom(int a, int b) {
		Random r = new Random();
		int lowerBound = 1;
		int upperBound = 11;
		int result = r.nextInt(upperBound - lowerBound) + lowerBound;
		return result;
	}
	
}
