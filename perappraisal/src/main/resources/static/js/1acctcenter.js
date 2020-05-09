(function ($) {
    $.fn.extend({
        "removerCover": function () {
            $('#coverid').remove();
        },
        "cover": function () {
            this.removerCover();
            var _width = this.outerWidth() + 2;
            var _height = this.outerHeight() + 2;
            var _left = this.position().left;
            var _top = this.position().top;
            var _zIndex = '99999999';
            var $div = $("<div id='coverid'><div>");
            $div.css({
                width: _width,
                height: _height,
                top: _top,
                left: _left,
                zIndex: _zIndex,
                position: 'absolute'
            }).appendTo('body');
        },
        "clear": function () {
            $(':input', this).not(':button, :submit, :reset,:hidden').val('').removeAttr('checked').removeAttr('selected');
        }
    });
})(jQuery);
$(function () {
    //$('input').focusout(function(event) {
    //   $('b').removeClass('nogocolor').text('');
    //});
    $(".carchange").click(function () {
        var _text = "",
            _showflag = false;
        if ($(this).attr('title') == '修改') {
            $(this).attr('title', '取消');
            _text = '取消';
            _showflag = true;
        } else {
            $(this).attr('title', '修改');
            _text = '修改';
            _showflag = false;
        }
        $(".carchange").attr('title', '修改').text('修改');
        $(".carchange1").attr('title', '重新评测').text('重新评测');
        $(".carchange2").attr('title', '认证').text('认证');
        $(this).text(_text).attr('title', _text);
        $('.carusult').hide().attr('title', _text);
        if (_showflag) {
            $(this).parents('dd').next('.carusult').show();
        } else {
            $(this).parents('dd').next('.carusult').hide();
        }
    });
    $(".carchange1").click(function () {
        var _text = "",
            _showflag = false;
        if ($(this).attr('title') == '重新评测') {
            $(this).attr('title', '取消');
            _text = '取消';
            _showflag = true;
        } else {
            $(this).attr('title', '重新评测');
            _text = '重新评测';
            _showflag = false;
        }
        $(".carchange").attr('title', '修改').text('修改');
        $(".carchange2").attr('title', '认证').text('认证');
        $(this).text(_text);
        $('.carusult').hide();
        if (_showflag) {
            $(this).parents('dd').next('.carusult').show();
        } else {
            $(this).parents('dd').next('.carusult').hide();
        }
    });
    $(".carchange2").click(function () {
        var _text = "",
            _showflag = false;
        if ($(this).attr('title') == '认证') {
            $(this).attr('title', '取消认证');
            _text = '取消认证';
            _showflag = true;
        } else {
            $(this).attr('title', '认证');
            _text = '认证';
            _showflag = false;
        }
        $(".carchange").attr('title', '修改').text('修改');
        $(".carchange1").attr('title', '重新评测').text('重新评测');
        $(this).text(_text).attr('title', _text);
        $('.carusult').hide();
        if (_showflag) {
            $(this).parents('dd').next('.carusult').show();
        } else {
            $(this).parents('dd').next('.carusult').hide();
        }
    });
    $('.carusult1 a').click(function () {
        $('.carusult1 a').removeClass('cs1change');
        $(this).addClass('cs1change');
        var showcode = $('.carusult1.carusult2 a[class=cs1change]').attr('code');
        var hidecode = $('.carusult1.carusult2 a[class!=cs1change]').attr('code');

        if (showcode == '1002') {
            $('#' + showcode).show();
            $("#bankaccount").parent().hide().attr('ignore', 'ignore');
        }
        if (showcode == '1003') {
            $('#' + hidecode).hide();
            $("#bankaccount").parent().show().removeAttr('ignore');
        }
        $('#channelcode').val(showcode);
        $(":input:visible").not(":hidden,:submit,:button").removeAttr('ignore');
        $(":input:hidden").not(":hidden,:submit,:button").attr('ignore', 'ignore');
    });
    //风险测评
    $fxcpForm = $("#doEstimateForm33").kdValidform();

    //取消
    $("#canceldiv").click(function (event) {
        $('#codediv').modal('hide');
    });

    //打开验证码模态框，获取手机验证码
    function checkcode(wait, $btn, type) {
        $("#checktip").text($("#checktip").attr('title')).css('color', '#333');
        $(':input', '#codediv').not(':button, :submit, :reset,:hidden').val('').removeAttr('checked').removeAttr('selected');
        _dialog(wait, $btn, type);
    }

    //获取验证码，修改登录密码
    $("#lgnphonecode").click(function () {
        var wa = $("#lgnphonecode").val();
        var array = wa.match(/\d+/);
        if (array != null) {
            wa = array[0];
        }
        if (wa && wa > 0 && wa < 150) {
            return;
        }
        checkcode(150, $("#lgnphonecode"), "", "lgpwd");
    });
    //获取验证码，修改交易密码
    $("#trdphonecode").click(function () {
        var wa = $("#trdphonecode").val();
        var array = wa.match(/\d+/);
        if (array != null) {
            wa = array[0];
        }
        if (wa && wa > 0 && wa < 150) {
            return;
        }
        checkcode(150, $("#trdphonecode"), "", "tapwd");
    });
    //获取验证码，修改手机
    $("#chgphonecode").click(function () {
        var wa = $("#chgphonecode").val();
        var array = wa.match(/\d+/);
        if (array != null) {
            wa = array[0];
        }
        if (wa && wa > 0 && wa < 150) {
            return;
        }
        var phonenum = $('#validphone #newphone').val();
        $('#validphone .nogocolor').remove();
        if ($.trim(phonenum).length < 1) {
            return;
        }
        var url = 'mall/checkAction4zjv2!phoneExsits.do';
        $.post(url, {
            phonenum: phonenum
        }, function (data) {
            var data = eval("(" + data + ")");
            if (data.result == '1') {
                checkcode(150, $("#chgphonecode"), phonenum, "phcode");
            } else {
                layer.alert(data.message);
                $("#newphone").siblings('b').addClass('nogocolor').text(data.message);
            }
        });
    });
    $updLogForm = $("#updloginpwd").kdValidform(); //修改登录密码表单
    $updtrdForm = $('#updtrdpwd').kdValidform(); //修改交易密码表单
});

