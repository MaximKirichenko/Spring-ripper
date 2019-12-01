package quoters;

import javax.annotation.PostConstruct;

@Profiling
@DeprecatedClass(newImpl=T1000.class)
public class TerminatorQuoter implements Quoter {

    @InjectRandomInt(min = 2, max = 7)
    private int repeat;
    private String message;

    public TerminatorQuoter() {
        System.out.println("Phase 1");
    }

    @PostConstruct
    private void init(){
        System.out.println("Phase 2");
    }

    public void setRepeat(int repeat) {
        this.repeat = repeat;
    }
    
    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    @PostProxy
    public void sayQuote() {
        for (int i = 0; i < repeat; i++) {
            System.out.println("Message = " + message);
        }
    }
}
