package service;

import java.util.List;
import java.util.Map;

import board.PagingVO;

import org.springframework.web.multipart.MultipartHttpServletRequest;

import board.BoardVO;

public interface BoardService {
	public List<BoardVO> list(PagingVO pagingVO) throws Exception;
	public int listCount() throws Exception;
	public int delete(BoardVO boardVO) throws Exception;
	public int edit(BoardVO boardVO) throws Exception;
	public void write(BoardVO boardVO, MultipartHttpServletRequest mpRequest)throws Exception;
	public BoardVO read(int seq) throws Exception;
    public List<BoardVO> readReply(int seq) throws Exception;
    public int countBoard() throws Exception;
    public List<BoardVO> selectBoard(PagingVO vo) throws Exception;
}
