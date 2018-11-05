def paramsMap = ["人员类别":["parameter":[["parameter":"刑事前科@危害国家安全案@间谍、资敌案","tag":"人员类别"],["parameter":"刑事前科@危害公共安全案","tag":"人员类别"],["parameter":"刑事前科@危害国家安全案","tag":"人员类别"]],"tag":"人员类别"],"hrpid":["parameter":"testHrpId1","tag":"hrpid"]] ;

def rulesMap = ["人员类别-艾滋病人":["key":"人员类别-艾滋病人","value":"20"],"人员类别-刑事前科@侵犯公民人身权利、民主权利案":["key":"人员类别-刑事前科@侵犯公民人身权利、民主权利案","value":"100"],"人员类别-涉稳人员":["key":"人员类别-涉稳人员","value":"80"],"人员类别-肇事肇祸精神病人":["key":"人员类别-肇事肇祸精神病人","value":"40"],"人员类别-刑事前科@渎职案":["key":"人员类别-刑事前科@渎职案","value":"20"],"人员类别-刑事前科@妨害社会管理案":["key":"人员类别-刑事前科@妨害社会管理案","value":"60"],"人员类别-权重封顶":["key":"人员类别-权重封顶","value":"40"],"人员类别-违法犯罪青少年":["key":"人员类别-违法犯罪青少年","value":"20"],"人员类别-刑事前科@破坏社会主义市场经济秩序案":["key":"人员类别-刑事前科@破坏社会主义市场经济秩序案","value":"60"],"人员类别-重点上访人员":["key":"人员类别-重点上访人员","value":"50"],"人员类别-刑事前科@危害公共安全案":["key":"人员类别-刑事前科@危害公共安全案","value":"100"],"人员类别-在逃人员":["key":"人员类别-在逃人员","value":"120"],"人员类别-涉毒人员@吸毒人员":["key":"人员类别-涉毒人员@吸毒人员","value":"20"],"人员类别-刑事前科@危害国家安全案":["key":"人员类别-刑事前科@危害国家安全案","value":"120"],"人员类别-刑事前科@侵犯财产案":["key":"人员类别-刑事前科@侵犯财产案","value":"80"],"人员类别-评分项权重":["key":"人员类别-评分项权重","value":"20"],"人员类别-涉毒人员@制贩毒人员":["key":"人员类别-涉毒人员@制贩毒人员","value":"20"],"人员类别-刑事前科@危害国防利益案":["key":"人员类别-刑事前科@危害国防利益案","value":"80"],"人员类别-涉恐人员":["key":"人员类别-涉恐人员","value":"90"]] ;

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