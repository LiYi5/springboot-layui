var uid;
$(function () {
    layui.use(['table', 'form', 'jquery'], function () {
        var table = layui.table,
            $ = layui.jquery,
            form = layui.form,
        layer=layui.layer;

        //数据加载
        table.render({
            elem: '#usertable'
            , url: 'showuser'
            , method: 'post'
            , toolbar: '#toolbarDemo'
            , datatype: 'json'
            , page: true
            , cellMinWidth: 80 //全局定义常规单元格的最小宽度，layui 2.2.1 新增
            , cols: [
                [
                    {field: 'userId', title: 'ID', align: 'center'}
                    , {field: 'username', title: '用户名', align: 'center'}
                    , {field: 'password', title: '密码', align: 'center'}
                    , {field: 'rolename', title: '角色', align: 'center'}
                    , {fixed: 'right', title: '操作', align: 'center', toolbar: '#barDemo'}
                ]
            ]
            , id: "userlist"
        });


        var $ = layui.$, active = {
            reload: function () {
                var duid = $('#duid');
                //执行重载
                table.reload('userlist', {
                    page: {
                        curr: 1 //重新从第 1 页开始
                    }
                    , where: {
                        user: duid.val()
                    }
                });
            }
        };

        $(document).on('click', '#search', function () {
            var type = $(this).data('type');
            active[type] ? active[type].call(this) : '';
        });


        //监听行工具事件
        table.on('tool(userTable)', function (obj) { //注：tool 是工具条事件名，test 是 table 原始容器的属性 lay-filter="对应的值"

            var data = obj.data, //获得当前行数据
                layEvent = obj.event,//获得 lay-event 对应的值
                uid=data.userId;
            //删除
            if (layEvent === 'del') {
                $.post('delete', {uid: data.userId}, function (data) {
                    if (data=="noPermission"){
                        layer.msg("您没有该权限！",{icon:4})
                    }else{
                        if (data == "ok") {
                            layer.msg('删除成功', {icon: 6});
                            table.reload('userlist', {});
                        } else if (data == "error") {
                            layer.msg('删除失败', {icon: 5});
                        }
                    }
                        // layer.close(index);

                });
                //编辑
            } else if (layEvent === "edit") {
                //编辑
                getUserAndRoles(data,uid);


            }

        });


        //监听提交
        //添加用户
        form.on('submit(put)', function (data) {
            var user = JSON.stringify(data.field);
            var array = new Array();
            var roleCheckd=$(".layui-form-checked");
            //获取选中的权限id
            for(var i=0;i<roleCheckd.length;i++){
                array.push($(roleCheckd.get(i)).prev().val());
            }
            var roleids=JSON.stringify(array);

            layer.confirm('确认添加吗?', {icon: 3, title: '添加信息'}, function (index) {
                $.post('adduser', {user: user, roleids:roleids}, function (data) {
                    if (data =="ok") {
                        layer.msg('添加成功', {icon: 6});
                        table.reload('userlist', {});
                    } else if (data =="error"){
                        layer.msg('添加失败,用户名不能重复！', {icon: 5});
                    }else if (data=="noPermission"){
                        layer.msg("您没有该权限！",{icon:4});
                    }
                    layer.close(index);
                });
                layer.closeAll();
            });
            return false;
        });



        //修改用户
        form.on('submit(pw)', function (data) {
            $("#RDiv").empty();
            var user = JSON.stringify(data.field);
            var array = new Array();
            var roleCheckd=$(".layui-form-checked");
            //获取选中的权限id
            for(var i=0;i<roleCheckd.length;i++){
                array.push($(roleCheckd.get(i)).prev().val());
            }
            var roleids=JSON.stringify(array);
            layer.confirm('确认修改吗?', {icon: 3, title: '修改信息'}, function (index) {
                $.post('updateu', {user: user, roleids:roleids,uid:uid}, function (flag) {
                    console.log(flag);
                    if (flag) {
                        layer.msg('修改成功', {icon: 6});
                        table.reload('userlist', {});
                    } else {
                        layer.msg('修改失败,用户名不能重复！', {icon: 5});
                    }
                    layer.close(index);
                });
                layer.closeAll();
            });
            return false;

        });

        //密码验证
        form.verify({
            pass: [
                /^[\S]{4,12}$/
                , '密码必须4到12位，且不能出现空格'
            ]
            , content: function (value) {
                layedit.sync(editIndex);
            }
        });




    });
});