//计时器
function timeCounter(wait, $btn) {
    if (wait === 0) {

        $btn.attr("cursor", "default").text("获取验证码");

        wait = 150;

    } else {

        $btn.attr("cursor", "not-allowed").text("重新发送(" + wait + ")");

        wait--;

        setTimeout(function () {
            timeCounter(wait, $btn)
        }, 1000)

    }
}

function refreshpage() {
    window.location.href = 'myselfinfo.html';
}

function toNextStep4login(formid, showid) {
    var phonecode = $.trim($('#' + formid + ' #phonecode').val());
    if (!phonecode.match(/^\d{6}$/)) {
        return;
    }
    var url = 'mall/validAcion4zjv2!checkPhoneCode.do';
    $.post(url, $('#' + formid).serializeArray(), function (data) {
        var data = eval("(" + data + ")");
        if (data.result == '1') {
            nextstep(formid, showid);
        } else {
            //手机验证码错误；
            layer.alert(data.message);
        }
    });
}

function toNextStep4trd(formid, showid) {
    var deacct = "${signbank.depositaccount!''}";
    if (deacct == null) {
        alert("您还未绑定银行卡！");
        return;
    }
    var phonecode = $.trim($('#' + formid + ' #phonecode').val());
    if (!phonecode.match(/^\d{6}$/)) {
        return;
    }
    var url = 'mall/validAcion4zjv2!checkPhoneCode.do';
    $.post(url, $('#' + formid).serializeArray(), function (data) {
        var data = eval("(" + data + ")");
        if (data.result == '1') {
            nextstep(formid, showid);
        } else {
            //手机验证码错误；
            layer.alert(data.message);
        }
    });
}

