
//规则变量和计算脚本的分割线

def count = paramsMap.get("婚姻情况")?paramsMap.get("婚姻情况").parameter:"未知" ;

def result = 0 ;
def hitInfos = [] ;

if(count=="已婚"){
	result = rulesMap.get("婚姻情况-已婚").value ;
	hitInfos.add("婚姻情况-已婚") ;
}
if(count=="再婚"){
	result = rulesMap.get("婚姻情况-再婚").value ;
	hitInfos.add("婚姻情况-再婚") ;
}
if(count=="丧偶"){
	result = rulesMap.get("婚姻情况-丧偶").value ;
	hitInfos.add("婚姻情况-丧偶") ;
}
if(count=="未婚"){
	result = rulesMap.get("婚姻情况-未婚").value ;
	hitInfos.add("婚姻情况-未婚") ;
}
if(count=="未知"){
	result = rulesMap.get("婚姻情况-未知").value ;
	hitInfos.add("婚姻情况-未知") ;
}

result = result.toInteger();

def weight = rulesMap.get("婚姻情况-权重封顶")?rulesMap.get("婚姻情况-权重封顶").value:null ;
if(weight?.trim()){
	weight = weight.toInteger();
	if(result>weight){
		result = weight ;
	}
}

return ["resultInfo":["hitInfos":hitInfos], "result":result/100] ;

