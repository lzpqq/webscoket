package webscoket.domain;

import lombok.Data;


@Data
public class ResponseBo {

	private int code;
	
	private String msg;
	
	private Long count;
	
	private Object data;

	private String action;


}
