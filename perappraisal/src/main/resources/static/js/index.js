// JavaScript Document
/*轮播图*/
$(function(){
	var sx=0;
	var myindex=100
	var timer=null
	$('.banner ol li').click(function(e) {
		myindex++
        $(this).addClass('current').siblings().removeClass()
		var index=$(this).index()
		$('.banner .banner_list').stop().animate({left:-sx * 100+'%' },500)
		sx=index
    });
	
	$('.banner .right').click(function(e) {
        sx++;
		if(sx>4){sx=0}
		$('.banner ol li').eq(sx).addClass('current').siblings().removeClass()
		$('.banner .banner_list').stop().animate({left:-sx * 100+'%' },500)
    });
	$('.banner .left').click(function(e) {
        sx--;
		if(sx<0){sx=4}
		$('.banner ol li').eq(sx).addClass('current').siblings().removeClass()
		$('.banner .banner_list').stop().animate({left:-sx * 100+'%' },500)
    });	
	//自动播放模块
	timer=setInterval(autoplay,3000)
	function autoplay(){
		sx++
		if(sx>4){sx=0}
		$('.banner ol li').eq(sx).addClass('current').siblings().removeClass()
		$('.banner .banner_list').stop().animate({left:-sx * 100+'%' },500)
	}	
	//鼠标移上停止定时器模块
	$('.banner').hover(function(e) {
        //停止自动播放，清除定时器；
		clearInterval(timer)
    },function(e){
		clearInterval(timer)
		timer=setInterval(autoplay,3000)
    });
})



$(function(){
	var time = setInterval(function () {
		t();}, 5000)


	function t() {
		var he = $(".notice_active>ul>li").height();//找到li高
		$(".notice_active>ul>li").eq(0).appendTo($(".notice_active>ul")); //复制第一个到最后一个
		$(".notice_active>ul").animate({
			"marginTop": "-" + he
		}, 500, function () {
			$(".notice_active>ul").css({
				"marginTop": 0
			})
		})
	}
});

$(function(){
	$(".money .all p").hover(function(){
  		$(".hint").toggle();
	});
});
