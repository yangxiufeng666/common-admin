package com.common.system.config;

import com.jagregory.shiro.freemarker.ShiroTags;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.autoconfigure.condition.ConditionalOnWebApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.view.freemarker.FreeMarkerConfig;
import org.springframework.web.servlet.view.freemarker.FreeMarkerConfigurer;
import org.springframework.web.servlet.view.freemarker.FreeMarkerView;
import org.springframework.web.servlet.view.freemarker.FreeMarkerViewResolver;

import javax.servlet.Servlet;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

/**
 * Created by Mr.Yangxiufeng on 2017/6/15.
 * Time:16:08
 * ProjectName:Common-admin
 */
@Configuration
public class FreemarkerCustomConfig {
    @Autowired
    private FreeMarkerConfigurer freeMarkerConfigurer;
    @Bean
    public freemarker.template.Configuration getFreemarkerConfiguration(){
        freemarker.template.Configuration configuration = freeMarkerConfigurer.getConfiguration();
        configuration.setSharedVariable("shiro",new ShiroTags());
        return configuration;
    }
}
