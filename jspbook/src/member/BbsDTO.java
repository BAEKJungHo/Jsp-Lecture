package member;

public class BbsDTO {

	private int id;
	private int memberId;
	private String title;
	private String date;
	private String content;
	private String name;
	
	public BbsDTO(int id, int memberId, String title, String date, String content) {
		super();
		this.id = id;
		this.memberId = memberId;
		this.title = title;
		this.date = date;
		this.content = content;
	}
	
	public BbsDTO(int memberId, String title, String date, String content) {
		super();
		this.memberId = memberId;
		this.title = title;
		this.date = date;
		this.content = content;
	}
	
	public BbsDTO(String title, String date, String content) {
		super();
		this.title = title;
		this.date = date;
		this.content = content;
	}

	public BbsDTO( ) { }

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getMemberId() {
		return memberId;
	}

	public void setMemberId(int memberId) {
		this.memberId = memberId;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return "BbsDTO [id=" + id + ", 글쓴이=" + name + ", 제목=" + title + ", 수정일=" + date.substring(0, 16) + ", 내용="
				+ content + "]";
	}
	
	// DetailSearch
	public String toStrDetail() {
		return "BbsDTO [글쓴이=" + name + ", 제목=" + title + ", 수정일=" + date.substring(0, 16) + ", 내용="
				+ content + "]";
	}
	// Update
	public String toStrOne() {
		return "BbsDTO [id=" + id + ", memberId=" + memberId + ", 제목=" + title + ", 수정일=" + date + ", 내용="
				+ content + "]";
	}
}
