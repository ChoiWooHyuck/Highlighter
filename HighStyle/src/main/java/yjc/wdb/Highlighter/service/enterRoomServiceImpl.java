package yjc.wdb.Highlighter.service;

import java.util.HashMap;


import javax.inject.Inject;

import org.springframework.stereotype.Service;

import yjc.wdb.Highlighter.persistence.enterRoomDAO;

@Service
public class enterRoomServiceImpl implements enterRoomService {

	@Inject
	private enterRoomDAO dao;
	@Override
	public void updateOpenStat(HashMap<String, String> vo) throws Exception {
		// TODO Auto-generated method stub
		dao.updateOpenStat(vo);
	}
	@Override
	public void updateCloseStat(HashMap<String, String> vo) throws Exception {
		// TODO Auto-generated method stub
		dao.updateCloseStat(vo);
		
	}
	@Override
	public String selectGrade(String user_id) throws Exception {
		// TODO Auto-generated method stub
		return dao.selectGrade(user_id);
	}
	@Override
	public String selectOpenStat(String ext_id) throws Exception {
		// TODO Auto-generated method stub
		return dao.selectOpenStat(ext_id);
	}

}
