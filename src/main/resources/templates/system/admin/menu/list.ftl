<div class="row">
    <div class="col-xs-12">
        <div class="box">
            <div class="box-header">
                <h3 class="box-title">菜单管理</h3>

                <div class="box-tools pull-right">
                <#--<@shiro.hasPermission name="admin:insert">-->
                    <a onclick="securityToListAjax();" class="btn btn-sm btn-primary" target="modal" modal="lg"
                       href="/menu/add">添加</a>
                <#--</@shiro.hasPermission>-->
                </div>
            </div>
            <div class="box-body">
                <div class="clearfix">
                    <div class="col-md-4">
                        <div class="input-group date ">
                            <div class="input-group-addon">
                                <i class="fa fa-calendar"></i>
                            </div>
                            <input type="text" class="form-control pull-right" id="securityTime" placeholder="选择时间...">
                        </div>
                    </div>
                    <div class="col-md-4">
                        <div class="input-group">
                            <span class="input-group-addon"><i class="fa fa-search"></i></span>
                            <input type="text" class="form-control" id="securityPremise" placeholder="根据账号搜索...">
                        </div>
                    </div>
                    <div class="col-md-4">
                        <button type="submit" onclick="securityReload();" class="btn btn-primary">搜索</button>
                    </div>
                </div>
                <table id="security_tab" class="table table-bordered table-striped">
                    <thead>
                    <tr>
                    <tr>
                        <th>序号</th>
                        <th>菜单名称</th>
                        <th>菜单编号</th>
                        <th>父菜单编号</th>
                        <th>请求地址</th>
                        <th>排序</th>
                        <th>层级</th>
                        <th>是否是菜单</th>
                        <th>状态</th>
                        <th>操作</th>
                    </tr>
                    </tr>
                    </thead>
                </table>
            </div>
        </div>
    </div>
</div>

<script type="text/javascript">
    var security_tab;
    $(function () {
        //初始化时间选择器
        $('#securityTime').datepicker({
            language: 'zh-CN',
            format: 'yyyy-mm-dd',
            autoclose: true,
            todayHighlight: true
        });
        //初始化表格

        var No = 0;
        security_tab = $('#security_tab').DataTable({
            "dom": 'itflp',
            "processing": true,
            "searching": false,
            "serverSide": true, //启用服务器端分页
            "bInfo": false,
            "language": {"url": "adminlte/plugins/datatables/language.json"},
            "ajax": {"url": "/menu/page", "type": "post"},
            "columns": [
                {"data": null},
                {"data": "name"},
                {"data": "code"},
                {"data": "pCode"},
                {"data": "url"},
                {"data": "sort"},
                {"data": "level"},
                {"data": null},
                {"data": null}
            ],
            "columnDefs": [
                {
                    targets: 0,
                    data: null,
                    render: function (data) {
                        No = No + 1;
                        return No;
                    }
                }, {
                    targets: 7,
                    data: null,
                    render: function (data) {
                        if (data.isMenu == 1) {
                            return '是';
                        } else {
                            return '否';
                        }
                    }
                }, {
                    targets: 8,
                    data: null,
                    render: function (data) {
                        if (data.status == 1) {
                            return '可用';
                        } else {
                            return '不可用';
                        }
                    }
                }, {
                    "targets": 9,
                    "data": null,
                    "render": function (data) {
                        var btn = "";
                        btn =
                                '<a class="btn btn-xs btn-info" callback="securityToListAjax();" target="modal" modal="lg" href="/menu/edit/' + data.id + '">编辑</a>'
                                + " &nbsp;"
                                +'<a class="btn btn-xs btn-default" callback="securityReload();" data-body="确认要删除吗？" target="ajaxTodo" href="/menu/delete/' + data.id + '">删除</a>'

                        return btn;
                    }
                }]
        }).on('preXhr.dt', function (e, settings, data) {
            No = 0;
        });

        $("#securitySeek").on("click", function () {
            reloadTable(security_tab, "#securityTime", "#securityPremise");
        });
    });

    function securityReload() {
        reloadTable(security_tab, "#securityTime", "#securityPremise");
    }

    function securityToListAjax() {
        list_ajax = security_tab;
        console.log(list_ajax);
    }
    function isNull(data) {
        return (data == "" || data == undefined || data == null) ? true : false;
    }
</script>