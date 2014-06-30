(function($){
    /**
	 * scroller
	 */
	$.fn.scroller=function(settings){
		settings=$.extend({
			nBtn:"n"
			,pBtn:"n"
			,child:""
			,direction:"lr"//tb
			,autoPlay:false
			,interval:1000
                        ,slideTime:100
			,endless:false
			,step:false
		},settings);
		var _this=this;
		return _this.each(function(){
			this.timer=null;
			$.fn.scroller.init(this,settings);
		})
	};

	$.fn.scroller.scroll=function(dir,option){
		switch(option.direction){
			case 'lr':
				this.scrollHorizontal(dir,option);
			break;
			case 'tb':
				this.scrollVertical(dir,option);
			break;
		}
	}

	$.fn.scroller.scrollVertical=function(dir,option){
		var $this=option.parent;
		var _top=option.startRect.top;
		var _unit=option.iHeight;
		var _scrollTop=$this.scrollTop();
		var __scrollHeight=_scrollTop+_unit*dir
		$this.animate({
			scrollTop:__scrollHeight
		},{duration :option.slideTime,queue :false,complete:function(){
			if(option.endless&&dir<0){
				if(__scrollHeight<=_top){
					$this.scrollTop(__scrollHeight+_top);
				}
			}else{
				if(__scrollHeight>=option.pWidth+_top){
					$this.scrollTop(__scrollHeight-_top)
				}
			}
		}});
	}

	$.fn.scroller.scrollHorizontal=function(dir,option){
		var $this=option.parent;
		var _left=option.startRect.left;
		var _unit=option.iWidth;
		var _scrollLeft=$this.scrollLeft();
		var __scrollWidth=_scrollLeft+_unit*dir;
		if (option.step) {
			__scrollWidth=_scrollLeft+1
		}
		$this.animate({
			scrollLeft:__scrollWidth
		},{duration:option.slideTime,queue:false,complete:function(){
			if(option.endless&&dir<0){
				if(__scrollWidth<=_left){
					$this.scrollLeft(__scrollWidth+_left);
				}
			}else{
				if(__scrollWidth>=option.pWidth+_left){
					$this.scrollLeft(__scrollWidth-_left)
				}
			}
		}});

	}

	$.fn.scroller.init=function(context,settings){
		//inital tier;
		var $this=$(context);
		var _child=settings.child!=""?settings.child:".scroller-item"
		var _this=this;
		var _scrollerItem=$this.find(_child);
		var _count=_scrollerItem.size();
		var _tempHtml=$this.html();
		var _startRect={
			left:0
			,top:0
		}
		
		var _scrollerItemWidth=_scrollerItem.width();
		var _scrollerItemHeight=_scrollerItem.height();
		if(settings.endless){
			_startRect={
				left:_count*_scrollerItemWidth
				,top:_count*_scrollerItemHeight
			}
			$this.append(_tempHtml);
			$this.prepend(_tempHtml);
		}
		var _option={
			parent:$this
			,size:_count
			,startRect:_startRect
			,pWidth:_count*_scrollerItemWidth
			,pHeight:_count*_scrollerItemHeight
			,iWidth:_scrollerItemWidth
			,iHeight:_scrollerItemHeight
			,direction:settings.direction
			,interval:settings.interval
            ,slideTime:settings.slideTime
			,endless:settings.endless
			,step:settings.step
			,context:context
		}
		
		if(settings.direction=="lr"){
			$this.css("white-space","nowrap");
			$this.scrollLeft(_startRect.left)
		}else{
			$this.css("white-space","normal");
			$this.scrollTop(_startRect.top);
		}
                if (settings.autoPlay) {
                    _this.start(_option,_this);
                }
		$("body").on("click",settings.nBtn,function(){
			_this.scroll(1,_option);
		}).on("click",settings.pBtn,function(){
			_this.scroll(-1,_option);
		}).on("mouseenter",_child,function(){
                    _this.stop(_option);
                }).on("mouseleave",_child,function(){
                    if (settings.autoPlay) {
                        _this.start(_option,_this);
                    }
                }).on("mouseenter",settings.nBtn,function(){
                    _this.stop(_option)})
		    .on("mouseenter",settings.pBtn,function(){
                    _this.stop(_option);})
		
	}
        
        $.fn.scroller.start=function(_option,_this){
	    var context=_option.context
            context.timer=setInterval(function(){
                _this.scroll(1,_option);
            },_option.interval)
        }
        
        $.fn.scroller.stop=function(_option){
	    var context=_option.context
            clearInterval(context.timer);
        }
})(jQuery);


var timer=null;
$(document).on("mouseenter",".nav-list .child",function(){
    $(".nav-list .child").removeClass("hover");
    $(this).addClass("hover")
    clearTimeout(timer);
}).on("mouseleave",".nav-list .child",function(){
    var $this=$(this);
    timer=setTimeout(function(){
        $this.removeClass("hover")
    },200)
}).on("mouseenter",".cnav",function(){
    $(this).parents("li").addClass("hover")
}).on("mouseleave",".cnav",function(){
    $(this).parents("li").removeClass("hover")
}).on("mouseenter",".tab-list li",function(){
    var $this=$(this);
    var _parent=$this.parents(".inner-case");
    var _index=$this.index();
    _parent.find(".tab-list li").removeClass("selected").eq(_index).addClass("selected");
    _parent.find(".tab-content").removeClass("selected").eq(_index).addClass("selected");
}).ready(function(){
	$(".main-content img,main-content table").each(function(){
		$(this).width($(this).width() > 700 ? "700px" : $(this).width())
	});
	$(".movie-inner ul").scroller({
	    endless:true
	    ,child:"li"
        ,interval:50
        ,slideTime:1
        ,autoPlay:true
	    ,step:true
    });
    $(".stage-inner").scroller({
        nBtn:".btn-right"
        ,pBtn:".btn-left"
	    ,child: ".scroller-item"
        ,endless:true
        ,interval:3000
        ,slideTime:500
        ,autoPlay:true
    })
})