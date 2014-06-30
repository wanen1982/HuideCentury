(function($){
	$.fn.countLevel=function(el){
		var pwdVal = $.trim(this.val());
		var chars = this.chars(pwdVal);
		if( (9>=pwdVal.length && pwdVal.length>=6 && chars==2) || (16>=pwdVal.length && pwdVal.length>=10 && chars==1) ){
			$(el).find("button:first-child").next().addClass("btn-primary");
			$(el).find("button:last-child").removeClass("btn-primary");
		}else if( (9>=pwdVal.length && pwdVal.length>=6 && chars==3) || (16>=pwdVal.length && pwdVal.length>=10 && chars==2) ){
			$(el).find("button:first-child").next().addClass("btn-primary");
			$(el).find("button:last-child").addClass("btn-primary");
		}else{
			//只显示弱的
			$(el).find("button:first-child").addClass("btn-primary");
			$(el).find("button:first-child").next().removeClass("btn-primary");
			$(el).find("button:last-child").removeClass("btn-primary");
		}
	};
	//返回组成类型：1种类型字符 2种类型字符 3种类型字符
	$.fn.chars=function(str){
        var a=0,b=0,c=0;
		for(var i=0;i<str.length;i++){
			if(str.charAt(i).charCodeAt(0)>=48 && str.charAt(i).charCodeAt(0) <=57){//数字
				a = 1;
			}else if( (str.charAt(i).charCodeAt(0)>=65 && str.charAt(i).charCodeAt(0)<=90) || (str.charAt(i).charCodeAt(0)>=97 && str.charAt(i).charCodeAt(0)<=122) ){//字母
				b = 1;
			}else{//符号
				c = 1;
			}
		}
		return (a+b+c);
	};
})(jQuery);