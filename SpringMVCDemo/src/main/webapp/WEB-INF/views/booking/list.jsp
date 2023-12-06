<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>    
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>Booking List</title>
		<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/purecss@3.0.0/build/pure-min.css" />
	</head>
	<body>
		Booking List
		<table class="pure-table pure-table-bordered">
			<thead>
				<tr><th>bookingId</th><th>roomId</th><th>name</th><th>date</th></tr>
			</thead>
			<tbody>
				<c:forEach var="booking" items="${ bookings }">
					<tr>
						 <td>
							<a href="javascript:void(0)" title="按我一下取消" 
							onClick="window.top.resultFrame.location.href='/SpringMVCDemo/mvc/booking/cancelBooking/${ booking.bookingId }'"
							>
							cancel
							</a>
						</td>
						<td>${ booking.bookingId }</td>
						<td>${ booking.roomId }</td>
						<td>
							<input type="text" id="name" name="name" value="${ booking.name }" size="10">
							<button type="button" onclick="Location.href='/SpringMVCDemo/mvc/booking/cancelBooking/${ booking.bookingId }/updateName?name='+ document.getElementById('name').value'">更名</button>
						</td>
						<td>${ booking.date }</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
	</body>
</html>