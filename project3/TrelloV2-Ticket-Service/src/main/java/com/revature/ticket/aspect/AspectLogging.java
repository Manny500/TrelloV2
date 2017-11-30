package com.revature.ticket.aspect;

import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Aspect
@Component("aspect")
public class AspectLogging {

	//private Logger log = Logger.getLogger(getClass());

//	@Before("execution(* com.revature.board.controllers.BoardCtrl.getBoards*(..))")
//    public void log(JoinPoint point) {
//        log.info("Validating User Method");
//    }
}