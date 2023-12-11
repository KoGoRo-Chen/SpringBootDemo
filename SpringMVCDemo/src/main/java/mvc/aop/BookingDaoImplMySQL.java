package mvc.aop;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import mvc.bean.BookingRoom;
import mvc.dao.BookingDao;

@Repository("bookingDao") //對外名字，預設bookingDaoImplMySQL
public class BookingDaoImplMySQL implements BookingDao{
	
	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Override
	public int addBookRoom(BookingRoom bookingRoom) {
		String sql = "insert into BookingRoom(roomId, username, bookingDate) values(?, ?, ?)";
		return jdbcTemplate.update(sql, bookingRoom.getRoomId(), bookingRoom.getUsername(), bookingRoom.getBookingDate());
	}

	@Override
	public int cancelBooking(Integer bookingId) {
		String sql = "delete from BookingRoom where bookingId = ?";
		return jdbcTemplate.update(sql, bookingId);
	}

	@Override
	public List<BookingRoom> findAllBookingRooms() {
		String sql = "select bookingId, roomId, username, bookingDate, createDate from BooingRoom order by bookingId";
		return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(BookingRoom.class));
	}

	@Override
	public int updateBookingUsername(Integer bookingId, String newUsername) {
		String sql = "update BookingRoom set username = ? where bookingId = ?";
		return jdbcTemplate.update(sql, newUsername, bookingId);
	}

}
