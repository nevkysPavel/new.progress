package all.configuration;

import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

//@FieldDefaults(level = PRIVATE)
public class WebAppInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {
    @Override
    protected Class<?>[] getRootConfigClasses() {
        return null;
    }

    @Override
    protected Class<?>[] getServletConfigClasses() {
        return new Class[]{JavaConfiguration.class};
    }

    @Override
    protected String[] getServletMappings() {
        return new String[]{"/food/*"};
    }
}
