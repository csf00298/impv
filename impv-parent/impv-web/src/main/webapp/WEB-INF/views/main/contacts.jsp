<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <title>Contacts</title>
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
<body id="page5">
	<div class="main">
        <!--==============================header=================================-->
        <header>
            <jsp:include page="../common/header.jsp"/>
        </header>
        <!--==============================content================================-->
        <section id="content"><div class="ic">More Website Templates @ <a href="http://www.cssmoban.com/" >网页模板</a> - February 13, 2012!</div>
            <div class="container_12">
                <div class="wrapper">
                    <article class="grid_8">
                    	<div class="indent-right">
                        	<h3 class="prev-indent-bot">Contact Form</h3>
                            <form id="contact-form" method="post" enctype="multipart/form-data">                    
                                <fieldset>
                                      <label><span class="text-form">Name:</span><input name="name" type="text" /></label>
                                      <label><span class="text-form">Email:</span><input name="email" type="text" /></label>                              
                                      <label><span class="text-form">Phone:</span><input name="phone" type="text" /></label>                              
                                      <div class="wrapper">
                                        <div class="text-form">Message:</div>
                                        <div class="extra-wrap">
                                            <textarea></textarea>
                                            <div class="clear"></div>
                                            <div class="buttons">
                                                <a class="button" href="#" onClick="document.getElementById('contact-form').reset()">Clear</a>
                                                <a class="button" href="#" onClick="document.getElementById('contact-form').submit()">Send</a>
                                            </div> 
                                        </div>
                                      </div>                            
                                </fieldset>						
                            </form>
                        </div>
                    </article>
                    <article class="grid_4">
                    	<div class="indent-left3">
                            <h3>Recent News</h3>
                            24 Hour Emergency Towing<br>
                            <span class="color-5">Monday - Friday: 7:30 am - 6:00</span><br>
                            <span class="color-5">Saturday: 7:30 am - Noon</span><br>
                            <p class="indent-bot">Night Drop Available</p>
                            <figure class="indent-bot">
                                <iframe width="285" height="185" ></iframe>
                            </figure>
                            <dl>
                                <dt>Demolink.org 8901 Marmora Road, Glasgow, D04 89GR.</dt>
                                <dd><span>Telephone:</span>+1 959 552 5963;</dd>
                                <dd><span>FAX:</span>+1 959 552 5963</dd>
                                <dd><span>E-mail:</span><a href="#">mail@demolink.org</a></dd>
                            </dl>

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
