package ns.util.office;

import java.io.*; 


import java.util.*; 
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.lang.reflect.Field;
import java.lang.reflect.Method; 

import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;



import jxl.*; 
import jxl.format.UnderlineStyle;
import jxl.write.*; 
public class ExcelMakes { 
	/****
	 * 导出Excel文件类型
	 * 默认值 “”  exportType 
	 * multiTable 多表格
	 */
	int defaultWd=15;//默认宽度
	private  String   exportType="";// Excel文件类型
	private  String   sheetName;//Excel文件名称
	private  String[] cellNames;// Excel文件导出列名
	private  String[] orderedAttrs;//对应属性
	
	private String titile="";//标题
	private String[] titiles;//标题
	private int titile_font_size=15;//标题字体大小
	/*****
	 * 多个数据组合 
	 * 依次是  表格数据列名  对应属性  数据值
	 * 		   
	 */
	private  String[][] sheet;//多个数据
	private List<? extends Object> dataList;//数据
	
	private String[] mapTitile;//标题 特殊处理
	public static void main(String args[]) { 
		 
			String[] cellNamesForReq = { "IP地址","姓名"};
			String[] orderedAttrsForReq = {"ip","name"};
	
			String[] cellNamesForReq1 = { "班级","姓名","年龄"};
			String[] orderedAttrsForReq1 = {"class","name","age"};
			
		    List<Map<String, String>> list2=new ArrayList<Map<String,String>>();
		    Map<String, String> a1=new HashMap<String, String>();
		    a1.put("ip", "172.16.2.67");
		    a1.put("name", "张三");
		    list2.add(a1);
		    
		    Map<String, String> a2=new HashMap<String, String>();
		    a2.put("ip", "10.118.2.66");
		    a2.put("name", "李四");
		    list2.add(a2); 
		    /********
		     * 表格2 
		     */
		    
		    List<Map<String, String>> list23=new ArrayList<Map<String,String>>();
		    Map<String, String> a10=new HashMap<String, String>();
		    a10.put("class", "1203");
		    a10.put("name", "张三");
		    a10.put("age", "40");
		    list23.add(a10);
		    
		    Map<String, String> a20=new HashMap<String, String>();
		    a20.put("class", "1201");
		    a20.put("name", "李四");
		    a20.put("age", "30");
		    list23.add(a20); 
		    
		    /****
		     *测试单表格数据
		     */
		    ExcelMakes excel=new ExcelMakes("测试单表格",cellNamesForReq1,orderedAttrsForReq1);
		  
		 	excel.setTitile("测试单表格");
			
			excel.addData(list23);
		/**********
		 * 测试多表格数据
		 */
		
		  ExcelMakes excelMore=new ExcelMakes(cellNamesForReq,cellNamesForReq1,orderedAttrsForReq,orderedAttrsForReq1);
		  excelMore.setSheetName("测试2015");
		  excelMore.setTitile("测试2015");
		  excelMore.setExportType("multiTable");
				
		  String titles[]={"标题一","标题二"};
			
		  String mapTitles[]={"标题0","标题1","标题2","标题3"};
		  excelMore.setTitiles(titles);
		  excelMore.setMapTitile(mapTitles);
		  excelMore.addData(list2,list23); 
	} 
 
	
	/*******
	 * 
	 * 通过构造方法实现Excel的具体文件生成
	 * @param sheetName
	 * @param cellNames
	 * @param orderedAttrs
	 * @param dataList
	 */
	public ExcelMakes(String sheetName, String[] cellNames, String[] orderedAttrs ) {
		super();
		this.sheetName = sheetName;
		this.cellNames = cellNames;
		this.orderedAttrs = orderedAttrs;
		 
	}
	/*********
	 * 可变参数处理
	 * @param sheetName
	 * @param cellNames
	 * @param orderedAttrs
	 */
 	public ExcelMakes(String[] ...sheet) {
 		super();
		
		this.sheet = sheet;
		 
		
	}
 	/*********
 	 * 下载弹出框
 	 */
 	private void downloads(String path){
	
 		
 		HttpServletResponse response = ServletActionContext.getResponse();
		try {
			String file= path+sheetName+".xls";
			 
			InputStream is = new FileInputStream(file);
			response.reset(); // 必要地清除response中的缓存信息
			response.setHeader("Content-Disposition", "attachment;filename="+ ExcelService.encodeFileName(sheetName) + ".xls");
			response.setContentType("application/vnd.ms-excel");//根据个人需要,这个是下载文件的类型
			javax.servlet.ServletOutputStream out = response.getOutputStream();
		byte[] content = new byte[1024];
		int length = 0;
		while ((length = is.read(content)) != -1) {
		out.write(content, 0, length);
		}
		out.write(content);
		out.flush();
		out.close();
		} catch (Exception e) {
		
		}
 	}
 	
