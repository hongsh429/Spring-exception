package com.exception;

import com.exception.filter.LogFilter;
import com.exception.interceptor.LogInterceptor;
import com.exception.resolver.MyHandlerExceptionResolver;
import com.exception.resolver.UserHandlerExceptionResolver;
import jakarta.servlet.DispatcherType;
import jakarta.servlet.Filter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.List;

@Configuration
public class WebConfig implements WebMvcConfigurer {


//    @Bean
    public FilterRegistrationBean logFilter() {
        FilterRegistrationBean<Filter> filterFilterRegistrationBean = new FilterRegistrationBean<>();
        filterFilterRegistrationBean.setFilter(new LogFilter());
        filterFilterRegistrationBean.setOrder(1);
        filterFilterRegistrationBean.addUrlPatterns("/*");
        /* 이 필터는 두가지 dispatcherType인 경우에 호출된다!*/
        filterFilterRegistrationBean.setDispatcherTypes(DispatcherType.REQUEST, DispatcherType.ERROR); // default : DispatcherType.REQUEST
        return filterFilterRegistrationBean;
    }


    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new LogInterceptor())
                .order(1)
                .addPathPatterns("/**")                             /*interceptor의 경우,setDispatcherTypes 이런기능 없지만, 그냥 경로로 없애주면 된다! */
                .excludePathPatterns("/css/**", "/*.ico", "/error",  "/error-page/**"); //오류 페이지 경로  ,  "/error-page/**"
    }
    /**
     *  interceptor는 excludePathPatterns()으로 ERROR 요청 제거
     *  filter는 .setDispatcherTypes(DispatcherType.REQUEST, DispatcherType.ERROR); 로 설정
     *
     * */


    /*HandlerException 등록*/
    @Override
    public void extendHandlerExceptionResolvers(List<HandlerExceptionResolver> resolvers) {
        resolvers.add(new MyHandlerExceptionResolver());
        resolvers.add(new UserHandlerExceptionResolver());
    }
}
