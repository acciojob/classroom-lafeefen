package com.driver;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentService {
    @Autowired
    StudentRepository studentRepository;
    public void addStudent(Student student){
        studentRepository.saveStudent(student);
    }
    public void addTeacher(Teacher teacher){
        studentRepository.saveTeacher(teacher);
    }
   public void addStudentTeacherPair(String student, String teacher){
        studentRepository.saveStudentTeacherPair(student, teacher);
   }
   public Student getStudentByName(String student){
        return studentRepository.findStudentByName(student);
   }
   public Teacher getTeacherByName(String teacher){
        return studentRepository.findTeacherByName(teacher);
   }
   public List<String> getStudentsByTeacherName(String teacher){
        return studentRepository.findStudentsOfTeacher(teacher);
   }
   public List<String> getAllStudents(){
        return studentRepository.findAllStudents();
   }
   public void deleteTeacherByName(String teacher){
        studentRepository.removeTeacher(teacher);
   }
    public void deleteAllTeachers(){
        studentRepository.removeAllTeachers();
    }
}