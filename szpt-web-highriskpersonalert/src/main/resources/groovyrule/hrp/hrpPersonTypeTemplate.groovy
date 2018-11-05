

//规则变量和计算脚本的分割线

def personTypes = paramsMap.get("人员类别").parameter ;

def result = 0 ;
def hitInfos = [] ;

personTypes.each { it ->
		
	def type = it.parameter ;	
		
	rulesMap.each { rulesIt ->
		def ruleType = rulesIt.key.split("-")[1] ;
		if(type.contains(ruleType)){
			result += rulesIt.value.value.toInteger();
			hitInfos.add(rulesIt.key) ;
		}
	}		
}

def weight = rulesMap.get("人员类别-权重封顶")?rulesMap.get("人员类别-权重封顶").value:null ;
if(weight?.trim()){
	weight = weight.toInteger();
	if(result>weight){
		result = weight ;
	}
}

return ["resultInfo":["hitInfos":hitInfos], "result":result/100] ;
