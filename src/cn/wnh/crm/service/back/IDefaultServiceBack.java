package cn.mldn.crm.service.back;

import java.util.Map;

public interface IDefaultServiceBack {
	/**
	 * 用户工作台的统计信息；
	 * @param mid
	 * @return 返回的信息包含有如下内容：<br>
	 * <li>key = allClients、value = IClientDAO.findAllSplitByMemberAndType()，设置type为-1</li>
	 * <li>key = allTasks、value = ITaskDAO.findAllSplitByMember()</li>
	 * <li>key = clientCount、value = IClientDAO.getAllCountByMemberAndType()，设置type为-1</li>
	 * <li>key = unfinishCount、value = ITaskDAO.getAllCountByBeforeUnFinish()</li>
	 * <li>key = wfinishCount、value = ITaskDAO.getAllCountByAfterFinish()</li>
	 * <li></li>
	 * @throws Exception
	 */
	public Map<String, Object> stat(String mid) throws Exception;
}
