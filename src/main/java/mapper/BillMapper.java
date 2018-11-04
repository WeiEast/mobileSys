package mapper;

import entity.Bill;
import org.apache.ibatis.annotations.Param;

public interface BillMapper {
    Bill selectBill(@Param("uid") Long uid, @Param("year") int year, @Param("month") int month);
}
