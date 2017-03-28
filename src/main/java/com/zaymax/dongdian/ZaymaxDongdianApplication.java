package com.zaymax.dongdian;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.zaymax.dongdian.config.freemarker.HighlightMethod;
import org.apache.tomcat.jdbc.pool.DataSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.web.servlet.view.freemarker.FreeMarkerConfigurer;

import javax.annotation.PostConstruct;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

@EnableGlobalMethodSecurity(securedEnabled = true)
@EnableScheduling
@EnableAutoConfiguration()
@EnableJpaAuditing
@SpringBootApplication
public class ZaymaxDongdianApplication {
	public static final Logger LOGGER = LoggerFactory.getLogger(ZaymaxDongdianApplication.class);
	public static final String SPRING_TLD = "/META-INF/spring.tld";
	public static final String SECURITY_TLD = "/META-INF/security.tld";

	@Autowired
	private HighlightMethod highlightMethod;

	@Autowired
	private FreeMarkerConfigurer freeMarkerConfigurer;

	public static void main(String[] args) {
		SpringApplication.run(ZaymaxDongdianApplication.class, args);
	}

	@PostConstruct
	public void  initialize() {
		freeMarkerConfigurer.getTaglibFactory().setClasspathTlds(Lists.newArrayList(
				SPRING_TLD,
				SECURITY_TLD
		));

		//freemarker自定义方法
		//		高亮显示搜索字段
		freeMarkerConfigurer.getConfiguration().setSharedVariable("highlight", highlightMethod);
	}

}
