package hello.core.singleton;

public class SingtonService {
    private static final SingtonService instance = new SingtonService();


    public static SingtonService getInstance(){
        return instance;
    }
}
