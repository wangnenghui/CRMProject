package cn.mldn.util;

public class SplitUtil {
	private int currentPage = 1; 
	private int lineSize = 8 ;
	private String column ;
	private String keyWord ;
	/**
	 * 设置currentPage的内容，传进来的是一个字符串
	 * @param cp
	 */
	public void setCp(String cp) {	// 处理cp参数
		try {
			this.currentPage = Integer.parseInt(cp) ;
		} catch (Exception e) {}
	}
	/**
	 * 设置column属性内容
	 * @param col
	 */
	public void setCol(String col) {
		this.column = col ;
	}
	/**
	 * 设置keyWord属性内容
	 * @param kw
	 */
	public void setKw(String kw) {
		this.keyWord = kw ;
	}
	public int getCurrentPage() {
		return currentPage;
	}
	public int getLineSize() {
		return lineSize;
	}
	public String getKeyWord() {
		return keyWord;
	}
	public String getColumn() {
		return column;
	}
}
