package com.revature.board.aspect;

import org.apache.log4j.Logger;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Aspect
@Component("aspect")
public class AspectLogging {

	private Logger log = Logger.getLogger(getClass());

	
	/**
	 * 
	 * @param point
	 */
	@Before("execution(* com.revature.board.controllers.BoardCtrl.getBoards*(..))")
    public void log(JoinPoint point) {
        log.info("Validating User Method");
    }
}