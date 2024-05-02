package com.numpyninja.lms.mappers;

import com.numpyninja.lms.dto.AttendanceDto;
import com.numpyninja.lms.entity.Attendance;
import com.numpyninja.lms.entity.Class;
import com.numpyninja.lms.entity.User;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-05-02T10:21:19-0400",
    comments = "version: 1.4.2.Final, compiler: javac, environment: Java 17.0.8 (Oracle Corporation)"
)
@Component
public class AttendanceMapperImpl implements AttendanceMapper {

    @Override
    public AttendanceDto toAttendanceDto(Attendance attendance) {
        if ( attendance == null ) {
            return null;
        }

        AttendanceDto attendanceDto = new AttendanceDto();

        attendanceDto.setCsId( attendanceObjClassCsId( attendance ) );
        attendanceDto.setStudentId( attendanceUserUserId( attendance ) );
        attendanceDto.setAttId( attendance.getAttId() );
        attendanceDto.setAttendance( attendance.getAttendance() );
        attendanceDto.setCreationTime( attendance.getCreationTime() );
        attendanceDto.setLastModTime( attendance.getLastModTime() );
        attendanceDto.setAttendanceDate( attendance.getAttendanceDate() );

        return attendanceDto;
    }

    @Override
    public Attendance toAttendance(AttendanceDto attendanceDto) {
        if ( attendanceDto == null ) {
            return null;
        }

        Attendance attendance = new Attendance();

        attendance.setObjClass( attendanceDtoToClass( attendanceDto ) );
        attendance.setUser( attendanceDtoToUser( attendanceDto ) );
        attendance.setAttId( attendanceDto.getAttId() );
        attendance.setAttendance( attendanceDto.getAttendance() );
        attendance.setCreationTime( attendanceDto.getCreationTime() );
        attendance.setLastModTime( attendanceDto.getLastModTime() );
        attendance.setAttendanceDate( attendanceDto.getAttendanceDate() );

        return attendance;
    }

    @Override
    public List<AttendanceDto> toAttendanceDtoList(List<Attendance> attendances) {
        if ( attendances == null ) {
            return null;
        }

        List<AttendanceDto> list = new ArrayList<AttendanceDto>( attendances.size() );
        for ( Attendance attendance : attendances ) {
            list.add( toAttendanceDto( attendance ) );
        }

        return list;
    }

    @Override
    public List<Attendance> toAttendanceList(List<AttendanceDto> AttendanceDtos) {
        if ( AttendanceDtos == null ) {
            return null;
        }

        List<Attendance> list = new ArrayList<Attendance>( AttendanceDtos.size() );
        for ( AttendanceDto attendanceDto : AttendanceDtos ) {
            list.add( toAttendance( attendanceDto ) );
        }

        return list;
    }

    private Long attendanceObjClassCsId(Attendance attendance) {
        if ( attendance == null ) {
            return null;
        }
        Class objClass = attendance.getObjClass();
        if ( objClass == null ) {
            return null;
        }
        Long csId = objClass.getCsId();
        if ( csId == null ) {
            return null;
        }
        return csId;
    }

    private String attendanceUserUserId(Attendance attendance) {
        if ( attendance == null ) {
            return null;
        }
        User user = attendance.getUser();
        if ( user == null ) {
            return null;
        }
        String userId = user.getUserId();
        if ( userId == null ) {
            return null;
        }
        return userId;
    }

    protected Class attendanceDtoToClass(AttendanceDto attendanceDto) {
        if ( attendanceDto == null ) {
            return null;
        }

        Class class1 = new Class();

        class1.setCsId( attendanceDto.getCsId() );

        return class1;
    }

    protected User attendanceDtoToUser(AttendanceDto attendanceDto) {
        if ( attendanceDto == null ) {
            return null;
        }

        User user = new User();

        user.setUserId( attendanceDto.getStudentId() );

        return user;
    }
}
