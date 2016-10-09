package cn.mldn.crm.vo;

import java.io.Serializable;
import java.util.List;

@SuppressWarnings("serial")
public class Role implements Serializable{
	private Integer rid ;
	private String title ;
	private List<Member> members ;
	private List<Groups> groups ;
	public Integer getRid() {
		return rid;
	}
	public void setRid(Integer rid) {
		this.rid = rid;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public List<Member> getMembers() {
		return members;
	}
	public void setMembers(List<Member> members) {
		this.members = members;
	}
	public List<Groups> getGroups() {
		return groups;
	}
	public void setGroups(List<Groups> groups) {
		this.groups = groups;
	}
	@Override
	public String toString() {
		return "Role [rid=" + rid + ", title=" + title + ", groups=" + groups
				+ "]";
	}
	
}
