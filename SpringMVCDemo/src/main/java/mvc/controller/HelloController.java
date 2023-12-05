package mvc.controller;

import java.util.Arrays;
import java.util.IntSummaryStatistics;
import java.util.List;
import java.util.stream.Collectors;

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
	
	//練習4
	//路徑: /age=17&age=21&age=20
	//網址： http://localhost:8080/SpringMVCDemo/mvc/hello/age?age=17&age=21&age=20
	@GetMapping(value="/age", produces = "text/plain;charset=utf-8")
	@ResponseBody
	public String getAverageAge(@RequestParam("age") List<Integer> ages) {
		double avg = ages.stream().mapToInt(Integer::intValue).average().getAsDouble();
		return String.format("平均年齡 = %.1f", avg);
		}
	
	/*
	 * 5. Lab 練習: 得到多筆 score 資料
	 * 路徑: "/exam?score=80&score=100&score=50&score=70&score=30"
	 * 全網址: http://localhost:8080/SpringMVC/mvc/hello/exam?score=80&score=100&score=50&score=70&score=30
	 * 請自行設計一個方法，此方法可以
	 * 印出: 最高分=?、最低分=?、平均=?、總分=? 
	 *      及格分數=?、不及格=?
	 * (支援中文字印出)     
	 * */
	@GetMapping(value="/exam", produces = "text/plain;charset=utf-8")
	@ResponseBody
	public String getExamInfo(@RequestParam("score") List<Integer> scores) {
		IntSummaryStatistics stat = scores.stream().mapToInt(Integer::intValue).summaryStatistics();
		List<Integer> passList = scores.stream().filter(score -> score >=60).collect(Collectors.toList());
		List<Integer> failList = scores.stream().filter(score -> score <60).collect(Collectors.toList());
		
		return String.format("最高分=%d、最低分=%d、平均=%.1f、總分=%d、及格分數=%s、不及格=%s", 
										stat.getMax(), stat.getMin(), stat.getAverage(), stat.getSum(), passList, failList);
		}
	
	 //試著把List<scores>換成int[]
	@GetMapping(value="/exam1", produces = "text/plain;charset=utf-8")
	@ResponseBody
	public String getExamInfo1(@RequestParam("score") int[] scores) {
		IntSummaryStatistics stat = Arrays.stream(scores).summaryStatistics();
		List<Integer> passList = Arrays.stream(scores).filter(score -> score >= 60).boxed().collect(Collectors.toList());
		List<Integer> failList = Arrays.stream(scores).filter(score -> score < 60).boxed().collect(Collectors.toList());
		
		return String.format("最高分=%d、最低分=%d、平均=%.1f、總分=%d、及格分數=%s、不及格=%s", 
										stat.getMax(), stat.getMin(), stat.getAverage(), stat.getSum(), passList, failList);}

}
	
	
	

