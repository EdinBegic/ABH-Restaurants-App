package atlantbh.restaurants;

import org.resthub.web.springmvc.router.RouterConfigurationSupport;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Configuration
@ComponentScan(basePackages = "atlantbh.restaurants.controllers")
public class WebAppConfig extends RouterConfigurationSupport {
    @Override
    public List<String> listRouteFiles() {
        return Arrays.asList("classpath:routes.conf");
    }
}
