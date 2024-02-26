import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;

public class ConsumerInterfaceTest {

    static int cnt = 0;
    public <T> void forEach(List<T> list, Consumer<T> c){
        for (var l : list){
            c.accept(l);
            cnt++;
            //for문 순회할때마다 cnt값 증가
        }
    }

    /**
     * 파라미터로 T타입의 컬렉션을 받아서 void를 리턴하는 함수형 인터페이스
     * forEach()메서드에 Consumer인터페이스가 동작파라미터화로 전달되어서
     * 람다식을 파리미터로 전달해 컬렉션을 탐색하여 반복적인 동작을 수행할 수 있다.
     */
    @Test
    public void Consumer_인터페이스_테스트(){
        List<Integer> list = Arrays.asList(5,4,3,2,1);

        ConsumerInterfaceTest ct = new ConsumerInterfaceTest();
        ct.forEach(list, System.out::println);

        Assertions.assertEquals(cnt,list.size());
        //cnt의 값과 컬렉션의 size를 검증
    }
}
