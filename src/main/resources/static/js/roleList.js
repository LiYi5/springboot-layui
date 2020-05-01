var roleid=null;
$(function () {
    layui.use(['table', 'form', 'jquery'],function () {
        var table = layui.table,
            $ = layui.jquery,
            form = layui.form;
            layer=layui.layer;

        //数据加载
        table.render({
            elem: '#roletable'
            , url: 'showrole'
            , method: 'post'
            , toolbar: '#toolbarDemo'
            , datatype: 'json'
            , page: true
            , cellMinWidth: 80 //全局定义常规单元格的最小宽度，layui 2.2.1 新增
            , cols: [
                [
                    {field: 'roleId', title: '角色ID', align: 'center'}
                    , {field: 'rolename', title: '角色名', align: 'center'}
                    , {field: 'powername', title: '权限名', align: 'center'}
                    , {fixed: 'right', title: '操作', align: 'center', toolbar: '#barDemo'}
                ]
            ]
            , id: "rolelist"
        });


        var $ = layui.$, active = {
            reload: function () {
                var drid = $('#drid');
                //执行重载
                table.reload('rolelist', {
                    page: {
                        curr: 1 //重新从第 1 页开始
                    }
                    , where: {
                        role: drid.val()
                    }
                });
            }
        };

        $(document).on('click', '#searchrole', function () {
            var type = $(this).data('type');
            active[type] ? active[type].call(this) : '';
        });


        //监听行工具事件
        table.on('tool(roletable)', function (obj) { //注：tool 是工具条事件名，test 是 table 原始容器的属性 lay-filter="对应的值"

            var data = obj.data, //获得当前行数据
                layEvent = obj.event,//获得 lay-event 对应的值
                rid=data.roleId;
            //删除
            if (layEvent === 'del') {
                layer.confirm('确认删除吗?', {icon: 3, title: '删除信息'}, function () {
                    $.post('role/delete', {rid: rid}, function (data) {
                        if (data.msg =="ok" ){
                            layer.msg('删除成功', {icon: 6});
                            table.reload('rolelist', {});
                        }else
                        {
                            layer.msg(data.username+'用户拥有该角色，删除失败！', {icon: 5});

                        }
                    })
                });

            }
            else if (layEvent === "edit") {
                //编辑

                getRoleAndpermissions(data,rid);
            }

        });

        //角色添加提交
        form.on('submit(put)', function (data) {
            $("#permissionDiv").empty();
            var role = JSON.stringify(data.field);
            var array = new Array();
            var roleCheckd=$(".layui-form-checked");
            //获取选中的权限id
            for(var i=0;i<roleCheckd.size();i++){
                array.push($(roleCheckd.get(i)).prev().val());
            }
            var pers=JSON.stringify(array);
            layer.confirm('确认编辑吗?', {icon: 3, title: '编辑信息'}, function (index) {
                $.post('addRole', {role: role, pers:pers,roleid:roleid}, function (data) {
                   if (data=="addok"){
                       layer.msg('添加成功', {icon: 6});
                       table.reload('rolelist', {});
                   }else if(data =="adderror"){
                       layer.msg('添加失败，角色名不能重复', {icon: 5});
                   }
                   else if (data=="upok"){

                       layer.msg('更新成功', {icon: 6});
                       table.reload('rolelist', {});
                   }else if (data=="uperror"){
                       layer.msg('更新失败，角色名不能重复', {icon: 5});

                   }
                    layer.close(index);
                });
                layer.closeAll();
            });
            return false;
        });




    });
    
});



//打开添加和编辑用户表单
function openRole(id,title){
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
        content:$("#addRole").html(),

    });
    roleid=id;
}

//添加用户
function insertRole(){
    $("#Rname").attr("value","");
    $.get("selectPermiassionList",function(data){
        if(data!=null){
            //显示权限数据
            $("#permissionDiv").empty();
            $.each(data, function (index, item) {
                //从数据库获取角色显示在添加用户表单中
                var permissionInput=$("<input type='checkbox' name='powerId' value="+item.powerId+" title="+item.powername+" lay-skin='primary'/>");
                var div=$("<div class='layui-unselect layui-form-checkbox' lay-skin='primary'>" +
                    "<span>"+item.powername+"</span><i class='layui-icon'>&#xe626;</i>" +
                    "</div>");
                $("#permissionDiv").append(permissionInput).append(div);
            })
            //重新渲染下form表单 否则复选框无效
            openRole(null,"添加角色");
            layui.form.render('checkbox');
        }else{
            //弹出错误提示
            layer.alert("获取角色数据有误，请您稍后再试",function () {
                layer.closeAll();
            });
        }
    });
}




//编辑角色
function getRoleAndpermissions(obj,id) {
    //回显数据
    $.get("getRoleAndpermissions",{"id":id},function(data){
        if(data.msg=="ok" && data.role!=null){
            var existPermission='';
            if(data.role.rolePowers !=null ){
                $.each(data.role.rolePowers, function (index, item) {
                    existPermission+=item.idPower+',';
                });
            }
            //回显用户数据(layui给input赋值)
            roleid = data.role.roleId;
            $("#Rname").attr("value",data.role.rolename);
            //显示角色数据
            $("#permissionDiv").empty();

            $.each(data.powers, function (index, item) {
                var permissionInput=$("<input type='checkbox' name='powerId' value="+item.powerId+" title="+item.powername+" lay-skin='primary'/>");
                var div=$("<div class='layui-unselect layui-form-checkbox' lay-skin='primary'>" +
                    "<span>"+item.rolename+"</span><i class='layui-icon'>&#xe626;</i>" +
                    "</div>");
                if(existPermission!='' && existPermission.indexOf(item.powerId)>=0){
                    permissionInput=$("<input type='checkbox' name='powerId' value="+item.powerId+" title="+item.powername+" lay-skin='primary' checked='checked'/>");
                    div=$("<div class='layui-unselect layui-form-checkbox  layui-form-checked' lay-skin='primary'>" +
                        "<span>"+item.powername+"</span><i class='layui-icon'>&#xe627;</i>" +
                        "</div>");
                }
                $("#permissionDiv").append(permissionInput).append(div);
            });
            openRole(id,"编辑用户");
            //重新渲染下form表单中的复选框 否则复选框选中效果无效
            // layui.form.render();
            layui.form.render('checkbox');
        }
    });

}