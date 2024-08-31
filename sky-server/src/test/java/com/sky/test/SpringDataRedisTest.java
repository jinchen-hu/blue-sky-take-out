package com.sky.test;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.*;

import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

@SpringBootTest
@Slf4j
public class SpringDataRedisTest {
    @Autowired
    private StringRedisTemplate stringRedisTemplate;

//    @Test
//    public void testRedisTemplate() {
//        log.info("start testing redis template:{}", redisTemplate);
//
//        ValueOperations<?,?> valueOperations = redisTemplate.opsForValue();
//        HashOperations<?, ?, ?> hashOperations = redisTemplate.opsForHash();
//        ListOperations<?,?>listOperations = redisTemplate.opsForList();
//        SetOperations<?,?> setOperations = redisTemplate.opsForSet();
//        ZSetOperations<?,?> zSetOperations = redisTemplate.opsForZSet();
//    }

    @Test
    public void testStringRedisTemplate() {
        log.info("start testing string template:{}", stringRedisTemplate);
        ValueOperations<String, String> valueOperations = stringRedisTemplate.opsForValue();

        valueOperations.set("name", "montreal");
        String city = valueOperations.get("name");
        Assertions.assertEquals("montreal", city);
        valueOperations.getAndDelete("name");

        valueOperations.set("code","1234",3, TimeUnit.MINUTES);
        valueOperations.getAndDelete("code");

        boolean flag = Boolean.TRUE.equals(valueOperations.setIfAbsent("lock", "1"));
        Assertions.assertTrue(flag);

        flag = Boolean.TRUE.equals(valueOperations.setIfAbsent("lock", "2"));
        Assertions.assertFalse(flag);
        valueOperations.getAndDelete("lock");
    }

    @Test
    public void testHash(){
        //hset hget hdel hkeys hvals
        HashOperations<String, Object, Object> hashOperations = stringRedisTemplate.opsForHash();

        hashOperations.put("100","name","tom");
        hashOperations.put("100","age","20");

        String name = (String) hashOperations.get("100", "name");
        Assertions.assertEquals("tom",name);

        Set<Object> keys = hashOperations.keys("100");
        Assertions.assertEquals(2, keys.size());
        Assertions.assertTrue(keys.contains("name"));
        Assertions.assertTrue(keys.contains("age"));

        List<Object> values = hashOperations.values("100");
        Assertions.assertEquals(2, values.size());
        Assertions.assertTrue(values.contains("tom"));
        Assertions.assertTrue(values.contains("20"));

        hashOperations.delete("100","age");
        hashOperations.delete("100","name");

        stringRedisTemplate.delete("100");
    }

    @Test
    public void testList(){
        ListOperations<String, String> listOperations = stringRedisTemplate.opsForList();
        listOperations.leftPushAll("mylist","a","b","c");
        listOperations.leftPush("mylist","d");

        List<String> mylist = listOperations.range("mylist", 0, -1);
        assert mylist != null;
        Assertions.assertArrayEquals(new String[]{"d", "c", "b", "a"}, mylist.toArray());

        listOperations.rightPop("mylist");
        mylist = listOperations.range("mylist", 0, -1);
        assert mylist != null;
        Assertions.assertArrayEquals(new String[]{"d", "c", "b"}, mylist.toArray());

        stringRedisTemplate.delete("mylist");
    }

    @Test
    public void testSet(){
        //sadd smembers scard sinter sunion srem
        SetOperations<String, String> setOperations = stringRedisTemplate.opsForSet();

        setOperations.add("set1","a","b","c","d");
        setOperations.add("set2","a","b","x","y");

        Set<String> members = setOperations.members("set1");
        System.out.println(members);

        Long size = setOperations.size("set1");
        System.out.println(size);

        Set<String> intersect = setOperations.intersect("set1", "set2");
        System.out.println(intersect);

        Set<String> union = setOperations.union("set1", "set2");
        System.out.println(union);

        setOperations.remove("set1","a","b");
        stringRedisTemplate.delete("set1");
        stringRedisTemplate.delete("set2");
    }

    @Test
    public void testZset(){
        //zadd zrange zincrby zrem
        ZSetOperations<String, String> zSetOperations = stringRedisTemplate.opsForZSet();

        zSetOperations.add("zset1","a",10);
        zSetOperations.add("zset1","b",12);
        zSetOperations.add("zset1","c",9);

        Set<String> zset1 = zSetOperations.range("zset1", 0, -1);
        System.out.println(zset1);

        zSetOperations.incrementScore("zset1","c",10);
        System.out.println(zset1);

        zSetOperations.remove("zset1","a","b");
        System.out.println(zset1);

        stringRedisTemplate.delete("zset1");
    }
}
