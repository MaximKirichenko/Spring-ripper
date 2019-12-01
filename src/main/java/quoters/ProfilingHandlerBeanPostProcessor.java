package quoters;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;

import javax.management.*;
import java.lang.management.ManagementFactory;
import java.lang.reflect.Proxy;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class ProfilingHandlerBeanPostProcessor implements BeanPostProcessor {
    private Map<String, Class<?>> map = new HashMap<>();
    private ProfilingController profilingController = new ProfilingController(true);

    public ProfilingHandlerBeanPostProcessor() throws MalformedObjectNameException, NotCompliantMBeanException, InstanceAlreadyExistsException, MBeanRegistrationException {
        MBeanServer mBeanServer = ManagementFactory.getPlatformMBeanServer();
        mBeanServer.registerMBean(profilingController, new ObjectName("profiling", "name", "controller"));
    }

    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        Class<?> beanClass = bean.getClass();
        if (beanClass.isAnnotationPresent(Profiling.class)) {
            map.put(beanName, beanClass);
        }
        return bean;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        return Optional.ofNullable(map.get(beanName))
                .map(clazz -> Proxy.newProxyInstance(clazz.getClassLoader(), clazz.getInterfaces(), (proxy, method, args) -> {
                    if (profilingController.isEnabled()) {
                        System.out.println("PROFILING ...");
                        Object invoke = method.invoke(bean, args);
                        System.out.println("END");
                        return invoke;
                    } else {
                        return method.invoke(bean, args);
                    }

                })).orElse(bean);

    }
}
