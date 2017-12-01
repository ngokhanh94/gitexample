package poly.entity;

import java.util.Date;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name="Records")
public class Records {
@Id 
@GeneratedValue
private Integer id;
private Boolean type;
private String reason;
@Temporal(TemporalType.DATE)
@DateTimeFormat(pattern = "MM/dd/yyyy")
private Date date;
@ManyToOne
@JoinColumn(name="StaffId")
private Staffs staff;
public Integer getId() {
	return id;
}
public void setId(Integer id) {
	this.id = id;
}
public Boolean getType() {
	return type;
}
public void setType(Boolean type) {
	this.type = type;
}
public String getReason() {
	return reason;
}
public void setReason(String reason) {
	this.reason = reason;
}
public Date getDate() {
	return date;
}
public void setDate(Date date) {
	this.date = date;
}
public Staffs getStaff() {
	return staff;
}
public void setStaff(Staffs staff) {
	this.staff = staff;
}

}
