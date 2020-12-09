package com.ovopark.mybatisplusdemo;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ovopark.mybatisplusdemo.Bean.User;
import com.ovopark.mybatisplusdemo.mapper.UserMapper;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.Assert;

import java.util.*;

@SpringBootTest

class MybatisplusdemoApplicationTests {

    @Autowired
    private UserMapper userMapper;
    @Test
    void testSelect() {
        System.out.println("--------selectAll method test--------");
        List<User> users = userMapper.selectList(null);
        users.forEach(System.out::println);
    }

    @Test
    void testAllEq(){
        // 条件构造器
//        QueryWrapper<User> userQueryWrapper = new QueryWrapper<>();
        QueryWrapper<User> userQueryWrapper = Wrappers.query();
        Map<String,Object> map = new HashMap<>();
        map.put("name","Jone");
//        map.put("age",19);
        userQueryWrapper.allEq(map);
        List<User> users = userMapper.selectList(userQueryWrapper);
        users.forEach(System.out::println);
    }

    @Test
    void testgt(){
        QueryWrapper<User> userQueryWrapper = Wrappers.query();
        userQueryWrapper.gt("age",18);
        List<User> users = userMapper.selectList(userQueryWrapper);
        users.forEach(System.out::println);
    }

    @Test
    void testIn(){
        QueryWrapper<User> userQueryWrapper = Wrappers.query();
        Collection<Integer> ages = new ArrayList<>();
        // 添加数据
        ages.add(18);
        ages.add(19);
        ages.add(21);
        userQueryWrapper.in("age",ages);
        List<User> users = userMapper.selectList(userQueryWrapper);
        users.forEach(System.out::println);
    }
//
//    @Test
//    void Inset(){
//        QueryWrapper<User> userQueryWrapper = Wrappers.query();
//        for(int i=0;i<20;i++){
//            String name = "Jone"+i;
//            String email = "test"+i+"@qq.com";
//            Long id = (long)(7+i);
//            User user = new User(id,name,19+i,email);
//            userMapper.insert(user);
//        }
//    }

    // 测试分页
    @Test
    void testPage(){
        Page<User> page = new Page<>(2,5);
        page = userMapper.selectPageVo(page,20);
        List<User> list = page.getRecords();
        for(User user:list){
            System.out.println(user);
        }
    }


}
