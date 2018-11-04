package mapper;

import entity.User;

/**
 * @author zhangao
 * @version 2018.10.28
 * 用户数据层操作
 * */
public interface UserMapper {
    User selectUser(Long id);

    void insertUser(User user);

    void deleteUser(Long id);
}
