package board;

import java.util.List;

import java.util.Map;

import board.BoardVO;
import board.PagingVO;

public interface BoardDao {
	public int listCount() throws Exception;
	public List<BoardVO> list(PagingVO pagingVO) throws Exception;
	public int delete(BoardVO boardVO) throws Exception;
	public int deleteAll() throws Exception;
	public int update(BoardVO boardVO) throws Exception;
	public void insert(BoardVO boardVO) throws Exception;
	public BoardVO select(int seq) throws Exception;
	public int updateReadCount(int seq) throws Exception;
    public List<BoardVO> readReply(int seq) throws Exception;
    public int countBoard() throws Exception;
    public List<BoardVO> selectBoard(PagingVO vo) throws Exception;
	public void insertFile(Map<String, Object> map) throws Exception;
	
}
