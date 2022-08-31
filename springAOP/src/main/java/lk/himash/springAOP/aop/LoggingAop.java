package lk.himash.springAOP.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LoggingAop {
	
	private static final Logger log = LoggerFactory.getLogger(LoggingAop.class);
	
     //                  * = any return type               * = className, * = MethodName, (..) = args
//  @Pointcut("execution(* lk.himash.springAOP.controller.*.*(..))") // All Controller package
//	@Pointcut("within(lk.himash.springAOP.service.serviceImpl.*)") // All ServiveImpl only
//	@Pointcut("this(lk.himash.springAOP.service.serviceImpl.LaptopServiceImpl)") // only one class
	@Pointcut("@annotation(lk.himash.springAOP.aop.LoggingCustomAop)") // custom pointCut
	public void loggingPointCut(){}

    @Before("loggingPointCut()")
    public void before(JoinPoint joinPoint) {
    	log.info("===================AOP BEFORE INIT======================");
    	log.info("Signature : " + joinPoint.getSignature());
    	log.info("Kind : " + joinPoint.getKind());
    	log.info("SourceLocation : " + joinPoint.getSourceLocation());
    	log.info("StaticPart : " + joinPoint.getStaticPart());
    	log.info("===================AOP BEFORE INIT======================");
    }

    @After("loggingPointCut()")
    public void after(JoinPoint joinPoint) {
    	log.info("===================AOP AFTER INIT======================");
    	log.info("Signature : " + joinPoint.getSignature());
    	log.info("Kind : " + joinPoint.getKind());
    	log.info("SourceLocation : " + joinPoint.getSourceLocation());
    	log.info("StaticPart : " + joinPoint.getStaticPart());
    	log.info("===================AOP AFTER INIT======================");
    }
    
    @AfterReturning(value = "execution(* lk.himash.springAOP.controller.*.*(..))", returning = "resp")
    public void afterReturning(JoinPoint joinPoint, ResponseEntity<?> resp) {
    	log.info("===================AOP AFTER RETURNING INIT======================");
    	log.info("Signature : " + joinPoint.getSignature());
    	log.info("Kind : " + joinPoint.getKind());
    	log.info("SourceLocation : " + joinPoint.getSourceLocation());
    	log.info("StaticPart : " + joinPoint.getStaticPart());
    	log.info("Response : " + resp);
    	log.info("===================AOP AFTER RETURNING INIT======================");
    }
    
    @AfterThrowing(value = "execution(* lk.himash.springAOP.controller.*.*(..))", throwing = "e")
    public void afterThrowing(JoinPoint joinPoint, Exception e) {
    	log.info("===================AOP AFTER THROWING INIT======================");
        log.info("Signature : " + joinPoint.getSignature());
        log.info("Kind : " + joinPoint.getKind());
        log.info("SourceLocation : " + joinPoint.getSourceLocation());
        log.info("StaticPart : " + joinPoint.getStaticPart());
        log.info("Exception : " + e.getMessage());
        log.info("===================AOP AFTER THROWING INIT======================");
    }
    
  @Around("loggingPointCut()") // Can use @Before and @After Advice instead
  public Object around(ProceedingJoinPoint joinPoint) throws Throwable {
      System.out.println("===================AOP AROUND INIT======================");
      
      Object obj = joinPoint.proceed();
      
      if(obj instanceof ResponseEntity<?>) {
    	  log.info("Signature 1 : " + joinPoint.getSignature());
          log.info("Kind 1 : " + joinPoint.getKind());
          log.info("SourceLocation 1 : " + joinPoint.getSourceLocation());
      } else {
    	  log.info("Signature 2 : " + joinPoint.getSignature());
          log.info("Kind 2 : " + joinPoint.getKind());
          log.info("SourceLocation 2 : " + joinPoint.getSourceLocation());
          log.info("StaticPart 2 : " + joinPoint.getStaticPart());
      }
      System.out.println("===================AOP AROUND INIT======================");
      return obj;
  }

}
