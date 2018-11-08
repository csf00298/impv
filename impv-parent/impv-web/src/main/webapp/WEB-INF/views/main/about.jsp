<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <title>About</title>
    <jsp:include page="../common/title.jsp"/>
    <script>
		$('#banners')
			.cycle({ 
				fx: 'fade', 
				delay: 7000 ,
				timeout: 30000,
				manualTrump:false,
				cleartypeNoBg: true,
				next: '#next',
				prev: '#prev'
			});
	</script>     
	<!--[if lt IE 8]>
    <div style=' clear: both; text-align:center; position: relative;'>
        <a href="http://windows.microsoft.com/en-US/internet-explorer/products/ie/home?ocid=ie6_countdown_bannercode">
        	<img src="http://storage.ie6countdown.com/assets/100/resource/images/banners/warning_bar_0000_us.jpg" border="0" height="42" width="820" alt="You are using an outdated browser. For a faster, safer browsing experience, upgrade for free today." />
        </a>
    </div>
	<![endif]-->
    <!--[if lt IE 9]>
   		<script type="text/javascript" src="/resource/js/html5.js"></script>
        <link rel="stylesheet" href="/resource/css/ie.css" type="text/css" media="screen">
	<![endif]-->
</head>
<body id="page2">
	<div class="main">
        <!--==============================header=================================-->
        <header>
            <jsp:include page="../common/header.jsp"/>
        </header>
        <!--==============================content================================-->
        <section id="content"><div class="ic">More Website Templates @ <a href="http://www.cssmoban.com/" >网页模板</a> - February 13, 2012!</div>
            <div class="container_12">
                <div class="wrapper">
                    <article class="grid_4">
                    	<div class="indent-right">
                        	<div class="maxheight img-indent-bot">
                                <h3>About Us</h3>
                                <p><span class="color-5">Nam liber tempor cum soluta</span> nobis eleifend option congue nihil imperdiet doming id quod mazim placerat facer possim assum. Lorem ipsum dolor sit amet, consectetuer adipiscing elit, sed diam nonummy nibh euismod tincidunt ut laoreet dolore magna.</p>
                                <span class="color-5">Aliquam erat volutpat.</span> Ut wisi enim ad minim veniam, quis nostrud exerci tation ullamcorper suscipit lobortis nisl ut aliquip ex ea commodo consequat. Consetetur sadipscing elitr, sed diam nonumy eirmod tempor invidunt ut labore et dolore magna aliquyam erat.
                            </div>
                            <a class="button" href="#">More</a>
                        </div>
                    </article>
                    <article class="grid_8">
                    	<div class="indent-left2">
                        	<div class="maxheight img-indent-bot">
                            	<h3>Our Team</h3>
                                <div class="wrapper prev-indent-bot">
                                	<figure class="img-indent"><img src="/resource/images/page2-img1.jpg" alt=""></figure>
                                    <div class="extra-wrap">
                                    	<h6>James Butler</h6>
                                        Liber tempor cum soluta nobis eleifend option congue nihimperdiet doming quod mazim placerat facer possim assum orem ipsum dolor sit amet consectetuer. Adipiscing elit, sed diam nonummy nibh euismod tincidunt ut laoreet dolore.
                                    </div>
                                </div>
                                <div class="wrapper prev-indent-bot">
                                	<figure class="img-indent"><img src="/resource/images/page2-img2.jpg" alt=""></figure>
                                    <div class="extra-wrap">
                                    	<h6>Tory Langdon</h6>
                                        Magna aliquam erat volutpat. Ut wisi enim ad minim veniam, quis nostrud exerci tation ullamcorper suscipit lobortis nisl ut aliquip ex ea commodo consequat. Duis autem vem iriure dolor in hendrerit in vulputate.
                                    </div>
                                </div>
                                <div class="wrapper">
                                	<figure class="img-indent"><img src="/resource/images/page2-img3.jpg" alt=""></figure>
                                    <div class="extra-wrap">
                                    	<h6>Michael Dowson</h6>
                                        Velit esse molestie consequat, vel illum dolore eu feugiat nulla facilisis. Consetetur sadipscing elitr, sed diam nonumy eirmod tempor invidunt ut labore et dolore magna aliquyam erat, sed diam voluptua vero eos et accusam.
                                    </div>
                                </div>
                            </div>
                            <a class="button" href="#">More</a>
                        </div>
                    </article>
                </div>
            </div>
          </section>
        <a class="button" href="/banner/topage/bannerAdd">Bnner设置</a>
        <!--==============================footer=================================-->
        <footer>
        	<div class="inner">
            	<div class="footer-bg">
                	Guide.com &copy; 2012
                    <span><a class="link" target="_blank" href="http://www.cssmoban.com/" rel="nofollow">Website Template</a>Collect from cssmoban.com | <a href="http://www.cssmoban.com">模板之家</a></span>
                </div>
            </div>
        </footer>
    </div>
	<script type="text/javascript"> Cufon.now(); </script>
</body>
</html>
