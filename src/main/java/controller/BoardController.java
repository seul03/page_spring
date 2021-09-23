package controller;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import board.BoardVO;
import board.PagingVO;
import service.BoardService;

@Controller
@SessionAttributes("boardVO")
public class BoardController {
	@Autowired
	private BoardService boardService;

	@RequestMapping(value="/list")
	public String list(Model model) throws Exception {
		model.addAttribute("boardList", boardService.list());
		return "list";
	}
	@RequestMapping(value="/read/{seq}")
	public String read(Model model, int seq) throws Exception {
		model.addAttribute("boardVO", boardService.read(seq));
		return "/read";
	}
	@RequestMapping(value="/write", method=RequestMethod.GET) 
	public String write(Model model) throws Exception {
		model.addAttribute("boardVO", new BoardVO());
		return "/write";
	}
	@RequestMapping(value="/write", method=RequestMethod.POST)
	public String write(BoardVO boardVO, BindingResult bindingResult, MultipartHttpServletRequest mpRequest) throws Exception{
		   if(bindingResult.hasErrors()) {
			   return "/write";
		   }
		   boardService.write(boardVO, mpRequest);
		   return "redirect:/list";
	}
	@RequestMapping(value="/edit/{seq}", method = RequestMethod.GET)
	public String edit(@PathVariable int seq, Model model) throws Exception {
	     	BoardVO boardVO = boardService.read(seq);
		    model.addAttribute("boardVO", boardVO);
    		return "/edit";
	}
	@RequestMapping(value="/edit/{seq}", method = RequestMethod.POST)
	public String edit(@Validated @ModelAttribute BoardVO boardVO,
		    BindingResult result, int pwd, 
		    SessionStatus sessionStatus, Model model) throws Exception {
			if(result.hasErrors()) {
				return "/edit";
			} else {
				if(boardVO.getPassword()==pwd) {
					boardService.edit(boardVO);
					sessionStatus.setComplete();
					return "redirect:/board/list";
				}
				}
				model.addAttribute("msg", "비밀번호가 일치하지 않습니다.");
				return "/edit";
			}
	@RequestMapping(value="/board/delete/{seq}", method = RequestMethod.GET)
	public String delete(@PathVariable int seq, Model model) {
		model.addAttribute("seq", seq);
		return "/delete";
	}
	@RequestMapping(value="/board/delete", method=RequestMethod.POST)
	public String delete(int seq, int pwd, Model model) throws Exception {
		   int rowCount;
		   BoardVO boardVO = new BoardVO();
		   boardVO.setSeq(seq);
		   boardVO.setPassword(pwd);
		   
		   rowCount = boardService.delete(boardVO);
		   
		   if(rowCount == 0) {
			   model.addAttribute("seq", seq);
			   model.addAttribute("msg", "비밀번호가 일치하지 않습니다");
			   return "/delete";
		   }
		   else {
			   return "redirect:/list";
		   }
	}
	@RequestMapping(value="/read/{seq}", method=RequestMethod.GET)
	public String readReply(@PathVariable int seq, Model model) throws Exception{
		    model.addAttribute("seq", seq);
		    
		    List<BoardVO> replyList = boardService.readReply(seq);
		    model.addAttribute("replyList", replyList);
     		return "/read";
	}		
	@RequestMapping("boardList")
	public String boardList(PagingVO vo, Model model
			, @RequestParam(value="now", required=false)String now
			, @RequestParam(value="cntPage", required=false)String cntPage) throws Exception {
		
		int total = boardService.countBoard();
		if (now == null && cntPage == null) {
			now = "1";
			cntPage = "5";
		} else if (now == null) {
			now = "1";
		} else if (cntPage == null) { 
			cntPage = "5";
		}
		vo = new PagingVO(total, Integer.parseInt(now), Integer.parseInt(cntPage));
		model.addAttribute("paging", vo);
		model.addAttribute("viewAll", boardService.selectBoard(vo));
		return "board/boardPaging";
}
}

