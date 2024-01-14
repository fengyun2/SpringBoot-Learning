package com.win.springboot.aop.aspect;

import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Spring Boot AOP 适用于许多不同的使用场景，包括但不限于：
 * - 日志记录：记录方法的输入参数、输出结果或执行时间，用于跟踪应用程序的行为和排查问题。
 * - 事务管理：自动管理事务的启动、提交或回滚，以确保数据的一致性。
 * - 权限控制：根据用户的权限对方法进行访问控制，确保只有授权用户能够执行特定操作。
 * - 性能监控：测量方法的执行时间，以便识别性能瓶颈和优化代码。
 * - 异常处理：在方法抛出异常时执行特定的处理逻辑，如记录错误信息或通知管理员。
 *
 * 在这里创建一个 AOP 切面类，用于记录方法的执行时间。这个切面将包含一个通知方法，在方法调用之前和之后记录方法的执行时间。
 */
@Aspect
@Component
public class MethodExecutionTimeAspect {
  private static final Logger logger = LoggerFactory.getLogger(MethodExecutionTimeAspect.class);
  private long startTime;

  @Pointcut("execution(* com.win.springboot.aop.service.*.*(..))")
  private void pointcut() {}

  @Before("pointcut()")
  public void beforeServiceMethodExecution() {
    startTime = System.currentTimeMillis();
  }

  @After("pointcut()")
  public void afterServiceMethodExecution() {
    long executionTime = System.currentTimeMillis() - startTime;
    logger.info("Service method execution time: {} ms", executionTime);
  }
}
