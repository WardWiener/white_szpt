def paramsMap = ["经济收入（月）":["parameter":6700,"tag":"经济收入（月）"],"就业情况":["parameter":"就业","tag":"就业情况"],"婚姻情况":["parameter":"已婚","tag":"婚姻情况"],"在控类型":["parameter":"高危","tag":"在控类型"],"hrpid":["parameter":"testHrpId1","tag":"hrpid"]] ;

def rulesMap = ["就业情况-无业":["key":"就业情况-无业","value":"100"],"就业情况-评分项权重":["key":"就业情况-评分项权重","value":"15"],"就业情况-失业":["key":"就业情况-失业","value":"80"],"就业情况-就业":["key":"就业情况-就业","value":"40"],"就业情况-待业":["key":"就业情况-待业","value":"80"]] ;

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