package tools;

import entity.Channel;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DB {
    private static Connection conn = null;
    private static PreparedStatement prst = null;
    private static ResultSet rs = null;
    private static ResultSetMetaData meta = null;

    private static String driverClass = "com.mysql.jdbc.Driver";
    private static String url = "jdbc:mysql://localhost:3306/cms";
    private static String user = "root";
    private static String password = "";

    /**
     * 获取数据库连接的方法
     */
    public static Connection getConn(){
        try {
            Class.forName ( driverClass );
            conn = DriverManager.getConnection ( url,user,password );
        } catch (ClassNotFoundException e) {
            e.printStackTrace ();
        } catch (SQLException e) {
            e.printStackTrace ();
        }
        return conn;
    }

    /**
     * 关闭数据库连接的方法
     */
    public static void close(AutoCloseable t)  {
        try {
            if (t != null){
                t.close ();
            }
        } catch (Exception e) {
            e.printStackTrace ();
        }
    }

    /**
     * 把结果集转换为离线集
     * @param rs
     * @return
     */
    public static List<Channel> resultSet2List (ResultSet rs) {

        List<Channel> list = null;
        try {
            while (rs.next ()){
                //判断结果集没实例化，就实例化
                if (list == null){
                    list = new ArrayList <> (  );
                }
                //把rs中当前记录封装到Channel对象
                Channel c = new Channel ();
                c.setCid ( rs.getInt ( "cid" ) );
                c.setCname ( rs.getString ( "cname" ) );
                c.setDescription ( rs.getString ( "description" ) );
                list.add ( c );
            }
        } catch (SQLException e) {
            e.printStackTrace ();
        }
        return list;
    }
}
