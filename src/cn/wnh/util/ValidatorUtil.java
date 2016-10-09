package cn.mldn.util;

import java.util.HashMap;
import java.util.Map;

import cn.mldn.util.servlet.DispatcherServlet;

public class ValidatorUtil {
	private String rules [] ;	// 保存拆分后的规则信息
	private DispatcherServlet servlet ;
	// 把参数名称作为错误的key，将验证规则作为value
	private Map<String,String> errors = new HashMap<String,String>() ;
	public ValidatorUtil(String rule,DispatcherServlet servlet) {
		this.rules = rule.split("\\|") ;
		this.servlet = servlet ;
		this.validate(); 
	}
	public void validate() {
		for (int x = 0 ; x < this.rules.length ; x ++) {
			String temp [] = this.rules[x].split(":") ;
			String value = this.servlet.getParameter(temp[0]) ;	// 接收具体参数
			switch (temp[1]) {
				case "int" : {
					if (!ValidateUtil.isInteger(value)) {	// 验证不通过
						this.errors.put(temp[0], temp[1]) ;
					}
					break ;
				}
				case "double" : {
					if (!ValidateUtil.isDouble(value)) {	// 验证不通过
						this.errors.put(temp[0], temp[1]) ;
					}
					break ;
				}
				case "date" : {
					if (!ValidateUtil.isDate(value)) {	// 验证不通过
						this.errors.put(temp[0], temp[1]) ;
					}
					break ;
				}
				case "string" : {
					if (ValidateUtil.isEmpty(value)) {	// 验证不通过
						this.errors.put(temp[0], temp[1]) ;
					}
					break ;
				}
			}
		}
	}
	/**
	 * 取得所有的错误信息
	 * @return 错误信息的集合
	 */
	public Map<String, String> getErrors() {
		return errors;
	}
}
