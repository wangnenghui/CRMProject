package cn.mldn.crm.service.back;

import java.util.List;

import cn.mldn.crm.vo.Action;

public interface IActionServiceBack {
	/**
	 * 实现权限的数据列表处理操作
	 * @return
	 * @throws Exception
	 */
	public List<Action> list(String mid) throws Exception ;
}
