package com.jingle.config.mvc;

import com.alibaba.fastjson.serializer.SerializerFeature;
import com.alibaba.fastjson.support.config.FastJsonConfig;
import com.alibaba.fastjson.support.spring.FastJsonHttpMessageConverter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.util.ResourceUtils;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.io.File;
import java.util.List;
/**
 * MVC配置类
 *
 * @Author chair
 * @Version 1.0, 2017年8月22日
 * @See
 * @Since com.jk.bestbaby.config
 * @Description: TODO
 */
@Configuration
public class MvcConfiguration implements WebMvcConfigurer {

	/**
	 * 上传文件根路径
	 */
	@Value("${files.path}")
	private String filesPath;

	// /** * 文件上传配置 * @return */
	// @Bean
	// public MultipartConfigElement multipartConfigElement() {
	// MultipartConfigFactory factory = new MultipartConfigFactory();
	// // 文件最大 factory.setMaxFileSize("10240KB");
	// // KB,MB
	// // 设置总上传数据总大小
	// factory.setMaxRequestSize("102400KB");
	// return factory.createMultipartConfig();
	// }

	/**
	 * 跨域支持
	 *
	 * @param registry
	 */
	@Override
	public void addCorsMappings(CorsRegistry registry) {
		registry.addMapping("/**").allowedOrigins("*").allowCredentials(true).allowedMethods("GET", "POST", "DELETE", "PUT").maxAge(3600 * 24);
	}

	/**
	 * 外部文件访问
	 */
	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler("/statics/**").addResourceLocations(ResourceUtils.FILE_URL_PREFIX + filesPath + File.separator);
	}

	/**
	 * 添加拦截器
	 */
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		// registry.addInterceptor(tokenInterceptor).addPathPatterns("/**").excludePathPatterns("/login");
		WebMvcConfigurer.super.addInterceptors(registry);
	}

	@Override
	public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
		// 1.创建一个convert消息转换对象
		FastJsonHttpMessageConverter fastConvert = new FastJsonHttpMessageConverter();
		// 2.创建一个fastJson的配置对象,然后配置格式化信息
		FastJsonConfig config = new FastJsonConfig();
		config.setSerializerFeatures(SerializerFeature.PrettyFormat);
		// 3.在convert中添加配置信息
		fastConvert.setFastJsonConfig(config);
		// 4.将convert添加到converts里面
		// SpringBoot 2.0.1版本中加载WebMvcConfigurer的顺序发生了变动，故需使用converters.add(0,
		// converter);指定FastJsonHttpMessageConverter在converters内的顺序，否则在SpringBoot
		// 2.0.1及之后的版本中将优先使用Jackson处理。
		converters.add(0, fastConvert);
	}

}
