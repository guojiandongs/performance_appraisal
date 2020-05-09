//自定义js

//公共配置
function account(){
    var customer = $("#customer").val();
    if(!customer){
        layer.confirm("您还没有登录,请先登录!", {
            btn : [ '确定' ]
        }, function() {
            window.location.href="http://47.92.151.206/m/login.html?anotherPlaceLogin=jt_jump";
        });
        return;
    }
    window.location.href="/home/wechat/trdorder/weblist";
}