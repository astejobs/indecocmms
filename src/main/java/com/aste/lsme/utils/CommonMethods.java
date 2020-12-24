package com.aste.lsme.utils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.security.SecureRandom;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.HashMap;
import java.util.Map;

import javax.mail.internet.MimeMessage;

import org.apache.commons.lang3.StringUtils;
import org.gavaghan.geodesy.Ellipsoid;
import org.gavaghan.geodesy.GeodeticCalculator;
import org.gavaghan.geodesy.GlobalPosition;
import org.springframework.core.io.ClassPathResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;

import com.aste.lsme.domain.Constants;
import com.aste.lsme.domain.Constants.Type;
import com.aste.lsme.domain.GeoLocation;
import com.aste.lsme.domain.NotificationPayload;
import com.aste.lsme.webservicesDtos.FaultReportDto;
import com.aste.lsme.webservicesDtos.TaskDTO;
import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.messaging.BatchResponse;
import com.google.firebase.messaging.FirebaseMessaging;
import com.google.firebase.messaging.MulticastMessage;
import com.google.firebase.messaging.Notification;

public class CommonMethods {
	


	static final String AB = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
	static SecureRandom rnd = new SecureRandom();

	public static String randomString(String type ){
	   StringBuilder sb = new StringBuilder( 10 );
	   for( int i = 0; i < 10; i++ ) 
	      sb.append( AB.charAt( rnd.nextInt(AB.length()) ) );
	   return type+sb.toString();
	}
	
	public static String durationBetweenTwoDates(LocalDateTime startDate,LocalDateTime endDate){
		
		String difference;
		long days = startDate.until( endDate, ChronoUnit.DAYS );
		startDate = startDate.plusDays( days );
		
		
		long hours = startDate.until( endDate, ChronoUnit.HOURS );
		startDate = startDate.plusHours( hours );
		
		long minutes = startDate.until( endDate, ChronoUnit.MINUTES );
		startDate = startDate.plusMinutes( minutes );
		
		difference = StringUtils.leftPad(String.valueOf(days), 2,"0")+":"+
					 StringUtils.leftPad(String.valueOf(hours), 2,"0")+":"+
					 StringUtils.leftPad(String.valueOf(minutes), 2,"0");
		
		return difference;	
	}
	
