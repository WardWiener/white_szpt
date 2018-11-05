
def testmap = ["test（键1）":"键值1", "test（键2）":"键值2"] ;

def testmap1 = ["test（键1）":["value":"键值1", "tag":"test（键1）"], "test（键2）":["value":"键值2", "tag":"test（键2）"]] ;

testmap.each { it ->
		println it.key+","+it.value ;
		println it.key+","+it.value ;
}

testmap1.each { it ->
		println "测试testmap1：" +  it.key+","+it.value.tag ;
		println "测试testmap1：" + it.key+","+it.value.value ;
}

paramMap.each { it ->
		println it.key+","+it.value.tag ;
		println it.key+","+it.value.value ;
}


println paramMap.get("标签（测试）tag1").tag ;
println paramMap.get("标签（测试）tag1").value ;

def json = new groovy.json.JsonSlurper().parseText(paramMapJsonStr) ;
println json ;
json.each { it ->
		println "测试json：" +  it.key+","+it.value.tag ;
		println "测试json：" + it.key+","+it.value.parameter ;
}

println "测试json get："+json.get("标签（测试）tag1").tag ;
println "测试json get："+json.get("标签（测试）tag1").parameter ;

return "123" ;

