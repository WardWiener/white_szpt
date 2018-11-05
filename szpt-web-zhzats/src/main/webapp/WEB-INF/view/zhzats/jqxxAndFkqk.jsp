<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>

<div id="jqxx" class="row main-block  light-block-er"
	style="display: none;">
	<div class="col-xs-4">
		<div id="factJq" style="padding-right: 40px;">
			<div class="m-ui-title3">
				<h2>警情基本信息</h2>
			</div>
			<table class="table table-sg" cellspacing="0" width="100%">
				<tbody>
					<tr>
						<td class="td-left" width="40%">警情名称</td>
						<td><span name="name"></span></td>
					</tr>
					<tr>
						<td class="td-left">警情内容</td>
						<td><span name="jqSummary"></span></td>
					</tr>
					<tr>
					<tr>
						<td class="td-left">追加内容</td>
						<td><span name="addContent"></span></td>
					</tr>
					<tr>
						<td class="td-left">发生地点</td>
						<td><span name="addr"></span></td>
					</tr>
					<tr>
						<td class="td-left">警情编码</td>
						<td><span name="code"></span></td>
					</tr>
					<tr>
						<td class="td-left">发生时间</td>
						<td><span name="occurrenceTime"></span></td>
					</tr>
					<tr>
						<td class="td-left">接警单位</td>
						<td><span name="pcsName"></span></td>
					</tr>
					<tr>
						<td class="td-left">接警人</td>
						<td><span name="jjr"></span></td>
					</tr>
					<tr>
						<td class="td-left">接警时间</td>
						<td><span name="answerAlarmDate"></span></td>
					</tr>
					<tr>
						<td class="td-left">警情来源</td>
						<td><span name="jqSource"></span></td>
					</tr>
					<tr>
						<td class="td-left">报警人</td>
						<td><span name="callingPerson"></span></td>
					</tr>
					<tr>
						<td class="td-left">联系电话</td>
						<td><span name="callingPersonPhone"></span></td>
					</tr>
					<tr>
						<td class="td-left">紧急程度</td>
						<td><span name="urgencyLevel"></span></td>
					</tr>
					<tr>
						<td class="td-left">警情类别</td>
						<td><span name="jqlxName"></span></td>
					</tr>

					<tr>
						<td class="td-left">社区</td>
						<td><span name="countryName"></span></td>
					</tr>
					<td class="td-left">附件</td>
					<td><span name="occurrenceTime"></span></td>
					</tr>

				</tbody>
			</table>
		</div>
	</div>
	<div id="jqAnalyze" class="col-xs-8">
		<div class="m-ui-title3">
			<h2>反馈情况</h2>
		</div>
		<div class="m-ui-title4">
			<h2 class="color-yellow1">反馈记录</h2>
		</div>
		<div style="max-height: 150px; overflow-x: hidden; overflow-y: auto;">
			<table id="feedbackCase" class="table table-sg table-sg-sm"
				cellspacing="0">

			</table>
		</div>

		<div class="m-ui-title4" style="margin-top: 10px">
			<h2 class="color-yellow1">刑侦接警反馈记录</h2>
		</div>
		<table id="" class="table table-sg table-sg-sm" cellspacing="0">
			<tbody style="display: none;">
				<tr>
					<td class="td-left" width="20%">反馈警情类型</td>
					<td width="30%"><span name="jqlxName"></span></td>
					<td class="td-left" width="20%">警情地址</td>
					<td><span name="addr"></span></td>
				</tr>
				<tr>
					<td class="td-left">所属村居</td>
					<td><span name="countryName"></span></td>
					<td class="td-left">反馈内容</td>
					<td><span name="" ></span></td>
				</tr>
			</tbody>
		</table>

		<h3 class="row-mar font14 color-yellow2">现场情况</h3>
		<table id="" class="table table-sg table-sg-sm" cellspacing="0">
			<tbody>
				<tr>
					<td class="td-left" width="20%">周边摄像头</td>
					<td width="30%"><span name="cameras" class="tabs-2"></span></td>
					<td class="td-left" width="20%">逃窜方式</td>
					<td><span name="runWay" class="tabs-2"></span></td>
				</tr>
				<tr>
					<td class="td-left">逃窜方向</td>
					<td colspan="3"><span name="runDirect" class="tabs-2"></span></td>
				</tr>
			</tbody>
		</table>
		<h3 class="row-mar font14 color-yellow2">
			受害人情况 &nbsp;&nbsp;<a href="###" id="btn-shouhairen" class="mar-left">更多</a>
		</h3>
		<table id="" class="table table-sg table-sg-sm" cellspacing="0">
			<tbody>
				<tr>
					<td class="td-left" width="20%">受侵害方式</td>
					<td width="30%"><span name="harmedWay" class="tabs-2"></span></td>
					<td class="td-left" width="20%">受害物品简要特征</td>
					<td><span name="materialChara" class="tabs-2"></span></td>
				</tr>
			</tbody>
		</table>

		<!----------受害人弹出层------------->
		<div id="con-shouhairen"
			style="display: none; width: 300px; height: 200px; margin-bottom: 0;">
			<h4 class="alert alert-info font16 text-center"
				style="padding: 10px;">受害人情况</h4>
			<div class="row row-mar">
				<div class="col-xs-4 m-label-left">姓名：</div>
				<div class="col-xs-7 tabs-2" name="victimName" class="tabs-2"></div>
			</div>
			<div class="row row-mar">
				<div class="col-xs-4 m-label-left">身份证号：</div>
				<div class="col-xs-7 tabs-2" name="victimId" class="tabs-2"></div>
			</div>
		</div>
		<!----------受害人弹出层------------->
		<script>
			$('#btn-shouhairen').on('click', function() {
				layer.open({
					type : 1,
					title : false,
					closeBtn : 1,
					anim : 2,
					shadeClose : true,
					skin : 'layui-layer-nobg', //没有背景色
					content : $('#con-shouhairen')
				});
			});
		</script>

		<h3 class="row-mar font14 color-yellow2">
			嫌疑人情况 &nbsp;&nbsp; <a href="###" id="btn-xianyiren" class="mar-left">更多</a>
		</h3>
		<table id="" class="table table-sg table-sg-sm" cellspacing="0">
			<tbody>
				<tr>
					<td class="td-left" width="20%">逃窜方式</td>
					<td width="30%"><span name="runWay" class="tabs-2"></span></td>
					<td class="td-left" width="20%">逃窜方向</td>
					<td><span name="runDirect" class="tabs-2"></span></td>
				</tr>
				<tr>
					<td class="td-left" width="20%">年龄段</td>
					<td width="30%"><span name="suspectAge" ></span></td>
					<td class="td-left" width="20%">性别</td>
					<td><span name="suspectSex" class="tabs-2"></span></td>
				</tr>
				<tr>
					<td class="td-left">体貌特征</td>
					<td colspan="3">
						<div class="row">
							<div class="col-xs-3">
								身高：<span name="suspectHeight" class="tabs-2"></span>
							</div>
							<div class="col-xs-3">
								发型：<span name="suspectHair" class="tabs-2"></span>
							</div>
							<div class="col-xs-3">
								体型：<span name="suspectBody" class="tabs-2"></span>
							</div>
							<div class="col-xs-3">
								肤色：<span name="suspectSkin" class="tabs-2"></span>
							</div>
						</div>
					</td>
				</tr>
				<tr>
					<td class="td-left">衣着特征</td>
					<td colspan="3"><span name="suspectclothChara" class="tabs-2"></span></td>
				</tr>
				<tr>
					<td class="td-left">其它明显特征</td>
					<td><span name="suspectOtherChara"></span></td>
					<td class="td-left">随身物品特征</td>
					<td><span name="suspectCarryItemChara" class="tabs-2"></span></td>
				</tr>
				<!--  <tr>
              <td class="td-left">作案工具</td>
              <td><span name=""></span></td>
              <td class="td-left">作案方式</td>
              <td><span name=""></span></td>
              </tr>   -->
			</tbody>
		</table>
		<!----------嫌疑人弹出层------------->
		<div id="con-xianyiren"
			style="display: none; width: 300px; height: 200px; margin-bottom: 0;">
			<h4 class="alert alert-info font16 text-center"
				style="padding: 10px;">嫌疑人情况</h4>
			<div class="row row-mar">
				<div class="col-xs-5 m-label-left">是否驾乘车辆：</div>
				<div class="col-xs-6 tabs-2" name="isDriving"></div>
			</div>
			<div class="row row-mar">
				<div class="col-xs-5 m-label-left">其他明显特征：</div>
				<div class="col-xs-6 tabs-2" name="suspectOtherChara"></div>
			</div>
		</div>
		<!----------嫌疑人弹出层------------->
		<script>
			$('#btn-xianyiren').on('click', function() {
				layer.open({
					type : 1,
					title : false,
					closeBtn : 1,
					anim : 2,
					shadeClose : true,
					skin : 'layui-layer-nobg', //没有背景色
					content : $('#con-xianyiren')
				});
			});
		</script>

		<h3 class="row-mar font14 color-yellow2">现场音视频</h3>
		<div class="row">
			<table id="jqVideoList" class="table table-sg table-sg-sm"
				cellspacing="0">

			</table>
			<!-- <div class="col-xs-7">
              <div class="photo-list video-list"  style="margin-left:-7px">      
                   <ul> 
                     <li>
                     <div class="video"><img src="../images/photo/img00001.png"></div>
                     <div class="play">06:00<a href="#" class="glyphicon glyphicon-play-circle color-blue1 mar-left"></a></div></li> 
                     
                   <li>
                     <div class="video"><img src="../images/photo/img00001.png"></div>
                     <div class="play">06:00<a href="#" class="glyphicon glyphicon-play-circle color-blue1 mar-left"></a></div></li>    
                   </ul>   
              </div>
              </div>
   <div class="col-xs-5">
         <div style="padding-left:30px;">
                 <ul class="list-news"> 
                 <li><span class="glyphicon glyphicon-headphones color-blue1 mar-right"></span><a href="#">2016071901.mp3</a></li> 
                 <li><span class="glyphicon glyphicon-headphones color-blue1 mar-right"></span><a href="#">2016071901.mp3</a></li> 
                 <li><span class="glyphicon glyphicon-headphones color-blue1 mar-right"></span><a href="#">2016071901.mp3</a></li> 
                 </ul>   
              </div>
              </div> -->
		</div>


	</div>

</div>
