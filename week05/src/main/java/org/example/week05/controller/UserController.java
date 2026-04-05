package org.example.week05.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import org.example.week05.entity.User;
import org.example.week05.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/list")
    public List<User> list() {
        return userService.list();
    }

    @GetMapping("/page")
    public IPage<User> page(
            @RequestParam(defaultValue = "1") int page,
            @RequestParam(defaultValue = "5") int size) {
        return userService.pageList(page, size);
    }

    @GetMapping("/{id}")
    public User getById(@PathVariable Integer id) {
        return userService.getById(id);
    }

    @PostMapping
    public String add(@RequestBody User user) {
        userService.save(user);
        return "添加成功";
    }

    @PutMapping
    public String update(@RequestBody User user) {
        userService.updateById(user);
        return "修改成功";
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable Integer id) {
        userService.removeById(id);
        return "删除成功";
    }

    // 事务成功
    @GetMapping("/test/tx-success")
    public String testTxSuccess() {
        User u1 = new User();
        u1.setUsername("tx-success-1");
        u1.setPassword("123");
        userService.save(u1);

        User u2 = new User();
        u2.setUsername("tx-success-2");
        u2.setPassword("123");
        userService.save(u2);
        return "事务成功：两条都插入";
    }

    // 事务失败回滚
    @Transactional
    @GetMapping("/test/tx-fail")
    public String testTxFail() {
        User u1 = new User();
        u1.setUsername("tx-fail-1");
        u1.setPassword("123");
        userService.save(u1);

        // 模拟异常
        int i = 1 / 0;

        User u2 = new User();
        u2.setUsername("tx-fail-2");
        u2.setPassword("123");
        userService.save(u2);
        return "不会执行到这里";
    }
}