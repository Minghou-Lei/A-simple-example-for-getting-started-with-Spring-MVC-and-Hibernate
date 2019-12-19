package BookStore.servlet;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

//Servlet 配置类
@Configuration
@ComponentScan({"BookStore.controller"})
public class ServletConfig {
	@Bean
	public InternalResourceViewResolver viewResolver() {
		InternalResourceViewResolver vr = new InternalResourceViewResolver();
		vr.setPrefix("/WEB-INF/page/");
		vr.setSuffix(".jsp");
		return vr;
	}
}












