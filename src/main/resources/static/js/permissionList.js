var pid=null;
$(function () {
    layui.use(['table', 'form', 'jquery'],function () {
        var table = layui.table,
            $ = layui.jquery,
            form = layui.form;
        layer=layui.layer;

        //数据加载
        table.render({
            elem: '#permissiontable'
            , url: 'showpermission'
            , method: 'post'
            , toolbar: '#toolbarDemo'
            , datatype: 'json'
            , page: true
            , cellMinWidth: 80 //全局定义常规单元格的最小宽度，layui 2.2.1 新增
            , cols: [
                [
                    {field: 'powerId', title: '权限ID', align: 'center'}
                    , {field: 'powername', title: '权限名', align: 'center'}
                    , {field: 'powerurl', title: 'powerurl', align: 'center'}
                    // , {fixed: 'right', title: '操作', align: 'center', toolbar: '#barDemo'}
                ]
            ]
            , id: "permissionlist"
        });

        //搜索
        var $ = layui.$, active = {
            reload: function () {
                var dpid = $('#dpid');
                //执行重载
                table.reload('permissionlist', {
                    page: {
                        curr: 1 //重新从第 1 页开始
                    }
                    , where: {
                        permissionname: dpid.val()
                    }
                });
            }
        };

        $(document).on('click', '#searchpermission', function () {
            var type = $(this).data('type');
            active[type] ? active[type].call(this) : '';
        });



        //监听行工具事件
        table.on('tool(permissiontable)', function (obj) { //注：tool 是工具条事件名，test 是 table 原始容器的属性 lay-filter="对应的值"

            var data = obj.data, //获得当前行数据
                layEvent = obj.event,//获得 lay-event 对应的值
                pid=data.powerId;
            //删除
            if (layEvent === 'del') {
                layer.confirm('确认删除吗?', {icon: 3, title: '删除信息'}, function (index) {
                    $.post('permission/delete', {pid: pid}, function (data) {
                        if (data.msg =="ok" ){
                            layer.msg('删除成功', {icon: 6});
                            table.reload('permissionlist', {});
                        }else
                        {
                            layer.msg(data.rolename+'角色拥有该权限，删除失败！', {icon: 5});

                        }
                    })
                });
            } else if (layEvent === "edit") {
                //编辑
                getpermission(data, pid);

            }

        });

        form.on('submit(put)', function (data) {
            var permission = JSON.stringify(data.field);
            console.log(data.field);
            layer.confirm('确认吗?', {icon: 3, title: '添加信息'}, function (index) {
                $.post('addpermission', {permission:permission,pid:pid}, function (data) {
                    if (data =="ok") {
                        layer.msg('成功', {icon: 6});
                        table.reload('permissionlist', {});
                    } else  if (data=="name"){
                        layer.msg('失败,权限名不能重复！', {icon: 5});
                    }else if (data =="url"){
                        layer.msg('失败,权限URL不能重复！', {icon: 5});

                    }else{
                        layer.msg('权限添加失败，请重新添加！', {icon: 5});
                    }
                    layer.close(index);
                });
                layer.closeAll();
            });
            return false;
        });

    });

});








//打开添加和编辑权限表单
function openpower(id,title){
    layer.open({
        type:1,
        title: title,
        fixed:true,
        resize :true,
        shadeClose: true,
        area: ['550px','300px'],
        content:$("#addPermission").html(),

    });
}

function clearpower(){
    $("#purl").attr("value","");
    $("#pname").attr("value","");
}
//添加权限
function addpower(){
    clearpower();
    openpower(null,"添加权限");
}

function getpermission(obj,id) {
    //回显数据
    $.get("getpermission",{"id":id},function(data){
            //回显权限数据(layui给input赋值)
            $("#pname").attr("value",data.powername);
            $("#purl").attr("value",data.powerurl);
            pid=data.powerId;
            openpower(null,"编辑权限")
    });

}