<div class="row">
	<div class="box-body  no-padding">
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
				<td>拥有角色：</td>
				<td>
					${(bean.role.name)!}
				</td>
			</tr>
			<tr>
				<td>创建时间：</td>
				<td>${bean.createTime?string('yyyy-MM-dd HH:mm:ss')}</td>
			</tr>
			<tr>
				<td>更新时间：</td>
				<#if bean.updateTime??>
                    <td>${bean.updateTime?string('yyyy-MM-dd HH:mm:ss')}</td>
				</#if>
			</tr>
		</table>
		<div class="box-footer">
			<div class="pull-right">
				<button type="button" class="btn btn-default btn-sm" id="close" data-dismiss="modal"><i class="fa fa-close"></i>关闭</button>
			</div>
      	</div>
	</div>
</div>