package com.ex.service;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.InputStream;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;

import com.ex.domain.Attendance;
import com.ex.domain.Event;
import com.ex.domain.Meetings;
import com.ex.domain.Photos;
import com.ex.domain.ReportCard;
import com.ex.domain.Student;
import com.ex.domain.User;
import com.ex.repo.AttendanceRepo;
import com.ex.repo.EventsRepo;
import com.ex.repo.MeetingRepo;
import com.ex.repo.PhotosRepo;
import com.ex.repo.ReportCardRepo;
import com.ex.repo.StudentRepo;
import com.ex.repo.TeacherRepo;
import com.ex.repo.UserRepo;

@Service
@Transactional
public class KinderServiceImpl implements KinderService {
	
	@Autowired
	private StudentRepo studentRepo;
	
	@Autowired
	private TeacherRepo teacherRepo;
	
	@Autowired
	private UserRepo userRepo;
	
	@Autowired
	private ReportCardRepo reportCardRepo;
	
	@Autowired
	private AttendanceRepo attendanceRepo;
	
	@Autowired
	private EventsRepo eventRepo;
	
	@Autowired
	private PhotosRepo photoRepo;
	
	@Autowired 
	private MeetingRepo meetingRepo;

	
	/*
	 * Student stuff(non-Javadoc)
	 */
	@Override
	public List<Student> getAllStudentsByTeacher(int teacherId) {
		User teacher = teacherRepo.findById(teacherId);
		return studentRepo.findByTeacherAndActiveTrue(teacher);
	}
	
	@Override
	public List<Student> deleteStudentsInClassByTeacher(int teacherId) {
		User teacher = teacherRepo.findById(teacherId);
		List<Student> students = studentRepo.findByTeacherAndActiveTrue(teacher);
		for (Student s : students) {
			s.setActive(false);
		}
		return students;
	}

	@Override
	public List<Student> deleteStudentsInClassByTeacher(int teacherId, int[] studentIds) {
		User teacher = teacherRepo.findById(teacherId);
		List<Student> students = studentRepo.findByTeacherAndActiveTrue(teacher);
		for (Student s : students) {
			for (int id : studentIds) {
				if (s.getId() == id) {
					s.setActive(false);
					break;
				}
			}
		}
		return students;
	}
	
	
	/*
	 * Login stuff(non-Javadoc)
	 */
	@Override
	public User authenticate(User user) {
		return userRepo.findByEmailAndPassword(user.getEmail(), user.getPassword());
	}
	
	
	
	/*
	 * ReportCard stuff
	 */
	@Override
	public ReportCard createReportCardEntry(ReportCard rc) {
		rc.setDate(new Timestamp(new Date().getTime()));
		return reportCardRepo.save(rc);
	}
	
	@Override
	public List<ReportCard> getAllReportCardsByTeacher(int teacherId) {
		User teacher = teacherRepo.findOne(teacherId);
		return reportCardRepo.findByTeacher(teacher);
	}

	
	

	//Event stuff
	 	@Override
	 	public Page<Event> getEventpage(Integer page, Integer size) {
			// TODO Auto-generated method stub
    		Pageable pageable =  new PageRequest(page, size);
			return eventRepo.findByNameOrderByDateDesc(pageable);
		}
	
		@Override
		public Event getEventByEventName(String name) {
			return eventRepo.findByName(name);
		}
	
		@Override
		public Event createEvent(Event event) {
			event.setDate(new Timestamp(new Date().getTime()));
			event.setDescription(event.getDescription());
			event.setName(event.getName());
			return eventRepo.save(event) ;
		}
		
		@Override
		public Event updateEvent(Event room, String eventName) {
	 		return null;
	 	}
	 
	 	@Override
	 	public Event deleteEvent(String name) {
	 		return null;
	 	}

	
	//Meeting Stuff 
	@Override
	public Meetings createMeeting(Meetings meeting) 
	{
				meeting.setDate(new Timestamp (new Date().getTime()));
				meeting.setReason(meeting.getReason());
				return meetingRepo.save(meeting);
			}
		
			@Override
			public Meetings getMeetingByDate(Timestamp date) {
				return meetingRepo.findByDate(date);
			}
		
			@Override
			public Meetings updateMeetingStatus(Meetings meeting, Boolean meetingStatus) {
		 		return null;
		 	}
	
	
	
	
	
	
	/*
	 * Attendance stuff
	 */
	@Override
	public Attendance submitAttendanceSheet(List<Student> absent) {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public List<Attendance> viewAttendanceSheets(int teacherId) {
		User teacher = teacherRepo.findOne(teacherId);
		return attendanceRepo.findByTeacher(teacher);
	}
	
	
	/*
	 * Photos stuff
	 */
	@Override
	public Photos uploadPhoto(Photos photo, File file) {
		AWSCredentials credentials = new BasicAWSCredentials("AKIAIBXAYMNGWRPDSOAA", "RjgMDOb9UAu83UVcpXqaAdgqIuIG6B98UiiGXDUS");
		AmazonS3 client = new AmazonS3Client(credentials);
		String bucketName = "jonathan-gary-lee-wilhite-bucket-this-name-better-not-be-taken";
		String folderName = "testfolder";
		String SUFFIX = "/";
		
		//client.createBucket(bucketName);
		//createFolder(bucketName, folderName, client);
		photo.setPhoto(folderName + SUFFIX + file.getName());
		
		String fileName = photo.getPhoto();
		client.putObject(new PutObjectRequest(bucketName, fileName, file));
		
		return photoRepo.save(photo);
	}
	
	@Override
	public List<Photos> getAllPhotos() {
		return photoRepo.findAll();
	}
	
	@Override
	public List<Photos> getPhotosByEvent(int eventId) {
		Event event = eventRepo.findOne(eventId);
		return photoRepo.findByEvent(event);
	}
	
	public static void createFolder(String bucketName, String folderName, AmazonS3 client) {
		// create meta-data for your folder and set content-length to 0
		ObjectMetadata metadata = new ObjectMetadata();
		metadata.setContentLength(0);
		
		// create empty content
		InputStream emptyContent = new ByteArrayInputStream(new byte[0]);
		
		// create a PutObjectRequest passing the folder name suffixed by /
		PutObjectRequest putObjectRequest = new PutObjectRequest(bucketName, folderName + "/", emptyContent, metadata);
		
		// send request to S3 to create folder
		client.putObject(putObjectRequest);
	}

}
