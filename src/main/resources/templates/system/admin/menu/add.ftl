<div class="row">
    <div class="col-md-12">
        <form id="securityAddForm">
            <div class="modal-body">
                <div class="form-group">
                    <label class="" id="nameLabel">菜单名称</label>
                    <input type="text" class="form-control" name="name" id="name" placeholder="输入菜单名称...">
                </div>
                <div class="form-group">
                    <label class="" id="urlLabel">请求地址</label>
                    <input type="text" class="form-control" name="url" id="url" placeholder="输入请求地址...">
                </div>
                <div class="form-group">
                    <label id="nickNameLabel">菜单编号</label>
                    <input type="text" class="form-control" name="code" id="code" placeholder="输入菜单编号...">
                </div>
                <div class="form-group">
                    <label id="nickNameLabel">排序</label>
                    <input type="text" class="form-control" name="sort" id="sort" placeholder="输入排序...">
                </div>
                <div class="form-group">
                    <label id="nickNameLabel">父级菜单</label>
                    <input type="text" class="form-control" name="pCode" id="pCode" placeholder="输入父级菜单...">
                </div>
                <div class="form-group">
                    <label id="nickNameLabel">图标</label>
                    <input type="text" class="form-control" name="icon" id="icon" placeholder="输入图标...">
                </div>
                <div class="form-group">
                    <label>是否是菜单</label>
                    <select name="isMenu" class="form-control select2" style="width: 100%;">
                        <option value="1">是</option>
                        <option value="0">否</option>
                    </select>
                </div>
            </div>
        </form>
    </div>
</div>
<div class="modal-footer">
    <div class="pull-right">
        <button type="button" class="btn btn-default btn-sm" data-dismiss="modal"><i class="fa fa-close"></i>关闭</button>
        <button type="button" class="btn btn-primary btn-sm" onclick="securitySave();"><i class="fa fa-save"></i>保存
        </button>
    </div>
</div>
</form>
</div>
</div>
<script type="text/javascript">
    function securitySave() {
        $("span").remove(".errorClass");
        $("br").remove(".errorClass");
        var status = 1;
        if ($("#name").val() == "") {
            $("#nameLabel").prepend('<span class="errorClass" style="color:red">*菜单不能为空</span><br class="errorClass"/>');
            status = 0;
        }
        if ($("#url").val() == "") {
            $("#urlLabel").prepend('<span class="errorClass" style="color:red">*请求地址不能为空</span><br class="errorClass"/>');
            status = 0;
        }
        if (status == 0) {
            return false;
        } else {
            $.ajax({
                url: '/menu/save',
                type: 'post',
                dataType: 'text',
                data: $("#securityAddForm").serialize(),
                success: function (data) {
                    var json = JSON.parse(data);
                    if (json.status) {
                        $("#lgModal").modal('hide');
                        alertMsg("添加成功", "success");
                        reloadTable(list_ajax, "#roleTime", "#rolePremise");
                    } else {
                        alertMsg("添加失败:" + json.msg, "success");
                    }
                }
            });
        }
    }
</script>