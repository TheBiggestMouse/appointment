//支持浏览器版本 IE6,IE7,IE8,IE9,IE10
//firefox3.0/4.0/5.0/6.0/7.0/8.0/12.0
//Chrome 28.0.1500.71
//测试浏览器为 IE8,Firefox12.0 Chrome28.0
function yuLan(obj,image,preview){
	var img = document.getElementById(image);
	var div = document.getElementById(preview);
	var kuan = img.width;
	var gao = img.height;
	if( window.navigator.userAgent.indexOf("MSIE")>=1 ){//是否是IE浏览器
		div.style.filter = "progid:DXImageTransform.Microsoft.AlphaImageLoader(sizingMethod=scale)";

		if(kuan==128&&gao==128){kuan=75;gao=100;}//如果<img/>没有设置宽和高，则默认是宽和高都 是128
		div.style.width=kuan;
		div.style.height = gao;
		obj.select();
		div.focus();
		var imgSrc =document.selection.createRange().text;
		//给滤镜设置src属性
		div.filters.item('DXImageTransform.Microsoft.AlphaImageLoader').src= imgSrc;
		//屏蔽掉当前图像内容
		img.style.display ='none';
		
	}else if( window.navigator.userAgent.indexOf("Firefox")>=1 ){//是否是Firefox3浏览器
		
		if( obj.files.length){

			img.style.display = "block";
			img.style.width = kuan;
			img.style.height = gao;
			
			var version = navigator.userAgent;
			var str = version.substring( version.indexOf("rv:")+3 ,version.indexOf(")")  );
			if( Number(str)>8 ){
				window.URL = window.URL || window.webkitURL;
				img.src = window.URL.createObjectURL(obj.files[0]);
				img.onload = function(e) {
					window.URL.revokeObjectURL(this.src);
				 }
			}else{
				img.src = obj.files[0].getAsDataURL(); //只在火狐3上好使
				img.src = obj.files.item(0).getAsDataURL(); 
			}
		}
	}else if(window.navigator.userAgent.indexOf("Chrome")>=1){//是否是谷歌浏览器
		img.style.display = "block";
		img.style.width = kuan;
		img.style.height = gao;
		window.URL = window.URL || window.webkitURL;
		img.src = window.URL.createObjectURL(obj.files[0]);
		img.onload = function(e) {
			window.URL.revokeObjectURL(this.src);
		 }
	}else if(window.navigator.userAgent.indexOf("SLCC2")>=1){
		var version = navigator.userAgent;
		var str = version.substring( version.indexOf("rv:")+3 ,version.indexOf(")")  );
		if( Number(str)>=11 ){
			img.style.display = "block";
			img.style.width = kuan;
			img.style.height = gao;
			window.URL = window.URL || window.webkitURL;
			img.src = window.URL.createObjectURL(obj.files[0]);
			img.onload = function(e) {
				window.URL.revokeObjectURL(this.src);
			 }
		}
	}else{
		img.style.display = "block";
		img.style.width = kuan;
		img.style.height = gao;
		window.URL = window.URL || window.webkitURL;
		img.src = window.URL.createObjectURL(obj.files[0]);
		img.onload = function(e) {
			window.URL.revokeObjectURL(this.src);
		 }
	}
}
function getPath(obj) { 
   if(obj){ 
        if (window.navigator.userAgent.indexOf("MSIE")>=1){ 
            obj.select(); 
            return document.selection.createRange().text; 
        } 
        else if(window.navigator.userAgent.indexOf("Firefox")>=1){ 
            if(obj.files){ 
                return obj.files.item(0).getAsDataURL(); 
            } 
            return obj.value; 
        } 
        return obj.value; 
    } 
}