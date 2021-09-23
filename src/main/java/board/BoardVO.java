package board;

import java.sql.Timestamp;
import org.apache.ibatis.type.Alias;

@Alias("BoardVO")
public class BoardVO {
	private int seq;
	private String title;
	private String content;
	private String writer;
	private int password;
	private Timestamp regDate;
	private int cnt;
	private String file_name;
	private int file_size;
	private int num;
	private String con;
	private String write;
	
	public BoardVO(){}

	public BoardVO(int seq, String title, String content, String writer, int password, Timestamp regDate, int cnt,
			String file_name, int file_size, int num, String con, String write) {
		super();
		this.seq = seq;
		this.title = title;
		this.content = content;
		this.writer = writer;
		this.password = password;
		this.regDate = regDate;
		this.cnt = cnt;
		this.file_name = file_name;
		this.file_size = file_size;
		this.num = num;
		this.con = con;
		this.write = write;
	}

	public int getSeq() {
		return seq;
	}

	public void setSeq(int seq) {
		this.seq = seq;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getWriter() {
		return writer;
	}

	public void setWriter(String writer) {
		this.writer = writer;
	}

	public int getPassword() {
		return password;
	}

	public void setPassword(int password) {
		this.password = password;
	}

	public Timestamp getRegDate() {
		return regDate;
	}

	public void setRegDate(Timestamp regDate) {
		this.regDate = regDate;
	}

	public int getCnt() {
		return cnt;
	}

	public void setCnt(int cnt) {
		this.cnt = cnt;
	}

	public String getFile_name() {
		return file_name;
	}

	public void setFile_name(String file_name) {
		this.file_name = file_name;
	}

	public int getFile_size() {
		return file_size;
	}

	public void setFile_size(int file_size) {
		this.file_size = file_size;
	}

	public int getNum() {
		return num;
	}

	public void setNum(int num) {
		this.num = num;
	}

	public String getCon() {
		return con;
	}

	public void setCon(String con) {
		this.con = con;
	}

	public String getWrite() {
		return write;
	}

	public void setWrite(String write) {
		this.write = write;
	}


}