package com.yun.service;



import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.yun.entities.Emp;
import com.yun.entities.Msg;
import com.yun.mapper.EmpMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * @author 代云
 */
@Service
public class Empservice {

    @Autowired
    EmpMapper empMapper;

    private Emp emp;
    private List<Emp> emps;

    //通过Id查询
    public Msg getEmp(Map<String, Object> para) {
        String pageString = (String) para.get("userId");
        Integer id = Integer.valueOf(pageString) ;
        Emp emp = empMapper.selectById(id);
        Map<String, Object> map = new HashMap<>();

        map.put("result", emp);
        return Msg.success().add(map);
    }

    //分页查询所有
    public Msg getAll(Map<String, Object> para) {
        Map<String, Object> map = new HashMap<>();
        if (para.containsKey("currentPage")) {
            Integer currentPage = Integer.parseInt((String) para.get("currentPage"));
            Integer pageSize = Integer.parseInt((String) para.get("pageSize"));
            PageHelper.startPage(currentPage, pageSize);
            emps = empMapper.selectList(null);
            PageInfo pageInfo = new PageInfo(emps);
            map.put("result", pageInfo);
        } else {
            emps = empMapper.selectList(null);
            map.put("result", emps);
        }
        return Msg.success().add(map);
    }

    //修改
    public Msg updateEmp(Map<String, Object> para) {

        String pageString = (String) para.get("userId");
        Integer userId = Integer.valueOf(pageString) ;
//        Integer userId = (Integer) para.get("userId");
        Integer deptId = null;
        String username = null;
        String email = null;
        String phone = null;
        String sex = null;
        emp = new Emp(userId, deptId, username, email, phone, sex);
        emp.setUserId(userId);
        int num = empMapper.updateById(emp);
        return num > 0 ? Msg.success() : Msg.fault();
    }

//    添加
    public Msg addEmp(Map<String, Object> para) {


        String empids = (String) para.get("userId");
        Integer userId = Integer.valueOf(empids) ;
        String deptIds = (String) para.get("deptId");
        Integer deptId = Integer.valueOf(deptIds);
//

//        Integer deptId = (Integer) para.get("deptId");
//        Integer userId = (Integer) para.get("userId");
        String username = (String) para.get("userName");
        String email = (String) para.get("email");
        String phone = (String) para.get("phone");
        String sex = para.get("sex").toString();
//        String sex = (String) para.get("sex");
        emp = new Emp(userId, deptId, username, email, phone, sex);
//        emp = new Emp(deptId, username, email, phone, sex);
        empMapper.insert(emp);
        int num = empMapper.insert(emp);
        return num > 0 ? Msg.success() : Msg.fault();
    }

    //删除
    public Msg deleteEmp(Map<String, Object> para) {
        Integer id = (Integer) para.get("userId");
        int num = empMapper.deleteById(id);
        empMapper.deleteById(id);
//        meetingRecordMapper.deleteByCondition(id);
        return num > 0 ? Msg.success() : Msg.fault();
    }
//    //查重
//    public Msg duplicateCheck(Map<String, Object> para) {
////        Long userId = (Long) para.get("userId");
//
//        String pageString = (String) para.get("userId");
//        Long userId = Long.valueOf(pageString);
//        Long deptId = null;
//        if (para.containsKey("deptId")) {
//            deptId = (Long) para.get("depId");
//        }
//        emp = new Emp(userId, deptId);
//        emp.setUserId(Long.valueOf(userId));
//        Emp emp1 = empMapper.duplicateCheck(emp);
//        if (emp1 != null) {
//            return Msg.fault().setError("该编号已经存在");
//        }
//        return Msg.success();
//    }

}
