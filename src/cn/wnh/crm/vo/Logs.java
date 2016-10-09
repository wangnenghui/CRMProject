package cn.mldn.crm.vo;

import java.io.Serializable;
import java.util.Date;

@SuppressWarnings("serial")
public class Logs implements Serializable {
	private Integer logid ;
	private Member member = new Member() ;
	private Date indate ;
	public Integer getLogid() {
		return logid;
	}
	public void setLogid(Integer logid) {
		this.logid = logid;
	}
	public Member getMember() {
		return member;
	}
	public void setMember(Member member) {
		this.member = member;
	}
	public Date getIndate() {
		return indate;
	}
	public void setIndate(Date indate) {
		this.indate = indate;
	} 
}
