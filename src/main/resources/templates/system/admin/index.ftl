<!DOCTYPE html>
<html>
<head>
  <#include "../macro/base.ftl"/>
  <meta charset="utf-8">
  <meta http-equiv="X-UA-Compatible" content="IE=edge">
  <title>Rock后台</title>
  <@style/>
</head>
<body class="sidebar-mini ajax-template skin-blue fixed">
	<div class="wrapper">
		<@header/>
		<@menu/>
		<!-- Content Wrapper. Contains page content -->
		<div class="content-wrapper">
		<!-- Content Header (Page header) -->
		<section class="content-header-navtabs">
			<div class="tabs-page">
				<ul class="tabs-list clearfix" id="navTabs">
					<li class="active"><span>我的主页</span></li>
				</ul>
				<a href="javascript:void(0);" class="prev fa fa-step-backward"></a>
				<a href="javascript:void(0);" class="next fa fa-step-forward"></a> 
			</div>
			<!--邮件按钮-->
			<div class="context-menu" id="contextMenu">
				<ul class="ct-nav">
					<li rel="reload">刷新标签页</li>
					<li rel="closeCurrent">关闭标签页</li>
					<li rel="closeOther">关闭其他标签页</li>
					<li rel="closeAll">关闭全部标签页</li>
				</ul>
			</div>
		</section>
		<!-- Main content -->
		<section class="content" id="content">
			<div class="tabs-panel">
				<h1>欢迎使用Rock管理后台</h1>
			</div>
		</section>
		</div>
		<@footer/>
		<@setting/>
	</div>
	<@jsFile/>
</body>
</html>
