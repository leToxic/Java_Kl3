package genericMethods;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.lang.reflect.Method;
import java.util.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertIterableEquals;

/**
 * Created: 26.10.2021
 *
 * @author Werner Gitschthaler (Werner)
 */
public class GenericUtilTest {

    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final ByteArrayOutputStream errContent = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;
    private final PrintStream originalErr = System.err;

    // some preparation
    @BeforeEach
    public void setUpStreams() {
        System.setOut(new PrintStream(outContent));
        System.setErr(new PrintStream(errContent));
    }

    @AfterEach
    public void restoreStreams() {
        System.setOut(originalOut);
        System.setErr(originalErr);
    }

    @Test
    public void doNotTrickMe(){
        Map<String,Integer> methodOccurrence = new HashMap<>();
        for (Method method : GenericUtil.class.getDeclaredMethods()) {
            if(methodOccurrence.putIfAbsent(method.getName(),1) != null){
                throw new IllegalArgumentException("you must not overload methods! >" + method.getName() +"< is overloaded");
            }
        }
    }


    // Tests start here
    @Test
    public void testUtilArrayPrintInteger(){
        Integer[] myInt = {1,2,3};
        GenericUtil.printArray(myInt);
        assertEquals(outContent.toString(),String.format("1%n2%n3%n"));
    }
    @Test
    public void testUtilArrayPrintString(){
        String[] myString = {"hello","world"};
        GenericUtil.printArray(myString);
        assertEquals(outContent.toString(),String.format("hello%nworld%n"));
    }
    @Test
    public void testSumWithInt(){
        Integer[] ints = {1,2,3};
        assertEquals(GenericUtil.sum(ints),6);
    }
    @Test
    public void testSumWithDouble(){
        Double[] doubles = {4d,5d,6d};
        assertEquals(GenericUtil.sum(doubles),15d);
    }
    @Test
    public void testMaxWithString(){
        List<String> list = Arrays.asList("x", "a", "b", "c");
        assertEquals(GenericUtil.maxElement(list),"x");
    }
    @Test
    public void testMaxWithInteger(){
        List<Integer> list = Arrays.asList(1,2,3);
        assertEquals(GenericUtil.maxElement(list),3);
    }
    @Test
    public void testMaxWithIntegerAndNotChanged(){
        List<Integer> list = Arrays.asList(3,2,1);
        List<Integer> listCopy = new ArrayList<>(list);
        assertEquals(GenericUtil.maxElement(list),3);
        assertIterableEquals(list,listCopy);
    }
    @Test
    public void testCountOddsInteger(){
        List<Integer> list = Arrays.asList(1,2,3,4,5);
        assertEquals(GenericUtil.countOdds(list),3);
    }
    @Test
    public void testCountOddsDouble(){
        List<Double> list = Arrays.asList(1d,2d,3d);
        assertEquals(GenericUtil.countOdds(list),2);
    }
    @Test
    public void testSwap(){
        LinkedList<Integer> x = new LinkedList<>(Arrays.asList(1,2,3,4,5));
        GenericUtil.swapElements(x,1,2);
        assertEquals(2,x.get(1));
        GenericUtil.swapElements(x,2,1);
        assertEquals(3,x.get(1));
    }
}
