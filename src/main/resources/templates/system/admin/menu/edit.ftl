<style>
    ul.ztree {
        margin-top: 10px;
        border: 1px solid #617775;
        background: #f0f6e4;
        width: 220px;
        height: 360px;
        overflow-y: scroll;
        overflow-x: auto;
    }
</style>
<div class="row">
    <div class="col-md-12">
        <form id="securityAddForm">
            <input type="text" hidden id="id" name="id" value="${menu.id}">
            <div class="modal-body">
                <div class="form-group">
                    <label class="" id="nameLabel">菜单名称</label>
                    <input type="text" class="form-control" name="name" id="name" value="${menu.name!}" placeholder="输入菜单名称...">
                </div>
                <div class="form-group">
                    <label class="" id="urlLabel">请求地址</label>
                    <input type="text" class="form-control" name="url" id="url" value="${menu.url!}" placeholder="输入请求地址...">
                </div>
                <div class="form-group">
                    <label id="codeLabel">菜单编号</label>
                    <input type="text" class="form-control" name="code" id="code" value="${menu.code!}" placeholder="输入菜单编号...">
                    <input hidden name="oldCode" id="oldCode" value="${menu.code!}">
                </div>
                <div class="form-group">
                    <label id="nickNameLabel">排序</label>
                    <input type="text" class="form-control" name="sort" id="sort" value="${menu.sort!}" placeholder="输入排序...">
                </div>
                <div class="form-group">
                    <label id="nickNameLabel">父级菜单</label>
                    <input type="text" readonly class="form-control" name="pName" id="pName" value="${pName!}" placeholder="输入父级菜单..."
                           onclick='showMenu(${zTree})'/>
                    <input type="text" hidden id="pId" name="pId" value="${menu.pId!}">
                    <input type="text" hidden id="pCode" name="pCode" value="${menu.pCode!}">
                    <input type="text" hidden id="level" name="level" value="${menu.level}">
                </div>
                <div class="form-group">
                    <label id="nickNameLabel">图标</label>
                    <input type="text" class="form-control" name="icon" id="icon" value="${menu.icon!}" placeholder="输入图标...">
                </div>
                <div class="form-group">
                    <label>是否是菜单</label>
                    <select name="isMenu" class="form-control select2" style="width: 100%;">
                        <option <#if menu.isMenu==1> selected="selected"</#if> value="1">是</option>
                        <option <#if menu.isMenu==0> selected="selected"</#if>  value="0">否</option>
                    </select>
                </div>
            </div>
        </form>
    </div>
    <div id="menuContent" class="menuContent" style="display:none; position: absolute;">
        <ul id="treeDemo" class="ztree" style="margin-top:0; width:180px; height: 300px;"></ul>
    </div>

</div>
<div class="modal-footer">
    <div class="pull-right">
        <button type="button" class="btn btn-default btn-sm" data-dismiss="modal"><i class="fa fa-close"></i>关闭</button>
        <button type="button" class="btn btn-primary btn-sm" onclick="securitySave();"><i class="fa fa-save"></i>更新
        </button>
    </div>
</div>
<script type="text/javascript" src="other/zTree/js/jquery.ztree.core.js"></script>
<script type="text/javascript" src="other/zTree/js/jquery.ztree.excheck.js"></script>
<link rel="stylesheet" type="text/css" href="other/zTree/css/zTreeStyle/zTreeStyle.css"/>
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
        if($("#code").val() == ""){
            $("#codeLabel").prepend('<span class="errorClass" style="color:red">*菜单编号不能为空</span><br class="errorClass"/>');
            status = 0;
        }
        if (status == 0) {
            return false;
        } else {
            $.ajax({
                url: '/menu/update',
                type: 'post',
                dataType: 'text',
                data: $("#securityAddForm").serialize(),
                success: function (data) {
                    var json = JSON.parse(data);
                    if (json.status) {
                        $("#lgModal").modal('hide');
                        alertMsg("更新成功", "success");
                        reloadMenuList();
                    } else {
                        alertMsg("更新失败:" + json.msg, "success");
                    }
                }
            });
        }
    }
    var setting = {
        check: {
            enable: true,
            chkStyle: "radio",
            radioType: "all"
        },
        view: {
            dblClickExpand: false
        },
        data: {
            simpleData: {
                enable: true
            }
        },
        callback: {
            onClick: onClick,
            onCheck: onCheck
        }
    };

    function onClick(e, treeId, treeNode) {
        var zTree = $.fn.zTree.getZTreeObj("treeDemo");
        zTree.checkNode(treeNode, !treeNode.checked, null, true);
        hideMenu();
        return false;
    }

    function onCheck(e, treeId, treeNode) {
        var zTree = $.fn.zTree.getZTreeObj("treeDemo"),
                nodes = zTree.getCheckedNodes(true);
        v = "";
        var id = '';
        var code = '';
        var lev=0;
        for (var i = 0, l = nodes.length; i < l; i++) {
            v += nodes[i].name + ",";
            id = nodes[i].id;
            code = nodes[i].code;
            lev = nodes[i].level;
        }
        if (v.length > 0) v = v.substring(0, v.length - 1);
        var pName = $("#pName");
        pName.attr("value", v);
        var pId = $("#pId");
        var pCode = $("#pCode");
        var level = $("#level");
        pId.attr("value", id);
        pCode.attr("value", code);
        level.attr("value", lev+1);
        hideMenu();
    }

    function showMenu(node) {
        initZTree(node);
        var cityObj = $("#pName");
        var cityOffset = $("#pName").offset();
        $("#menuContent").css({
            left: cityOffset.left + "px",
            top: cityOffset.top + "px"
        }).slideDown("fast");

        $("body").bind("mousedown", onBodyDown);
    }
    function hideMenu() {
        $("#menuContent").fadeOut("fast");
        $("body").unbind("mousedown", onBodyDown);
    }
    function onBodyDown(event) {
        if (!(event.target.id == "menuBtn" || event.target.id == "pName" || event.target.id == "menuContent" || $(event.target).parents("#menuContent").length > 0)) {
            hideMenu();
        }
    }
    function initZTree(zNodes){
        $.fn.zTree.init($("#treeDemo"), setting, zNodes);
    };
</script>