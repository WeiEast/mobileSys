import bl.LocalFlowFeeServiceImpl;
import org.junit.Test;
import service.LocalFlowFeeService;

import java.util.Date;

public class LocalFLowService {
    LocalFlowFeeService service = new LocalFlowFeeServiceImpl();
    @Test
    public void testGetCallFee(){
        String s = service.getLocalFlowFeeByUserId(1L);
        System.out.println(s);
    }

    @Test
    public void testCreateCall(){
        service.createLocalFlowFee(1L, 46.0, new Date(), 50);
    }
}
