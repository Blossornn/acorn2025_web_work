package test.util;

import java.sql.Connection;
import java.sql.PreparedStatement;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

public class DbcpBean {

    // Connection 객체를 리턴해주는 메소드
    public Connection getConn() {
        Connection conn = null;
        try {
            // Tomcat 서버가 관리하는 Connection 객체를 하나 얻어와서 필드에 저장
            Context initContext = new InitialContext();
            Context envContext = (Context) initContext.lookup("java:/comp/env");
            DataSource ds = (DataSource) envContext.lookup("jdbc/myoracle");
            conn = ds.getConnection();

            System.out.println("Connection!");

        } catch (Exception e) {
            e.printStackTrace();
        }
        return conn;
    }
}
