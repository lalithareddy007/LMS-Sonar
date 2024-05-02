package com.numpyninja.lms.repository;

import com.numpyninja.lms.entity.Attendance;
import com.numpyninja.lms.entity.Class;
import com.numpyninja.lms.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface AttendanceRepository extends JpaRepository<Attendance, Long>{
	//Get Attendance by Student
	//List<Attendance> findByUser(User user);
	List<Attendance> findByuser_userId(String studentId);
	//List<Attendance> findByClass(Integer cs);
	List<Attendance> findByobjClass_csId(Long csId);
	List<Attendance> findByobjClass_csIdIn(List<Long> csIds);
	List<Attendance> findByObjClassAndUser(Class objClass, User user);
	List<Attendance> findByobjClass_csIdAndUser_userIdAndAttendanceDate(Long classId, String userId, java.time.LocalDate attendanceDate);

	//Optional<Attendance> findByObjClassAndUser(Class objClass, User user);
	//List<Attendance> findByUserAndObjClass(String userid, Long csid);
	//Optional <Attendance> findByuserAndObjClass(User userid, Class csid);
	//Optional <Attendance> existByuserAndObjClass(User userid, Class csid);
	//List <Attendance> findByCsidAndStudentId(Long csid,String StudentId);
}
