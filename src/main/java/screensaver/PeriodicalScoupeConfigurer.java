package screensaver;

import javafx.util.Pair;
import org.springframework.beans.factory.ObjectFactory;
import org.springframework.beans.factory.config.Scope;

import java.time.LocalTime;
import java.util.HashMap;
import java.util.Map;

import static java.time.LocalDateTime.now;

/**
 * @author Maksym Kyrychenko
 * @since 01.12.2019
 */
public class PeriodicalScoupeConfigurer implements Scope {
    private Map<String, Pair<LocalTime, Object>> beanCache = new HashMap<>();
    @Override
    public Object get(String name, ObjectFactory<?> objectFactory) {
        if (beanCache.containsKey(name)) {
            Pair<LocalTime, Object> localTimeObjectPair = beanCache.get(name);
            LocalTime previousTime = localTimeObjectPair.getKey();
            if(now().getSecond() - previousTime.getSecond()>3){
                beanCache.put(name, new Pair<>(LocalTime.now(), objectFactory.getObject()));
            }
        }else {
            beanCache.put(name, new Pair<>(LocalTime.now(), objectFactory.getObject()));
        }
        return beanCache.get(name).getValue();
    }

    @Override
    public Object remove(String name) {
        return null;
    }

    @Override
    public void registerDestructionCallback(String name, Runnable callback) {

    }

    @Override
    public Object resolveContextualObject(String key) {
        return null;
    }

    @Override
    public String getConversationId() {
        return null;
    }
}
