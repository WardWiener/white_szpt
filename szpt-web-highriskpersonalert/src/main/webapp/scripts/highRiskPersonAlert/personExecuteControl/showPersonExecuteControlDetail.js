(function($){
	"use strict";
	var frameData = window.top.$.layerAlert.getFrameInitData(window) ;
	var pageIndex = frameData.index ;//当前弹窗index
	var initData = frameData.initData ;
	var detail = initData.detail ;
	
	$(document).ready(function() {	
		var arr=new Object();
		arr=$.util.parseJsonByFunc(detail);
		var str="";
		/***************   FlightInfo  ***************/
		if(arr.idCard!=null){
			str+="<tr><td>身份证号</td><td>"+arr.idCard+"</td></tr>";
		}
		if(arr.flightNumber!=null){
			str+="<tr><td>航班号</td><td>"+arr.flightNumber+"</td></tr>";
		}
		if(arr.takeoffAirport!=null){
			str+="<tr><td>起飞机场</td><td>"+arr.takeoffAirport+"</td></tr>";
		}
		if(arr.arriveatAirport!=null){
			str+="<tr><td>到达机场</td><td>"+arr.arriveatAirport+"</td></tr>";
		}
		if(arr.takeoffTime!=null){
			str+="<tr><td>起飞时间</td><td>"+$.date.timeToStr(arr.takeoffTime, "yyyy-MM-dd HH:mm:ss")+"</td></tr>";
		}
		if(arr.arrivalTime!=null){
			str+="<tr><td>到达时间</td><td>"+$.date.timeToStr(arr.arrivalTime, "yyyy-MM-dd HH:mm:ss")+"</td></tr>";
		}
		/***************   HotelInfo  ***************/
		if(arr.hotelName!=null){
			str+="<tr><td>旅馆名称</td><td>"+arr.hotelName+"</td></tr>";
		}
		if(arr.hotelCode!=null){
			str+="<tr><td>旅馆编码</td><td>"+arr.hotelCode+"</td></tr>";
		}
		if(arr.hotelAddress!=null){
			str+="<tr><td>旅馆地址</td><td>"+arr.hotelAddress+"</td></tr>";
		}
		
		/***************   ImgInfo   ***************/
		if(arr.cameraCode!=null){
			str+="<tr><td>摄像头编码</td><td>"+arr.cameraCode+"</td></tr>";
		}
		if(arr.cameraAddr!=null){
			str+="<tr><td>摄像头地址</td><td>"+arr.cameraAddr+"</td></tr>";
		}
		if(arr.confidenceLevel!=null){
			str+="<tr><td>置信度</td><td>"+arr.confidenceLevel+"</td></tr>";
		}
		if(arr.surveilListCode!=null){
			str+="<tr><td>布控单编码</td><td>"+arr.surveilListCode+"</td></tr>";
		}
		if(arr.catchDate!=null){
			str+="<tr><td>预警时间</td><td>"+$.date.timeToStr(arr.catchDate, "yyyy-MM-dd HH:mm:ss")+"</td></tr>";
		}
		/***************   InternetBarInfo   ***************/
		if(arr.cyberCafeName!=null){
			str+="<tr><td>网吧名称</td><td>"+arr.cyberCafeName+"</td></tr>";
		}
		if(arr.cyberCafeCode!=null){
			str+="<tr><td>网吧编码</td><td>"+arr.cyberCafeCode+"</td></tr>";
		}
		if(arr.cyberCafeAddress!=null){
			str+="<tr><td>网吧地址</td><td>"+arr.cyberCafeAddress+"</td></tr>";
		}
		/***************   TrainInfo   ***************/
		if(arr.trainNumber!=null){
			str+="<tr><td>车次</td><td>"+arr.trainNumber+"</td></tr>";
		}
		if(arr.startStation!=null){
			str+="<tr><td>出发车站</td><td>"+arr.startStation+"</td></tr>";
		}
		if(arr.arrivalStation!=null){
			str+="<tr><td>目的车站</td><td>"+arr.arrivalStation+"</td></tr>";
		}
		if(arr.startTime!=null){
			str+="<tr><td>发车时间</td><td>"+arr.startTime+"</td></tr>";
		}
		/***************   WifiInfo   ***************/
		if(arr.mac!=null){
			str+="<tr><td>mac地址</td><td>"+arr.mac+"</td></tr>";
		}
		if(arr.placeCode!=null){
			str+="<tr><td>mac场所编码</td><td>"+arr.placeCode+"</td></tr>";
		}
		if(arr.placeName!=null){
			str+="<tr><td>mac场所名称</td><td>"+arr.placeName+"</td></tr>";
		}
		if(arr.phone!=null){
			str+="<tr><td>手机号</td><td>"+arr.phone+"</td></tr>";
		}
		/***************   公共   ***************/
		if(arr.longitude!=null){
			str+="<tr><td>摄像头经度</td><td>"+arr.longitude+"</td></tr>";
		}
		if(arr.latitude!=null){
			str+="<tr><td>摄像头纬度</td><td>"+arr.latitude+"</td></tr>";
		}
		if(arr.enterTime!=null){
			str+="<tr><td>进入时间</td><td>"+$.date.timeToStr(arr.enterTime, "yyyy-MM-dd HH:mm:ss")+"</td></tr>";
		}
		if(arr.leaveTime!=null){
			str+="<tr><td>离开时间</td><td>"+$.date.timeToStr(arr.leaveTime, "yyyy-MM-dd HH:mm:ss")+"</td></tr>";
		}
		$('#table').append(str);
	});
	
})(jQuery);

