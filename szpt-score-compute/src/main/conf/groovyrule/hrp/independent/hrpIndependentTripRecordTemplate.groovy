

def paramsMap = ["近一月出行次数":["parameter":6,"tag":"近一月出行次数"],"hrpid":["parameter":"testHrpId1","tag":"hrpid"]] ;

def rulesMap = ["近一月出行次数-评分项权重":["key":"近一月出行次数-评分项权重","value":"15"],"近一月出行次数-=3次":["key":"近一月出行次数-=3次","value":"60"],"近一月出行次数-=2次":["key":"近一月出行次数-=2次","value":"50"],"近一月出行次数-=4次":["key":"近一月出行次数-=4次","value":"70"],"近一月出行次数-=6次":["key":"近一月出行次数-=6次","value":"100"],"近一月出行次数-=5次":["key":"近一月出行次数-=5次","value":"90"],"近一月出行次数-<=1次":["key":"近一月出行次数-<=1次","value":"0"]] ;

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
