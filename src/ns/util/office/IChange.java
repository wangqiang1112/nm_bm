/* 

 * @(#)IChange.java  2012-4-26

 *

 * YUANWANG HIGHLY CONFIDENTIAL INFORMATION: 

 * THIS SOFTWARE CONTAINS CONFIDENTIAL INFORMATION AND TRADE SECRETS OF YUANWANG 

 * INCORPORATED AND MAY BE PROTECTED BY ONE OR MORE PATENTS. USE, DISCLOSURE, OR 

 * REPRODUCTION OF ANY PORTION OF THIS SOFTWARE IS PROHIBITED WITHOUT THE PRIOR 

 * EXPRESS WRITTEN PERMISSION OF YUANWANG INCORPORATED. 

 * Copyright 2009 YUANWANG Incorporated. All rights reserved as an unpublished 

 * work. 

 * 

*/

 

/*

 Modification Log:

-----------------------------------------------------------------------------------

 Version   Date         By                Notes

 ----   ----------   ----------------   -------------------------------------------

 V1.0   2011/12/12     linzhenpei       Create

 

-----------------------------------------------------------------------------------

 

*/


package ns.util.office;

public interface IChange {
	public String fieldChangeStrategy(String initFileValue);
}
