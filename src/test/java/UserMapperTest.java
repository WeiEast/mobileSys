import entity.User;
import mapper.UserMapper;
import org.apache.ibatis.session.SqlSession;
import org.junit.Test;
import utils.DBUtil;

public class UserMapperTest {
    SqlSession  session = DBUtil.openSqlSession();
    UserMapper mapper = session.getMapper(UserMapper.class);

    @Test
    public void testInsert(){
        User user = new User();
        user.setName("afsdsd");
        mapper.insertUser(user);
        session.commit();
        session.close();
    }

    @Test
    public void TestDelete(){
        mapper.deleteUser(48L);
    }
}
