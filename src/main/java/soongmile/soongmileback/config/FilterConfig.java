package soongmile.soongmileback.config;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import soongmile.soongmileback.filter.MyFilter1;
import soongmile.soongmileback.filter.MyFilter2;
import soongmile.soongmileback.filter.MyFilter3;

@Configuration
public class FilterConfig {

    @Bean
    public FilterRegistrationBean<MyFilter1> filter1() {
        FilterRegistrationBean<MyFilter1> bean = new FilterRegistrationBean<>(new MyFilter1());
        bean.addUrlPatterns("/token");	// 해당 url에 맞는 요청에 Filter가 적용된다.
        bean.setOrder(0);			// 숫자가 작은 순서대로 적용된다.
        return bean;
    }

    @Bean
    public FilterRegistrationBean<MyFilter2> filter2() {
        FilterRegistrationBean<MyFilter2> bean = new FilterRegistrationBean<>(new MyFilter2());
        bean.addUrlPatterns("/token");
        bean.setOrder(1);
        return bean;
    }

    @Bean
    public FilterRegistrationBean<MyFilter3> filter3() {
        FilterRegistrationBean<MyFilter3> bean = new FilterRegistrationBean<>(new MyFilter3());
        bean.addUrlPatterns("/token");
        bean.setOrder(2);
        return bean;
    }
}
