package cn.mldn.crm.vo;

import java.io.Serializable;
import java.util.List;

@SuppressWarnings("serial")
public class Action implements Serializable{
	private Integer actid ;
	private String title ;
	private Integer menu ;
	private String url ;
	private List<Groups> groups ;
	public Integer getActid() {
		return actid;
	}
	public void setActid(Integer actid) {
		this.actid = actid;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public Integer getMenu() {
		return menu;
	}
	public void setMenu(Integer menu) {
		this.menu = menu;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public List<Groups> getGroups() {
		return groups;
	}
	public void setGroups(List<Groups> groups) {
		this.groups = groups;
	}
	@Override
	public String toString() {
		return "Action [actid=" + actid + ", title=" + title + ", menu=" + menu
				+ ", url=" + url + "]";
	} 
}
