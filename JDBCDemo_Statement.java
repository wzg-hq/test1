package com.jdbc;

import com.pojo.Account;
import jdk.jfr.StackTrace;
import org.junit.Test;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class JDBCDemo_Statement {
    @Test
    public void testDML() throws Exception {
        String url = "jdbc:mysql://127.0.0.1:3306/db2?useSSL=false";
        String user = "root";
        String password = "468468";
        Connection conn = DriverManager.getConnection(url, user, password);
        String sql = "insert into teacher(id, name, salary) values(6, 'wzg', 200000), (7, 'wzg', 200000)";
        Statement stmt = conn.createStatement();
        try {
            conn.setAutoCommit(false);
            int count = stmt.executeUpdate(sql);
            System.out.println(count);
            if(count > 0){
                System.out.println("修改成功");
            }else{
                System.out.println("修改失败");
            }
            conn.commit();
        } catch (Exception e) {
            conn.rollback();
            e.printStackTrace();
        }
    }
    @Test
    public void testDDL() throws Exception {
        String url = "jdbc:mysql://127.0.0.1:3306/db1?useSSL=false";
        String user = "root";
        String password = "468468";
        Connection conn = DriverManager.getConnection(url, user, password);
        String sql1 = "create database db2";
        Statement stmt = conn.createStatement();
        try {
            conn.setAutoCommit(false);
            int count1 = stmt.executeUpdate(sql1);
            System.out.println(count1);
            conn.commit();
        } catch (Exception e) {
            conn.rollback();
            e.printStackTrace();
        }
    }
    @Test
    public void test() throws Exception{
        String url = "jdbc:mysql://localhost:3306/db1?useSSL=false";
        String user = "root";
        String password = "468468";
        Connection conn = DriverManager.getConnection(url, user, password);
        String sql = "select * from account";
        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery(sql);
        List<Account> list = new ArrayList<>();
        while(rs.next()){
            Account account = new Account();
           int id =  rs.getInt("id");
           String name = rs.getString("name");
           Double money = rs.getDouble("money");
           account.setId(id);
           account.setName(name);
           account.setMoney(money);
           list.add(account);
        }
        System.out.println(list);
        rs.close();
        stmt.close();
        conn.close();
    }
}