//显示下一个div
function nextstep(formid, showid) {
    $('#' + formid + '  div:not("#' + showid + '")').hide();
    $('#' + formid + '  #' + showid).show().children().show();
    if(showid='valid2sc'){
    	$('.loginPhone').show();
    	$('.tips').show();
    }
}

//修改登录密码
function updLoginPwd(formid, showid) {
    $('.nextstep a', $("#" + formid)).attr("disabled", true);
    if (!$("#" + formid).kdValidform().check()) return;
    var _pwd = $.trim($("#password").val());
    var desPwd = $.des.getDes(_pwd);
    $("#password").val(desPwd);
    $("#password2").val(desPwd);
    $.ajax({
        cache : true,
        type : "POST",
        url : "/home/resetloginpwd",
        data :$('#' + formid).serialize(),// 你的formid
        async : false,
        error : function(request) {
            layer.alert("修改失败");
        },
        success : function(data) {
            if (data.code == 0) {
                layer.confirm(data.msg, {
                    btn : [ '确定' ]
                }, function() {
                    window.location.href="/home/open/accontset";
                });
            } else {
                layer.alert(data.msg)
            }

        }
    });
}

//修改交易密码
function updTradePwd(formid, showid) {
    if (!$("#" + formid).kdValidform().check()) return;
    var phonecode = $("#updtrdpwd #phonecode").val();
    var tradepwd = $.trim($("#tradePWD11").val());
    var desPwd = $.des.getDes(tradepwd);
    var url = "mall/setCustPasswordZjv2!resetTradePwd.do";
    $.post(url, {
        npassword: desPwd,
        phonecode: phonecode
    }, function (data, textStatus, xhr) {
        var data = eval("(" + data + ")");
        if (data.result == '1') {
            nextstep(formid, showid);
            timeOutRelocate(3, 'myselfinfo.html', 'successTPD');
        } else {
            layer.alert(data.message);
        }
    });
}

//登出
function logout() {
    $.post("mall/newlogin!newlogout.do", {}, function (data) {
        var result = eval("(" + data + ")");
        if (result.result == 1) {
            window.location.href = "/kasp/login.html";
        } else {
            layer.alert(data.message, refreshpage);
        }
    });
}

//延迟刷新
function timeOutRelocate(time, url, id) {
    var n = time,
        temp = setInterval(function () {
            if (n == 0) {
                clearInterval(temp);
                window.location.href = url;
            }
            if (n > 0) $("#" + id).text(n--);
        }, 1000);
}

//延迟登出
function timeOutlogout(time, loFunc, id) {
    var n = time,
        temp = setInterval(function () {
            if (n == 0) {
                clearInterval(temp);
                loFunc();
            }
            if (n > 0) $("#" + id).text(n--);
        }, 1000);
}

//密保问题设置
function selectedChange(formid, selectid) {
    var $selected = $('#' + selectid);
    var $formid = $("#" + formid);
    var _val = $selected.val();
    var $allSelect = $('select', $formid);
    $selected.siblings('b').removeClass('nogocolor').text('');
    var allVal = []; //缓存所有select选中的值
    var allid = []; //缓存所有select的id
    $allSelect.each(function (index, el) {
        allVal[index] = $(el).val();
        allid[index] = $(el).attr('id');
    });
    $allSelect.each(function (index, el) {
        var cachselect = $('#cachselect').html();//取出缓存的全部option内容
        $(el).html('').append(cachselect);//添加到当前循环的对象
        if ($(el).attr('id') == allid[index]) {//设置为默认选中
            $(el).val(allVal[index]);
        }
    });
    var $otherSelect = $('select:not("#' + selectid + '")', $formid); //非当前选中项
    $otherSelect.each(function (index, el) {
        var indexVal = $(el).val();
        var indexId = $(el).attr('id');
        $('option[value=' + _val + ']', $(el)).remove(); //移除其他select当前选中的option
        if ('-1' != indexVal) {
            $('option[value=' + indexVal + ']', $selected).remove();
            $selected.val(_val);//移除当前select，其他select选中的option
        }
        for (var i = 0; i < allid.length; i++) {
            if (allid[i] != indexId && '-1' != allVal[i]) {
                $('option[value=' + allVal[i] + ']', $(el)).remove();
                $(el).val(indexVal);
            }
        }
    });
}

