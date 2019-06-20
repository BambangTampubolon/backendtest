package spring.manager.impl;

import org.springframework.http.HttpStatus;

import spring.util.ErrorDetail;

public class ManagerImpl {
	private ErrorDetail info;
	private int totalRow;

	public ErrorDetail getInfo() {
		return info;
	}
	public void setInfo(ErrorDetail info) {
		this.info = info;
	}
	public int getTotalRow() {
		return totalRow;
	}
	public void setTotalRow(int totalRow) {
		this.totalRow = totalRow;
	}

	public void setInfoConflict(String errMsg) {
		ErrorDetail info = new ErrorDetail();

		info.setMessage(HttpStatus.CONFLICT.name());
		info.setStatus(HttpStatus.CONFLICT.value());
		info.setDetailInfo(HttpStatus.CONFLICT);
		info.setDetailmessage(errMsg);
		
		this.info = info;
	}
}
