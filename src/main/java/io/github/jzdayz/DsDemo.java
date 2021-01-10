package io.github.jzdayz;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.dynamic.datasource.annotation.DSTransactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.Statement;

@Component
public class DsDemo {

    @Autowired
    private DsDemo dsDemo;

    // 单应用多数据源事务
    @DSTransactional
    public void t1(DataSource ds){
        dsDemo.test1(ds);
        dsDemo.test2(ds);
    }

    @DS("e2")
    public void test2(DataSource ds){
        try (
                Connection connection = ds.getConnection();
        ){
            Statement statement = connection.createStatement();
            statement.execute("insert into test1 values (null,'小白e2')");
        }catch (Exception e){
            e.printStackTrace();
        }
        throw new RuntimeException();
    }

    @DS("e1")
    public void test1(DataSource ds){
        try (
                Connection connection = ds.getConnection();
        ){
            Statement statement = connection.createStatement();
            statement.execute("insert into test values (null,'小黑e1')");
        }catch (Exception e){
            e.printStackTrace();
        }
    }

}
