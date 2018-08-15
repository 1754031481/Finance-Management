/**
 * zlx(作者)
 * 2018-01-18(开始日期)
 * 2018-07-1(最终更改日期)
 * xxx(最后更改者)
 * */
//图像验证唯一id
var codeid;
localStorage.setItem('flag','1')
$(function(){

	pcMove();
	getCode();
//  $.get('http://123.206.92.142:20031/mobile/view/checkimagecount/hdimage/1234',{
//		uniquenessCode:'123'
//	},function(res){
//		console.log(res)
//		res=JSON.parse(res);
//		if(res.code==1){
//			$('#back').css({'background':'url("'+res.content.smallUrl+'")','backgroundSize':'100% 100%'});
//			$('.xiao').attr('src',res.content.templeUrl);
//			codeid=res.content.uniqueCode;
//		}
//
//	})
})
function getCode(){
	$.ajax({
		type:"get",
		url:"http://139.199.130.252:8781/mobile/view/checkimagecount/hdimage/1234",
		async:true,
		data:{uniquenessCode:'123'},
		success:function(res){
			//res=JSON.parse(res);
			if(res.code==1){
				$('#back').css({'background':'url("'+res.content.smallUrl+'")','backgroundSize':'100% 100%'});
				$('.xiao').attr('src',res.content.templeUrl);
				codeid=res.content.uniqueCode;
			}

		},
		error :function(res){
			$('#tiao span').html('加载中...');
		}
	})
//	$.get('http://123.206.92.142:20031/mobile/view/checkimagecount/hdimage/1234',{
//		uniquenessCode:'123'
//	},function(res){
//		console.log(res)
//		res=JSON.parse(res);
//		if(res.code==1){
//			$('#back').css({'background':'url("'+res.content.smallUrl+'")','backgroundSize':'100% 100%'});
//			$('.xiao').attr('src',res.content.templeUrl);
//			codeid=res.content.uniqueCode;
//		}
//	})
}
//pc端滑动验证
function pcMove(){
	//小滑块
	var div1 = document.getElementById("div1");
	var div3 = document.getElementById("div2");
	//大图片
	var div2 = document.getElementById("tiao");
	//小图片
	var img = document.getElementById("xiao");
	div1.onmousedown = function(ev){
		console.log(localStorage.getItem('flag'))
		if(localStorage.getItem('flag')==1){

			$('#div2').attr('class','');
			$('#div2').addClass('right2');
			$('#div1').attr('class','');
			$('#div1').addClass('right');
			$('#tiao span').html('');
			var oevent = ev || event;
			var distanceX = oevent.clientX - div1.offsetLeft;
			var distanceY = oevent.clientY - div1.offsetTop;
			var left;
			document.onmousemove = function(ev){
				$('#div1').css({"transition":"left 0s"});
				var oevent = ev || event;
				left=oevent.clientX - distanceX;
				if(left<0){
					left=0;
				}else if(left>div2.offsetWidth-div1.offsetWidth){
					left= div2.offsetWidth-div1.offsetWidth;
				}
				if(left==div2.offsetWidth-div1.offsetWidth){
					left=0;
				}
				div1.style.left = left + 'px';
				img.style.left = left + "px";
				div3.style.width=left+"px";
//　　　　　		div1.style.top = oevent.clientY - distanceY + 'px';
			};
			document.onmouseup = function(){
				document.onmousemove = null;
				document.onmouseup = null;
				//验证图片
				var part=parseInt(Math.round((left/div2.offsetWidth*400)*100)/100);
				yan(part)
				console.log(left);
			};
		}
	};
}
//验证
function yan(left){
	$.ajax({
		type:"get",
		url:"http://139.199.130.252:8781/mobile/view/checkimagecount/checkhdimage/1234",
		async:true,
		data:{uniquenessCode:codeid,x:left,type:'123'},
		success:function(res){
			$('#tiao span').html('');
			if(res.code==1){//验证通过
				$('#div2').attr('class','');
				$('#div2').addClass('dui2');
				$('#div1').attr('class','');
				$('#div1').addClass('dui');
				$('#checked').val(1)
				localStorage.setItem('flag','2')
			}else if(res.code==0){//验证错误,没对准
				$('#div2').attr('class','');
				$('#div2').addClass('wrong2');
				$('#div1').attr('class','');
				$('#div1').addClass('wrong');
				$("#zhedang").css('display', 'block');
				setTimeout(function(){
					$("#con").css('left', '-5px');
				},500);
				setTimeout(function(){
					$("#con").css('left', '5px');
				},600);
				setTimeout(function(){
					$("#con").css('left', '-5px');
				},700);
				setTimeout(function(){
					$("#con").css('left', '0px')
					// $('#con').removeClass('wrong2');
					// $('#con').removeClass('right2');
				},800);
				setTimeout(function(){
					$('#div1').css({"left":"0","transition":"left 1s"});
					$('#xiao').css({"left":"0"});
					$("#zhedang").css('display', 'none');
					$('#div2').removeClass('wrong2');
					$('#div2').removeClass('wrong');
					$('#div2').addClass('hhh');
					$('#div1').removeClass('wrong');
					$('#div1').addClass('hhh');
				},1000);

			}
		},
		error :function(res){

		}
	});

	/*	$.get('http://139.199.130.252:8781/mobile/view/checkimagecount/checkhdimage/1234',{
	 uniquenessCode:codeid,
	 x:left,
	 },function(res){
	 //console.log(res)
	 //res=JSON.parse(res);
	 if(res.code==1){
	 alert("success"+res.code);
	 }else if (res.code!=1){
	 alert("error"+res.code);
	 }
	 })*/
}