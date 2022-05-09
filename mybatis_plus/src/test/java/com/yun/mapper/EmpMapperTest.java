package com.yun.mapper;

import com.yun.entities.Emp;
import org.apache.ibatis.annotations.Param;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.sql.Wrapper;
import java.util.List;

@SpringBootTest
class EmpMapperTest {

    @Autowired
    private EmpMapper empMapper;

    @Autowired
    private  DeptMapper deptMapper;

    @Test
    public void testSelect() {
        System.out.println(("----- selectAll method test ------"));
        List<Emp> userList = empMapper.selectList(null);
        for(Emp user:userList) {
            System.out.println(user);
        }

        System.out.println(("----- selectAll method test1 ------"));
        System.out.println(deptMapper.selectList(null));


    }
}
