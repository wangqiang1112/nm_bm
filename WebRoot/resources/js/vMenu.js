function showPage(pageUrl,obj){
    try{
        parent.rightFrame.location.href = pageUrl;
    }catch(e){
        parent.rightFrame.location.href(pageUrl);
    }
}
$(document).ready(function() {
	$('.list a').click(function(){
            $('.list li').removeClass('selected');
		    $('.list li').removeClass('selected2');
		    if($(this).siblings('ul').length > 0){
		        $(this).parent("li").addClass("selected");
		    }else{
		        $(this).parent("li").addClass("selected2");
		    }
		
		    if($(this).siblings('ul').css('display')=='none'){
		        $(this).parent('li').siblings('li').removeClass('inactives');
		        $(this).addClass('inactives');
		        $(this).siblings('ul').slideDown(200);//.children('li');
		        $(this).parents('li').siblings('li').children('ul').parent('li').children('a').removeClass('inactives');
		        $(this).parents('li').siblings('li').children('ul').slideUp(200);
		    }else{
		        $(this).removeClass('inactives');
		        $(this).siblings('ul').slideUp(200);
		        $(this).siblings('ul').children('li').children('ul').parent('li').children('a').addClass('inactives');
		        $(this).siblings('ul').children('li').children('ul').slideUp(200);
		        $(this).siblings('ul').children('li').children('a').removeClass('inactives');
		    }
	});
});
