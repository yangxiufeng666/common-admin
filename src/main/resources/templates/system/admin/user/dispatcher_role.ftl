<div class="row">
    <form id="dispatcherRoleForm">
        <div class="box-body  no-padding">
            <input name="id" hidden value="${bean.id}">
            <table class="table table-striped">
                <tr>
                    <td>账号：</td>
                    <td style="width: 90%">${bean.username!}</td>
                </tr>
                <tr>
                    <td>昵称：</td>
                    <td>${bean.name!}</td>
                </tr>
                <tr>
                    <td>性别:</td>
                    <td>
                    <#if bean.sex == 0>女</#if>
                    <#if bean.sex == 1>男</#if>
                    </td>
                </tr>
                <tr>
                    <td>创建时间：</td>
                    <td>${bean.createTime?string('yyyy-MM-dd HH:mm:ss')}</td>
                </tr>
                <tr>
                    <td>角色：</td>
                    <td>
                    <#list roles as role>
                        <label>
                            <input type="checkbox" name="roleId" class="flat-red" value="${role.id}"
                                   <#if role.checked == true>checked</#if>/>${role.name}
                        </label>
                    </#list>
                    </td>
                </tr>
            </table>
            <div class="box-footer">
                <div class="pull-right">
                    <button type="button" class="btn btn-default btn-sm" data-dismiss="modal"><i
                            class="fa fa-close"></i>关闭
                    </button>
                    <button onclick="securitySetRole()" type="button" class="btn btn-primary btn-sm"><i
                            class="fa fa-paste"></i>更新
                    </button>
                </div>
            </div>
        </div>
    </form>
</div>
<script type="text/javascript">
    function securitySetRole(){
        $.ajax({
            url: '/user/doDispatcherRole',
            type: 'post',
            dataType: 'text',
            data: $("#dispatcherRoleForm").serialize(),
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