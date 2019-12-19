package BookStore.init;


import BookStore.servlet.ServletConfig;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

//使用注解代替原来的web.xml文件
public class WebInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {
	@Override
	protected Class<?>[] getRootConfigClasses() {
		return new Class[0];
	}

	@Override
	protected Class<?>[] getServletConfigClasses() {
		return new Class[]{ServletConfig.class};
	}

	//截获所有请求交给 BookStoreWebConfig.class 处理
	@Override
	protected String[] getServletMappings() {
		return new String[] {"/"};
	}
}











