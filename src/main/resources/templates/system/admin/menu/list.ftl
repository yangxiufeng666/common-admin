<div class="row">
    <div class="col-xs-12">
        <div class="box">
            <div class="box-header">
                <h3 class="box-title">菜单管理</h3>
            </div>
            <div class="box-body">
                <div class="clearfix">
                    <div class="col-md-4">
                        <a class="btn btn-sm btn-primary" target="modal" modal="lg"
                           href="/menu/add">添加</a>
                    </div>
                </div>
                <table id="menu_tab" class="table" style="margin-top: 20px">

                </table>
            </div>
        </div>
    </div>
</div>
<link rel="stylesheet" type="text/css" href="/other/jquery-easyui-1.5.3/themes/default/easyui.css">
<link rel="stylesheet" type="text/css" href="/other/jquery-easyui-1.5.3/themes/icon.css">
<script type="text/javascript" src="/other/jquery-easyui-1.5.3/js/jquery.easyui.min.js"></script>
<script type="text/javascript">
    $(function () {
        var table = $('#menu_tab').treegrid({
            height: 700,
            rownumbers: true,
            animate: true,
            collapsible: true,
            fitColumns: true,
            url: '/menu/getTreeGridMenu',
            method: 'get',
            idField: 'id',
            treeField: 'name',
            showFooter: true,
            columns: [
                [{
                    title: '菜单名称',
                    field: 'name',
                    width: 80
                },
                    {
                        title: '请求URL',
                        field: 'url',
                        align: 'center',
                        width: 80
                    },
                    {
                        title: '菜单编码',
                        field: 'code',
                        align: 'center',
                        width: 80
                    },
                    {
                        title: '层级',
                        field: 'level',
                        align: 'center',
                        width: 40
                    },
                    {
                        title: '顺序',
                        field: 'sort',
                        align: 'center',
                        width: 40
                    },
                    {
                        title: '创建时间',
                        field: 'createDate',
                        align: 'center',
                        width: 80
                    },
                    {
                        title:'操作',
                        field:'menuId',
                        align: 'center',
                        width: 80,
                        formatter:function(value){
                            console.log(value);
                            var  content="";
                            if (value != '000000000000000000'){
                                content = '<a class="btn btn-xs btn-info"  target="modal" modal="lg" href="/menu/edit/' + value + '">编辑</a>'
                                        + " &nbsp;"
                                        +'<a class="btn btn-xs btn-default" callback="reloadMenuList()" data-body="确认要删除吗？" target="ajaxTodo" href="/menu/delete/' + value + '">删除</a>'

                            }
                            return content;
                        }

                    }
                ]
            ]
        });
    })
    function reloadMenuList(){
        $('#menu_tab').treegrid('reload');
    }
    function doClick() {
        var row = $('#tg').treegrid('getSelected');
        console.log(JSON.stringify(row));
    }
</script>