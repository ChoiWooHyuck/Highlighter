package yjc.wdb.Highlighter.service;

import java.util.HashMap;
import java.util.List;

import yjc.wdb.Highlighter.domain.test_InfoVO;

public interface test_InfoService {
	public void create(test_InfoVO vo) throws Exception;
	public String extidse(String ext_id) throws Exception;
	public List<HashMap> testList(String ext_id,String user_id) throws Exception;
	public List<test_InfoVO> testImage(String test_id) throws Exception;
	
	/*��� ���̵� ���*/
	public List<String> allid(String ext_id) throws Exception;
	
	/*	���� ���� ����Ʈ ���*/
	public List<test_InfoVO> teachertestList(String ext_id) throws Exception;
	
	public String TImage(String ext_id) throws Exception;
}
