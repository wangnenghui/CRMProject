package cn.mldn.util;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class BeanOperateUtil {
	private Object currentObject ; 
	private String attribute ;
	private String value ;
	private String [] values ;	// 保存数组的操作
	private BeanOperateUtil(Object obj,String attribute) {
		this.currentObject = obj ;	// 保存当前的操作对象
		this.attribute = attribute ;
	}
	/**
	 * 要设置通过反射操作的属性名称以及属性内容
	 * @param obj 包含实例化对象的类
	 * @param attribute 要操作的属性名称
	 * @param value 要设置的内容
	 */
	public BeanOperateUtil(Object obj,String attribute,String value) {
		this(obj,attribute) ;
		this.value = value ;
		this.handleParameter(); 	// 直接进行参数的匹配
	}
	/**
	 * 要设置通过反射操作的属性名称以及属性内容
	 * @param obj 包含实例化对象的类
	 * @param attribute 要操作的属性名称
	 * @param value 要设置的内容
	 */
	public BeanOperateUtil(Object obj,String attribute,String [] values) {
		this(obj,attribute) ;
		this.values = values ;
		this.handleParameter(); 	// 直接进行参数的匹配
	}
	/**
	 * 因为具体的Bean操作对外部而言没有任何的意义，所以将其设置为私有操作
	 * 其目的是根据属性的结构进行拆分，而后调用相应的setter方法设置具体的内容
	 */
	private void handleParameter() {
		try {
			String result[] = this.attribute.split("\\."); // 根据“.”拆
			if (result.length == 2) {// 此时第一个是VO类的对象，第二个是具体的属性
				// 通过getter方法找到对应的VO类对象（emp对象），getter方法没有接收参数
				Method getMethod = this.currentObject.getClass().getMethod(
						"get" + StringUtil.initcap(result[0]));
				this.currentObject = getMethod.invoke(this.currentObject) ;
				this.setBeanValue(result[1]);
			}
			if (result.length > 2) {	// 表示现在是多级VO类对象
				for (int x = 0; x < result.length - 1; x++) {
					Method getMethod = this.currentObject.getClass().getMethod(
							"get" + StringUtil.initcap(result[x]));
					this.currentObject = getMethod.invoke(this.currentObject) ;	// 改变
				}
				this.setBeanValue(result[result.length - 1]);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	/**
	 * 设置指定对象中指定属性的内容
	 * @param attr 要操作的属性
	 */
	private void setBeanValue(String attr) throws Exception {
		Object valueObject = null ;	// 最终设置的内容
		Field field = this.currentObject.getClass().getDeclaredField(attr) ;
		switch(field.getType().getSimpleName()) {	// JDK 1.7的新功能支持字符串判断
			case "Integer" : {
				if (ValidateUtil.isInteger(this.value)) {
					valueObject = Integer.parseInt(this.value) ;
				}
				break ;
			}
			case "int" : {
				if (ValidateUtil.isInteger(this.value)) {
					valueObject = Integer.parseInt(this.value) ;
				}
				break ;
			}
			case "Double" : {
				if (ValidateUtil.isDouble(this.value)) {
					valueObject = Double.parseDouble(this.value) ;
				} 
				break ;
			}
			case "double" : {
				if (ValidateUtil.isDouble(this.value)) {
					valueObject = Double.parseDouble(this.value) ;
				}
				break ;
			}
			case "String" : {
				if (!ValidateUtil.isEmpty(this.value)) {
					valueObject = this.value ;
				}
				break ;
			}
			case "Date" : {
				if (ValidateUtil.isDate(this.value)) {
					if (this.value.split(" ").length == 1) {	// 表示此时后面没有时间
						this.value = this.value + " 00:00:00" ;
					}
					valueObject = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(this.value) ;
				}
				break ;
			}
			case "String[]" : {
				valueObject = this.values ;	// 直接保存数组
			}
			case "Integer[]" : {
				List<Integer> all = new ArrayList<Integer>() ;
				for (int x = 0; x < this.values.length; x++) {
					if (ValidateUtil.isInteger(this.values[x])) {
						all.add(Integer.parseInt(this.values[x])) ;
					}
				}
				valueObject = all.toArray(new Integer[]{}) ;	// 返回指定类型的数组
			}
			case "int[]" : {
				List<Integer> all = new ArrayList<Integer>() ;
				for (int x = 0; x < this.values.length; x++) {
					if (ValidateUtil.isInteger(this.values[x])) {
						all.add(Integer.parseInt(this.values[x])) ;
					}
				}
				valueObject = all.toArray(new Integer[]{}) ;	// 返回指定类型的数组
			}
		}
		Method setMethod = this.currentObject.getClass().getMethod(
				"set" + StringUtil.initcap(attr), field.getType());
		setMethod.invoke(this.currentObject, valueObject) ;
	} 
}
