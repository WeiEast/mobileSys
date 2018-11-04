package bl;

import entity.Plan;
import entity.PlanOperation;
import entity.UserPlan;
import enums.ValidDateEnum;
import mapper.ManagePlanMapper;
import mapper.PlanMapper;
import mapper.UserPlanMapper;
import org.apache.ibatis.session.SqlSession;
import service.PlanService;
import utils.DBUtil;

import java.util.Date;
import java.util.List;

public class PlanServiceImpl implements PlanService {

    public List<PlanOperation> listHistoryPlanOperationByUserId(Long uid) {
        SqlSession session = DBUtil.openSqlSession();
        ManagePlanMapper planMapper = session.getMapper(ManagePlanMapper.class);
        List<PlanOperation> planOps = planMapper.listPlanOpsByUserId(uid);
        return planOps;
    }

    public boolean purchasePlan(Long uid, Long pid, ValidDateEnum type) {
        SqlSession session = DBUtil.openSqlSession();
        ManagePlanMapper managePlanMapper = session.getMapper(ManagePlanMapper.class);
        PlanOperation planOp = managePlanMapper.selectLatestPlanOp(uid, pid);
        boolean optype = false;
        if (planOp!=null){optype=planOp.getOptype();}
        //如果最近已经订购过,则不能重复订购
        if (optype){return false;}

        //在manage plan中加入此操作
        PlanOperation op = new PlanOperation();
        op.setDate(new Date());
        op.setValidDate(type);
        op.setOptype(true);
        op.setPid(pid);
        op.setUid(uid);
        managePlanMapper.insertPlanOp(op);

        //如果是立即生效,则在user plan中插入记录

            PlanMapper planMapper = session.getMapper(PlanMapper.class);
            Plan plan = planMapper.selectPlanByPlanId(pid);

        if (type==ValidDateEnum.NOW){
            UserPlanMapper userPlanMap = session.getMapper(UserPlanMapper.class);
            UserPlan userPlan = new UserPlan();
            userPlan.setPid(pid);
            userPlan.setUid(uid);
            userPlan.setLeftCall(plan.getFreeCall());
            userPlan.setLeftMessage(plan.getFreeMessage());
            userPlan.setLeftNFlow(plan.getNationalFlow());
            userPlan.setLeftLFlow(plan.getLocalFlow());
            userPlanMap.insertUserPlan(userPlan);
        }

        return true;
    }

    public boolean deletePlan(Long uid, Long pid, ValidDateEnum type) {
        SqlSession session = DBUtil.openSqlSession();
        ManagePlanMapper managePlanMapper = session.getMapper(ManagePlanMapper.class);
        PlanOperation planOp = managePlanMapper.selectLatestPlanOp(uid, pid);
        boolean optype = false;
        if (planOp!=null){optype=planOp.getOptype();}
        //如果最近已经取消过,则不能重复订购
        if (!optype){return false;}

        //在manage plan中加入此操作
        PlanOperation op = new PlanOperation();
        op.setDate(new Date());
        op.setValidDate(type);
        op.setOptype(false);
        op.setPid(pid);
        op.setUid(uid);
        managePlanMapper.insertPlanOp(op);

        //如果是立即生效,则在user plan中插入记录
        if (type == ValidDateEnum.NOW){
            UserPlanMapper userPlanMap = session.getMapper(UserPlanMapper.class);
            userPlanMap.deleteUserPlan(uid, pid);
        }
        return true;
    }
}
