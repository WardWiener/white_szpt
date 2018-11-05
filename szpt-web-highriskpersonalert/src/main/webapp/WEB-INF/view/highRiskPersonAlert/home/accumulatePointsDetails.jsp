<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@include file="/WEB-INF/base/javaPart.jsp"%>
<html>
<head>
<title></title>
<%@include file="/WEB-INF/base/basePart.jsp"%>

<%@include file="/common/library/echarts/echarts-base-import.jsp"%>
<link rel="stylesheet" href="<%=request.getContextPath()%>/custom/szpt/style/zTreeMenu.css" />
<script type="text/javascript" src="<%=context%>/common/library/zTree/custom/zTreeMenu.js"></script>
<script type="text/javascript" src="<%=context%>/scripts/szpt/util/businessDataInit.js"></script>
<script type="text/javascript" src="<%=context%>/scripts/szpt/util/cascadedDicItem.js"></script>

</head>
<body id="cbaGradeTemplate" class="validform m-ui-layer-body">
	<div class="m-ui-layer-box" style="width:95%px;">
<div class="m-ui-title2"><h2>积分详情</h2><div class="m-ui-closebox"><button class="btn m-ui-close"></button></div></div>
<div class="row row-mar mar-top  mar-left">
<div class="col-xs-4 m-label-right"> 姓名：周知只</div>
<div class="col-xs-5 m-label-right"> 身份证：110109192212114223</div>
</div>
<div class="row row-mar  mar-left">
<div class="col-xs-2 m-label-right"> 人员类型：</div>
<div class="col-xs-9 m-label-right">
<span class="state state-blue2">涉毒人员-贩卖毒品案</span>
<span class="state state-blue2">刑事前科-盗窃案</span>
<span class="state state-blue2">在逃人员-在逃人员</span>
</div>
</div>

