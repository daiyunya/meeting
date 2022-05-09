package com.yun.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.yun.entities.Emp;
import com.yun.entities.Msg;
import com.yun.mapper.DeptMapper;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashMap;
import java.util.Map;

public class DepService {


    @Autowired
    DeptMapper deptMapper;


//    //通过Id查询
//    public Msg getEmp(Map<String, Object> para) {
//        String pageString = (String) para.get("userId");
//        Long id = Long.valueOf(pageString);
////        long id = (Long) para.get("userId");
//        Emp emp = deptMapper.selectById(id);
//        Map<String, Object> map = new HashMap<>();
//        map.put("result", emp);
//        return Msg.success().add(map);
//    }
//
//    //分页查询所有
//    public Msg getAll(Map<String, Object> para) {
//        Map<String, Object> map = new HashMap<>();
//        if (para.containsKey("currentPage")) {
//            Integer currentPage = Integer.parseInt((String) para.get("currentPage"));
//            Integer pageSize = Integer.parseInt((String) para.get("pageSize"));
//            PageHelper.startPage(currentPage, pageSize);
//            emps = deptMapper.selectList(null);
//            PageInfo pageInfo = new PageInfo(emps);
//            map.put("result", pageInfo);
//        } else {
//            emps = empMapper.selectList(null);
//            map.put("result", emps);
//        }
//        return Msg.success().add(map);
//    }
}
