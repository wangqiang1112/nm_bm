	function f_page(index){
				var id=$('#stafferId').val();
				if(null==id||""==id){
					if(index==1)
						window.location.href="baseAdd.do";
					else 
						alert("请先填写基本资料");
				}else{
					if(index==1)
						window.location.href="baseAdd.do?stafferId="+id;
					else if(index==2)
						window.location.href="unitAdd.do?stafferId="+id;
					else if(index==3)
						window.location.href="secretAdd.do?stafferId="+id;
					else if(index==4)
						window.location.href="expelAdd.do?stafferId="+id;
					else if(index==5)
						window.location.href="eduList.do?stafferId="+id;
					else if(index==6)
						window.location.href="jobList.do?stafferId="+id;
					else if(index==7)
						window.location.href="familyList.do?stafferId="+id;
					else if(index==8)
						window.location.href="fileList.do?stafferId="+id;
				}
	}