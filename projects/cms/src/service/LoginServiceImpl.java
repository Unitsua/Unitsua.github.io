package service;

import dao.UserDao;
import entity.User;
import tools.DB;

import java.sql.Connection;
import java.sql.SQLException;

public class LoginServiceImpl extends BaseService implements LoginService {
    private UserDao userDao;
    public void setUserDao(UserDao userDao){
        this.userDao = userDao;
    }
    @Override
    public User chkUser (String user) {
        User u = null;
        Connection conn = DB.getConn ();
        try {
            conn.setAutoCommit ( false );
            //dao
            u = userDao.findUserByUserName(user,conn);
            conn.commit ();
        } catch (SQLException e) {
            try {
                conn.rollback ();
            } catch (SQLException e1) {
                e1.printStackTrace ();
            }
            e.printStackTrace ();
            DB.close(conn);
        }
        return u;
    }
}
