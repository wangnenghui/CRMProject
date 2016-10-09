package cn.mldn.crm.servlet.back;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.annotation.WebServlet;

import cn.mldn.crm.factory.ServiceFactory;
import cn.mldn.crm.vo.Member;
import cn.mldn.util.BasepathUtil;
import cn.mldn.util.MD5Code;
import cn.mldn.util.SplitUtil;

@SuppressWarnings("serial")
@WebServlet("/pages/back/member/MemberServletBack/*")
public class MemberServletBack extends AbstractCRMServlet {
	private Member member = new Member();

	public String editPasswordPre() { // 修改密码
		return "member.password.edit.page";
	}

	public String editPassPre() {
		if (super.isAction(17)) {
			return "member.edit.password.admin.page";
		} else {
			return "error.page";
		}
	}

	public String editPass() {
		if (super.isAction(17)) {
			this.member.setPassword(new MD5Code().getMD5ofStr(this.member
					.getPassword()));
			try {
				if (ServiceFactory.getIMemberServiceBackInstance()
						.editPasswordByAdmin(super.getMid(), this.member)) {
					super.setMsgAndUrl("member.edit.password.admin.success",
							"member.list.servlet");
				} else {
					super.setMsgAndUrl("member.edit.password.admin.failure",
							"member.list.servlet");
				}
			} catch (Exception e) {
				super.setMsgAndUrl("member.edit.password.admin.failure",
						"member.list.servlet");
			}
			return "forward.page";
		} else {
			return "error.page";
		}
	}

	public String editPassword() {
		String mid = (String) super.getSession().getAttribute("mid");
		String newpass = new MD5Code().getMD5ofStr(super
				.getParameter("newpass"));
		String oldpass = new MD5Code().getMD5ofStr(super
				.getParameter("oldpass"));
		try {
			if (ServiceFactory.getIMemberServiceBackInstance().editPassword(
					mid, newpass, oldpass)) {
				super.setMsgAndUrl("member.password.edit.success",
						"member.logout.servlet");
			} else {
				super.setMsgAndUrl("member.password.edit.failure",
						"member.logout.servlet");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "forward.page";
	}

	public String addPre() {
		if (super.isAction(15)) {
			try {
				Map<String, Object> map = ServiceFactory
						.getIMemberServiceBackInstance().addPre(super.getMid());
				request.setAttribute("allRoles", map.get("allRoles"));
			} catch (Exception e) {
				e.printStackTrace();
			}

			return "member.add.page";
		} else {
			return "error.page";
		}
	}

	public String rm() {
		if (super.isAction(18)) {
			String ids = super.getParameter("ids");
			String result[] = ids.split("\\|");
			List<String> photos = new ArrayList<String>(); // 保存的是删除图片
			Set<String> mids = new HashSet<String>(); // 删除的用户名
			for (int x = 0; x < result.length; x++) {
				String temp[] = result[x].split(":");
				mids.add(temp[0]);
				if (!"nophoto.jpg".equals(temp[1])) {
					photos.add(temp[1]);
				}
			}
			try {
				if (ServiceFactory.getIMemberServiceBackInstance().rm(
						super.getMid(), mids)) {
					super.deleteFile(photos); 
					super.setMsgAndUrl("vo.rm.success", "member.list.servlet");
				} else {
					super.setMsgAndUrl("vo.rm.failure", "member.list.servlet");
				}
			} catch (Exception e) {
				super.setMsgAndUrl("vo.rm.failure", "member.list.servlet");
				e.printStackTrace();
			}
			return "forward.page";
		} else {
			return "error.page";
		}
	}

	public String editPre() {
		if (super.isAction(17)) {
			try {
				Map<String, Object> map = ServiceFactory
						.getIMemberServiceBackInstance().editPre(
								super.getMid(), super.getParameter("mid"));
				request.setAttribute("allRoles", map.get("allRoles"));
				request.setAttribute("member", map.get("member"));
			} catch (Exception e) {
				e.printStackTrace();
			}
			return "member.edit.page";
		} else {
			return "error.page";
		}
	}

	public String edit() {
		if (super.isAction(17)) {

			// 对于用户信息的修改牵扯到用户照片的修改，但是此时需要考虑原始是否存在照片

			List<String> names = null;
			if (super.existsUpload()) { // 现在有上传图片
				if ("nophoto.jpg".equals(this.member.getPhoto())) { // 原始没有图片
					names = super.createUploadFileName();
					this.member.setPhoto(names.get(0));
				} else {
					names = new ArrayList<String>();
					names.add(this.member.getPhoto());
				}
			}
			try {
				if (ServiceFactory.getIMemberServiceBackInstance().edit(
						super.getMid(), this.member)) {
					if (super.existsUpload()) {
						super.saveUploadFile(names); // 保存上传文件
					}
					super.setMsgAndUrl("vo.edit.success", "member.list.servlet");
				} else {
					super.setMsgAndUrl("vo.edit.failure", "member.list.servlet");
				}
			} catch (Exception e) {
				e.printStackTrace();
				super.setMsgAndUrl("vo.edit.failure", "member.list.servlet");
			}
			return "forward.page";
		} else {
			return "error.page";
		}
	}

	public String listSplit() {
		if (super.isAction(16)) {
			SplitUtil su = super.handleSplitParam();
			try {
				Map<String, Object> map = ServiceFactory
						.getIMemberServiceBackInstance().list(super.getMid(),
								su.getColumn(), su.getKeyWord(),
								su.getCurrentPage(), su.getLineSize());

				request.setAttribute("allMembers", map.get("allMembers"));
				super.request.setAttribute("allRecorders",
						map.get("memberCount"));
				super.request.setAttribute(
						"url",
						BasepathUtil.getPath(super.request)
								+ super.getPage("member.list.servlet"));
			} catch (Exception e) {
				e.printStackTrace();
			}

			return "member.list.page";
		} else {
			return "error.page";
		}
	}

	public String add() {
		if (super.isAction(15)) {
			this.member.setPassword(new MD5Code().getMD5ofStr(this.member
					.getPassword()));
			List<String> names = null;
			if (super.existsUpload()) { // 现在有上传图片
				names = super.createUploadFileName();
				this.member.setPhoto(names.get(0));
			} else {
				this.member.setPhoto("nophoto.jpg");
			}
			try {
				if (ServiceFactory.getIMemberServiceBackInstance().add(
						super.getMid(), this.member)) {
					if (super.existsUpload()) {
						super.saveUploadFile(names); // 保存上传文件
					}
					super.setMsgAndUrl("vo.add.success", "member.add.servlet");
				} else {
					super.setMsgAndUrl("vo.add.failure", "member.add.servlet");
				}
			} catch (Exception e) {
				e.printStackTrace();
				super.setMsgAndUrl("vo.add.failure", "member.add.servlet");
			}
			return "forward.page";
		} else {
			return "error.page";
		}
	}

	@Override
	public String getMarkTitle() {
		return "用户";
	}

	@Override
	public String getUploadDir() {
		return "/upload/member/";
	}

	@Override
	public String getDefaultColumn() {
		return "mid";
	}

	@Override
	public String getColumntData() {
		return "用户名:mid|联系电话:tel";
	}

	public Member getMember() {
		return member;
	}
}
