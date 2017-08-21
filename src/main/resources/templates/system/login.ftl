<!DOCTYPE html>
<html lang="zh">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport" content="width=device-width, initial-scale=1, user-scalable=yes">
    <title>Rock后台管理系统</title>
    <link rel="shortcut icon" type="image/x-icon" href="adminlte/dist/img/favicon.ico">
    <link rel="stylesheet" href="/adminlte/dist/css/login2.css">
    <!--[if IE]>
	<script src="http://libs.useso.com/js/html5shiv/3.7/html5shiv.min.js"></script>
    <![endif]-->
    <script type="text/javascript" charset="utf-8" src="/adminlte/plugins/an_login/edge_includes/edge.6.0.0.min.js"></script>
    <script>
        AdobeEdge.loadComposition('/adminlte/plugins/an_login/edge_includes/', 'EDGE-279555267', {
            scaleToFit: "width",
            centerStage: "none",
            minW: "0px",
            maxW: "undefined",
            width: "1000px",
            height: "400px"
        }, {dom: [ ]}, {dom: [ ]});
    </script>
    <style type="text/css">
        .flow-wrapper{
            position: absolute;
            bottom: -100px;
            top:0px;
            margin: auto;
        }
        #Stage{
            background-color: rgb(57, 173, 205) !important;
        }
        #bg{
            height: 40%;
            position: absolute;
            width: 100%;
            bottom:0px;;
        }
        .login{
            background: none;
            color: 575757;;
        }
    </style>
</head>
<body style=" background-color: rgb(57, 173, 205) !important;">
<div id="Stage" class="EDGE-279555267">
</div>
<div id="bg" style="background: #78d2eb"></div>
<div class="cont" style="background-image:none;">
    <div class="demo">
        <div class="login">
            <div class="login__check">
                <img src="/adminlte/dist/img/logo.png" alt="">
            </div>
            <form action="/postLogin" class="login__form" method="post">
                <!--默认账号密码user、admin、super-->
			<#--<input type="hidden" name="username" />-->
			<#--<input type="hidden" name="password" />-->
                <div class="login__row">
                    <svg class="login__icon name svg-icon" viewBox="0 0 20 20">
                        <path d="M0,20 a10,8 0 0,1 20,0z M10,0 a4,4 0 0,1 0,8 a4,4 0 0,1 0,-8" />
                    </svg>
                    <!--账号-->
                    <input type="text" name="username" class="login__input name" placeholder="Username"/>
                </div>
                <div class="login__row">
                    <svg class="login__icon pass svg-icon" viewBox="0 0 20 20">
                        <path d="M0,20 20,20 20,8 0,8z M10,13 10,16z M4,8 a6,8 0 0,1 12,0" />
                    </svg>
                    <!--密码-->
                    <input type="password" name="password" class="login__input pass" placeholder="Password"/>
                </div>
                <p>${failed!}</p>
                <button type="submit" class="login__submit">登 录</button>
                <p class="login__signup">还没有账号? &nbsp;<a href="#" target="_blank">立刻注册</a></p>
            </form>
        </div>
        <div class="app">
            <div class="app__bot">
                <div class="app__title">
                </div>
                <form class="app__meetings reg__form">
                    <div class="app__meeting">
                        <div class="glyphicon glyphicon-user"></div>
                        <div class="reg__enter">
                            <input type="text" class="name" placeholder="用户名"/>
                        </div>
                    </div>
                    <div class="app__meeting">
                        <div class="glyphicon glyphicon-envelope "></div>
                        <div class="reg__enter">
                            <input type="email" class="name" placeholder="邮箱地址"/>
                        </div>
                    </div>
                    <div class="app__meeting">
                        <div class="glyphicon glyphicon-lock "></div>
                        <div class="reg__enter">
                            <input type="password" class="name" placeholder="密码"/>
                        </div>
                    </div>
                    <div class="app__meeting">
                        <div class="glyphicon glyphicon-log-in"></div>
                        <div class="reg__enter">
                            <input type="text" class="name" placeholder="确认密码"/>
                        </div>
                    </div>
                    <div class="text-center">
                        <button class="login__submit">注册</button>
                    </div>
                </form>
            </div>
            <div class="app__logout">
                <svg class="app__logout-icon svg-icon" viewBox="0 0 20 20">
                    <path d="M6,3 a8,8 0 1,0 8,0 M10,0 10,12"/>
                </svg>
            </div>
        </div>
    </div>
</div>

<script src="/adminlte/dist/js/pages/login2.js"></script>
<!-- jQuery 2.2.3 -->
<script src="/adminlte/plugins/jQuery/jquery-2.2.3.min.js"></script>

<script>
    $(document).ready(function () {
        var animating = false, submitPhase1 = 1100, submitPhase2 = 400, logoutPhase1 = 800, $login = $('.login'), $app = $('.app');
        function ripple(elem, e) {
            $('.ripple').remove();
            var elTop = elem.offset().top, elLeft = elem.offset().left, x = e.pageX - elLeft, y = e.pageY - elTop;
            var $ripple = $('<div class=\'ripple\'></div>');
            $ripple.css({
                top: y,
                left: x
            });
            elem.append($ripple);
        }
        ;
        $('.login__form,.reg__form').on('submit', function (e) {
            if (animating)
                return false;
            animating = true;
            var that = $(".login__submit");
            ripple($(that), e);
            $(that).addClass('processing');
            setTimeout(function () {
                animating = false;
                that.removeClass('processing');
                alert("登录成功")
            }, submitPhase1);
            return true;
        });

        $(".login__signup a").on('click', function (e) {
            var that = $(".login__submit");
            that.addClass('processing success');
            setTimeout(function () {
                $app.show();
                $app.css('top');
                $app.addClass('active');
            }, submitPhase2 - 70);
            setTimeout(function () {
                $login.hide();
                $login.addClass('inactive');
                animating = false;
                that.removeClass('success processing');
            }, submitPhase2);
            return false;
        });

        $(document).on('click', '.app__logout', function (e) {
            if (animating)
                return;
            $('.ripple').remove();
            animating = true;
            var that = $(".login__submit");
            that.addClass('processing success');
            setTimeout(function () {
                $app.removeClass('active');
                $login.show();
                $login.css('top');
                $login.removeClass('inactive');
            }, submitPhase2 - 70);
            setTimeout(function () {
                $app.hide();
                animating = false;
                that.removeClass('success processing');
            }, submitPhase2);
        });
    });
</script>
</body>
</html>