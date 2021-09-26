package board;

import org.mybatis.spring.SqlSessionTemplate;

import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Map;

import board.BoardVO;
import board.PagingVO;

@Repository
public class BoardDaoMybatis implements BoardDao {

	private SqlSessionTemplate sqlSessionTemplate;

	public BoardDaoMybatis(SqlSessionTemplate sqlSessionTemplate) {
		this.sqlSessionTemplate = sqlSessionTemplate;
	}

	public void setSqlSessionTemplate(SqlSessionTemplate sqlSessionTemplate) {
		this.sqlSessionTemplate = sqlSessionTemplate;
	}

	@Override
	public List<BoardVO> list(PagingVO pagingVO) throws Exception {
		return sqlSessionTemplate.selectList("listpage");
	}
	@Override
	public int listCount() throws Exception {
		return sqlSessionTemplate.selectOne("listCount");
	}

	@Override
	public int delete(BoardVO boardVO) throws Exception{
		return sqlSessionTemplate.delete("delete", boardVO);
	}

	@Override
	public int deleteAll() throws Exception{
		return sqlSessionTemplate.delete("deleteAll");
	}

	@Override
	public int update(BoardVO boardVO) throws Exception{
		return sqlSessionTemplate.update("update", boardVO);
	}

	@Override
	public void insert(BoardVO boardVO) throws Exception{
		sqlSessionTemplate.insert("insert", boardVO);
	}

	@Override
	public BoardVO select(int seq) throws Exception{
		return sqlSessionTemplate.selectOne("select", seq);
	}

	@Override
	public int updateReadCount(int seq) throws Exception{
		return sqlSessionTemplate.update("updateCount", seq);
	}
	@Override
	public List<BoardVO> readReply(int seq) throws Exception{
		return sqlSessionTemplate.selectList("replyMapper.readReply", seq);		
	}
	@Override
	public void insertFile(Map<String, Object> map) throws Exception{
		sqlSessionTemplate.insert("insertFile", map);
	}
	@Override
	public int countBoard() throws Exception {
		return sqlSessionTemplate.selectOne("countBoard");
	}
	@Override
	public List<BoardVO> selectBoard (PagingVO vo) throws Exception {
		return sqlSessionTemplate.selectList("selectBoard", vo);
	}
}
