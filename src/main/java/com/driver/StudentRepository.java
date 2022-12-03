package com.driver;

import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

@Repository
public class StudentRepository {
    private HashMap<String, Student> studentMap;
    private HashMap<String, Teacher> teacherMap;
    private HashMap<String, List<String>> studentTeacherMapping;

    public StudentRepository() {
        this.studentMap = new HashMap<String, Student>();
        this.teacherMap = new HashMap<String, Teacher>();
        this.studentTeacherMapping = new HashMap<String, List<String>>();
    }
    public void saveStudent(Student student){
        studentMap.put(student.getName(), student);
    }
    public void saveTeacher(Teacher teacher){
        teacherMap.put(teacher.getName(),teacher);
    }
    public void saveStudentTeacherPair(String student, String teacher){
        if(studentMap.containsKey(student)&&teacherMap.containsKey(teacher)){
            studentMap.put(student, studentMap.get(student));
            teacherMap.put(teacher, teacherMap.get(teacher));
            List<String> students = new ArrayList<String>();
            if(studentTeacherMapping.containsKey(teacher)) students = studentTeacherMapping.get(teacher);
            students.add(student);
            studentTeacherMapping.put(teacher,students);
        }
    }
    public Student findStudentByName(String student){
        return studentMap.get(student);
    }
    public Teacher findTeacherByName(String teacher){
        return teacherMap.get(teacher);
    }
    public List<String> findStudentsOfTeacher(String teacher){
        List<String> students = new ArrayList<String>();
        if(studentTeacherMapping.containsKey(teacher)) students = studentTeacherMapping.get(teacher);
        return students;
    }
    public List<String> findAllStudents(){
        return new ArrayList<String>(studentMap.keySet());
    }
    public void removeTeacher(String teacher){
        List<String> students = new ArrayList<String>();
        if(studentTeacherMapping.containsKey(teacher)){
            students = studentTeacherMapping.get(teacher);
            for (String student: students){
                if(studentMap.containsKey(student)) studentMap.remove(student);
            }
            studentTeacherMapping.remove(teacher);
        }
        if(teacherMap.containsKey(teacher)) teacherMap.remove(teacher);
    }
    public void removeAllTeachers(){
        HashSet<String> students = new HashSet<String>();
        HashSet<String> teachers = new HashSet<String>();
        for(String teacher: studentTeacherMapping.keySet()){
            teachers.add(teacher);
            for(String student: studentTeacherMapping.get(teacher)){
                if(studentMap.containsKey(student)) studentMap.remove(student);
            }
            if(teacherMap.containsKey(teacher)) teacherMap.remove(teacher);
        }
        for(String teacher: teachers) studentTeacherMapping.remove(teacher);
    }
}