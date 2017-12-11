package com.revature.board.aspect;

import org.apache.log4j.Logger;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Aspect
@Component("aspect")
public class AspectLogging {
	
	//Log4j
	private Logger log = Logger.getLogger(getClass());

	@Before("execution(* com.revature.board.controllers.BoardCtrl.switchLane*(..))")
    public void log(JoinPoint point) {
        log.info("Switching lanes");
    }
}