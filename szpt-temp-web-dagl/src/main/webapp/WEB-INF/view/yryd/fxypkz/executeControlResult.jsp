<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<div class="row">
	<div id="executeControlId" class="col-xs-6">
		<p class="mar-top font12 color-gray2">
			保存快照原因：<span valName="">XXXXX</span>。<span class="color-green1">已推送办案民警</span><span
				class="color-orange1">办案民警未查看</span>
		</p>
		<p class="row-mar">
			布控单 <strong id="num" name="num">BK2016070001</strong> 累计共捕获 <span
				class="color-red1 valCell" id="resultNum" name="resultNum"></span> 条信息
		</p>
		<div class="row row-mar-sm">
			<div class="col-xs-3 m-label-left color-gray2">身份证号：</div>
			<div class="col-xs-8 valCell valCell" id="idCardNo" name="idCardNo"></div>
		</div>
		<div class="row row-mar-sm">
			<div class="col-xs-3 m-label-left color-gray2">姓名：</div>
			<div class="col-xs-8 valCell" id="personName" name="personName" ></div>
		</div>
		<div class="row row-mar-sm">
			<div class="col-xs-3 m-label-left color-gray2">手机：</div>
			<div class="col-xs-8 valCell" id="phone" name="phone" ></div>
		</div>
		<div class="row row-mar-sm">
			<div class="col-xs-3 m-label-left color-gray2">民族：</div>
			<div class="col-xs-8 valCell" id="ethnicGroup" name="ethnicGroup" ></div>
		</div>
		<div class="row row-mar-sm">
			<div class="col-xs-3 m-label-left color-gray2">户籍地址：</div>
			<div class="col-xs-8 valCell" id="residenceAddress" name="residenceAddress" ></div>
		</div>
		<div class="row row-mar-sm">
			<div class="col-xs-3 m-label-left color-gray2">现住地址：</div>
			<div class="col-xs-8valCell" id="localAddressDetail" name="localAddressDetail" ></div>
		</div>
		<div class="row row-mar-sm">
			<div class="col-xs-3 m-label-left color-gray2">照片：</div>
			<div id="exrcuteControlImg" class="col-xs-8">
				<div class="col-xs-3 mar-right">
					<p style="height: 100px; overflow: hidden">
						<img src="../images/man/photo-2.png" width="100%">
					</p>
				</div>
				<div class="col-xs-3 mar-right">
					<p style="height: 100px; overflow: hidden">
						<img src="" width="100%">
					</p>
				</div>
			</div>
		</div>
		<div class="row row-mar-sm">
			<div class="col-xs-3 m-label-left color-gray2">布控时间范围：</div>
			<div class="col-xs-8 valCell" id="time" name="time" ></div>
		</div>
		<div class="row row-mar-sm">
			<div class="col-xs-3 m-label-left color-gray2">布控原因：</div>
			<div class="col-xs-4" id="note" name="note" class="valCell"></div>
		</div>
	</div>
	<div class="col-xs-6">
		<div id="executeControlMap" style="overflow: hidden; height: 350px; margin-left: 10px;">
		
		</div>
	</div>
</div>
<div class="m-ui-title4">
	<h2>布控结果</h2>
</div>
<div class="row" style="max-height: 200px; overflow: auto;">
	<div class="m-ui-table">
		<table id="executeControlTable"
			class="table table-bordered table-hover m-ui-table-whole"
			cellspacing="0" width="100%">
		</table>
	</div>
</div>
