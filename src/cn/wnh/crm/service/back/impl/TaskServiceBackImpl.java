package cn.mldn.crm.service.back.impl;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import cn.mldn.crm.factory.DAOFactory;
import cn.mldn.crm.service.back.AbstractCRMServiceBack;
import cn.mldn.crm.service.back.ITaskServiceBack;
import cn.mldn.crm.vo.Task;
import cn.mldn.util.DateCompare;

public class TaskServiceBackImpl extends AbstractCRMServiceBack implements
		ITaskServiceBack {

	@Override
	public boolean add(Task vo) throws Exception {
		if (!DateCompare.compare(vo.getTdate())) {
			return false;
		}
		try {
			// 1、判断当前的添加任务的用户是否具备有指定的权限
			if (super.isAction(vo.getMember().getMid(), 5)) {
				// 2、判断当前处理的客户任务的客户ID是否属于当前的用户
				if (DAOFactory.getIClientDAOInstance(this.dbc.getConnection())
						.findExistsByMemberAndCid(vo.getMember().getMid(),
								vo.getClient().getCid())) {
					vo.setStatus(1); // 表示任务正在进行中
					return DAOFactory.getITaskDAOInstance(
							this.dbc.getConnection()).doCreate(vo);
				}
			}
			return false;
		} catch (Exception e) {
			throw e;
		} finally {
			this.dbc.close();
		}
	}

	@Override
	public Map<String, Object> listByMemberAndClient(String mid, int cid)
			throws Exception {
		try {
			Map<String, Object> map = new HashMap<String, Object>();
			if (super.isAction(mid, 30)) {
				map.put("client",
						DAOFactory.getIClientDAOInstance(
								this.dbc.getConnection()).findByMemberAndId(
								mid, cid));
				map.put("allTasks",
						DAOFactory
								.getITaskDAOInstance(this.dbc.getConnection())
								.findAllByMemberAndClient(mid, cid));
			}
			return map;
		} catch (Exception e) {
			throw e;
		} finally {
			this.dbc.close();
		}
	}

	@Override
	public Map<String, Object> listByClient(String mid, int cid)
			throws Exception {
		try {
			Map<String, Object> map = new HashMap<String, Object>();
			if (super.isAction(mid, 29)) {
				map.put("client",
						DAOFactory.getIClientDAOInstance(
								this.dbc.getConnection()).findById(cid));
				map.put("allTasks",
						DAOFactory
								.getITaskDAOInstance(this.dbc.getConnection())
								.findAllByClient(cid));
			}
			return map;
		} catch (Exception e) {
			throw e;
		} finally {
			this.dbc.close();
		}
	}

	@Override
	public Task show(String mid, int cid, int tid) throws Exception {
		try {
			if (super.isAction(mid, 31)) {
				return DAOFactory.getITaskDAOInstance(this.dbc.getConnection())
						.findById(mid, cid, tid);
			}
			return null;
		} catch (Exception e) {
			throw e;
		} finally {
			this.dbc.close();
		}
	}

	@Override
	public Task show(String mid, int tid) throws Exception {
		try {
			if (super.isAction(mid, 32)) {
				return DAOFactory.getITaskDAOInstance(this.dbc.getConnection())
						.findById(tid);
			}
			return null;
		} catch (Exception e) {
			throw e;
		} finally {
			this.dbc.close();
		}
	}

	@Override
	public Map<String, Object> listByMember(String mid, int visit, int type,
			String column, String keyWord, int currentPage, int lineSize)
			throws Exception {
		try {
			Map<String, Object> map = new HashMap<String, Object>();
			if (super.isAction(mid, 6)) {
				List<Task> allTasks = DAOFactory.getITaskDAOInstance(
						this.dbc.getConnection()).findAllSplitByMember(mid,
						visit, type, column, keyWord, currentPage, lineSize);
				// 先遍历一次，将所有的客户的id取出
				Set<Integer> cids = new HashSet<Integer>();
				Iterator<Task> iter = allTasks.iterator();
				while (iter.hasNext()) {
					cids.add(iter.next().getClient().getCid());
				}
				map.put("allTasks", allTasks);
				map.put("allClients",
						DAOFactory.getIClientDAOInstance(
								this.dbc.getConnection()).findByIds(cids));
				map.put("taskCount",
						DAOFactory
								.getITaskDAOInstance(this.dbc.getConnection())
								.getAllCountByMember(mid, visit, type, column,
										keyWord));
			}
			return map;
		} catch (Exception e) {
			throw e;
		} finally {
			this.dbc.close();
		}
	}

	@Override
	public Map<String, Object> list(String mid, int visit, int type,
			String column, String keyWord, int currentPage, int lineSize)
			throws Exception {
		try {
			Map<String, Object> map = new HashMap<String, Object>();
			if (super.isAction(mid, 28)) {
				List<Task> allTasks = DAOFactory.getITaskDAOInstance(
						this.dbc.getConnection()).findAllSplit(visit, type,
						column, keyWord, currentPage, lineSize);
				// 先遍历一次，将所有的客户的id取出
				Set<Integer> cids = new HashSet<Integer>();
				Iterator<Task> iter = allTasks.iterator();
				while (iter.hasNext()) {
					cids.add(iter.next().getClient().getCid());
				}
				map.put("allTasks", allTasks);
				map.put("allClients",
						DAOFactory.getIClientDAOInstance(
								this.dbc.getConnection()).findByIds(cids));
				map.put("taskCount",
						DAOFactory
								.getITaskDAOInstance(this.dbc.getConnection())
								.getAllCount(visit, type, column, keyWord));
			}
			return map;
		} catch (Exception e) {
			throw e;
		} finally {
			this.dbc.close();
		}
	}

	@Override
	public Task editPre(String mid, int tid) throws Exception {
		try {
			if (super.isAction(mid, 7)) {
				return DAOFactory.getITaskDAOInstance(this.dbc.getConnection())
						.findById(mid, tid);
			}
			return null;
		} catch (Exception e) {
			throw e;
		} finally {
			this.dbc.close();
		}
	}

	@Override
	public boolean edit(Task vo) throws Exception {
		if (!DateCompare.compare(vo.getTdate())) {
			return false;
		}
		try {
			if (super.isAction(vo.getMember().getMid(), 7)) {
				return DAOFactory.getITaskDAOInstance(this.dbc.getConnection())
						.doUpdateByMember(vo);
			}
			return false;
		} catch (Exception e) {
			throw e;
		} finally {
			this.dbc.close();
		}
	}

	@Override
	public boolean editOver(String mid, int tid) throws Exception {
		try {
			if (super.isAction(mid, 9)) {
				return DAOFactory.getITaskDAOInstance(this.dbc.getConnection())
						.doUpdateByStatus(mid, tid, 0);
			}
			return false;
		} catch (Exception e) {
			throw e;
		} finally {
			this.dbc.close();
		}
	}

	@Override
	public boolean editFinish(String mid, int tid) throws Exception {
		try {
			if (super.isAction(mid, 8)) {
				return DAOFactory.getITaskDAOInstance(this.dbc.getConnection())
						.doUpdateByStatus(mid, tid, 2);
			}
			return false;
		} catch (Exception e) {
			throw e;
		} finally {
			this.dbc.close();
		}
	}

	@Override
	public boolean rmByMember(String mid, Set<Integer> tids) throws Exception {
		if (tids == null || tids.size() == 0) {
			return false;
		}
		try {
			if (super.isAction(mid, 10)) {
				return DAOFactory.getITaskDAOInstance(this.dbc.getConnection())
						.doRemoveByMember(mid, tids);
			}
			return false;
		} catch (Exception e) {
			throw e;
		} finally {
			this.dbc.close();
		}
	}
}
