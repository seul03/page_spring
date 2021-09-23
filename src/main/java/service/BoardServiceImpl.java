package service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import java.util.List;
import java.util.Map;

import board.BoardVO;
import board.PagingVO;
import util.FileUtils;
import board.BoardDao;

@Service
public class BoardServiceImpl implements BoardService {
    @Autowired  
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
      public void write(BoardVO boardVO, MultipartHttpServletRequest mpRequest) throws Exception {
    	  boardDao.insert(boardVO);
    	  
  		  List<Map<String,Object>> list = FileUtils.parseInsertFileInfo(boardVO, mpRequest); 
  	      int size = list.size();
  		  for(int i=0; i<size; i++){ 
  		  boardDao.insertFile(list.get(i)); 
      }
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
      @Override
      public int countBoard() throws Exception{
      	return boardDao.countBoard();
      }

      @Override
      public List<BoardVO> selectBoard(PagingVO vo) throws Exception {
      	return boardDao.selectBoard(vo);
      }
}
