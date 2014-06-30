/**
 * jQuery生日选择插件
 */
(function($){
	/**
	 * 通过年月返回天数
	 */
	var getDays4Month = function(year,month){
		//大月
		var bigMonths = [1,3,5,7,8,10,12];
		if($.inArray(month,bigMonths) > -1){
			return 31;
		}
		//小月
		var smallMonths = [4,6,9,11];
		if($.inArray(month,smallMonths) > -1){
			return 30;
		}
		//2月，平月分闰年和非闰年
		if((year%4==0 && year%100!=0) || (year%400==0)){
			return 29;
		}
		return 28;
	};
	$.fn.ininBirthday=function(yearEl,monthEl,dayEl){
		var nowDate = new Date();
		var nowYear = nowDate.getFullYear();
		//年份初始化往前推65年
		for(var i=0; i<=65; i++){
			var val = nowYear-i;
			$(yearEl).append("<option value='"+val+"'>"+val+"</option>");
		}
		//年份切换事件
		$(yearEl).bind("change",function(e){
			if($(this).val()==""){
				$(monthEl).empty();
				$(monthEl).append("<option value=''>请选择</option>");
				$(dayEl).empty();
				$(dayEl).append("<option value=''>请选择</option>");
				return ;
			}
			//选中具体值时,初始化月份
			$(monthEl).empty();
			for(var i=1; i<=12; i++){
				$(monthEl).append("<option value='"+i+"'>"+i+"</option>");
			}
			$(dayEl).empty();
			for(var i=1; i<=31; i++){
				$(dayEl).append("<option value='"+i+"'>"+i+"</option>");
			}
		});
		//月份切换事件
		$(monthEl).bind("change",function(e){
			//判断闰年闰月
			var dayTotal = getDays4Month(parseInt($(yearEl).val()),parseInt($(monthEl).val()));
			$(dayEl).empty();
			for(var i=1; i<=dayTotal; i++){
				$(dayEl).append("<option value='"+i+"'>"+i+"</option>");
			}
		});
	};
})(jQuery);