package com.example.spring.controller;

import com.example.spring.model.Member;
import com.example.spring.model.Student;
import com.example.spring.model.Teacher;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@RestController
public class MemberController {

    public static List<Member> memberList = Arrays.asList(
            new Teacher("1", "Billy", "male", "數學", "教務主任"),
            new Teacher("2", "Heidi", "female", "英文", "教師"),
            new Student("3", "Jacky", "male", "301", "201910"),
            new Student("4", "Lawrence", "male", "801", "201812")
    );

    // 呼叫 all teacher 方法
    @GetMapping(value = "/rest/all-teacher", produces = "application/json; charset=utf-8")
    public List<Member> getAllTeachers(){
        List<Member> allTeacherList = new ArrayList<>();
        for (int i = 0; i < memberList.size(); i++) {
            if (memberList.get(i) instanceof Teacher) {
                allTeacherList.add(memberList.get(i));
            }
        }
        return allTeacherList;
    }

    // 呼叫 all student 方法
    @GetMapping(value = "/rest/all-student", produces = "application/json; charset=utf-8")
    public List<Member> getAllStudents(){
        List<Member> allStudentList = new ArrayList<>();
        for (int i = 0; i < memberList.size(); i++) {
            if (memberList.get(i) instanceof Student) {
                allStudentList.add(memberList.get(i));
            }
        }
        return allStudentList;
    }

    // 呼叫 id = 3 方法
    @GetMapping(value = "/rest/student", produces = "application/json; charset=utf-8")
    public List<Member> getStudentById(String id) {
        List<Member> idStudent = new ArrayList<>();
        for (int i = 0; i < memberList.size(); i++) {
            if (i == Integer.parseInt(id)) {
                idStudent.add(memberList.get(i-1));
            }
        }
        return idStudent;
    }

}
