import bl.CallFeeServiceImpl;
import org.junit.Test;
import service.CallFeeService;

import java.util.Date;

public class CallServiceTest {
    CallFeeService service = new CallFeeServiceImpl();
    @Test
    public void testGetCallFee(){
        String s = service.getCallFeeByUserId(1L);
        System.out.println(s);
    }

    @Test
    public void testCreateCall(){
        service.createCallFee(1L, 50, new Date());
    }
}
