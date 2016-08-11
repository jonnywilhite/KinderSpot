package com.ex.service;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.InputStream;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;
import java.util.Properties;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.profile.ProfileCredentialsProvider;
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


import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

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
	public List<Student> getAllStudents(){
		return studentRepo.findAll();
	}
	
	@Override
	public Student getStudentById(int studentId) {
		return studentRepo.findOne(studentId);
	}
	
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
	
	
	
	@Override
	public User findById(int id) {
		return userRepo.findById(id);
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
	
	@Override
	public ReportCard getReportCardByStudent(int studentId) {
		Student student = studentRepo.findOne(studentId);
		return reportCardRepo.findByStudent(student);
	}
	
	

	//Event stuff
	 	@Override
	 	public List<Event> getAllEvents() {
    		
			return eventRepo.findAll();
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
				meeting.setTeacher(meeting.getTeacher());
				meeting.setReason(meeting.getReason());
				return meetingRepo.save(meeting);
			}
		
			@Override
			public List<Meetings> getAllMeetings() {
				return meetingRepo.findAll();
			}
		
			@Override
			public Meetings updateMeetingStatus(Meetings meeting, Boolean meetingStatus) {
				
				Meetings meetingStat = new Meetings ();
				
				meetingStat.settApprove(true);
				
		 		return meetingRepo.save(meetingStat);
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
		AWSCredentials credentials = new ProfileCredentialsProvider().getCredentials();
		System.out.println(credentials.getAWSAccessKeyId());
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

	

	
	//Email stuff
	@Override
	public void sendEmail(int senderId, int recipientId, String subject, String body) {
		// TODO send the email
		//String senderEmail = teacherRepo.findById(senderId).getEmail();
		String recipientEmail = teacherRepo.findById(recipientId).getEmail();
		final String uname = "kinderspotemail@gmail.com";
	       final String pword = "Kinderspot1234";

	       Properties props = new Properties();
	       props.put("mail.smtp.starttls.enable", "true");
	       props.put("mail.smtp.auth", "true");
	       props.put("mail.smtp.host", "smtp.gmail.com");
	       props.put("mail.smtp.port", "587");

	       Session session = Session.getInstance(props,
	         new javax.mail.Authenticator() {
	           protected PasswordAuthentication getPasswordAuthentication() {
	               return new PasswordAuthentication(uname, pword);
	           }
	         });

	       try {

	           Message message = new MimeMessage(session);
	           message.setFrom(new InternetAddress("randyma12@gmail.com"));
	           message.setRecipients(Message.RecipientType.TO,
	               InternetAddress.parse(recipientEmail));
	           message.setSubject(subject);
	           message.setText(body);

	           Transport.send(message);
	           System.out.println("Successfully sent email to " + recipientEmail);


	       } catch (MessagingException e) {
	           throw new RuntimeException(e);
	       }
	}


	@Override
	public void emailAllParents(int teacherId, String subject, String body) {
		List<Student> studentList = studentRepo.findByTeacherAndActiveTrue(teacherRepo.findById(teacherId));
		for (int i = 0; i < studentList.size(); i++){
		
		final String uname = "kinderspotemail@gmail.com";
	       final String pword = "Kinderspot1234";

	       Properties props = new Properties();
	       props.put("mail.smtp.starttls.enable", "true");
	       props.put("mail.smtp.auth", "true");
	       props.put("mail.smtp.host", "smtp.gmail.com");
	       props.put("mail.smtp.port", "587");

	       Session session = Session.getInstance(props,
	         new javax.mail.Authenticator() {
	           protected PasswordAuthentication getPasswordAuthentication() {
	               return new PasswordAuthentication(uname, pword);
	           }
	         });

	       try {

	           Message message = new MimeMessage(session);
	           message.setFrom(new InternetAddress("randyma12@gmail.com"));
	           message.setRecipients(Message.RecipientType.TO,
	               InternetAddress.parse(studentList.get(i).getParent().getEmail()));
	           message.setSubject(subject);
	           message.setText(body);

	           Transport.send(message);
	           System.out.println("Successfully sent email to "+ studentList.get(i).getParent().getEmail());


	       } catch (MessagingException e) {
	           throw new RuntimeException(e);
	       }
		}
		
	}
	
}
