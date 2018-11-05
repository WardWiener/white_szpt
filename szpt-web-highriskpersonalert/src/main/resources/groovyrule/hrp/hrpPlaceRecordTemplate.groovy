

//规则变量和计算脚本的分割线

def dayMillisecond = 86400000 ;
def hourMillisecond = 3600000 ;

def internetBarHours = paramsMap.get("场所属性-网吧权重（近一年）").parameter/hourMillisecond ;
def hotelDays = paramsMap.get("场所属性-酒店宾馆权重").parameter/dayMillisecond ;

def hitInfos = [] ;

def internetBarResult = 0 ;
if(internetBarHours>=240){
	internetBarResult = rulesMap.get("场所属性-网吧权重（近一年）@>=240小时").value ;
	hitInfos.add("场所属性-网吧权重（近一年）@>=240小时") ;
}
if(internetBarHours>56 && internetBarHours<240){
	internetBarResult = rulesMap.get("场所属性-网吧权重（近一年）@>56小时，且<240小时").value ;
	hitInfos.add("场所属性-网吧权重（近一年）@>56小时，且<240小时") ;
}
if(internetBarHours<=56){
	internetBarResult = rulesMap.get("场所属性-网吧权重（近一年）@<=56小时").value ;
	hitInfos.add("场所属性-网吧权重（近一年）@<=56小时") ;
}
internetBarResult = internetBarResult.toInteger();

def hotelResult = 0 ;
if(hotelDays>=20){
	hotelResult = rulesMap.get("场所属性-酒店宾馆权重（近一年）@>=20天").value ;
	hitInfos.add("场所属性-酒店宾馆权重（近一年）@>=20天") ;
}
if(hotelDays>2 && hotelDays<20){
	hotelResult = rulesMap.get("场所属性-娱乐场所权重（近一年）@>2次，且<20次").value ;
	hitInfos.add("场所属性-娱乐场所权重（近一年）@>2次，且<20次") ;
}
if(hotelDays<=2){
	hotelResult = rulesMap.get("场所属性-娱乐场所权重（近一年）@<=2次").value ;
	hitInfos.add("场所属性-娱乐场所权重（近一年）@<=2次") ;
}
hotelResult = hotelResult.toInteger();

def result = internetBarResult+hotelResult;

def weight = rulesMap.get("场所属性-权重封顶")?rulesMap.get("场所属性-权重封顶").value:null ;
if(weight?.trim()){
	weight = weight.toInteger();
	if(result>weight){
		result = weight ;
	}
}

return ["resultInfo":["hitInfos":hitInfos], "result":result/100] ;