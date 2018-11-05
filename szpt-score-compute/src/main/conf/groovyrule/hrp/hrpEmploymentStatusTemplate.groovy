

//规则变量和计算脚本的分割线

def count = paramsMap.get("就业情况").parameter ;

def result = 0 ;
def hitInfos = [] ;

if(count=="无业"){
	result = rulesMap.get("就业情况-无业").value ;
	hitInfos.add("就业情况-无业") ;
}

if(count=="待业"){
	result = rulesMap.get("就业情况-待业").value ;
	hitInfos.add("就业情况-待业") ;
}

if(count=="失业"){
	result = rulesMap.get("就业情况-失业").value ;
	hitInfos.add("就业情况-失业") ;
}

if(count=="就业"){
	result = rulesMap.get("就业情况-就业").value ;
	hitInfos.add("就业情况-就业") ;
}

result = result.toInteger();

def weight = rulesMap.get("就业情况-权重封顶")?rulesMap.get("就业情况-权重封顶").value:null ;
if(weight?.trim()){
	weight = weight.toInteger();
	if(result>weight){
		result = weight ;
	}
}

return ["resultInfo":["hitInfos":hitInfos], "result":result/100] ;