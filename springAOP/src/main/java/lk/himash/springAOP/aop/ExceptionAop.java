package lk.himash.springAOP.aop;

import java.nio.file.AccessDeniedException;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class ExceptionAop {
	
	private static final Logger log = LoggerFactory.getLogger(ExceptionAop.class);
	
//	@Pointcut("within(@org.springframework.web.bind.annotation.RestController *) && @annotation(exceptionCustomAop) && execution(* *(..))")
	@Pointcut("within(@org.springframework.web.bind.annotation.RestController *) && @annotation(lk.himash.springAOP.aop.ExceptionCustomAop) && execution(* *(..))")
	public void pointCut() {}
	

	@Around("pointCut()")
	public Object around(ProceedingJoinPoint proJoiPoint) {
		log.info("Start | advice() method | ExceptionAop.class |");
		try {
			ResponseEntity<?> res = (ResponseEntity<?>) proJoiPoint.proceed();
			ResponseEntity<?> resp = null;
			if(res instanceof ResponseEntity<?>) {
				//If you have reponseDto class then set the base class values and return
				resp = new ResponseEntity<>(res, HttpStatus.OK);
			}
			return resp;
		}catch(Throwable e) {
			ResponseEntity<?> resp = null;
			// If you have multiple exceptions checking then check and set the response and return 
			if(e instanceof AccessDeniedException) {
				resp = new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
			} else {
				resp = new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
			}
			return resp;
		}
	}

}