//修改密保问题
function updSafetyQuestion(formid, showid) {
    if (!$("#" + formid).kdValidform().check()) return;
  
    $('b', $('#' + formid)).removeClass('nogocolor').text('');
    
    var qesArray = [];
    var flag = true;
    $('select', $('#' + formid)).each(function (index, el) {
        qesArray[index] = $(el).val();
        var _value = $(el).val();
        if (_value == '-1') {
            $(el).siblings('b').addClass('nogocolor').text("请选择密保问题！");
            flag = false;
            return false;
        }
    });
    if (!flag) return;
    $('input', $('#' + formid)).each(function (index, el) {
        var _value = $(el).val();
        if (_value == null || _value.length < 1) {
            $(el).siblings('b').addClass('nogocolor').text("答案不能为空！");
            flag = false;
            return false;
        }
    });
    if (!flag) return;
    if (qesArray[0] == qesArray[1]) {
        $("#q2").siblings('b').addClass('nogocolor').text("密保问题不能相同！");
        return;
    }
    if (qesArray[0] == qesArray[2]) {
        $("#q3").siblings('b').addClass('nogocolor').text("密保问题不能相同！");
        return;
    }
    if (qesArray[1] == qesArray[2]) {
        $("#q3").siblings('b').addClass('nogocolor').text("密保问题不能相同！");
        return;
    }
    var url = "mall/saftyQuestion4zjv2!setQuestion.do";
    $.post(url, $("#" + formid).serializeArray(), function (data) {
        var data = eval("(" + data + ")");
        if (data.result == '1') {
        	
            //nextstep(formid, showid);
        	layer.alert("密保问题设置成功", function () {
                window.location.href = 'myselfinfo.html';
            });
        } else {
            layer.alert(data.message, refreshpage);
        }
    });
}

//验证密保问题
function validQuestion(formid, showid) {
    alert("222");
    //if (!$("#"+formid).kdValidform().check()) return;
    var $formid = $('#' + formid);
    $('b', $formid).removeClass('nogocolor').text("");
    var flag = true;
    var _pwd = $.trim($('#loginpwd', $formid).val());
    if (_pwd.length < 1) {
        $('#loginpwd', $formid).siblings('b').addClass('nogocolor').text($('#loginpwd', $formid).attr('nullmsg'));
        return;
    }
    $('input[type=text]:visible', $formid).each(function (index, el) {
        if ($.trim($(el).val()).length < 1) {
            $(el).siblings('b').addClass('nogocolor').text($(el).attr('nullmsg'));
            flag = false;
            return false;
        }
    });
    if (!flag) {
        return
    }
    var desPwd = $.des.getDes(_pwd);
    $('#loginpwd', $formid).val(desPwd);
    // var url = "mall/validQuestion4zjv2!validate4ChangeMobile.do";
    var url = "/fund/changphone";

    $.post(url, $formid.serializeArray(), function (data) {
        var data = eval("(" + data + ")");
        if (data[0].success) {
            nextstep(formid, showid);
            //  timeOutRelocate(3, 'myselfinfo.html', 'relocation');
        } else {
            $('#loginpwd', $formid).val('');
            layer.alert(data[0].msg);
        }
    });
    $.ajax({
        cache : true,
        type : "POST",
        url : "/changephone/changphone",
        data :$('#' + formid).serialize(),// 你的formid
        async : false,
        error : function(request) {
            layer.alert("修改失败");
        },
        success : function(data) {
            if (data.code == 0) {
                nextstep(formid, showid);
            } else {
                $('#loginpwd', $formid).val('');
                layer.alert(data.msg);
            }

        }
    });
}

