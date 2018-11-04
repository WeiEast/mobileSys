import bl.LocalFlowFeeServiceImpl;
import bl.NationalFlowFeeServiceImpl;
import org.junit.Test;
import service.LocalFlowFeeService;
import service.NationalFlowFeeService;

import java.util.Date;

public class NationalFlowServiceTest {
    NationalFlowFeeService service = new NationalFlowFeeServiceImpl();
    @Test
    public void testGetCallFee(){
        String s = service.getNationalFlowFeeByUserId(1L);
        System.out.println(s);
    }

    @Test
    public void testCreateCall(){
        service.createNationalFlowFee(1L, 46.0, new Date(), 50);
    }
}
