package org.example.week02.controller;

import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.*;
import org.example.week02.dto.StudentAddDTO;
import org.example.week02.dto.StudentUpdateDTO;
import org.example.week02.service.StudentService;
import org.example.week02.vo.StudentVO;

import java.util.List;

@RestController
@RequestMapping("/api/v1/students")
public class StudentController {

    @Resource
    private StudentService studentService;

    // 获取所有学生
    @GetMapping("/all")
    public List<StudentVO> getAllStudents() {
        return studentService.getAllStudents();
    }

    // 根据ID获取学生
    @GetMapping("/{id}")
    public StudentVO getStudent(@PathVariable Long id) {
        return studentService.getStudent(id);
    }

    // 删除学生
    @DeleteMapping("/{id}")
    public void deleteStudent(@PathVariable Long id) {
        studentService.deleteStudent(id);
    }

    // 根据名称模糊查询学生
    @GetMapping
    public List<StudentVO> getStudentByName(@RequestParam String name) {
        return studentService.getStudentByName(name);
    }

    // 添加学生
    @PostMapping
    public String addStudent(@RequestBody StudentAddDTO studentAddDTO) {
        studentService.addStudent(studentAddDTO);
        return "添加成功";
    }

    // 修改学生
    @PutMapping("/{id}")
    public String updateStudent(@PathVariable Long id, @RequestBody StudentUpdateDTO studentUpdateDTO) {
        studentService.updateStudent(id, studentUpdateDTO);
        return "修改成功";
    }
}