<!DOCTYPE html>
<html>

<head>
<!-- Standard Meta -->
<meta charset="utf-8" />
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
<meta name="viewport"
	content="width=device-width, initial-scale=1, minimum-scale=1, maximum-scale=1">
<link rel="image_src" type="image/jpeg" href="/dist/im/logo.png" />
<!-- Site Properities -->
<meta name="generator" content="DocPad v6.78.4" />
<title>${entity.title}</title>
<meta name="description"
	content="彭元彬, iokays, PengYuanbing <#list entity.keywords as keyword>,${keyword}</#list>" />
<meta name="keywords"
	content="彭元彬, iokays, PengYuanbing <#list entity.keywords as keyword>,${keyword}</#list>" />
<script src="/dist/js/jquery.min.js"></script>
<script src="/dist/js/clipboard.min.js"></script>
<script src="/dist/js/cookie.min.js"></script>
<script src="/dist/js/easing.min.js"></script>
<script src="/dist/js/highlight.min.js"></script>
<script src="/dist/js/history.min.js"></script>
<script src="/dist/js/tablesort.min.js"></script>
<script src="/dist/js/semantic.min.js"></script>
<script src="/dist/js/docs.js"></script>
<link rel="stylesheet" type="text/css" class="ui"
	href="/dist/css/semantic.min.css">
<link rel="stylesheet" type="text/css" href="/dist/css/docs.css">
<link rel="stylesheet" type="text/css" href="/dist/css/rtl.css">
<script type="text/javascript" src="/dist/js/live.js"></script>
</head>

<body id="example" class="started" ontouchstart="">
	<div class="ui vertical inverted sidebar menu" id="toc">
		<div class="item">
			<a class="ui logo icon image" href="/"> <img src="/favicon.ico"></a>
			<a href="/"> <b>${name}</b>
			</a>
		</div>
		<#list catalog?keys as key>
		<div class="item">
			<div class="header">${key}</div>
			<div class="menu">
				<#list catalog[key] as child> <a class="item"
					href="/${child.name}.html">${child.title}</a></#list>
			</div>
		</div>
		</#list>
	</div>
	<div class="ui black big launch right attached fixed button">
		<i class="content icon"></i> <span class="text">Menu</span>
	</div>
	<div class="ui fixed inverted main menu">
		<div class="ui container">
			<a class="launch icon item"> <i class="content icon"></i>
			</a>
			<div class="item">${name}</div>
			<div class="right menu">
				<div class="vertically fitted borderless item"></div>
			</div>
		</div>
	</div>
	<div class="pusher">
		<div class="full height">
			<div class="toc">
				<div class="ui vertical inverted sticky menu">
					<div class="item">
						<a class="ui logo icon image" href="/"> <img
							src="/favicon.ico"></a> <a href="/"> <b>${name}</b>
						</a>
					</div>
					<#list catalog?keys as key>
					<div class="item">
						<div class="header">${key}</div>
						<div class="menu">
							<#list catalog[key] as child> <a class="item"
								href="/${child.name}.html">${child.title}</a></#list>
						</div>
					</div>
					</#list>
				</div>
			</div>
			<div class="article">
				<script src="/dist/js/started.js"></script>
				<div class="ui masthead vertical segment">
					<div class="ui container">
						<div class="introduction">
							<h1 class="ui header">
								${name}
								<div class="sub header">${sign}</div>
							</h1>
							<div class="ui hidden divider"></div>
						</div>
						<div class="advertisement"></div>
					</div>
				</div>
				<div class="main ui intro container">${entity.content}</div>
			</div>
		</div>
	</div>
	<script>
		window.less = {
			async : true,
			environment : 'production',
			fileAsync : false,
			onReady : false,
			useFileCache : true
		};
	</script>

	<script src="/dist/js/less.min.js"></script>
	<script src="/dist/js/popmotion.global.min.js"></script>
	<script src="/dist/js/sort.algorithm.js"></script>
</body>

</html>