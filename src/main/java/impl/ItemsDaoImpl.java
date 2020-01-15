package impl;

import dao.ItemsDao;
import domain.Items;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class ItemsDaoImpl implements ItemsDao {
    public List<Items> findAll() throws Exception {
        List<Items> list = new ArrayList<Items>();
        //先获取connection对象
        Connection connection = null;
        //获取真正操作数据库的对象
        PreparedStatement pst = null;
        //执行数据库查询操作
        ResultSet rs = null;
        try{
            //加载驱动类
            Class.forName("com.mysql.jdbc.Driver");
            //先获取connection对象
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/maven","root","123");
            //获取真正操作数据库的对象
            pst = connection.prepareCall("select * from user");
            //执行数据库查询操作
            rs = pst.executeQuery();
            //把数据库结果集转成java的List集合
            while (rs.next()){
                Items items = new Items();
                items.setId(rs.getInt("id"));
                items.setName(rs.getString("name"));
                items.setNum(rs.getInt("age"));
                list.add(items);
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            connection.close();
            pst.close();
            rs.close();
        }

        return list;
    }
}