//修改手机号
function setphone(formid, showid) {
    $('b', $('#' + formid)).removeClass('nogocolor').text('');
    if ($('#' + formid).kdValidform().check()) return;
    var flag = true;
    $('input[type=text]:visible', $("#" + formid)).each(function (index, el) {
        if ($.trim($(el).val()).length < 1) {
            $(el).siblings('b').addClass('nogocolor').text($(el).attr('nullmsg'));
            flag = false;
            return false;
        }
    });
    if (!flag) {
        return
    }
    if (!$('#' + formid).kdValidform().check($('#newphone'), true)) {
        // $('#newphone').siblings('b').addClass('nogocolor').text($('#newphone').attr('errormsg'));
        return;
    }
    if (!$('#' + formid).kdValidform().check($('#nphonecode'), true)) {
        //$('#nphonecode').siblings('b').addClass('nogocolor').text($('#nphonecode').attr('errormsg'));
        return;
    }
    $.ajax({
        cache : true,
        type : "POST",
        url : "/setphone/setphone",
        data :$('#' + formid).serialize(),// 你的formid
        async : false,
        error : function(request) {
            layer.alert("修改失败");
        },
        success : function(data) {
            if (data.code == 0) {
                layer.confirm(data.msg, {
                    btn : [ '确定' ]
                }, function() {
                     window.location.href="/home/open/accontset";
                });
            } else {
                layer.alert(data.msg)
            }

        }
    });
    var url = "mall/setCustPhone4zjv2!setphone.do";
    $.post(url, $("#" + formid).serializeArray(), function (data) {
        var data = eval("(" + data + ")");
        if (data.result == '1') {
            nextstep(formid, showid);
            timeOutRelocate(3, 'login.html', 'changephone');
        } else {
            layer.alert(data.message, refreshpage);
        }
    });
}

//发送手机验证码
function sendcode(wait, $btn, type) {

    var validcode = $.trim($("#validcode").val());
    var mobiletel = $('#validphone #newphone').val();
    if(mobiletel==''){
    	mobiletel = $('#oldphonenumber').val();
    }
    $.post('mall/sendPhoneCode4zjv2!sendPhoneCode.do', {
        validcode: validcode,
        mobiletel: mobiletel

    }, function (data) {
        var data = eval("(" + data + ")");
        if (data.result == '0') {
            $('#codediv').modal('hide');
            if ("验证码错误！" == data.message) {
                layer.alert(data.message, null);
            } else {
                timeCounter(wait, $btn);
            }
        } else {
            $('#codediv').modal('hide');
            layer.alert(data.message);
        }
    });
}

//发送邮件
function sendEmail() {
    
    $('b', $('#emailform')).removeClass('nogocolor').text('');
    var url = 'mall/shx_email!sendEmailSimpleVersion.do';
    var emailAddress = $.trim($('#emailAddress').val());
    if (emailAddress.length < 1) {
        $('#emailAddress').siblings('b').addClass('nogocolor').text('请输入邮箱地址！');
        return;
    }
    var reg = new RegExp("^([0-9a-zA-Z]([-.\w]*[0-9a-zA-Z])*@(([0-9a-zA-Z])+([-\w]*[0-9a-zA-Z])*\.)+[a-zA-Z]{2,9})$");
    if (!reg.test(emailAddress)) {
        $('#emailAddress').siblings('b').addClass('nogocolor').text('邮箱地址不合法！');
        return;
    }
    $("#sendemail").attr("disabled", true);
    $.post(url, $('#emailform').serializeArray(), function (data) {
        var redata = eval("(" + data + ")");
        if (redata.result == '1') {
            layer.alert(redata.message, function () {
                window.location.href = 'myselfinfo.html';
            });
        } else if (redata.result == '0') {
            layer.alert(redata.message, function () {
                $("#emailAddress").val("");
                $("#sendemail").attr("disabled", false);
                layer.closeAll();
            });
        }

    });
}

