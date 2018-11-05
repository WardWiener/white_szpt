 <%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>       
 <!-------右侧内容begin--------> 
 <div class="validform" id="basicYZ">
<div class="m-ui-title4 mar-top"><button class="btn btn-info pull-right" id="updataBasicMessageBtn" style="margin-top:-6px;">维护案件基本信息</button> <h2>案件基本资料</h2></div> 

<div class="alert alert-info">
<h1 class="font20 row-mar" id="basicName"></h1>
<div class="row">
<div class="col-xs-5"> 专案编号：<p id="basicCode"></p></div>
<div class="col-xs-6"> 专案性质：<p id="basicNature"></p></div>
</div>
</div>

<h5 class="row-mar"> 涉及子案件：</h5>
<table id="basicSonProjectTable" class="table table-bordered table-hover m-ui-table-whole" cellspacing="0" style="margin-bottom:20px;">
       
    </table>
<h5 class="row-mar"> 简要案情：</h5>
<div class="row" id="basicJYAQ"></div>
 
<p class="m-line"></p>

<h5 class="row-mar"> 目前工作进展情况：</h5>
<div class="row" id="basicJZ"></div>
<p class="m-line"></p>
<h5 class="row-mar"> 侦查工作中的主要问题：</h5>
<div class="row" id="basicWT"></div>
<p class="m-line"></p>
<h5 class="row-mar"> 下一步工作计划：</h5>
<div class="row" id="basicJH"></div>
<p class="m-line"></p>

</div>




<div style="display: none" >
<h5 class="row-mar mar-top"> 关联情报线索：</h5>
<div class="text-right">
<button class="btn btn-xs btn-primary">添加关联线索</button>
<button class="btn btn-xs btn-danger">删除关联线索</button>
</div>
<table class="table table-bordered table-hover m-ui-table-whole" cellspacing="0">
        <thead>
            <tr>
                <th width="8%">序号</th>
                <th width="18%">情报编码</th>
                <th width="20%">情报名称</th>
                <th width="23%">采集时间</th>
                <th>内容描述</th>
            </tr>
        </thead>
        <tbody>
            <tr>
              <td>1</td>
              <td>********</td>
              <td>XX要上访</td>
              <td>2016-07-06 14:30</td>
              <td>东城区MAMO现代城有人吸毒…</td>
          </tr> 
           <tr>
              <td>2</td>
              <td></td>
              <td></td>
              <td>2016-07-05 11:30</td>
              <td>少林方丈贪污且…</td>
          </tr> 
        </tbody>
    </table>
    </div>
<!-------右侧内容end-------->
