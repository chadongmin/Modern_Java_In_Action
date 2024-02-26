import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class FunctionalInterface {
    @Test
    public void 함수형인터페이스에_람다식을_전달할수있는이유(){
        Runnable r1 = () -> System.out.println("Hello world");

        /**
         * Runnable 인터페이스는 추상메서드가 단 하나로 구성 된 함수형 인터페이스이다.
         * 그래서 람다식의 전체 표현식을 함수형 인터페이스의 인스턴스로 취급할 수 있다.
         */
        Runnable r2 = new Runnable() {
            @Override
            public void run() {
                System.out.println("Hello World2");
            }
        };
        r1.run();
        r2.run();
    }
}