//完善客户信息
function addCustOtherInfo() {
    var url = "mall/addCust4zjv2!addCust.do";
    $.post(url, $('#otherinfo').serializeArray(), function (data) {
        if (Eop.Util.isNotEmpty(data)) {
            var data = eval('(' + data + ')');
            if (data.flag === '1') {
                layer.alert(data.msg, function () {
                    window.location.href = "myselfinfo.html";
                });
            } else {
                layer.alert("客户资料保存失败,请重试");
            }
        }
        //console.log(data);
    });
}

//刷新验证码图片
function refreshImg(obj) {
    $(obj).attr('src', '/kasp/validcode.do?vtype=admin&rmd=' + new Date().getTime());
}

function _dialog(wait, $btn, type) {
    let _div = `
            <div class="modal fade" id="codediv" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
                <div class="modal-dialog">
                    <div class="modal-content" style="background-color: #fff;">
                        <div class="modal-header">
                            <button type="button" class="close" data-dismiss="modal">
                                <span aria-hidden="true">&times;</span><span class="sr-only">关闭</span></button>
                            <h4 class="modal-title" id="myModalLabel">验证码</h4></div>
                        <div class="modal-body" style="text-align:center;">
                            <span>验证码&nbsp;<em style="color:red">*</em>&nbsp;</span>
                            <input name="validcode" style="width:100px;height:30px;border:1px solid #eee" type="text" value=""
                                   id="validcode" datatype="*" class="input" errormsg="请输入右侧的验证码！"/>
                            <img id="codeimg" style="width: 100px;height: 30px;cursor: pointer;border: 1px solid #eee;"
                                 title="看不清楚？"/>
                            <span class="" id="checktip" title="请输入验证码！">请输入验证码！</span></div>
                        <div class="modal-footer">
                            <a href="javascript:void(0);" class="kdmall-btn" id="submitok">确定</a>&nbsp;&nbsp;
                            <a class="kdmall-btn" href="javascript:void(0);" id="canceldiv">取消</a></div>
                        <div></div>
                    </div>
                </div>
            </div>`;
    let $div = $(_div);
    $('#codediv').remove();
    $div.appendTo('body');
    $("#codeimg").attr('src', '/kasp/validcode.do?vtype=admin&rmd=' + new Date().getTime());
    //验证码绑定刷新事件
    $('#codeimg').bind('click', function () {
        refreshImg(this);
        $("#checktip").text($("#checktip").attr('title')).css('color', '#333');
    });
    $('#codediv').modal('show');
    $('#submitok').click(function (event) {
        var validcode = $.trim($('#validcode').val());
        if (validcode == null || '' == validcode) {
            $("#checktip").text("验证码不能为空!").css('color', '#ec403e');
            return;
        }
        sendcode(wait, $btn, type);
    });
    $('#canceldiv').click(function () {
        $('#codediv').modal('hide');
    });
}

