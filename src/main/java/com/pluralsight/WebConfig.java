package com.pluralsight;

import java.util.Locale;

import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

//These annotations essentially act as applicationContext, web.xml and servlet-config.xml
@Configuration
@EnableWebMvc
@ComponentScan(basePackages = "com.pluralsight")
public class WebConfig extends WebMvcConfigurerAdapter //extending WebMvcConfigurerAdapter allows us to use addResourceHandlers method and others
// for localization etc.
{
    //This Bean is being autowired by name i.e. it must be named messageSource for Spring to recognise it properly
    @Bean
    public MessageSource messageSource()
    {
        ResourceBundleMessageSource messageSource = new ResourceBundleMessageSource();

        messageSource.setBasename("messages");

        return messageSource;
    }

    //This Bean is also being autowired by name
    @Bean
    public LocaleResolver localeResolver()
    {
        SessionLocaleResolver resolver = new SessionLocaleResolver();

        resolver.setDefaultLocale(Locale.ENGLISH);

        return resolver;
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry)
    {
        LocaleChangeInterceptor changeInterceptor = new LocaleChangeInterceptor();

        changeInterceptor.setParamName("language");

        registry.addInterceptor(changeInterceptor);
    }

    //Spring knows what to do with this Bean by the Type (i.e. Autowired by Type)
    @Bean
    public InternalResourceViewResolver getInternalResourceViewResolver()
    {
        InternalResourceViewResolver resolver = new InternalResourceViewResolver();

        resolver.setPrefix("/WEB-INF/jsp/"); //case sensitive and needs forward slash at end
        resolver.setSuffix(".jsp");

        return resolver;
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry)
    {
        //the consecutive method calls below is called Chaining
        //the /pdfs/ part is for the URL, the /WEB-INF/pdf is internal folder structure and can't be accessed through the URL
        registry.addResourceHandler("/pdfs/**").addResourceLocations("/WEB-INF/pdf/"); //don't have to be named pdfs, just depends on folder names
        registry.addResourceHandler("/css/**").addResourceLocations("/WEB-INF/css/"); //just to serve as another example
    }
}
