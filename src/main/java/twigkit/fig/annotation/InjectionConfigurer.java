package twigkit.fig.annotation;

import twigkit.fig.Config;
import twigkit.fig.Value;
import twigkit.fig.util.ReflectionUtils;

import java.lang.reflect.Field;

/**
 * @author mr.olafsson
 */
public class InjectionConfigurer {

    private Config config;

    public InjectionConfigurer(Config config) {
        this.config = config;
    }

    public InjectionConfigurer configure(Object target) {
        inject(target);
        return this;
    }

    private void inject(Object target) {
        Field[] fields = ReflectionUtils.getDeclaredAndInheritedFields(target.getClass(), false);

        for (Field field : fields) {
            if (field.isAnnotationPresent(Configure.class)) {
                Configure annotation = field.getAnnotation(Configure.class);

                field.setAccessible(true);
                try {
                    field.set(target, config);
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            } else if (field.isAnnotationPresent(Configure.Value.class)) {
                Configure.Value annotation = field.getAnnotation(Configure.Value.class);

                String name = annotation.name();
                if (name == null || name.equals("")) {
                    name = field.getName();
                }

                Value value = config.value(name);
                if (value != null) {
                    field.setAccessible(true);
                    try {
                        field.set(target, value.get());
                    } catch (IllegalAccessException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }
}