//打开添加和编辑用户表单
function openUser(id,title,name){
    if(id==null || id==""){
        $("#id").val("");
    }
    layer.open({
        type:1,
        title: title,
        fixed:true,
        resize :true,
        shadeClose: true,
        area: ['550px','300px'],
        content:name.html(),

    });
}

//添加用户
function addUser(){
    $.get("selectRoleList",function(data){
        if (data=="noPermission"){
            layer.msg("您没有该权限！",{icon:4})
        }
        else{
            if(data!=null){
                //显示角色数据
                $("#roleDiv").empty();
                $.each(data, function (index, item) {
                    //从数据库获取角色显示在添加用户表单中
                    var roleInput=$("<input type='checkbox' name='roleId' value="+item.roleId+" title="+item.rolename+" lay-skin='primary'/>");
                    var div=$("<div class='layui-unselect layui-form-checkbox' lay-skin='primary'>" +
                        "<span>"+item.rolename+"</span><i class='layui-icon'>&#xe626;</i>" +
                        "</div>");
                    $("#roleDiv").append(roleInput).append(div);
                })
                //重新渲染下form表单 否则复选框无效
                openUser(null,"添加用户",$("#add"));
                layui.form.render('checkbox');
            }else{
                //弹出错误提示
                layer.alert("获取角色数据有误，请您稍后再试",function () {
                    layer.closeAll();
                });
            }

        }
    });
}



//编辑用户
function getUserAndRoles(obj,id) {
        //回显数据
        $.get("getUserAndRole",{"id":id},function(data){
            if (data=="noPermission"){
                layer.msg("您没有该权限！",{icon:4})
            } else {
                if (data.msg == "ok" && data.user != null) {
                    var existRole = '';
                    if (data.user.userRoles != null) {
                        $.each(data.user.userRoles, function (index, item) {
                            existRole += item.idRole + ',';
                        });
                    }
                    //回显用户数据(layui给input赋值)
                    uid = data.user.userId;
                    $("#uname").attr("value", data.user.username);
                    $("#passw").attr("value", data.user.password);
                    //显示角色数据
                    $("#RDiv").empty();
                    $.each(data.roles, function (index, item) {
                        var roleInput = $("<input type='checkbox' name='roleId' value=" + item.roleId + " title=" + item.rolename + " lay-skin='primary'/>");
                        var div = $("<div class='layui-unselect layui-form-checkbox' lay-skin='primary'>" +
                            "<span>" + item.rolename + "</span><i class='layui-icon'>&#xe626;</i>" +
                            "</div>");
                        if (existRole != '' && existRole.indexOf(item.roleId) >= 0) {
                            roleInput = $("<input type='checkbox' name='roleId' value=" + item.roleId + " title=" + item.rolename + " lay-skin='primary' checked='checked'/>");
                            div = $("<div class='layui-unselect layui-form-checkbox  layui-form-checked' lay-skin='primary'>" +
                                "<span>" + item.rolename + "</span><i class='layui-icon'>&#xe627;</i>" +
                                "</div>");
                        }
                        $("#RDiv").append(roleInput).append(div);
                    });
                    openUser(id, "编辑用户", $("#edit"));
                    //重新渲染下form表单中的复选框 否则复选框选中效果无效
                    // layui.form.render();
                    layui.form.render('checkbox');
                }

            }
        });

}
