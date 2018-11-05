

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