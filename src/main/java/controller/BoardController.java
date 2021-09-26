package controller;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.net.URLEncoder;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.FilenameUtils;
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
import org.springframework.web.multipart.MultipartFile;
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
	public String list(Model model, PagingVO pagingVO) throws Exception {
		model.addAttribute("boardList", boardService.list(pagingVO));
		
		model.addAttribute("pagingVO", pagingVO);
		
		return "list";
	}
	@RequestMapping(value="/read/{seq}")
	public String read(Model model, int seq) throws Exception {
		model.addAttribute("boardVO", boardService.read(seq));
		return "read";
	
	}
	//파일 다운
		@RequestMapping(value="/fileDown/{seq}")
		public void fileDown(@PathVariable int seq,HttpServletRequest req, HttpServletResponse resp) throws Exception{
			//업로드 파일이 있는 경로
			String realPath ="C:\\Users\\HOME\\Pictures";
			BoardVO vo = boardService.read(seq);
			String filename = vo.getFileName();
			
			File downFile = new File(realPath + "\\" + filename);
			//파일 이름이 파라미터로 넘어오지 않으면 리다이렉트 시킨다.
//			if(request.getParameter("fileName") == null || "".equals(request.getParameter("fileName"))) {
			if (downFile.exists() && downFile.isFile()) {
		         try {
		            filename = URLEncoder.encode(filename, "utf-8").replaceAll("\\+","%20");
		            long filesize = downFile.length();
		            
		            resp.setContentType("application/octet-stream; charset=utf-8");
		            resp.setContentLength((int) filesize);
		            String strClient = req.getHeader("user-agent");
		            
		            if (strClient.indexOf("MSIE 5.5") != -1) {
		               resp.setHeader("Content-Disposition", "filename="
		                            + filename + ";");
		                } else {
		                   resp.setHeader("Content-Disposition",
		                            "attachment; filename=" + filename + ";");
		                }
		            resp.setHeader("Content-Length", String.valueOf(filesize));
		            resp.setHeader("Content-Transfer-Encoding", "binary;");
		            resp.setHeader("Pragma", "no-cache");
		            resp.setHeader("Cache-Control", "private");
		 
		                byte b[] = new byte[1024];
		 
		                BufferedInputStream in = new BufferedInputStream(
		                        new FileInputStream(downFile));
		 
		                BufferedOutputStream out = new BufferedOutputStream(
		                      resp.getOutputStream());
		 
		                int read = 0;
		 
		                while ((read = in.read(b)) != -1) {
		                    out.write(b, 0, read);
		                }
		                out.flush();
		                out.close();
		                in.close();
		            
		         } catch (Exception e) {
		            System.out.println("Download Exception : " + e.getMessage());
		         }
		      } else {
		         System.out.println("Download Error : downFile Error [" + downFile + "]");
		      }
		}
	@RequestMapping(value="/write", method=RequestMethod.GET) 
	public String write(Model model) throws Exception {
		model.addAttribute("boardVO", new BoardVO());
		return "write";
	}
	@RequestMapping(value="/write", method=RequestMethod.POST)
	public String write(@Validated BoardVO boardVO, MultipartHttpServletRequest mpRequest) throws Exception{
		   String fileName = null;
		   MultipartFile uploadFile = boardVO.getUploadFile();
		   if(!uploadFile.isEmpty()) {
				String originalFileName = uploadFile.getOriginalFilename();
				String ext = FilenameUtils.getExtension(originalFileName);
				fileName = uploadFile.getOriginalFilename();
				uploadFile.transferTo(new File("C:\\Users\\HOME\\Pictures"+ fileName));
			}
		   boardVO.setFileName(fileName);
		   boardService.write(boardVO, mpRequest);
		   return "redirect:list";
	}
	@RequestMapping(value="/edit/{seq}", method = RequestMethod.GET)
	public String edit(@PathVariable int seq, Model model) throws Exception {
	     	BoardVO boardVO = boardService.read(seq);
		    model.addAttribute("boardVO", boardVO);
    		return "edit";
	}
	@RequestMapping(value="/edit/{seq}", method = RequestMethod.POST)
	public String edit(@Validated @ModelAttribute BoardVO boardVO,
		    BindingResult result, int pwd, 
		    SessionStatus sessionStatus, Model model) throws Exception {
			if(result.hasErrors()) {
				return "edit";
			} else {
				if(boardVO.getPassword()==pwd) {
					boardService.edit(boardVO);
					sessionStatus.setComplete();
					return "redirect:list";
				}
				}
				model.addAttribute("msg", "비밀번호가 일치하지 않습니다.");
				return "edit";
			}
	@RequestMapping(value="/delete/{seq}", method = RequestMethod.GET)
	public String delete(@PathVariable int seq, Model model) {
		model.addAttribute("seq", seq);
		return "delete";
	}
	@RequestMapping(value="delete", method=RequestMethod.POST)
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
			   return "redirect:list";
		   }
	}
	@RequestMapping(value="/read/{seq}", method=RequestMethod.GET)
	public String readReply(@PathVariable int seq, Model model) throws Exception{
		    model.addAttribute("seq", seq);
		    
		    List<BoardVO> replyList = boardService.readReply(seq);
		    model.addAttribute("replyList", replyList);
     		return "read";
	}	
}