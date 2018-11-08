<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <title>Products</title>
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
   		<script type="text/javascript" src="js/html5.js"></script>
        <link rel="stylesheet" href="resource/css/ie.css" type="text/css" media="screen">
	<![endif]-->
</head>
<body id="page4">
	<div class="main">
        <!--==============================header=================================-->
        <header>
            <jsp:include page="../common/header.jsp"/>
        </header>
        
        <!--==============================content================================-->
        <section id="content"><div class="ic">More Website Templates @ <a href="http://www.cssmoban.com/" >网页模板</a> - February 13, 2012!</div>
            <div class="container_12">
                <div class="wrapper">
                    <article class="grid_12">
                    	<h3>Featured Products</h3>
                        <div class="wrapper">
                        	<div class="grid_6 alpha">
                            	<div class="indent-right">
                                	<p class="indent-bot"><span class="color-5">Lorem ipsum dolor sit amet consetetur sadipscing elitr</span>, sed diam nonumy eirmod tempor invidunt ut labore et dolore magna aliquyam erat, sed diam voluptua. At vero eos et accusam et justo duo dolores et ea rebum. Stet clita kasd gubergren, no sea takimata sanctus.</p>
                                    <div class="wrapper p3">
                                    	<figure class="img-indent"><img src="/resource/images/page4-img1.jpg" alt=""></figure>
                                        <div class="extra-wrap">
                                        	<h5 class="prev-indent-bot">Et accusam et justo duo dolores et ea rebum stet clita kasd.</h5>
                                            <p class="indent-bot">Gubergren no sea takmata sanctus est Lorem ipsum dolor ametorem ipsum dolor.</p>
                                            <a class="button" href="#">More</a>
                                        </div>
                                    </div>
                                    <div class="wrapper">
                                    	<figure class="img-indent"><img src="/resource/images/page4-img2.jpg" alt=""></figure>
                                        <div class="extra-wrap">
                                        	<h5 class="prev-indent-bot">Et accusam et justo duo dolores et ea rebum stet clita kasd.</h5>
                                            <p class="indent-bot">Gubergren no sea takmata sanctus est Lorem ipsum dolor ametorem ipsum dolor.</p>
                                            <a class="button" href="#">More</a>
                                        </div>
                                    </div>
                                </div>
                            </div>
                            <div class="grid_6 omega">
                                <div class="indent-left3">
                                    <ul class="list-1 p2">
                                        <li><a href="#">Nam liber tempor cum soluta nobis eleifend option</a></li>
                                        <li><a href="#">Congue nihil imperdiet doming id quod mazim</a></li>
                                        <li><a href="#">Placerat facer possim assum lorem ipsum dolor sit amet</a></li>
                                    </ul>
                                    <div class="wrapper p3">
                                    	<figure class="img-indent"><img src="/resource/images/page4-img3.jpg" alt=""></figure>
                                        <div class="extra-wrap">
                                        	<h5 class="prev-indent-bot">Et accusam et justo duo dolores et ea rebum stet clita kasd.</h5>
                                            <p class="indent-bot">Gubergren no sea takmata sanctus est Lorem ipsum dolor ametorem ipsum dolor.</p>
                                            <a class="button" href="#">More</a>
                                        </div>
                                    </div>
                                    <div class="wrapper">
                                    	<figure class="img-indent"><img src="/resource/images/page4-img4.jpg" alt=""></figure>
                                        <div class="extra-wrap">
                                        	<h5 class="prev-indent-bot">Et accusam et justo duo dolores et ea rebum stet clita kasd.</h5>
                                            <p class="indent-bot">Gubergren no sea takmata sanctus est Lorem ipsum dolor ametorem ipsum dolor.</p>
                                            <a class="button" href="#">More</a>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </article>
                </div>
            </div>
          </section>
        
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
