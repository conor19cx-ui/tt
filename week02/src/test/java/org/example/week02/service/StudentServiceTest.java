package org.example.week02.service;

import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.example.week02.constant.GenderEnum;
import org.example.week02.dto.StudentAddDTO;
import org.example.week02.dto.StudentUpdateDTO;
import org.example.week02.vo.StudentVO;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
@Slf4j
@DisplayName("StudentService 单元测试")
class StudentServiceTest {

    @Resource
    private StudentService studentService;

    @Test
    @DisplayName("测试获取所有学生 - 验证数量和基本信息")
    void testGetAllStudents() {
        List<StudentVO> students = studentService.getAllStudents();
        // 验证返回2个学生
        assertEquals(2, students.size(), "应该返回2个学生");
        log.info("所有学生：{}", students);
    }

    @Test
    @DisplayName("测试根据ID获取学生")
    void testGetStudent() {
        StudentVO student = studentService.getStudent(1001L);
        assertNotNull(student, "学生ID=1001应该存在");
        assertEquals("张三", student.getName(), "学生姓名应该是张三");
        log.info("ID=1001的学生：{}", student);
    }

    @Test
    @DisplayName("测试根据名字模糊查询")
    void testGetStudentByName() {
        List<StudentVO> students = studentService.getStudentByName("张");
        assertEquals(2, students.size(), "应该返回2个名字带'张'的学生");
        log.info("名字带'张'的学生：{}", students);
    }

    @Test
    @DisplayName("测试添加学生")
    void testAddStudent() {
        StudentAddDTO dto = new StudentAddDTO();
        dto.setName("测试学生");
        dto.setMobile("13900001111");
        dto.setGender(GenderEnum.MALE);
        dto.setAvatar("https://test.com/avatar.jpg");
        dto.setBirthday(LocalDate.of(2000, 1, 1));

        studentService.addStudent(dto);
        // 验证添加后总数为3
        List<StudentVO> allStudents = studentService.getAllStudents();
        assertEquals(3, allStudents.size(), "添加后应该有3个学生");
        log.info("添加学生后总数：{}", allStudents.size());
    }

    @Test
    @DisplayName("测试修改学生")
    void testUpdateStudent() {
        StudentUpdateDTO dto = new StudentUpdateDTO();
        dto.setName("张三(已修改)");
        dto.setMobile("13800009999");

        studentService.updateStudent(1001L, dto);
        StudentVO student = studentService.getStudent(1001L);
        assertEquals("张三(已修改)", student.getName(), "学生姓名应该被修改");
        log.info("修改后的学生：{}", student);
    }

    @Test
    @DisplayName("测试删除学生")
    void testDeleteStudent() {
        studentService.deleteStudent(1001L);
        List<StudentVO> allStudents = studentService.getAllStudents();
        assertEquals(1, allStudents.size(), "删除后应该剩1个学生");
        log.info("删除学生后总数：{}", allStudents.size());
    }
}