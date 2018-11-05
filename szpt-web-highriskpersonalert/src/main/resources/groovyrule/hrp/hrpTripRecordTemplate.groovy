

//规则变量和计算脚本的分割线

def count = paramsMap.get("近一月出行次数").parameter ;

def result = 0 ;

def hitInfos = [] ;

if(count>=6){
	result = rulesMap.get("近一月出行次数-=6次").value ;
	hitInfos.add("近一月出行次数-=6次") ;
}

if(count==5){
	result = rulesMap.get("近一月出行次数-=5次").value ;
	hitInfos.add("近一月出行次数-=5次") ;
}

if(count==4){
	result = rulesMap.get("近一月出行次数-=4次").value ;
	hitInfos.add("近一月出行次数-=4次") ;
}

if(count==3){
	result = rulesMap.get("近一月出行次数-=3次").value ;
	hitInfos.add("近一月出行次数-=3次") ;
}

if(count==2){
	result = rulesMap.get("近一月出行次数-=2次").value ;
	hitInfos.add("近一月出行次数-=2次") ;
}

if(count<=1){
	result = rulesMap.get("近一月出行次数-<=1次").value ;
	hitInfos.add("近一月出行次数-<=1次") ;
}

result = result.toInteger();

def weight = rulesMap.get("近一月出行次数-权重封顶")?rulesMap.get("近一月出行次数-权重封顶").value:null ;
if(weight?.trim()){
	weight = weight.toInteger();
	if(result>weight){
		result = weight ;
	}
}

return ["resultInfo":["hitInfos":hitInfos], "result":result/100] ;
