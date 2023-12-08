package mvc.aop;

import java.util.Arrays;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Component // 可被 Spring 管理的物件
@Aspect    // 告訴 Spring 此為切面程式
@Order(1)  // 執行順序(愈小愈先被執行)
public class MyBookingLoggerAspect {
	//切點方法
	@Pointcut(value = "execution(* mvc.controller.BookingController.bookingBookRoom(..))") 
	public void pt1() {}
	
	@Pointcut(value = "execution(* mvc.controller.BookingController.cancelBooking(..))") 
	public void pt2() {}
	
	@Before(value = "pt1() && pt2")
	public void beforeAdvice(JoinPoint joinPoint) {
		String methodName = joinPoint.getSignature().getName();
		Object[] args = joinPoint.getArgs();
		System.out.printf("methodName: %s args: %s%n", methodName, Arrays.toString(args));
	}

}
