package quoters;

import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class TerminatorQuoterTest {

    @Test
    public void mBeanWithProfilingTest() throws InterruptedException {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("application-context.xml");
        while (true) {
            Thread.sleep(1000);
            context.getBean(Quoter.class).sayQuote();
        }
    }

    @Test
    public void postConstructTest() throws InterruptedException {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("application-context.xml");
        context.getBean(Quoter.class).sayQuote();
    }

    @Test
    public void propertyContextTest(){
        PropertyFileApplicationContext context = new PropertyFileApplicationContext("context.properties");
        context.getBean(Quoter.class).sayQuote();
    }
}