<div style="background:#f8f8f8; border-top:1px solid #e8e8e8;border-bottom:1px solid #e8e8e8; padding-top:5px; height:240px; overflow:hidden; ">
<div class="row row-mar mar-left">
<div class="col-xs-2"> 当前分数为：</div>
<div class="col-xs-8" style="margin-top:-20px;"><div id="echart-container" style="height:320px;"></div></div> 
</div>
</div>
<p class="color-gray row-mar mar-top">最新计分时间：2016-07-15 17：01</p>
<div style="overflow-x:auto;">
<div style="width:2400px;">
<table id="example1" class="table table-bordered integral-table" cellspacing="0" width="100%">
                      <thead>
                          <tr>
                              <th width="5%">评分项类别</th>
                              <th width="6%">评分项权重（100）</th>
                              <th colspan="17">评分项类别的值（100分）</th>
                        </tr>
                      </thead>
              
                      <tbody>             
                         <tr>
                              <td class="info">在控类型</td>
                              <td class="warning">20</td>
                              <td><p class="color-red2">高危<span class="glyphicon glyphicon-ok"></span></p><p>110%</p></td>
                              <td><p class="color-gray">关注</p><p>80%</p></td>
                              <td><p class="color-gray">一般</p><p>60%</p></td>
                              <td><p class="color-gray">空</p><p>0%</p></td>
                              <td colspan="13"></td>
                          </tr> 
                           <tr>
                              <td rowspan="2" class="info">人员类型</td>
                              <td rowspan="2" class="warning">60（封顶80）</td>
                              <td rowspan="2"><p class="color-red2">在逃<span class="glyphicon glyphicon-ok"></span></p><p>120%</p></td>
                              <td colspan="8" style="text-align:center"><p class="color-gray"><strong>刑事前科</strong></p></td>
                              <td rowspan="2"><p class="color-gray">涉稳</p><p>80%</p></td>
                              <td rowspan="2"><p class="color-gray">涉恐</p><p>90%</p></td>
                               <td colspan="2"  style="text-align:center"><p class="color-gray"><strong>涉毒</strong></p></td>
                               <td rowspan="2"><p class="color-gray">肇事肇祸精神病</p><p>40%</p></td>
                                <td rowspan="2"><p class="color-gray">重点上访</p><p>50%</p></td>
                                <td rowspan="2"><p class="color-gray">违法犯罪青少年</p><p>20%</p></td>
                                 <td rowspan="2"><p class="color-gray">艾滋病人</p><p>20%</p></td>
                          </tr> 
                          <tr>
                              <td><p class="color-red2">危害国家安全案<span class="glyphicon glyphicon-ok"></span></p><p>120%</p></td>
                              <td><p class="color-gray">危害公共安全案</p><p>100%</p></td>
                              <td><p class="color-gray">破坏社会主义市场经济秩序案</p><p>60%</p></td>
                              <td><p class="color-gray">侵犯公民人身权利、民主权利案</p><p>100%</p></td>
                              <td><p class="color-gray">侵犯财产案</p><p>80%</p></td>
                              <td><p class="color-gray">妨害社会管理案</p><p>60%</p></td>
                               <td><p class="color-gray">危害国防利益案</p><p>80%</p></td>
                                <td><p class="color-gray">渎职案</p><p>20%</p></td>
                                 <td><p class="color-gray">吸毒人员</p><p>20%</p></td>
                                  <td><p class="color-gray">制贩毒人员</p><p>60%</p></td>
                          </tr>  
                       <tr>
                              <td class="info">就业情况</td>
                              <td class="warning">5</td>
                              <td><p class="color-red2">无业<span class="glyphicon glyphicon-ok"></span></p><p>100%</p></td>
                              <td><p class="color-gray">待业</p><p>80%</p></td>
                              <td><p class="color-gray">失业</p><p>80%</p></td>
                              <td><p class="color-gray">就业</p><p>40%</p></td>
                               <td colspan="13"></td>
                          </tr>    
                      <tr>
                              <td class="info">婚姻情况</td>
                              <td class="warning">5</td>
                              <td><p class="color-red2">已婚<span class="glyphicon glyphicon-ok"></span></p><p>50%</p></td>
                              <td><p class="color-gray">再婚</p><p>60%</p></td>
                              <td><p class="color-gray">丧偶</p><p>100%</p></td>
                              <td><p class="color-gray">未婚</p><p>100%</p></td>
                              <td><p class="color-gray">离婚</p><p>100%</p></td>
                              <td><p class="color-gray">空</p><p>0%</p></td>
                              <td colspan="11"></td>
                          </tr>  
                       
                       <tr>
                              <td class="info">经济收入（月）</td>
                              <td class="warning">5</td>
                              <td><p class="color-red2">少于1000元<span class="glyphicon glyphicon-ok"></span></p><p>100%</p></td>
                              <td><p class="color-gray">1000~2000</p><p>90%</p></td>
                              <td><p class="color-gray">2000~5000</p><p>70%</p></td>
                              <td><p class="color-gray">5000以上</p><p>50%</p></td>
                              <td><p class="color-gray">空</p><p>0%</p></td>
                              <td colspan="12"></td>
                          </tr>   
                     <tr>
                              <td class="info">近一月出行次数</td>
                              <td class="warning">9</td>
                              <td><p class="color-red2">=6次<span class="glyphicon glyphicon-ok"></span></p><p>100%</p></td>
                              <td><p class="color-gray">=5次</p><p>90%</p></td>
                              <td><p class="color-gray">=4次</p><p>70%</p></td>
                              <td><p class="color-gray">=3次</p><p>60%</p></td>
                              <td><p class="color-gray">=2次</p><p>50%</p></td>
                               <td><p class="color-gray"><=1次</p><p>0%</p></td>
                              <td colspan="11"></td>
                          </tr>            
                      <tr>
                              <td rowspan="2" class="info">场所属性</td>
                              <td rowspan="2" class="warning">5（封顶10）</td>
                              <td colspan="3"><p class="color-red2"><strong>娱乐场所权重（近一年）</strong><span class="glyphicon glyphicon-ok"></span></p><p>120%</p></td>
                              <td colspan="3" style="text-align:center"><p class="color-gray"><strong>网吧权重（近一年）</strong></p></td>
                              <td colspan="3" style="text-align:center"><p class="color-gray"><strong>酒店宾馆权重（近一年）</strong></p></td>
                              <td colspan="10"></td>
                          </tr> 
                          <tr>
                           <td><p class="color-red2">>=20次<span class="glyphicon glyphicon-ok"></span></p><p>120%</p></td>
                              <td><p class="color-gray">>2次，且<20次</p><p>60%</p></td>
                              <td><p class="color-gray"><=2次</p><p>0%</p></td>
                              <td><p class="color-gray">>=240小时</p><p>90%</p></td>
                              <td><p class="color-gray">>56小时，且<240小</p><p>50%</p></td>
                              <td><p class="color-gray"><=56小时</p><p>0%</p></td>
                              <td><p class="color-gray">>=20天</p><p>90%</p></td>
                               <td><p class="color-gray">>2天，且<20天</p><p>50%</p></td>
                              <td><p class="color-gray"><=2天</p><p>0%</p></td>
                              <td colspan="10"></td>
                          </tr>                                                                                         
                   </tbody>
                  </table>
</div>
</div>
    <div class="m-ui-commitbox" style="margin-top:0">
		<button class="btn btn-default btn-lg" id="returnBtn">返回</button> 
	</div>

<!--内容end-->
</div>


<script type="text/javascript">

var dom = document.getElementById("echart-container");
var myChart = echarts.init(dom);
var app = {};
option = null;
option = {
    tooltip : {
        formatter: "{a} <br/>{b} : {c}分"
    },
   
    series: [
        {
            name: '当前分数',
            type: 'gauge',
            detail: {formatter:'{value}'},
            data: [{value: 78.93, name: '积分'}]
        }
    ]
};
/*
//动态的指针
app.timeTicket = setInterval(function () {
    option.series[0].data[0].value = (Math.random() * 100).toFixed(2) - 0;
    myChart.setOption(option, true);
},2000);
;*/
if (option && typeof option === "object") {
    myChart.setOption(option, true);
}
       </script>

   <script type="text/javascript"
	src="<%=context%>/scripts/highRiskPersonAlert/home/accumulatePointsDetails.js"></script>
</body>
</html>