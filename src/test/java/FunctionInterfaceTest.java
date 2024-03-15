import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;
import org.junit.jupiter.api.Test;

public class FunctionInterfaceTest {

    public <T, R> List<R> map(List<T> list, Function<T, R> f) {
        List<R> result = new ArrayList<>();
        for (var l : list) {
            result.add(f.apply(l));
        }
        return result;
    }

    @Test
    public void function_인터페이스_테스트() {

        List<Integer> StringLenList = new ArrayList<>();
        List<String> str = List.of("a", "aa", "aaa");

        StringLenList = map(str, (String s) -> s.length());

        assertThat(StringLenList.get(0)).isEqualTo(1);
        assertThat(StringLenList.get(2)).isEqualTo(3);
    }
}
