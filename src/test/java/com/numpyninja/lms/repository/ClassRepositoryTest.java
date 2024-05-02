package com.numpyninja.lms.repository;

import com.numpyninja.lms.entity.*;
import com.numpyninja.lms.entity.Class;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;


@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@DataJpaTest
public class ClassRepositoryTest {

    @MockBean
    private ClassRepository mockClassRepository;

    @Mock
    private List<Class> mockClasses;

    @Mock
    TestEntityManager entManger;

    @BeforeEach
    public void setup() {
        this.mockClasses = setMockClasses();
    }

    private List<Class> setMockClasses() {
        List<Class>  mockClassList = new ArrayList<Class>();

        String sDate = "05/25/2022";
        Date classDate = null;
        try {
            classDate = new SimpleDateFormat("dd/mm/yyyy").parse(sDate);
        } catch (ParseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        LocalDateTime now = LocalDateTime.now();
        Timestamp timestamp = Timestamp.valueOf(now);

        User staffInClass = new User("U01", "Steve", "Jobs", "", (long) 1234567890, "CA", "PST", "@stevejobs",
                "", "", "", "Citizen", timestamp, timestamp);

        Program program = new Program((long) 7, "Django", "new Prog", "nonActive", timestamp, timestamp);
        Batch batchInClass = new Batch(35, "SDET 1", "SDET Batch 1", "Active", program, 5, timestamp, timestamp);


        Class aClass = new Class((long) 1, batchInClass, 1, classDate,
                "Selenium","Active", staffInClass, "Selenium Class", "OK",
                "c:/ClassNotes",
                "c:/RecordingPath", timestamp, timestamp);

        Class aClass1 = new Class((long) 1, batchInClass, 1, classDate,
                "Selenium","Active", staffInClass, "Selenium Class", "OK",
                "c:/ClassNotes",
                "c:/RecordingPath", timestamp, timestamp);

        mockClassList.add(aClass);
        return mockClassList;
    }

    @DisplayName("test -  get class by classTopic")
    @Test
    public void testFindByClassTopic() {
        //given
        List<Class> expectedClassTopic = this.mockClasses;
        //when
        when(mockClassRepository.findByClassTopicContainingIgnoreCaseOrderByClassTopicAsc("Selenium")).thenReturn(expectedClassTopic);
        //then
        assertThat(expectedClassTopic).isNotNull();
        assertThat(expectedClassTopic).size().isGreaterThan(0);
    }

    @DisplayName("test - get class by Class Id and Batch Id ")
    @Test
    public void testFindByClassIdAndBatchId(){
        List<Class> expectedClass = this.mockClasses;
        //when
        when(mockClassRepository.findByClassIdAndBatchId( 1L, 35)).thenReturn(expectedClass);
        //then
        assertThat(expectedClass).isNotNull();
        assertThat(expectedClass).size().isGreaterThan(0);

    }

    @DisplayName("test - get class by Batch Id ")
    @Test
    public void testFindByBatchInClass_batchId(){
        List<Class> expectedClassByBatch = this.mockClasses;
        //when
        when(mockClassRepository.findByBatchInClass_batchId(35)).thenReturn((expectedClassByBatch));
        //then
        assertThat(expectedClassByBatch).isNotNull();
        assertThat(expectedClassByBatch).size().isGreaterThan(0);
    }

    @DisplayName("test - get staff in class by User Id ")
    @Test
    public void testFindByStaffInClass_userId(){
        List<Class> expectedStaffByClass = this.mockClasses;
        //when
        when(mockClassRepository.findBystaffInClass_userId("U01")).thenReturn(expectedStaffByClass);
        //then
        assertThat(expectedStaffByClass).isNotNull();
        assertThat(expectedStaffByClass).size().isGreaterThan(0);

    }

}

