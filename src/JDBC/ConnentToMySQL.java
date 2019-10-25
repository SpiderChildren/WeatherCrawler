package JDBC;

import java.sql.*;

public class ConnentToMySQL {
   //  alter table 数据表名 CONVERT TO CHARACTER SET utf8;
//create table testpicture ( url varchar(500) not null unique, name  varchar(200)  not null unique , place varchar(50), time DATETIME);
//insert into testpicture values ( "url" , "name" , "bei" , "20191025164600");
    static String DATA_BASE="test";
//    static String TABLE = "test_table";
    static String COL_NAME = "url";
//    static String CREATE_TABLE="";

    // 数据库的用户名与密码，需要根据自己的设置
    private static String USER = "root";
    private static String PASS = "root";

    Connection conn;
    Statement stmt;
    ResultSet rs;
    PreparedStatement pst;


    // MySQL 8.0 以下版本 - JDBC 驱动名及数据库 URL
    static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
    static final String DB_URL = "jdbc:mysql://localhost:3306/"+DATA_BASE+"?useSSL=false&serverTimezone=UTC";

    // MySQL 8.0 以上版本 - JDBC 驱动名及数据库 URL
//    static String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
//    static String DB_URL = "jdbc:mysql://localhost:3306/"+DATA_BASE+"?useSSL=false&serverTimezone=UTC";


    public ConnentToMySQL(){
        conn = null;
        stmt = null;
        try{
            // 注册 JDBC 驱动
            Class.forName(JDBC_DRIVER);

            // 打开链接
            System.out.println("连接数据库...");
            conn = DriverManager.getConnection(DB_URL,USER,PASS);
            stmt = conn.createStatement();
            System.out.println("实例化Statement对象...");
            stmt = conn.createStatement();

        }catch(SQLException se){
            // 处理 JDBC 错误
            se.printStackTrace();
        }catch(Exception e){
            // 处理 Class.forName 错误
            e.printStackTrace();
        }
        System.out.println("完成注册数据库：\n");
    }

    public int create_table(String table_name){
        String create_table = "CREATE TABLE " + table_name+" ("
                + "url varchar(500) ,"
                + "date varchar(50) "
                + ")";
        try{
            stmt.executeLargeUpdate(create_table);
            return 0;
        }catch(SQLException se){
            // 处理 JDBC 错误
            se.printStackTrace();
        }catch(Exception e){
            // 处理 Class.forName 错误
            e.printStackTrace();
        }
        return 1;
    }

    public int insert(String table_name,String url, String name , String place , String date){
        String sql;
        sql = "INSERT INTO "+table_name+" ( url, name , place , date  ) VALUE ( ?,? );";
        try{
            pst = conn.prepareStatement(sql);
            pst.setString(1, url);
            pst.setString(2, name);
            pst.setString(3, place);
            pst.setString(4, date);
            pst.executeUpdate();
            return 0;
        }catch(SQLException se){ // 处理 JDBC 错误
            se.printStackTrace();
        }catch(Exception e){ // 处理 Class.forName 错误
            e.printStackTrace();
        }
        return 1;
    }

    public int output(String table_name,long limit){
        try{
            String sql;
            // 展开结果集数据库
            if(limit==0l){
                sql = "SELECT "+COL_NAME+" FROM "+table_name;
                rs = stmt.executeQuery(sql);
            }else{
                sql = "SELECT "+COL_NAME+" FROM "+table_name+" LIMIT 0,"+limit+";";
                rs = stmt.executeQuery(sql);
            }
            while(rs.next()){
                // 通过字段检索
//                int id  = rs.getInt("id");
                String url = rs.getString("url");
                // 输出数据
//                System.out.print("ID: " + id);
                System.out.println("站点：" + url);
            }
            return 0;
        }catch(SQLException se){ // 处理 JDBC 错误
            se.printStackTrace();
        }catch(Exception e){ // 处理 Class.forName 错误
            e.printStackTrace();
        }
        return 1;
    }

    public int release(){
        try{
            // 完成后关闭
            rs.close();
            stmt.close();
            conn.close();
            return 0;
        }catch(SQLException se){ // 处理 JDBC 错误
            se.printStackTrace();
        }catch(Exception e){ // 处理 Class.forName 错误
            e.printStackTrace();
        }finally {
            // 关闭资源
            try{
                if(stmt!=null) stmt.close();
            }catch(SQLException se2){ /* 什么都不做*/ }
            try{
                if(conn!=null) conn.close();
            }catch(SQLException se){
                se.printStackTrace();
            }
        }
        return 1;
    }



//    public static void main(String[] args) {
//        ConnentToMySQL connentToMySQL = new ConnentToMySQL();
////        int ctFlag = connentToMySQL.create_table("testcreate");
//        int isFlag = connentToMySQL.insert("testcreate","https://inserttest.fake.com","2019年10月某日");
//        int opFlag = connentToMySQL.output("testcreate",0l);
//        int rFlag = connentToMySQL.release();
//        if(rFlag==0){
//            System.out.println("\n成功关闭资源");
//        }
//    }
}