def paramsMap = ["经济收入（月）":["parameter":6700,"tag":"经济收入（月）"],"就业情况":["parameter":"就业","tag":"就业情况"],"婚姻情况":["parameter":"已婚","tag":"婚姻情况"],"在控类型":["parameter":"高危","tag":"在控类型"],"hrpid":["parameter":"testHrpId1","tag":"hrpid"]] ;

def rulesMap = ["在控类型-无":["key":"在控类型-无","value":"0"],"在控类型-关注":["key":"在控类型-关注","value":"80"],"在控类型-一般":["key":"在控类型-一般","value":"60"],"在控类型-高危":["key":"在控类型-高危","value":"110"],"在控类型-评分项权重":["key":"在控类型-评分项权重","value":"20"]] ;

//规则变量和计算脚本的分割线

def count = paramsMap.get("在控类型")?paramsMap.get("在控类型").parameter:"无" ;

def result = 0 ;
def hitInfos = [] ;

if(count=="高危"){
	result = rulesMap.get("在控类型-高危").value ;
	hitInfos.add("在控类型-高危") ;
}

if(count=="关注"){
	result = rulesMap.get("在控类型-关注").value ;
	hitInfos.add("在控类型-关注") ;
}

if(count=="一般"){
	result = rulesMap.get("在控类型-一般").value ;
	hitInfos.add("在控类型-一般") ;
}

if(count=="无"){
	result = rulesMap.get("在控类型-无").value ;
	hitInfos.add("在控类型-无") ;
}

result = result.toInteger();

def weight = rulesMap.get("在控类型-权重封顶")?rulesMap.get("在控类型-权重封顶").value:null ;
if(weight?.trim()){
	weight = weight.toInteger();
	if(result>weight){
		result = weight ;
	}
}

return ["resultInfo":["hitInfos":hitInfos], "result":result/100] ;