	/***
	 * 删除临时文件
	 * @param src
	 */
	private void deleteFolder(String src){
		String cmd = "cmd /c rmdir /S /Q \""+src+"\";exit -1";
	 
	 
		try{
			Process ps = Runtime.getRuntime().exec(cmd);
			InputStream in = ps.getInputStream();
			int c;
			while ((c = in.read()) != -1){
			}
			in.close();
			ps.waitFor();
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	/************
	 * 添加集合
	 * @param dataList
	 */
 	public  void addData(List<? extends Object> ...dataList){
	 
		String exportType=this.exportType;
		/******
		 * 单表格数据
		 */
		System.out.println(dataList.length);
		if(exportType==null || exportType.equals("") ){
			
				if(dataList.length==1){ 
						exportExcel(dataList[0]); 
				}
			
		}
		/*****
		 * 多表格数据
		 */
		if (exportType.equals("multiTable")) {
			exportExcels(dataList);
		}
		 
	} 
	
	/****
	 * Excel生成方法 单个数据
	 * @param dataList
	 */
	private  void exportExcel(List<? extends Object> dataList){
		 int rows=0;//行数
		 //创建文件夹
		 String serverPath = ServletActionContext.getServletContext().getRealPath("/");
		 if(!new File(serverPath+"upload").exists()){
		 			new File(serverPath+"upload").mkdir();
		 }
		 String path=serverPath+"upload\\xls\\";	
		 File dir=new File(path);
		 if(!dir.exists()){
		 		dir.mkdir();
		 } 
		
		
		String sheetName=this.sheetName;
		String[] cellNames=this.cellNames;
		String[] orderedAttrs=this.orderedAttrs;
	 
		
		int columnBestWidth[]=new  int[dataList.size()];    //保存最佳列宽数据的数组
		 
		//打开文件
		WritableWorkbook book;
		try {
			book = Workbook.createWorkbook(new File(path+sheetName+".xls"));
			//生成名为“第一页”的工作表，参数0表示这是第一页 
			WritableSheet sheetOne=book.createSheet("第一页",0); 
			
			/****添加列名称****/
			for (int i = 0; i < cellNames.length; i++) {
				 if(!getTitile().trim().equals("")){ 
						// 设置标题格式
						WritableFont wfTitle = new jxl.write.WritableFont(
						WritableFont.COURIER, titile_font_size, WritableFont.BOLD, true);
						WritableCellFormat wcfTitle = new WritableCellFormat(wfTitle);
						// 设置水平对齐方式
						wcfTitle.setAlignment(jxl.format.Alignment.CENTRE);
						// 设置垂直对齐方式
					 	wcfTitle.setVerticalAlignment(jxl.format.VerticalAlignment.CENTRE);
						// 设置是否自动换行
						wcfTitle.setWrap(true);
					 
					 Label title=new Label(i,rows,getTitile().trim(),wcfTitle);
				 	 sheetOne.addCell(title);
				 	 
				 	 //合并单元格
				 	 sheetOne.mergeCells(0, rows, cellNames.length-1,rows);
				 	 sheetOne.setRowView(0,500);
				 	// rows=rows+1;
				 }
				 
			 	 Label titleTable=new Label(i,rows+1,cellNames[i]);
			 	 sheetOne.addCell(titleTable);
			 	 
			}
			/****添加列对应的值****/
			for (int i = 0; i < dataList.size(); i++) {
				 for (int j = 0; j < orderedAttrs.length; j++) {
					 	int rowSpacing=0;
					 	if(!getTitile().trim().equals("")){
					 		rowSpacing=i+2;
					 	}else{
					 		rowSpacing=i+1;
					 	}
					 	 Object object=getFieldValueByName(orderedAttrs[j],dataList.get(i)); 
						 Label nextValue=new Label(j,rowSpacing,object.toString());
						 sheetOne.addCell(nextValue);
					 	 int width=object.toString().length()+getChineseNum(object.toString());    ///汉字占2个单位长度
					 	 if(width<defaultWd){
						 		width=defaultWd;
						  }
					 	 
					 	 sheetOne.setColumnView(j,width);
				 } 
			} 
			book.write(); 
			book.close(); 
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		downloads(path);
		/**
		 * 删除临时文件夹
		 */
		deleteFolder(path);
	}
	
	/****
	 * Excel生成方法 多个数据
	 * @param dataList
	 */
	private  void exportExcels(List<? extends Object> ...dataLists){
			 int rows=0;//行数
			 //创建文件夹
			 String serverPath = ServletActionContext.getServletContext().getRealPath("/");
			 if(!new File(serverPath+"upload").exists()){
			 			new File(serverPath+"upload").mkdir();
			 }
			 String path=serverPath+"upload\\xls\\";	
			 File dir=new File(path);
			 if(!dir.exists()){
			 		dir.mkdir();
			 }  
		
		
			String sheetName=this.sheetName;
			String[][] sheet=this.sheet;
			if(dataLists.length==1){
				List<? extends Object> dataList=dataLists[0];
				String[] cellNames=sheet[0];
				String[] orderedAttrs=sheet[1];
				
				int columnBestWidth[]=new  int[dataList.size()];    //保存最佳列宽数据的数组
				 
				//打开文件
				WritableWorkbook book;
				try {
					book = Workbook.createWorkbook(new File(path+sheetName+".xls"));
					//生成名为“第一页”的工作表，参数0表示这是第一页 
					WritableSheet sheetOne=book.createSheet("第一页",0); 
					/****添加列名称****/
					for (int i = 0; i < cellNames.length; i++) {
						/***
						 * 设置标题
						 */
						if(!getTitile().trim().equals("")){
								/*****
								 * 设置标题样式
								 */
								// 设置标题格式
								WritableFont wfTitle = new jxl.write.WritableFont(
								WritableFont.COURIER, titile_font_size, WritableFont.BOLD, true);
								WritableCellFormat wcfTitle = new WritableCellFormat(wfTitle);
								// 设置水平对齐方式
								wcfTitle.setAlignment(jxl.format.Alignment.CENTRE);
								// 设置垂直对齐方式
							 	wcfTitle.setVerticalAlignment(jxl.format.VerticalAlignment.CENTRE);
								// 设置是否自动换行
								wcfTitle.setWrap(true);
							 
							 Label title=new Label(i,0,getTitile().trim(),wcfTitle);
						 	 sheetOne.addCell(title);
						 	 //合并单元格
						 	 sheetOne.mergeCells(0, 0, cellNames.length-1, 0);
						 	 Label titleTable=new Label(i,1,cellNames[i]);
						 	 sheetOne.addCell(titleTable);
						 	 sheetOne.setRowView(0,500);
						 	 
						 }else{
	
							 Label title=new Label(i,0,cellNames[i]);
						 	 sheetOne.addCell(title);
						 }
					}
					/****添加列对应的值****/
					for (int i = 0; i < dataList.size(); i++) {
						 for (int j = 0; j < orderedAttrs.length; j++) {
						 	int rowSpacing=0;
						 	if(!getTitile().trim().equals("")){
						 		rowSpacing=i+2;
						 	}else{
						 		rowSpacing=i+1;
						 	}
						 	 Object object=getFieldValueByName(orderedAttrs[j],dataList.get(i)); 
							 Label nextValue=new Label(j,rowSpacing,object.toString());
							 sheetOne.addCell(nextValue);
						 	 int width=object.toString().length()+getChineseNum(object.toString());    ///汉字占2个单位长度
						 	 if(width<defaultWd){
							 		width=defaultWd;
							  }
						 	 
						 	 sheetOne.setColumnView(j,width);
						  
						 	
						 } 
					} 
					book.write(); 
					book.close(); 
					
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} 
		  }
		  /**********
		   * 多数据
		   */
		 else{
			   if((dataLists.length*2)!=sheet.length){
				   System.out.println("格式有误！");
			   }else{
	
					//打开文件
					WritableWorkbook book;
					try {
						book = Workbook.createWorkbook(new File(path+sheetName+".xls"));
						//生成名为“第一页”的工作表，参数0表示这是第一页 
						WritableSheet sheetOne=book.createSheet("第一页",0); 
						
						/*
						 * 设置标题样式
						 */
							if(1==1){
								// 设置标题格式
								WritableFont wfTitle = new jxl.write.WritableFont(
								WritableFont.COURIER, titile_font_size, WritableFont.NO_BOLD, true);
								WritableCellFormat wcfTitle = new WritableCellFormat(wfTitle);
								// 设置水平对齐方式
								wcfTitle.setAlignment(jxl.format.Alignment.CENTRE);
								// 设置垂直对齐方式
							 	wcfTitle.setVerticalAlignment(jxl.format.VerticalAlignment.CENTRE);
								// 设置是否自动换行
								wcfTitle.setWrap(true);
							 
								 Label title=new Label(0,0,getTitile().trim(),wcfTitle);
							 	 sheetOne.addCell(title);
						 	 //合并单元格
							 	 sheetOne.mergeCells(0, 0, 6, 0);
							 	 sheetOne.setRowView(0,500);
							}
							/*
							 * 设置标题样式
							 */
								if(1==1 && getTitiles().length>0){
									// 设置标题格式
									WritableFont wfTitle = new jxl.write.WritableFont(
									WritableFont.COURIER, 10, WritableFont.BOLD, true);
									WritableCellFormat wcfTitle = new WritableCellFormat(wfTitle);
									// 设置水平对齐方式
									wcfTitle.setAlignment(jxl.format.Alignment.JUSTIFY);
									// 设置垂直对齐方式
								 	wcfTitle.setVerticalAlignment(jxl.format.VerticalAlignment.CENTRE);
									// 设置是否自动换行
									wcfTitle.setWrap(true);
								 
									 Label title=new Label(0,1,getTitiles()[0].trim(),wcfTitle);
								 	 sheetOne.addCell(title);
							 	 //合并单元格
								 	 sheetOne.mergeCells(0, 1, 3, 1);
								 	 sheetOne.setRowView(1,300);
								}
								if(1==1 && getTitiles().length>1){
									// 设置标题格式
									WritableFont wfTitle = new jxl.write.WritableFont(
						 			WritableFont.ARIAL, 10, WritableFont.BOLD, true);
									WritableCellFormat wcfTitle = new WritableCellFormat(wfTitle);
									// 设置水平对齐方式
									wcfTitle.setAlignment(jxl.format.Alignment.JUSTIFY);
									// 设置垂直对齐方式
								 	wcfTitle.setVerticalAlignment(jxl.format.VerticalAlignment.CENTRE);
									// 设置是否自动换行
									wcfTitle.setWrap(true);
								 
									 
									 int rowSpacing=dataLists[0].size()+5+3;
									 Label title=new Label(0,rowSpacing,getTitiles()[1].trim(),wcfTitle);
								 	 sheetOne.addCell(title);
								 	 //合并单元格
								 	 sheetOne.mergeCells(0, rowSpacing, 3, rowSpacing);
								 	 sheetOne.setRowView(rowSpacing,300);
								}
						    if(getMapTitile()!=null){
						    	for (int i = 0; i < getMapTitile().length; i++) {
						    		WritableFont wf_head = new WritableFont(WritableFont.ARIAL, 11,
											WritableFont.NO_BOLD, false, UnderlineStyle.NO_UNDERLINE,
											jxl.format.Colour.BLACK); // 定义格式 字体 下划线 斜体 粗体 颜色
						    		
						    		WritableCellFormat wcf_head2 = new WritableCellFormat(wf_head); 
									wcf_head2.setBackground(jxl.format.Colour.GREY_25_PERCENT);
									wcf_head2.setAlignment(jxl.format.Alignment.CENTRE); 
								//	wcf_head2.setBorder(jxl.format.Border.ALL, jxl.format.BorderLineStyle.THIN,jxl.format.Colour.BLACK); 
			
						    		if((i+1)%2==1){
						    			 Label title=new Label(i,2,getMapTitile()[i].trim(),wcf_head2);
									 	 sheetOne.addCell(title);
									 	 
									 	 
						    		}else{
						    			 Label title=new Label(i,2,getMapTitile()[i].trim());
									 	 sheetOne.addCell(title);
						    		}
						    		
						    		 int width=getMapTitile()[i].toString().length()+getChineseNum(getMapTitile()[i].toString().toString());    ///汉字占2个单位长度
								 	 if(width<defaultWd){
								 		width=defaultWd;
								 	 }
								 	 
								 	 sheetOne.setColumnView(i,width);
								}
						    }
						    for (int k = 0; k < dataLists.length; k++) {
						    	 	List<? extends Object> dataList=dataLists[k];
									String[] cellNames=sheet[k];
									String[] orderedAttrs=sheet[(k+dataLists.length)];
									
											/****添加列名称****/
										 
											for (int i = 0; i < cellNames.length; i++) {
				 
												 int rowSpacing=0;
												 if(k==0){
													 rowSpacing=5;
												 }else {
													 rowSpacing=dataLists[k-1].size()+4+5;
												 } 
												 Label title=new Label(i,rowSpacing,cellNames[i]);
											 	 sheetOne.addCell(title);
											  
											}
											/****添加列对应的值****/
											for (int i = 0; i < dataList.size(); i++) {
												 for (int j = 0; j < orderedAttrs.length; j++) {
													 
													 int rowSpacing=0;
													 if(k==0){
														 rowSpacing=i+6;
													 }else {
														 rowSpacing=dataLists[k-1].size()+5+5+i;
													 } 
													 
													 Object object=getFieldValueByName(orderedAttrs[j],dataList.get(i)); 
													 Label nextValue=new Label(j,rowSpacing,object.toString());
													 sheetOne.addCell(nextValue);
												 	 int width=object.toString().length()+getChineseNum(object.toString());    ///汉字占2个单位长度
												 	 if(width<defaultWd){
												 		width=defaultWd;
												 	 }
												 	 
												 	 sheetOne.setColumnView(j,width);
													
												 } 
											}  
							}
				    
						book.write(); 
						book.close(); 
						
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} 
				   
			   }
			  
		 }
	
		downloads(path);
		/**
		 * 删除临时文件夹
		 */
		deleteFolder(path);
		
		
	}
	/*******
	 * Excel列宽 设置
	 * @param sheet
	 * @param list
	 * @throws Exception
	 */
    public static void writeDataToSheet(WritableSheet sheet,List<List<String>> list) throws Exception{
        int columnBestWidth[]=new  int[list.get(0).size()];    //保存最佳列宽数据的数组
        
        for(int i=0;i<list.size();i++){
            List<String> row=list.get(i);
            for(int j=0;j<row.size();j++){
                 sheet.addCell(new Label(j,i,row.get(j)));
                 int width=row.get(j).length()+getChineseNum(row.get(j));    ///汉字占2个单位长度
                 if(columnBestWidth[j]<width)    ///求取到目前为止的最佳列宽
                     columnBestWidth[j]=width;
            }
        }
        
        for(int i=0;i<columnBestWidth.length;i++){    ///设置每列宽
            sheet.setColumnView(i, columnBestWidth[i]);
        }
    }
	/***
	 * 设置列宽
	 * @param context
	 * @return
	 */
    public static int getChineseNum(String context){    ///统计context中是汉字的个数
        int lenOfChinese=0;
        Pattern p = Pattern.compile("[\u4e00-\u9fa5]");    //汉字的Unicode编码范围
        Matcher m = p.matcher(context);
        while(m.find()){
            lenOfChinese++;
        }
        return lenOfChinese;
    }
 
	/**
	* 使用反射根据属性名称获取属性值 
	* 
	* @param  fieldName 属性名称
	* @param  o 操作对象
	* @return Object 属性值
	*/
 
	public static  Object getFieldValueByName(String fieldName, Object o) 
	{    
		/****
		 * 首先判断是否属于 key value的方式存放值
		 */
		if(o instanceof Map) {
			
				 Map<String, Object> map=(Map)o;
				 Object value =  map.get(fieldName);   
				 if(value==null){
					  return "";   
				 }else{
					  return value;   
				 }
			   
		}else{
			 try {    
			       String firstLetter = fieldName.substring(0, 1).toUpperCase();    
			       String getter = "get" + firstLetter + fieldName.substring(1);    
			       Method method = o.getClass().getMethod(getter, new Class[] {});    
			       Object value = method.invoke(o, new Object[] {});    
			       return value;    
			  }catch (Exception e) {    
			       System.out.println("属性不存在");    
			       return null;    
			  }    
		}
		
	  
	}  
	 
	

	/**********
	 * 对象属性方法 封装
	 * @return
	 */
	public String getSheetName() {
		return sheetName;
	}
	public void setSheetName(String sheetName) {
		this.sheetName = sheetName;
	}
	public String[] getCellNames() {
		return cellNames;
	}
	public void setCellNames(String[] cellNames) {
		this.cellNames = cellNames;
	}
	public String[] getOrderedAttrs() {
		return orderedAttrs;
	}
	public void setOrderedAttrs(String[] orderedAttrs) {
		this.orderedAttrs = orderedAttrs;
	}
	public List<? extends Object> getDataList() {
		return dataList;
	}
	public void setDataList(List<? extends Object> dataList) {
		this.dataList = dataList;
	}
	public String getExportType() {
		return exportType;
	}
	public void setExportType(String exportType) {
		this.exportType = exportType;
	}
	public String[][] getSheet() {
		return sheet;
	}
	public void setSheet(String[][] sheet) {
		this.sheet = sheet;
	}


	public String getTitile() {
		return titile;
	}


	public void setTitile(String titile) {
		this.titile = titile;
	}


	public String[] getTitiles() {
		return titiles;
	}


	public void setTitiles(String[] titiles) {
		this.titiles = titiles;
	}


	public String[] getMapTitile() {
		return mapTitile;
	}


	public void setMapTitile(String[] mapTitile) {
		this.mapTitile = mapTitile;
	}
    
    
    
} 

/************
 * 内部类处理excel 条数以及 导出的文件名称信息
 * @author Administrator
 *
 */
class ExcelService {
	
	private static int DATA_COUNT = 10000;

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static List splitList(List bigList){
		List splitList = new ArrayList(10);
		if(bigList == null || bigList.isEmpty()){
			return splitList;
		}
		int dataSize = bigList.size();
		int forCount = dataSize / ExcelService.DATA_COUNT;
		if(dataSize % ExcelService.DATA_COUNT != 0){
			forCount ++;
		}
		for(int i=0; i<forCount; i++){
			List smallList = new ArrayList();
			for(int j = ExcelService.DATA_COUNT*i; 
					j < Math.min(ExcelService.DATA_COUNT*(i+1), dataSize); j++){
						smallList.add(bigList.get(j));
					}
			splitList.add(smallList);
			
		}
		return splitList;
	}
	
	/**
	 * 
	 * @param fileName
	 * @return
	 */
	public static String encodeFileName(String fileName){
		try {
			return new String(fileName.getBytes(),
					"ISO8859-1");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	/**
	 * 
	 * @param fields
	 * @param obj
	 * @param repalceFields
	 * @param repalceValues
	 * @param replaceStrategyMap
	 * @return
	 */
	public static Map<String, Object> getFieldValues(Field[] fields, Object obj,
			Map<String, IChange> replaceStrategyMap) {

		Map<String, Object> fieldValues = new HashMap<String, Object>();

		for (int k = 0; k < fields.length; k++) {
			try {
				String fieldName = fields[k].getName();
				Object fieldValue = fields[k].get(obj);

				if (replaceStrategyMap != null) {
					IChange strategy = replaceStrategyMap.get(fieldName);
					if (strategy != null && fieldValue != null) {
						fieldValue = strategy.fieldChangeStrategy(fieldValue
								.toString());
					}
				}
				fieldValues.put(fieldName, fieldValue);
			} catch (IllegalArgumentException e) {
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			}
		}
		return fieldValues;
	}
}
