package screensaver;

import org.springframework.context.annotation.*;

import java.awt.*;
import java.util.Random;

/**
 * @author Maksym Kyrychenko
 * @since 01.12.2019
 */
@Configuration
@ComponentScan(basePackages = "screensaver")
public class Config {
    @Bean
    @Scope(scopeName = "periodical")
    public Color color() {
        Random random = new Random();
        return new Color(random.nextInt(255), random.nextInt(255), random.nextInt(255));
    }

    @Bean
    public ColorFrame colorFrame(){
        return new ColorFrame() {
            @Override
            protected Color getColor() {
                return color();
            }
        };
    }

}
