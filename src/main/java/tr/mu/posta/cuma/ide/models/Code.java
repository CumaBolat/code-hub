package tr.mu.posta.cuma.ide.models;

public class Code {

  private Integer id;

  private User user;
	
	private String code;
	
	public Code() {}

  public Code(String code, User user) {
    this.code = code;
    this.user = user;
  }

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }
	
	public Code(String code) {
		this.code = code;
	}
	
	public String getCode() {
		return this.code;
	}
	
	public void setCode(String code) {
		this.code = code;
	}
}
