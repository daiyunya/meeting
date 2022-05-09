package com.yun.controller;


import com.yun.entities.Msg;
import com.yun.service.Empservice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
public class EmpController {


    @Autowired
    Empservice empservice;



    //根据ID查

    @GetMapping("/room")
    public Msg getEmp(@RequestParam Map<String,Object> para){
        return empservice.getEmp(para);
    }
    //分页查询全部（currentPage,pageSize）

    @GetMapping("/rooms")
    public Msg getAllEmps(@RequestParam Map<String,Object> para){
        return empservice.getAll(para);
    }

    //修改信息：带什么改什么
//    @PutMapping("/room")
//    public Msg update(@RequestBody Map<String,Object> para){
//        return  empservice.updateEmp(para);
//////        Msg msg = empservice.duplicateCheck(para);
////        if(msg.getCode()==100){
////            return  empservice.updateEmp(para);
////        }else{
////            return msg;
////        }
//    }

    //修改信息：带什么改什么
    @PutMapping("/room")
    public Msg update(@RequestBody Map<String,Object> para){
        return empservice.updateEmp(para);

//        return  empservice.updateEmp(para);
//        Msg msg = empService.duplicateCheck(para);
//        if(msg.getCode()==100){
//            return  empService.updateEmp(para);
//        }else{
//            return msg;
//        }
    }
//    //添加  （roomNo,roomSize）
//
    @PostMapping("/room")
    public Msg add(@RequestBody Map<String,Object> para){
        return empservice.addEmp(para);
    }
//    //通过ID删除  --实现连带删除
//
    @DeleteMapping("/room")
    public Msg delete(@RequestBody Map<String,Object> para){

        return empservice.deleteEmp(para);
    }
//    //分页模糊查询不带id （currentPage,pageSize,....）
//
//    @GetMapping("/roomNo")
//    public Msg selectByNo(@RequestParam Map<String,Object> para){
//        return empService.selectByNo(para);
//    }

}
