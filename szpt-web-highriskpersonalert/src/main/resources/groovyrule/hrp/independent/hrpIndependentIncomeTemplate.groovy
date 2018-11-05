def paramsMap = ["经济收入（月）":["parameter":6700,"tag":"经济收入（月）"],"就业情况":["parameter":"就业","tag":"就业情况"],"婚姻情况":["parameter":"已婚","tag":"婚姻情况"],"在控类型":["parameter":"高危","tag":"在控类型"],"hrpid":["parameter":"testHrpId1","tag":"hrpid"]] ;

def rulesMap = ["经济收入（月）-1000~2000":["key":"经济收入（月）-1000~2000","value":"90"],"经济收入（月）-5000以上":["key":"经济收入（月）-5000以上","value":"50"],"经济收入（月）-少于1000元":["key":"经济收入（月）-少于1000元","value":"100"],"经济收入（月）-空":["key":"经济收入（月）-空","value":"0"],"经济收入（月）-评分项权重":["key":"经济收入（月）-评分项权重","value":"5"],"经济收入（月）-2000~5000":["key":"经济收入（月）-2000~5000","value":"70"]] ;

//规则变量和计算脚本的分割线

def count = paramsMap.get("经济收入（月）")?paramsMap.get("经济收入（月）").parameter:-1 ;

def result = 0 ;
def hitInfos = [] ;

if(count<1000){
	result = rulesMap.get("经济收入（月）-少于1000元").value ;
	hitInfos.add("经济收入（月）-少于1000元") ;
}
if(count>=1000 && count<2000){
	result = rulesMap.get("经济收入（月）-1000~2000").value ;
	hitInfos.add("经济收入（月）-1000~2000") ;
}
if(count>=2000 && count<5000){
	result = rulesMap.get("经济收入（月）-2000~5000").value ;
	hitInfos.add("经济收入（月）-2000~5000") ;
}
if(count>=5000){
	result = rulesMap.get("经济收入（月）-5000以上").value ;
	hitInfos.add("经济收入（月）-5000以上") ;
}
if(count<0){
	result = rulesMap.get("经济收入（月）-空").value ;
	hitInfos.add("经济收入（月）-空") ;
}

result = result.toInteger();

def weight = rulesMap.get("经济收入（月）-权重封顶")?rulesMap.get("经济收入（月）-权重封顶").value:null ;
if(weight?.trim()){
	weight = weight.toInteger();
	if(result>weight){
		result = weight ;
	}
}

return ["resultInfo":["hitInfos":hitInfos], "result":result/100] ;