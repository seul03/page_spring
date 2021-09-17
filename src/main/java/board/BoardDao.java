package board;

import java.util.List;
import board.BoardVO;

public interface BoardDao {
	public List<BoardVO> list() throws Exception;
	public int delete(BoardVO boardVO) throws Exception;
	public int deleteAll() throws Exception;
	public int update(BoardVO boardVO) throws Exception;
	public void insert(BoardVO boardVO) throws Exception;
	public BoardVO select(int seq) throws Exception;
	public int updateReadCount(int seq) throws Exception;
    public List<BoardVO> readReply(int seq) throws Exception;
	
}
