package mapper;

import entity.UserPlan;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface UserPlanMapper {
    void insertUserPlan(UserPlan plan);
    List<UserPlan> listUserPlan(Long uid);
    void updateUserPlan(UserPlan plan);
    void deleteUserPlan(@Param("uid") Long uid, @Param("pid") Long pid);
//    Double selectNowConsumption(Long uid);
}
