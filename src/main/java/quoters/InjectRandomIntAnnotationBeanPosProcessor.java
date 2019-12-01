package quoters;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.util.ReflectionUtils;

import java.util.Arrays;
import java.util.Random;

public class InjectRandomIntAnnotationBeanPosProcessor implements BeanPostProcessor {
    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        Arrays.stream(bean.getClass().getDeclaredFields())
                .filter(field -> field.isAnnotationPresent(InjectRandomInt.class))
                .findFirst()
                .ifPresent(field -> {
                    Class<?> type = field.getType();
                    if (type == int.class || type == Integer.class) {
                        InjectRandomInt fieldAnnotation = field.getAnnotation(InjectRandomInt.class);
                        Integer random = fieldAnnotation.min() + new Random().nextInt(fieldAnnotation.min() + fieldAnnotation.max());
                        field.setAccessible(true);
                        ReflectionUtils.setField(field, bean, random);
                    }

                });
        return bean;
    }
}
