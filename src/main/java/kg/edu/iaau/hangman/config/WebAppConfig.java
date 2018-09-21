package kg.edu.iaau.hangman.config;

import kg.edu.iaau.hangman.other.MasterPreparer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.view.UrlBasedViewResolver;
import org.springframework.web.servlet.view.tiles3.SpringBeanPreparerFactory;
import org.springframework.web.servlet.view.tiles3.TilesConfigurer;
import org.springframework.web.servlet.view.tiles3.TilesView;

@Configuration
public class WebAppConfig implements WebMvcConfigurer
{
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry)
    {
        registry.addResourceHandler(new String[]{"/assets/**"})
                .addResourceLocations(new String[]{"/assets/"});
    }

    @Bean
    public MasterPreparer masterPreparer() {
        return new MasterPreparer();
    }

    @Bean
    public UrlBasedViewResolver viewResolver()
    {
        UrlBasedViewResolver tilesViewResolver = new UrlBasedViewResolver();
        tilesViewResolver.setViewClass(TilesView.class);
        return tilesViewResolver;
    }

    @Bean
    public TilesConfigurer tilesConfigurer()
    {
        TilesConfigurer tc = new TilesConfigurer();
        tc.setDefinitions(new String[]{"/WEB-INF/tiles.xml"});
        tc.setCheckRefresh(true);
        tc.setPreparerFactoryClass(SpringBeanPreparerFactory.class);
        return tc;
    }
}
