package service;

import org.springframework.stereotype.Service;
import java.util.List;

import board.BoardVO;
import board.BoardDao;

@Service
public class BoardServiceImpl implements BoardService {
      private BoardDao boardDao;
      
      public BoardDao getBoardDao() {
    	  return boardDao;
      }
      public void setBoardDao(BoardDao boardDao) {
    	  this.boardDao = boardDao;
      }
      @Override
      public List<BoardVO> list() throws Exception {
    	  return boardDao.list() ;
      }
      @Override
      public int delete(BoardVO boardVO) throws Exception {
    	  return boardDao.delete(boardVO);
      }
      @Override
      public int edit(BoardVO boardVO) throws Exception {
    	  return boardDao.update(boardVO);
      }
      @Override
      public void write(BoardVO boardVO) throws Exception {
    	    boardDao.insert(boardVO);
      }
      @Override
      public BoardVO read(int seq) throws Exception {
    	  boardDao.updateReadCount(seq);
    	  return boardDao.select(seq);
      }
      @Override
      public List<BoardVO> readReply(int seq) throws Exception {
    	  return boardDao.readReply(seq);
      }
}
