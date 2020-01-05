package cn.liu.aspect;


import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class RedisCacheAop {

    @Autowired
    private StringRedisTemplate stringRedisTemplate ;


    @Autowired
    private RedisTemplate redisTemplate ;
    @Around("@annotation(cn.liu.annotation.AddCache)")
    public Object arround(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        System.out.println("切点");
        //设置key的序列化格式

        StringRedisSerializer stringRedisSerializer = new StringRedisSerializer();
        redisTemplate.setKeySerializer(stringRedisSerializer);
        //判断是否有缓存
        HashOperations hashOperations = redisTemplate.opsForHash();
        //namespace
        String name = proceedingJoinPoint.getTarget().getClass().getName();
        //方法名
        String name1 = proceedingJoinPoint.getSignature().getName();
        //参数
        Object[] args = proceedingJoinPoint.getArgs();
        StringBuilder stringBuilder = new StringBuilder(name1);
        for (Object arg : args) {
            stringBuilder.append(arg.toString());
        }
        System.out.println(stringBuilder);

        if(hashOperations.hasKey(name,stringBuilder)){
            Object o = hashOperations.get(name, stringBuilder);
            return   o ;
        }

        Object proceed = proceedingJoinPoint.proceed();
        hashOperations.put(name , stringBuilder , proceed);
        return  proceed ;
    }

    @After("@annotation(cn.liu.annotation.ClearCache)")
    public void after(JoinPoint joinPoint){
        String name = joinPoint.getTarget().getClass().getName();
        stringRedisTemplate.delete(name);
    }
}
