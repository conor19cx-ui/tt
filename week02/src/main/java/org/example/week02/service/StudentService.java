package org.example.week02.service;

import org.example.week02.constant.GenderEnum;
import org.example.week02.dto.StudentAddDTO;
import org.example.week02.dto.StudentUpdateDTO;
import org.example.week02.entity.Student;
import org.example.week02.vo.StudentVO;
import org.springframework.stereotype.Service;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class StudentService {
    private static final Map<Long, Student> STUDENT_DATA = new ConcurrentHashMap<>();

    // 静态初始化数据
    static {
        Student student1 = new Student(
                1001L,
                "张三",
                "13888888888",
                GenderEnum.MALE,
                "https://mqxu.top/1.png",
                true,
                LocalDate.of(1990, 1, 1),
                LocalDateTime.now()
        );
        Student student2 = new Student(
                1002L,
                "张三丰",
                "13888888889",
                GenderEnum.FEMALE,
                "https://mqxu.top/2.png",
                true,
                LocalDate.of(1990, 1, 1),
                LocalDateTime.now()
        );
        STUDENT_DATA.put(student1.getId(), student1);
        STUDENT_DATA.put(student2.getId(), student2);
    }

    // 1. 获取所有学生
    public List<StudentVO> getAllStudents() {
        List<StudentVO> list = new ArrayList<>();
        for (Student student : STUDENT_DATA.values()) {
            StudentVO vo = new StudentVO();
            vo.setId(student.getId());
            vo.setName(student.getName());
            vo.setMobile(student.getMobile());
            vo.setGender(student.getGender());
            vo.setCreateTime(student.getCreateTime());
            list.add(vo);
        }
        return list;
    }

    // 2. 根据ID获取学生
    public StudentVO getStudent(Long id) {
        Student student = STUDENT_DATA.get(id);
        if (student == null) {
            throw new RuntimeException("学生不存在，ID=" + id);
        }
        StudentVO vo = new StudentVO();
        vo.setId(student.getId());
        vo.setName(student.getName());
        vo.setMobile(student.getMobile());
        vo.setGender(student.getGender());
        vo.setCreateTime(student.getCreateTime());
        return vo;
    }

    // 3. 根据名称模糊查询
    public List<StudentVO> getStudentByName(String name) {
        List<StudentVO> list = new ArrayList<>();
        for (Student student : STUDENT_DATA.values()) {
            if (student.getName().contains(name)) {
                StudentVO vo = new StudentVO();
                vo.setId(student.getId());
                vo.setName(student.getName());
                vo.setMobile(student.getMobile());
                vo.setGender(student.getGender());
                vo.setCreateTime(student.getCreateTime());
                list.add(vo);
            }
        }
        return list;
    }

    // 4. 添加学生
    public void addStudent(StudentAddDTO studentAddDTO) {
        Student student = new Student();
        student.setId(System.currentTimeMillis());
        student.setName(studentAddDTO.getName());
        student.setMobile(studentAddDTO.getMobile());
        student.setGender(studentAddDTO.getGender());
        student.setAvatar(studentAddDTO.getAvatar());
        student.setEnabled(true);
        student.setBirthday(studentAddDTO.getBirthday());
        student.setCreateTime(LocalDateTime.now());
        STUDENT_DATA.put(student.getId(), student);
    }

    // 5. 修改学生
    public void updateStudent(Long id, StudentUpdateDTO studentUpdateDTO) {
        Student student = STUDENT_DATA.get(id);
        if (student == null) {
            throw new RuntimeException("学生不存在，ID=" + id);
        }
        if (studentUpdateDTO.getName() != null) {
            student.setName(studentUpdateDTO.getName());
        }
        if (studentUpdateDTO.getMobile() != null) {
            student.setMobile(studentUpdateDTO.getMobile());
        }
        if (studentUpdateDTO.getAvatar() != null) {
            student.setAvatar(studentUpdateDTO.getAvatar());
        }
    }

    // 6. 删除学生
    public void deleteStudent(Long id) {
        STUDENT_DATA.remove(id);
    }
}