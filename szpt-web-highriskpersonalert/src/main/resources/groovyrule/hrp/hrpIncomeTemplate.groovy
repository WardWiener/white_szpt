

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