var Project = Project || {};
Project.Basic = {
    init: function () {
        var self = this;
        $("#provinceSelect").change(function () {
            self.changeCity();
        });
        self.initSelect();
    },
    hjinit: function () {
        var self = this;
        $("#hjprovinceSelect").change(function () {
            self.hjchangeCity();
        });
        self.hjinitSelect();
    },
    initSelect: function () {
        var self = this;
        $("#provinceSelect").val($("#provinceSelect").attr("data-value"));
        self.changeCity();
    },
    changeCity: function () {
        $("#citySelect").html("<option value='-1'>--\u8bf7\u9009\u62e9--</option>");
        var selectedProvince = $("#provinceSelect").children("option:selected").val();
        var selectedProvinceText = $("#provinceSelect").children("option:selected").text();
        var citySelect = $("#citySelect").attr("data-value");
        if (selectedProvince != undefined || selectedProvince != -1) {
            $.get("mall/provincedict!getCitiesByProvince.do", {
                ajax: "yes",
                ref_key: selectedProvince,
                ref_id:"D00003"
            }, function (data) {
                var result = $.parseJSON(data);
                if (result.flag == 1) {
                    var cities = [];
                    cities = result.items;
                    var options = " ";
                    for (var c in cities) {
                        options += "<option value='" + cities[c].dict_key + "' >" + cities[c].dict_value + "</option>";
                    }
                    $("#citySelect").append(options);
                    $("#citySelect").find("option[value = '"+citySelect+"']").attr("selected","selected");
                    //$("#citySelect").val(citySelect);
                }
            });
        }
    },
    hjinitSelect: function () {
        var self = this;
        $("#hjprovinceSelect").val($("#hjprovinceSelect").attr("data-value"));
        self.hjchangeCity();
    },
    hjchangeCity: function () {
        $("#hjcitySelect").html("<option value='-1'>--\u8bf7\u9009\u62e9--</option>");
        var selectedProvince = $("#hjprovinceSelect").children("option:selected").val();
        var selectedProvinceText = $("#hjprovinceSelect").children("option:selected").text();
        var hjcitySelect = $("#hjcitySelect").attr("data-value");
        if (selectedProvince != undefined || selectedProvince != -1) {
            $.get("mall/provincedict!getCitiesByProvince.do", {
                ajax: "yes",
                ref_key: selectedProvince,
                ref_id:"HJ0001"
            }, function (data) {
                var result = $.parseJSON(data);
                if (result.flag == 1) {
                    var cities = [];
                    cities = result.items;
                    var options = " ";
                    for (var c in cities) {
                        options += "<option value='" + cities[c].dict_key + "' >" + cities[c].dict_value + "</option>";
                    }
                    $("#hjcitySelect").append(options);
                    $("#hjcitySelect").val(hjcitySelect);
                }
            });
        }
    }
};
$(function () {
    Project.Basic.init();
    var t2 = window.setTimeout("Project.Basic.hjinit()",1000);//使用字符串执行方法 
});

//绑定银行卡or绑定资金账户下一步
function bindNextStep(formid, curObj) {
    var showid = $('.carusult1 a[class=cs1change]').attr("name");
    $(':input', '#' + formid).not(':button, :submit, :reset,:hidden').val('').removeAttr('checked').removeAttr('selected');
    $("#channelcode").val("");
    $('div:not("#' + showid + '")', $('#' + formid)).hide();
    $("#" + showid, $('#' + formid)).show().children().show();
    $(curObj).hide();
    if ('acct2sc' == showid) $('#bind').show().parent().show();
    $("#1002").hide();
    $('b', $('#' + formid)).removeClass('nogocolor').text('');
}

//绑定银行卡提交
function submitBindAct(formid, showid, obj) {
    var showcode = $('.carusult1.carusult2 a[class=cs1change]').attr('code');
    if (showcode == null || showcode.length < 1) {
        layer.alert("请选择银行！");
        return;
    }
    var name = $.trim($('#certificatename').val());
    var certno = $.trim($('#certificateno').val());
    var bankacct = $.trim($('#bankaccount').val());
    if (name.length < 1) {
        validClass($('#certificatename'), '真实姓名不能为空!');
        return;
    } else {
        validClass($('#certificatename'), '');
    }
    if (certno.length < 1) {
        validClass($('#certificateno'), '身份证号不能为空!');
        return;
    } else {
        validClass($('#certificateno'), '');
        if (!$('#acctform').kdValidform().check($('#certificateno'), true)) {
            validClass($('#certificateno'), '身份证号不正确!');
            return;
        }
    }
    if (showcode == '1003') {
        if (bankacct.length < 1) {
            validClass($('#bankaccount'), '银行卡号不能为空!');
            return;
        } else {
            validClass($('#bankaccount'), '');
        }
    }
    $(obj).attr('disabled', true).text('绑定中...').cover();
    var url = 'mall/openacct4zjv2!openAccount.do?ajax=yes';
    $.post(url, $("#acctform").serializeArray(), function (data) {
        var data = eval('(' + data + ')');
        if (data.result == '1') {
            if (showcode == '1003') {
                $('#acct3td').text('恭喜您，绑定银行卡成功！');
            } else {
                $('#acct3td').text('恭喜您，工行预指定成功！');
            }
            nextstep(formid, showid);
            $(obj).hide();
            timeOutRelocate(3, 'myselfinfo.html', '');
        } else {
            layer.alert(data.message);
            $(obj).attr('disabled', false).text('绑定').removerCover();
        }
    });
}