	public static boolean saveFile(String file,String fileName,String path){
		byte[] bArray = Base64.decode(file,Base64.DEFAULT);

		File folder = new File(path);
		if (!folder.exists())
			folder.mkdirs();
		try {
			FileOutputStream fileOs = new FileOutputStream(Constants.PATH+fileName);
			fileOs.write(bArray);
			fileOs.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}
	
	public static void sendNotification(NotificationPayload notify){
		
		 try {
		    	/*Resource resource = ;
		    	 File initialFile = new File("/usr/google-services.json");
		 	    InputStream targetStream = new FileInputStream(initialFile);*/
	            FirebaseOptions options = FirebaseOptions.builder()
	                    .setCredentials(GoogleCredentials.fromStream(new ClassPathResource("google-services.json").getInputStream())).build();

	            if (FirebaseApp.getApps().isEmpty()) {
	                FirebaseApp.initializeApp(options);
	            } else {
	                FirebaseApp.getInstance();
	            }
	        } catch (IOException e) {
	           System.err.println("Create FirebaseApp Error"+ e.getMessage());
	        }
		    
		    Notification.Builder builder = Notification.builder();
		    builder.setTitle(notify.getTitle());
		    builder.setBody(notify.getBody());
		    
		    Map<String,String> data = new HashMap<>();
		    data.put("title",notify.getTitle());
		    data.put("message",notify.getBody());
		    data.put("click_action",notify.getActivity());
		    data.put("workspace", notify.getWorkspace());
		    data.put("id", notify.getId());
		    Notification notification =builder.build();
		 		    
		    try {
		    MulticastMessage message = MulticastMessage.builder().setNotification(notification).putAllData(data)
		    	    .addAllTokens(notify.getDevices())
		    	    .build();
		    	BatchResponse response = FirebaseMessaging.getInstance().sendMulticast(message);
		    	// See the BatchResponse reference documentation
		    	// for the contents of response.
		    	System.out.println(response.getSuccessCount() + " messages were sent successfully");
		    }
		    catch(Exception e) {
		    	 System.err.println("exception" + e.getMessage());		    
		    }
			
	}
	
	public static void sendEmail(JavaMailSender mailSender,String message,String email,String subject){
		MimeMessage msg = mailSender.createMimeMessage();
		try {
			MimeMessageHelper helper = new MimeMessageHelper(msg, false, "utf-8");
			msg.setContent(message, "text/html");
			helper.setTo(email);
			helper.setSubject(subject);
			helper.setFrom("<info@stie.com.sg>");
			mailSender.send(msg);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}
	
	public static double getDistance(GeoLocation pointLocation,GeoLocation userLocation){
		GeodeticCalculator geoCalc = new GeodeticCalculator();
		Ellipsoid reference = Ellipsoid.WGS84;
		GlobalPosition pointA = new GlobalPosition(pointLocation.getLatitude(), pointLocation.getLongitude(), 0.0);
		GlobalPosition userPos = new GlobalPosition(userLocation.getLatitude(), userLocation.getLongitude(), 0.0);
		double distance = geoCalc.calculateGeodeticCurve(reference, userPos, pointA).getEllipsoidalDistance();
		return distance;
	}
	
	
	public static NotificationPayload generatefaultRepNotification(Type type,FaultReportDto fr,String w,String username){
		NotificationPayload nf=new NotificationPayload();
		String title,text;
	
		switch (type) {
		case FAULT_CREATED:
			title="Fault Report is created";
			  text="Fault Report is created with Fault Report Id :"+" "+fr.getFrId() +" " +"in building" +" " +fr.getBuilding().getName() + " " +"& location"+" " + fr.getLocation().getName();				
			 
			 nf =new NotificationPayload(title, text,Constants.FR_NOTIFICATION_CLICK_ACTIVITY,w,fr.getFrId());
			break;

		case FAULT_COMPLETED:
			 title="Fault Report has been Completed";
			  text="Fault Report is completed with Fault Report Id :"+" "+fr.getFrId() +" " +"in building" +" " +fr.getBuilding().getName() + " " +"& location"+" " + fr.getLocation().getName();				
 
			 nf =new NotificationPayload(title, text,Constants.FR_NOTIFICATION_CLICK_ACTIVITY,w,fr.getFrId());
			break;

		case FAULT_PAUSED:
			 title="Fault Report is Paused";
			 text="Fault Report is Paused with Fault Report Id :"+" "+fr.getFrId() +" " +"in building" +" " +fr.getBuilding().getName() + " " +"& location"+" " + fr.getLocation().getName();				

			 
			 nf =new NotificationPayload(title, text,Constants.FR_NOTIFICATION_CLICK_ACTIVITY,w,fr.getFrId());
			break;

		case FAULT_REOPENED:
			 title="Fault Report is Re-Opened";
			  text="Fault Report is Re-Opened with Fault Report Id :"+" "+fr.getFrId() +" " +"in building" +" " +fr.getBuilding().getName() + " " +"& location"+" " + fr.getLocation().getName();				
 
			 nf =new NotificationPayload(title, text,Constants.FR_NOTIFICATION_CLICK_ACTIVITY,w,fr.getFrId());
			break;

		case FAULT_PAUSE_REQUESTED:
			 title= username+" "+"has requested for EOT";
			 text="Request for EOT on Fault Report Id :"+" "+fr.getFrId() +" " +"in building" +" " +fr.getBuilding().getName() + " " +"& location"+" " + fr.getLocation().getName();				
			 
			 nf =new NotificationPayload(title, text,Constants.FR_NOTIFICATION_CLICK_ACTIVITY,w,fr.getFrId());
			break;

		case EOT_ACCEPTED:
			    title= " EOT has been Accepted";
				 text=" EOT is Accepted for Fault Report Id :"+" "+fr.getFrId() +" " +"in building" +" " +fr.getBuilding().getName() + " " +"& location"+" " + fr.getLocation().getName();				
				 
			 nf =new NotificationPayload(title, text,Constants.FR_NOTIFICATION_CLICK_ACTIVITY,w,fr.getFrId());
			break;
		case EOT_REJECTED:
		      title= " EOT has been Rejected";
			  text="EOT is Rejected for Fault Report Id :"+" "+fr.getFrId() +" " +"in building" +" " +fr.getBuilding().getName() + " " +"& location"+" " + fr.getLocation().getName();				
		
			 nf =new NotificationPayload(title, text,Constants.FR_NOTIFICATION_CLICK_ACTIVITY,w,fr.getFrId());
			break;
		case QUOTATION_UPLOADED:
			 title="Quotation is saved";
			  text="Quotation is saved for Fault Report Id :"+" "+fr.getFrId() +" " +"in building" +" " +fr.getBuilding().getName() + " " +"& location"+" " + fr.getLocation().getName();				
			 nf =new NotificationPayload(title, text,Constants.FR_NOTIFICATION_CLICK_ACTIVITY,w,fr.getFrId());
			break;
			

		default:
			nf=null;
			break;
		}
		
		System.out.println();
		
		return nf;
	}

	public static NotificationPayload generateTaskNotification(Type type,TaskDTO taskDto,String w){
		NotificationPayload nf=new NotificationPayload();
		String title,text;
	
		switch (type) {
		case TASK_COMPLETED:
			 title="Task Completed";
			 text="Task is Completed with Task Id :"+" "+taskDto.getTask_number() +" " +"in building" +" " +taskDto.getBuildingName() + " " +"& location"+" " + taskDto.getLocationName();				
			 nf =new NotificationPayload(title, text,Constants.FR_NOTIFICATION_CLICK_ACTIVITY,w,String.valueOf(taskDto.getTaskId()));
		   	 break;

		case TASK_REOPENED:
			 title="Task has been Opened";
			  text="Task is Opened with Task Id :"+" "+taskDto.getTask_number() +" " +"in building" +" " +taskDto.getBuildingName() + " " +"& location"+" " + taskDto.getLocationName();				
			  nf =new NotificationPayload(title, text,Constants.FR_NOTIFICATION_CLICK_ACTIVITY,w,String.valueOf(taskDto.getTaskId()));
			break;
	
		default:
			nf=null;
			break;
		}
		
		System.out.println();
		
		return nf;
	}
	
}
