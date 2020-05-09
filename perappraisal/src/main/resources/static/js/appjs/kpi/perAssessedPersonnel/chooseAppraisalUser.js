var menuIds;
$(function() {
    getMenuTreeData();
    validateRule();
});
$.validator.setDefaults({
    submitHandler : function() {
        getAllSelectNodes();
        update();
    }
});
function loadMenuTree(menuTree) {
    $('#menuTree').jstree({
        "plugins" : [ "wholerow", "checkbox" ],
        'core' : {
            'data' : menuTree
        },
        "checkbox" : {
            //"keep_selected_style" : false,
            //"undetermined" : true
            //"three_state" : false,
            //"cascade" : ' up'
        }
    });
    $('#menuTree').jstree('open_all');
}
function getAllSelectNodes() {
    var ref = $('#menuTree').jstree(true); // 获得整个树
    menuIds = ref.get_bottom_selected(); // 获得所有选中节点的，返回值为数组
}
function getMenuTreeData() {
    var examineeUserId = $('#examineeUserId').val();
    $.ajax({
        type : "GET",
        url : "/kpi/perAssessedPersonnel/tree/"+examineeUserId,
        success : function(data) {
            loadMenuTree(data);
        }
    });
}
function update() {
    $('#menuIds').val(menuIds);
    var role = $('#signupForm').serialize();
    $.ajax({
        cache : true,
        type : "POST",
        url : "/kpi/perAssessedUser/update",
        data : role, // 你的formid
        async : false,
        error : function(request) {
            alert("Connection error");
        },
        success : function(r) {
            if (r.code == 0) {
                parent.layer.msg(r.msg);
                parent.reLoad();
                var index = parent.layer.getFrameIndex(window.name); // 获取窗口索引
                parent.layer.close(index);
            } else {
                parent.layer.msg(r.msg);
            }
        }
    });
}

function validateRule() {
    var icon = "<i class='fa fa-times-circle'></i> ";
    $("#signupForm").validate({

    });
}