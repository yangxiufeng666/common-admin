<div class="row">
    <div class="col-md-12">
        <form id="securityEditForm">
            <input type="hidden" id="id" name="id" value="${bean.id}">
            <div class="box-body">
                <div class="form-group">
                    <label id="userNoLabel">账号</label>
                    <input type="text" class="form-control" name="username" id="username" value="${bean.username!}"
                           disabled placeholder="输入账号...">
                </div>
                <div class="form-group">
                    <label id="nickNameLabel">昵称</label>
                    <input type="text" class="form-control" name="name" id="name" value="${bean.name!}"
                           placeholder="输入昵称...">
                </div>
                <div class="form-group">
                    <label>性别</label>
                    <select name="sex" class="form-control select2" style="width: 100%;">
                        <option <#if bean.sex == 0>selected="selected"</#if> value="0">女</option>
                        <option <#if bean.sex == 1>selected="selected"</#if> value="1">男</option>
                    </select>
                </div>
            </div>
            <div class="box-footer">
                <div class="pull-right">
                    <button type="button" class="btn btn-default btn-sm" data-dismiss="modal"><i
                            class="fa fa-close"></i>关闭
                    </button>
                    <button onclick="securityUpdateUser();" type="button" class="btn btn-primary btn-sm"><i
                            class="fa fa-paste"></i>更新
                    </button>
                </div>
            </div>
        </form>
    </div>
</div>
<script type="text/javascript">
    function securityUpdateUser() {
//        debugger;
        $.ajax({
            url: '/user/update',
            type: 'post',
            dataType: 'text',
            data: $("#securityEditForm").serialize(),
            success: function (data) {
                var json = JSON.parse(data);
                if (json.status) {
                    $("#lgModal").modal('hide');
                    alertMsg("更新成功", "success");
                    reloadTable(list_ajax);
                } else {
                    alertMsg("更新失败:" + json.msg, "success");
                }
            },
            error: function (XMLHttpRequest, textStatus, errorThrown) {
                alert(XMLHttpRequest.status);
                alert(XMLHttpRequest.readyState);
                alert(textStatus);
            }
        });
    }

</script>