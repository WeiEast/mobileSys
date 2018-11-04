package utils;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.InputStream;

public class DBUtil {
    private static final String Config_Path = "mybatis-config.xml";

    private static SqlSessionFactory sqlSessionFactory = null;
    private static final Class CLASS_LOCK = DBUtil.class;

    private static SqlSessionFactory initSqlSessionFactory() {
        InputStream is = null;
        try {
            is = Resources.getResourceAsStream(Config_Path);
        } catch (IOException e) {
            e.printStackTrace();
        }
        synchronized (CLASS_LOCK) {
            if (sqlSessionFactory == null) {
                sqlSessionFactory = new SqlSessionFactoryBuilder().build(is);
            }
        }
        return sqlSessionFactory;
    }

    public static SqlSession openSqlSession() {
        if (sqlSessionFactory == null)
            initSqlSessionFactory();
        return sqlSessionFactory.openSession(true);
    }

}
