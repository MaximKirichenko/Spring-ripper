package quoters;

import org.springframework.beans.factory.support.PropertiesBeanDefinitionReader;
import org.springframework.context.support.GenericApplicationContext;

/**
 * @author Maksym Kyrychenko
 * @since 01.12.2019
 */
public class PropertyFileApplicationContext extends GenericApplicationContext {
    public PropertyFileApplicationContext(String fileName) {
        PropertiesBeanDefinitionReader propertiesBeanDefinitionReader = new PropertiesBeanDefinitionReader(this);
        int i = propertiesBeanDefinitionReader.loadBeanDefinitions(fileName);
        System.out.println(String.format("Found %s beans", i));
        refresh();
    }
}