//绑定资金账户提交
function submitBindCap(formid, showid, obj) {
    var showcode = $('.carusult1.carusult2 a[class=cs1change]').attr('code');
    var certificatenocap = $.trim($('#certificatenocap').val());
    var bankaccountcap = $.trim($('#bankaccountcap').val());
    var opassword = $.trim($('#opassword').val());
    var despwd = $.des.getDes(opassword);
    //if ($("#" + formid).kdValidform().check()) return;
    $('#opassword').val(despwd);
    if (certificatenocap.length < 1) {
        layer.alert('证件号码不能为空!');
        return;
    }
    if (bankaccountcap.length < 1) {
        layer.alert('资金账号不能为空!');
        return;
    }
    if (opassword.length < 1) {
        layer.alert('交易密码不能为空!');
        return;
    }
    $(obj).attr('disabled', true).text('绑定中...').cover();
    var url = 'mall/openacct4zjv2!bindAccount.do?ajax=yes';
    $.post(url, $("#acctform").serializeArray(), function (data) {
        var data = eval('(' + data + ')');
        if (data.result == '0') {
            layer.alert(data.message);
            $('#opassword').val('');
            $(obj).attr('disabled', false).text('绑定').removerCover();
        } else {
            nextstep(formid, showid);
            timeOutRelocate(3, 'myselfinfo.html', '');
        }
    });
}

$(".btnSubmitFXCP").bind("click", function () {
    if (!$('#doEstimateForm33').kdValidform().check()) return;
    $('#pass').siblings('span').removeClass('kdValidform_checktip kdValidform_wrong').text('');
    if (!$('#pass').prop("checked")) {
        $('#pass').siblings('span').addClass('kdValidform_checktip kdValidform_wrong').text('请选择！');
        return;
    }
    var url = 'mall/newRiskEstimate!doEstimate.do?ajax=yes';
    $("#btnSubmitFXCP").attr("disabled", false).cover();
    $.post(url, $("#doEstimateForm33").serializeArray(), function (data) {
        var data = eval('(' + data + ')');
        layer.alert(data.message, refreshpage)
    });
});


function dorisk() {
    if (!$('#doEstimateForm33').kdValidform().check()) return;
//    $('#pass').siblings('span').removeClass('kdValidform_checktip kdValidform_wrong').text('');
//   if (!$('#pass').prop("checked")) {
//       $('#pass').siblings('span').addClass('kdValidform_checktip kdValidform_wrong').text('请选择！')
//        return;
//    };
    var url = 'mall/newRiskEstimate!doEstimate.do?ajax=yes';
    $("#btnSubmitFXCP").attr("disabled", false).cover();
    $.post(url, $("#doEstimateForm33").serializeArray(), function (data) {
        var data = eval('(' + data + ')');
        if (data.result == '1') {
            layer.alert(data.message, refreshpage)
        } else if (data.result == '0') {
            layer.alert(data.message, function () {
                window.location.href = 'bankmanage.html';
            })
        }

    });
}


function validClass($obj, msg) {
    $obj.next('b').text(msg).addClass('nogocolor');
    if (msg == null || msg.length < 1) $obj.removeClass('nogocolor');
}

$("#fxs").bind("click", function () {
    $(".modal-dialog").css("width", "600");
});

function windowUp() {
    $(".modal-dialog").css("width", "600");
    setTimeout(200);
    layer.alert($('#fxjss').html(), {title: '风险揭示书'}, function () {
        layer.closeAll();
    })
}
