package session01.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
//@ComponentScan(value = "session01.bean")  //設定掃描單個資料夾
@ComponentScan(basePackages = "session01.bean") //設定掃描指定資料夾及包含在其中的子資料夾
public class JavaSpringConfig2 {
	
}
