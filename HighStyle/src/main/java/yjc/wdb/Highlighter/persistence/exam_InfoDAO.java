package yjc.wdb.Highlighter.persistence;

import yjc.wdb.Highlighter.domain.exam_InfoVO;

public interface exam_InfoDAO {
	public void createexam(exam_InfoVO vo) throws Exception;
	/*<!-- ���ÿ��� �Ǵ�! -->*/
	public void test_state_up(exam_InfoVO vo) throws Exception;
}
