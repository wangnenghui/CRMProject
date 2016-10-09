package cn.mldn.crm.vo;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

@SuppressWarnings("serial")
public class Member implements Serializable {
	private String mid ;
	private String password ;
	private String tel ;
	private Date lastdate ;
	private String photo ;
	private Integer flag ;
	private Integer locked ;
	private List<Logs> logs ; 
	private List<News> newses ;
	private List<Task> tasks ;
	private List<MemberNews> memberNewses ;
	private List<Client> clients ;
	private Role role = new Role() ;
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((flag == null) ? 0 : flag.hashCode());
		result = prime * result
				+ ((lastdate == null) ? 0 : lastdate.hashCode());
		result = prime * result + ((locked == null) ? 0 : locked.hashCode());
		result = prime * result + ((mid == null) ? 0 : mid.hashCode());
		result = prime * result
				+ ((password == null) ? 0 : password.hashCode());
		result = prime * result + ((photo == null) ? 0 : photo.hashCode());
		result = prime * result + ((tel == null) ? 0 : tel.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Member other = (Member) obj;
		if (flag == null) {
			if (other.flag != null)
				return false;
		} else if (!flag.equals(other.flag))
			return false;
		if (lastdate == null) {
			if (other.lastdate != null)
				return false;
		} else if (!lastdate.equals(other.lastdate))
			return false;
		if (locked == null) {
			if (other.locked != null)
				return false;
		} else if (!locked.equals(other.locked))
			return false;
		if (mid == null) {
			if (other.mid != null)
				return false;
		} else if (!mid.equals(other.mid))
			return false;
		if (password == null) {
			if (other.password != null)
				return false;
		} else if (!password.equals(other.password))
			return false;
		if (photo == null) {
			if (other.photo != null)
				return false;
		} else if (!photo.equals(other.photo))
			return false;
		if (tel == null) {
			if (other.tel != null)
				return false;
		} else if (!tel.equals(other.tel))
			return false;
		return true;
	}
	public void setNewses(List<News> newses) {
		this.newses = newses;
	}
	public List<News> getNewses() {
		return newses;
	}
	public void setTasks(List<Task> tasks) {
		this.tasks = tasks;
	}
	public List<Task> getTasks() {
		return tasks;
	}
	public void setClients(List<Client> clients) {
		this.clients = clients;
	}
	public List<Client> getClients() {
		return clients;
	}
	public void setRole(Role role) {
		this.role = role;
	}
	public Role getRole() {
		return role;
	}
	public void setLogs(List<Logs> logs) {
		this.logs = logs;
	}
	public List<Logs> getLogs() {
		return logs;
	}
	public String getMid() {
		return mid;
	}
	public void setMid(String mid) {
		this.mid = mid;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getTel() {
		return tel;
	}
	public void setTel(String tel) {
		this.tel = tel;
	}
	public Date getLastdate() {
		return lastdate;
	}
	public void setLastdate(Date lastdate) {
		this.lastdate = lastdate;
	}
	public String getPhoto() {
		return photo;
	}
	public void setPhoto(String photo) {
		this.photo = photo;
	}
	public Integer getFlag() {
		return flag;
	}
	public void setFlag(Integer flag) {
		this.flag = flag;
	}
	public Integer getLocked() {
		return locked;
	}
	public void setLocked(Integer locked) {
		this.locked = locked;
	}
	@Override
	public String toString() {
		return "Member [mid=" + mid + ", password=" + password + ", tel=" + tel
				+ ", lastdate=" + lastdate + ", photo=" + photo + ", flag="
				+ flag + ", locked=" + locked + ", logs=" + logs + ", role="
				+ role + "]";
	}
	
}
