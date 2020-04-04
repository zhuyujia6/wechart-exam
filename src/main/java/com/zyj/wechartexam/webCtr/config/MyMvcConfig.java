package com.zyj.wechartexam.webCtr.config;


import com.zyj.wechartexam.webCtr.component.LoginHandlerInterceptor;
import com.zyj.wechartexam.webCtr.component.MyLocaleResolver;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;


//使用WebMvcConfigurerAdapter可以来扩展SpringMVC的功能
//@EnableWebMvc   不要接管SpringMVC
@Configuration
public class MyMvcConfig implements WebMvcConfigurer {

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        // super.addViewControllers(registry);
        //浏览器发送 /atguigu 请求来到 success
        registry.addViewController("/").setViewName("login");
        registry.addViewController("/index.html").setViewName("login");
        registry.addViewController("/main.html").setViewName("calendar");
        registry.addViewController("/qrCode").setViewName("rights/qrCode");
        registry.addViewController("/excelUpload").setViewName("rights/excelUpload");
        registry.addViewController("/excelDownload").setViewName("rights/excelDownload");
        registry.addViewController("/userManage").setViewName("rights/allUsers");

    }

    //注册拦截器
   @Override
    public void addInterceptors(InterceptorRegistry registry) {
       //静态资源（已经做好映射）
        registry.addInterceptor(new LoginHandlerInterceptor()).addPathPatterns("/**")
        .excludePathPatterns("/index.html","/","/user/login","/wx","/modifyUserInfo","/user/addUser");
    }

    /**
     * 添加静态资源文件，外部可以直接访问地址
     * @param registry
     */
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/static/**").addResourceLocations("classpath:/static/**");
    }

    @Bean
    public LocaleResolver localeResolver(){
        return new MyLocaleResolver();
    }

   /* //所有的WebMvcConfigurerAdapter组件都会一起起作用
    @Bean //将组件注册在容器
    public WebMvcConfigurationSupport  WebMvcConfigurationSupport (){
        WebMvcConfigurationSupport   adapter = new WebMvcConfigurationSupport  () {
            @Override
            public void addViewControllers(ViewControllerRegistry registry) {
                registry.addViewController("/").setViewName("login");
                registry.addViewController("/index.html").setViewName("login");
            }
        };
        return adapter;
    }*/
}
