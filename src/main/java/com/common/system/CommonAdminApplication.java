package com.common.system;

import com.common.system.config.druid.DruidProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.boot.web.support.SpringBootServletInitializer;

@SpringBootApplication
@EnableConfigurationProperties(value = {DruidProperties.class})
public class CommonAdminApplication{

	public static void main(String[] args) {
		SpringApplication.run(CommonAdminApplication.class, args);
	}
}
