package com.win.springbootbasic;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;

import jakarta.annotation.Resource;

@SpringBootTest
class SpringbootBasicApplicationTests {

  @Resource
  private RedisTemplate redisTemplate;

  @Test
  void testString() {
    ValueOperations ops = redisTemplate.opsForValue();
    ops.set("name", "win");
    String name = (String) ops.get("name");
    System.out.println("name = " + name);
  }

	@Test
	void contextLoads() {
	}

}
