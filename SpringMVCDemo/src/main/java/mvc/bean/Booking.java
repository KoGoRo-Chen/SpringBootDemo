package mvc.bean;

import java.util.Date;
import java.util.concurrent.atomic.AtomicInteger;

public class Booking {
	private AtomicInteger bookingIdCount = new AtomicInteger(0);
	private int roomId;
	private String name;
	private Date meetdate;
	
	public Booking(int roomId, String name, Date meetdate) { 
		this.roomId = roomId;
		this.name = name;
		this.meetdate = meetdate;
	}

	public int getRoomId() {
		return roomId;
	}

	public void setRoomId(int roomId) {
		this.roomId = roomId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Date getMeetdate() {
		return meetdate;
	}

	public void setMeetdate(Date meetdate) {
		this.meetdate = meetdate;
	}
	
	

}
