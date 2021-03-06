package com.ex.service;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.InputStream;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

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
import com.ex.domain.AttendanceStudent;
import com.ex.domain.Badge;
import com.ex.domain.BadgeStudent;
import com.ex.domain.Event;
import com.ex.domain.EventStudent;
import com.ex.domain.EventType;
import com.ex.domain.Meetings;
import com.ex.domain.Photos;
import com.ex.domain.ReportCard;
import com.ex.domain.Student;
import com.ex.domain.User;
import com.ex.repo.AttendanceRepo;
import com.ex.repo.AttendanceStudentRepo;
import com.ex.repo.BadgeRepo;
import com.ex.repo.BadgeStudentRepo;
import com.ex.repo.EventStudentRepo;
import com.ex.repo.EventTypeRepo;
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
	private EventStudentRepo eventStudentRepo;

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

	@Autowired
	private BadgeStudentRepo badgeStudentRepo;
	
	@Autowired
	private BadgeRepo badgeRepo;

	@Autowired
	private AttendanceStudentRepo attendanceStudentRepo;
	
	@Autowired
	private EventTypeRepo eventTypeRepo;


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
	public List<Student> getStudentByParent(int parentId) {
		User parent = userRepo.findById(parentId);
		return studentRepo.findByParentAndActiveTrue(parent);
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
	
	@Override
	public ReportCard updateReportCardByStudent(int studentId, ReportCard rc) {
		Student student = studentRepo.findOne(studentId);
		ReportCard reportCard = reportCardRepo.findByStudent(student);
		reportCard.setGrade(rc.getGrade());
		reportCard.setComments(rc.getComments());
		return reportCard;
	}



	//Event stuff

	 	@Override
	 	public List<Event> getAllEvents() {
    		
			return eventRepo.findAll();
		}
	
		@Override
		public List<EventType> getAllTypes() {
			return eventTypeRepo.findAll();
		}
	
		@Override
		public Event createEvent(Event event, String name) {
			
			event.setDate(event.getDate());
			event.setEventType(event.getEventType());
			event.setDescription(event.getDescription());
			event.setName(name);
			return eventRepo.save(event) ;
		}
		
		
		@Override
		public List<Event> getStudentEvents(int studentId) {
			
			Student student = studentRepo.findOne(studentId);
			
			List<EventStudent> esList = eventStudentRepo.findByStudent(student);
			List<Event> eList = new ArrayList<Event>();
			
			for(EventStudent es: esList)
			{
				eList.add(es.getEvent());
			}
			
			return eList;
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
	public Meetings createMeeting(Meetings meeting, int id, User teacher) 
	{			
		User parent = userRepo.findById(id);
		User t = userRepo.findById(teacher.getId());

		meeting.setParent(parent);
		meeting.setTeacher(t);
		
		String subject = "Meeting Scheduled";
		String body = "You now have a meeting scheduled with " + t.getFirstName() + " " + t.getLastName() + ".\n\n"
				+ "Date: " + meeting.getDate() + "\n" 
				+ "Reason: " + meeting.getReason() + "\n\n"
				+ "Thank you,";
		
		sendEmail(teacher.getId(), id, subject, body);
		
		return meetingRepo.save(meeting);
	}

	
	//Meeting Stuff 
	@Override
	public List<Meetings> getAllMeetings(int teachId) {
		
		User teacher = new User ();
		teacher.setId(teachId);
				
		return meetingRepo.findByTeacher(teacher);
		//return meetingRepo.findByTeacher(teacher);
	}
	
	/*Gets meetings specific to a parent. I couldn't figure out how to use the Repo to get this, so I'm 
	  just doing this instead.*/
	@Override
	public List<Meetings> getMeetingsByParent(int parentId) 
	{
		List<Meetings> mList = meetingRepo.findAll();
		List<Meetings> returnList = new ArrayList<Meetings>();
		
		for(Meetings m: mList)
		{
			if(m.getParent().getId() == parentId)
				returnList.add(m);
		}

		return returnList;
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
	public Attendance submitAttendanceSheet(List<AttendanceStudent> attendance, int teacherId) {
		
		Timestamp timestamp = new Timestamp(new Date().getTime());
		
		Calendar cal = Calendar.getInstance();
		cal.setTime(timestamp);
		cal.set(Calendar.HOUR_OF_DAY, cal.getMinimum(Calendar.HOUR_OF_DAY));
	    cal.set(Calendar.MINUTE, cal.getMinimum(Calendar.MINUTE));
	    cal.set(Calendar.SECOND, cal.getMinimum(Calendar.SECOND));
	    cal.set(Calendar.MILLISECOND, cal.getMinimum(Calendar.MILLISECOND));
		
		Attendance attendanceSheet = new Attendance();
		attendanceSheet.setDate(new Timestamp(cal.getTime().getTime()));
		attendanceSheet.setTeacher(teacherRepo.findById(teacherId));
		attendanceRepo.save(attendanceSheet);

		for (AttendanceStudent as : attendance) {
			as.setStudent(studentRepo.findOne(as.getStudent().getId()));
			as.setAttendance(attendanceSheet);
			attendanceStudentRepo.save(as);
		}
		return null;
	}

	@Override
	public List<Attendance> viewAttendanceSheets(int teacherId) {
		User teacher = teacherRepo.findOne(teacherId);
		
		return attendanceRepo.findByTeacherOrderByDate(teacher);
	}

	@Override
	public List<AttendanceStudent> viewAttendanceEntriesByStudent(int studentId) {
		Student student = studentRepo.findOne(studentId);
		List<AttendanceStudent> list = attendanceStudentRepo.findByStudentOrderByAttendance(student);
		Collections.sort(list);
		
		return list;
	}

	@Override
	public List<AttendanceStudent> viewAttendanceSheetForDate(int teacherId, Date date) {
		User teacher = teacherRepo.findOne(teacherId);
		
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.set(Calendar.HOUR_OF_DAY, cal.getMinimum(Calendar.HOUR_OF_DAY));
	    cal.set(Calendar.MINUTE, cal.getMinimum(Calendar.MINUTE));
	    cal.set(Calendar.SECOND, cal.getMinimum(Calendar.SECOND));
	    cal.set(Calendar.MILLISECOND, cal.getMinimum(Calendar.MILLISECOND));
	    
	    Attendance a = attendanceRepo.findByTeacherAndDate(teacher, cal.getTime());
		return attendanceStudentRepo.findByAttendance(a);
	}
	
	@Override
	public Attendance updateAttendanceSheetForDate(int teacherId, Date date, List<AttendanceStudent> attendanceSheet) {
		List<AttendanceStudent> oldList = viewAttendanceSheetForDate(teacherId, date);
		Attendance oldAtt = oldList.get(0).getAttendance();
		for (AttendanceStudent as : oldList) {
			attendanceStudentRepo.delete(as);
		}
		attendanceRepo.delete(oldAtt);
		return submitAttendanceSheet(attendanceSheet, teacherId);
	}
	
	/*
	 * Photos stuff
	 */
	@Override
	public Photos uploadPhoto(File file, int eventId) {
		AWSCredentials credentials = new ProfileCredentialsProvider().getCredentials();
		AmazonS3 client = new AmazonS3Client(credentials);
		String bucketName = "kinderspot-photos";
		String folderName = "class-photos";
		String SUFFIX = "/";

		//client.createBucket(bucketName);
		//createFolder(bucketName, folderName, client);
		Event event = eventRepo.findOne(eventId);
		Photos photo = new Photos();
		photo.setEvent(event);
		photo.setPhoto("https://s3.amazonaws.com/" + bucketName + "/" + folderName + SUFFIX + file.getName());

		String fileName = folderName + SUFFIX + file.getName();
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
		String recipientEmail = userRepo.findById(recipientId).getEmail();
		final String uname = "kinderspotemail@gmail.com";
		final String pword = "Kinderspot1234";

		String emailHeader = "Hello " + userRepo.findById(recipientId).getFirstName() + ",\n\n";
		String emailSignature = "\n\n" + userRepo.findById(senderId).getFirstName();
		body = emailHeader + body + emailSignature;

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

			String emailHeader = "To all parents,\n\n";
			String emailSignature = "\n\n" + userRepo.findById(teacherId).getFirstName();
			body = emailHeader + body + emailSignature;

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


			} catch (MessagingException e) {
				throw new RuntimeException(e);
			}
		}

	}



	//Badge Stuff
	@Override
	public List<Badge> getBadgesByStudent(int studentId) {
		List<BadgeStudent> list = badgeStudentRepo.findByStudent(studentRepo.findOne(studentId));
		List<Badge> badgeList = new ArrayList<>();
		for (int i = 0; i < list.size(); i++){
			badgeList.add(list.get(i).getBadge());
		}

		return badgeList;
	}


	@Override
	public void assignBadgeToStudent(int studentId, Badge b) {
		Student s = studentRepo.findOne(studentId);
		badgeStudentRepo.save(new BadgeStudent(b, s));
	}


	@Override
	public List<Badge> getAllBadges(){
		return badgeRepo.findAll();
	}


}
