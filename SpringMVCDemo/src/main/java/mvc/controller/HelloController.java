package mvc.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
/*
 * Base url = http://localhost:8080/SpringMVC/mvc
 * Controller url = Base url + "/hello"  
 *                = http://localhost:8080/SpringMVC/mvc/hello
 * */
@Controller
@RequestMapping("/hello")
public class HelloController {
	
	/**
	 * 1. 取得 Welcome SpringMVC 字串
	 * 路徑: "/welcome"
	 * 全網址: http://localhost:8080/SpringMVC/mvc/hello/welcome
	 * */
	@GetMapping("/welcome")
	@ResponseBody
	public String welcome() {
		return "Welcome SpringMVC";
	}
	
	/**
	 * 2. ? 帶參數
	 * 路徑: "/hi?name=John&age=18"
	 * 路徑: "/hi?name=Mary&age=20"
	 * 路徑: "/hi?name=Helen"
	 * 全網址: http://localhost:8080/SpringMVC/mvc/hello/hi?name=John&age=18
	 * 全網址: http://localhost:8080/SpringMVC/mvc/hello/hi?name=Mary&age=20
	 * 全網址: http://localhost:8080/SpringMVC/mvc/hello/hi?name=Helen
	 * 限制: name 參數是一定要有的(預設)
	 *      age 參數是不一定要有的, 初始值 = 0
	 * */
	@GetMapping("/hi")
	@ResponseBody
	public String hi(@RequestParam(value = "name", required = true) String name,
					 		@RequestParam(value = "age", required = false, defaultValue = "0") Integer age) {
		
		return String.format("Hi %s, %d", name, age);
	}
	
	//練習3
	//網址： http://localhost:8080/SpringMVCDemo/mvc/hello/bmi?h=170.0&w=60.0
	@GetMapping("/bmi")
	@ResponseBody
	public String bmi(@RequestParam(value = "h", required = true, defaultValue = "0.0") Double h,
					 		   @RequestParam(value = "w", required = true, defaultValue = "0.0") Double w) {
		Double BMI = w / Math.pow(h/100, 2);
		return String.format("h = %.1f, w= %.1f, bmi=%.2f", h, w, BMI);
	}
	
}
