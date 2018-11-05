<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@include file="/WEB-INF/base/javaPart.jsp"%>

<html>
<head>
<title></title>
<%@include file="/WEB-INF/base/basePart.jsp"%>
</head>
<body class="m-ui-layer-body">
	<div class="text-center" style="padding: 20px" >
		<button id="btn1" divId="writ1" class="btn btn-danger">第一联</button>
		<button id="btn2" divId="writ2" class="btn btn-primary">第二联</button>
	</div>
	<input type="hidden" id="writId"
				value=<%=request.getParameter("writId")%>>
	<div style="padding: 20px;" >
		<div id="writ1" style="width: 600px; MARGIN-RIGHT: auto; MARGIN-LEFT: auto;" class="writDiv m-ui-layer-content">
			<h3 align="center">刑事案件破案登记表</h3>
			<br/>
			填报单位：<span class="valCell" valName="a1"></span>
			<table border="2" style="width: 600px;text-align: center;">
				<tr style="text-align: center;">
					<td>原案件编号</td>
					<td colspan="4" ><span class="valCell" valName="a2"></span></td>
					<td>案件类别</td>
					<td><span class="valCell" valName="a3"></span>&nbsp;<span class="valCell" valName="a4"></span>&nbsp;<span class="valCell" valName="a5"></span>&nbsp;</td>
				</tr>
				<tr>
					<td>破案部门</td>
					<td colspan="4" ><span class="valCell" valName="a6"></span></td>
					<td>破案时间</td>
					<td><span class="valCell" valName="a7"></span>&nbsp;</td>
				</tr>
				<tr>
					<td>破案单位</td>
					<td colspan="4" ><span class="valCell" valName="a8"></span></td>
					<td>破案方式</td>
					<td><span class="valCell" valName="a9"></span>&nbsp;</td>
				</tr>
				<tr>
					<td>作案人姓名</td>
					<td>性别</td>
					<td>年龄</td>
					<td>身份</td>
					<td>民族或国家</td>
					<td>文化程度</td>
					<td>工作单位或住址</td>
				</tr>
				<tr>
					<td><span class="valCell" valName="a10"></span>&nbsp;</td>
					<td><span class="valCell" valName="a20"></span>&nbsp;</td>
					<td><span class="valCell" valName="a30"></span>&nbsp;</td>
					<td><span class="valCell" valName="a40"></span>&nbsp;</td>
					<td><span class="valCell" valName="a50"></span>&nbsp;</td>
					<td><span class="valCell" valName="a60"></span>&nbsp;</td>
					<td><span class="valCell" valName="a70"></span>&nbsp;</td>
				</tr>
				<tr>
					<td><span class="valCell" valName="a11"></span>&nbsp;</td>
					<td><span class="valCell" valName="a21"></span>&nbsp;</td>
					<td><span class="valCell" valName="a31"></span>&nbsp;</td>
					<td><span class="valCell" valName="a41"></span>&nbsp;</td>
					<td><span class="valCell" valName="a51"></span>&nbsp;</td>
					<td><span class="valCell" valName="a61"></span>&nbsp;</td>
					<td><span class="valCell" valName="a71"></span>&nbsp;</td>
				</tr>
				<tr>
					<td><span class="valCell" valName="a12"></span>&nbsp;</td>
					<td><span class="valCell" valName="a22"></span>&nbsp;</td>
					<td><span class="valCell" valName="a32"></span>&nbsp;</td>
					<td><span class="valCell" valName="a42"></span>&nbsp;</td>
					<td><span class="valCell" valName="a52"></span>&nbsp;</td>
					<td><span class="valCell" valName="a62"></span>&nbsp;</td>
					<td><span class="valCell" valName="a72"></span>&nbsp;</td>
				</tr>
				<tr>
					<td><span class="valCell" valName="a13"></span>&nbsp;</td>
					<td><span class="valCell" valName="a23"></span>&nbsp;</td>
					<td><span class="valCell" valName="a33"></span>&nbsp;</td>
					<td><span class="valCell" valName="a43"></span>&nbsp;</td>
					<td><span class="valCell" valName="a53"></span>&nbsp;</td>
					<td><span class="valCell" valName="a63"></span>&nbsp;</td>
					<td><span class="valCell" valName="a73"></span>&nbsp;</td>
				</tr>
				<tr>
					<td><span class="valCell" valName="a14"></span>&nbsp;</td>
					<td><span class="valCell" valName="a24"></span>&nbsp;</td>
					<td><span class="valCell" valName="a34"></span>&nbsp;</td>
					<td><span class="valCell" valName="a44"></span>&nbsp;</td>
					<td><span class="valCell" valName="a54"></span>&nbsp;</td>
					<td><span class="valCell" valName="a64"></span>&nbsp;</td>
					<td><span class="valCell" valName="a74"></span>&nbsp;</td>
				</tr>
				<tr>
					<td><span class="valCell" valName="a15"></span>&nbsp;</td>
					<td><span class="valCell" valName="a25"></span>&nbsp;</td>
					<td><span class="valCell" valName="a35"></span>&nbsp;</td>
					<td><span class="valCell" valName="a45"></span>&nbsp;</td>
					<td><span class="valCell" valName="a55"></span>&nbsp;</td>
					<td><span class="valCell" valName="a65"></span>&nbsp;</td>
					<td><span class="valCell" valName="a75"></span>&nbsp;</td>
				</tr>
				<tr>
					<td><span class="valCell" valName="a16"></span>&nbsp;</td>
					<td><span class="valCell" valName="a26"></span>&nbsp;</td>
					<td><span class="valCell" valName="a36"></span>&nbsp;</td>
					<td><span class="valCell" valName="a46"></span>&nbsp;</td>
					<td><span class="valCell" valName="a56"></span>&nbsp;</td>
					<td><span class="valCell" valName="a66"></span>&nbsp;</td>
					<td><span class="valCell" valName="a76"></span>&nbsp;</td>
				</tr>
				<tr>
					<td><span class="valCell" valName="a17"></span>&nbsp;</td>
					<td><span class="valCell" valName="a27"></span>&nbsp;</td>
					<td><span class="valCell" valName="a37"></span>&nbsp;</td>
					<td><span class="valCell" valName="a47"></span>&nbsp;</td>
					<td><span class="valCell" valName="a57"></span>&nbsp;</td>
					<td><span class="valCell" valName="a67"></span>&nbsp;</td>
					<td><span class="valCell" valName="a77"></span>&nbsp;</td>
				</tr>
				<tr>
					<td><span class="valCell" valName="a18"></span>&nbsp;</td>
					<td><span class="valCell" valName="a28"></span>&nbsp;</td>
					<td><span class="valCell" valName="a38"></span>&nbsp;</td>
					<td><span class="valCell" valName="a48"></span>&nbsp;</td>
					<td><span class="valCell" valName="a58"></span>&nbsp;</td>
					<td><span class="valCell" valName="a68"></span>&nbsp;</td>
					<td><span class="valCell" valName="a78"></span>&nbsp;</td>
				</tr>
				<tr>
					<td><span class="valCell" valName="a19"></span>&nbsp;</td>
					<td><span class="valCell" valName="a29"></span>&nbsp;</td>
					<td><span class="valCell" valName="a39"></span>&nbsp;</td>
					<td><span class="valCell" valName="a49"></span>&nbsp;</td>
					<td><span class="valCell" valName="a59"></span>&nbsp;</td>
					<td><span class="valCell" valName="a69"></span>&nbsp;</td>
					<td><span class="valCell" valName="a79"></span>&nbsp;</td>
				</tr>
				<tr>
					<td>户籍所在地</td>
					<td colspan="2">外地人来本地目的</td>
					<td colspan="2">违法经历</td>
					<td>现实状况</td>
					<td>处置形式</td>
				</tr>
				<tr>
					<td><span class="valCell" valName="a80"></span>&nbsp;</td>
					<td colspan="2"><span class="valCell" valName="a90"></span>&nbsp;</td>
					<td colspan="2"><span class="valCell" valName="a100"></span>&nbsp;</td>
					<td><span class="valCell" valName="a110"></span>&nbsp;</td>
					<td><span class="valCell" valName="a120"></span>&nbsp;</td>
				</tr>
				<tr>
					<td><span class="valCell" valName="a81"></span>&nbsp;</td>
					<td colspan="2"><span class="valCell" valName="a91"></span>&nbsp;</td>
					<td colspan="2"><span class="valCell" valName="a101"></span>&nbsp;</td>
					<td><span class="valCell" valName="a111"></span>&nbsp;</td>
					<td><span class="valCell" valName="a121"></span>&nbsp;</td>
				</tr>
				<tr>
					<td><span class="valCell" valName="a82"></span>&nbsp;</td>
					<td colspan="2"><span class="valCell" valName="a92"></span>&nbsp;</td>
					<td colspan="2"><span class="valCell" valName="a102"></span>&nbsp;</td>
					<td><span class="valCell" valName="a112"></span>&nbsp;</td>
					<td><span class="valCell" valName="a122"></span>&nbsp;</td>
				</tr>
				<tr>
					<td><span class="valCell" valName="a83"></span>&nbsp;</td>
					<td colspan="2"><span class="valCell" valName="a93"></span>&nbsp;</td>
					<td colspan="2"><span class="valCell" valName="a103"></span>&nbsp;</td>
					<td><span class="valCell" valName="a113"></span>&nbsp;</td>
					<td><span class="valCell" valName="a123"></span>&nbsp;</td>
				</tr>
				<tr>
					<td><span class="valCell" valName="a84"></span>&nbsp;</td>
					<td colspan="2"><span class="valCell" valName="a94"></span>&nbsp;</td>
					<td colspan="2"><span class="valCell" valName="a104"></span>&nbsp;</td>
					<td><span class="valCell" valName="a114"></span>&nbsp;</td>
					<td><span class="valCell" valName="a124"></span>&nbsp;</td>
				</tr>
				<tr>
					<td><span class="valCell" valName="a85"></span>&nbsp;</td>
					<td colspan="2"><span class="valCell" valName="a95"></span>&nbsp;</td>
					<td colspan="2"><span class="valCell" valName="a105"></span>&nbsp;</td>
					<td><span class="valCell" valName="a115"></span>&nbsp;</td>
					<td><span class="valCell" valName="a125"></span>&nbsp;</td>
				</tr>
				<tr>
					<td><span class="valCell" valName="a86"></span>&nbsp;</td>
					<td colspan="2"><span class="valCell" valName="a96"></span>&nbsp;</td>
					<td colspan="2"><span class="valCell" valName="a106"></span>&nbsp;</td>
					<td><span class="valCell" valName="a116"></span>&nbsp;</td>
					<td><span class="valCell" valName="a126"></span>&nbsp;</td>
				</tr>
				<tr>
					<td><span class="valCell" valName="a87"></span>&nbsp;</td>
					<td colspan="2"><span class="valCell" valName="a97"></span>&nbsp;</td>
					<td colspan="2"><span class="valCell" valName="a107"></span>&nbsp;</td>
					<td><span class="valCell" valName="a117"></span>&nbsp;</td>
					<td><span class="valCell" valName="a127"></span>&nbsp;</td>
				</tr>
				<tr>
					<td><span class="valCell" valName="a88"></span>&nbsp;</td>
					<td colspan="2"><span class="valCell" valName="a98"></span>&nbsp;</td>
					<td colspan="2"><span class="valCell" valName="a108"></span>&nbsp;</td>
					<td><span class="valCell" valName="a118"></span>&nbsp;</td>
					<td><span class="valCell" valName="a128"></span>&nbsp;</td>
				</tr>
				<tr>
					<td><span class="valCell" valName="a89"></span>&nbsp;</td>
					<td colspan="2"><span class="valCell" valName="a99"></span>&nbsp;</td>
					<td colspan="2"><span class="valCell" valName="a109"></span>&nbsp;</td>
					<td><span class="valCell" valName="a119"></span>&nbsp;</td>
					<td><span class="valCell" valName="a129"></span>&nbsp;</td>
				</tr>
				<tr>
					<td>作案原因</td>
					<td colspan="3"><span class="valCell" valName="a130"></span></td>
					<td colspan="2">作案手段</td>
					<td><span class="valCell" valName="a131"></span>&nbsp;</td>
				</tr>
				<tr>
					<td>作案形式</td>
					<td colspan="3"><span class="valCell" valName="a132"></span></td>
					<td colspan="2">销赃方法</td>
					<td><span class="valCell" valName="a133"></span>&nbsp;</td>
				</tr>
				<tr>
					<td>作案范围</td>
					<td colspan="3"><span class="valCell" valName="a134"></span></td>
					<td colspan="2">销赃地区</td>
					<td><span class="valCell" valName="a135"></span>&nbsp;</td>
				</tr>
			</table>
		</div>
		<div id="writ2" style="display: none; width: 600px; MARGIN-RIGHT: auto; MARGIN-LEFT: auto;" class="writDiv m-ui-layer-content">
			<br/>
			<table border="2" style="width: 600px;text-align: center;">
				<tr style="text-align: center;">
					<td>破案登记</td>
					<td colspan="7" style="text-align: left;"><span class="valCell" valName="b1"></span>&nbsp;</td>
				</tr>
				<tr>
					<td>收缴物品名称</td>
					<td>所有权性质</td>
					<td>特征</td>
					<td>数量</td>
					<td>收缴物品名称</td>
					<td>所有权性质</td>
					<td>特征</td>
					<td>数量</td>
				</tr>
				<tr>
					<td><span class="valCell" valName="b2"></span>&nbsp;</td>
					<td><span class="valCell" valName="b19"></span>&nbsp;</td>
					<td><span class="valCell" valName="b36"></span>&nbsp;</td>
					<td><span class="valCell" valName="b53"></span>&nbsp;</td>
					<td><span class="valCell" valName="b11"></span>&nbsp;</td>
					<td><span class="valCell" valName="b28"></span>&nbsp;</td>
					<td><span class="valCell" valName="b45"></span>&nbsp;</td>
					<td><span class="valCell" valName="b62"></span>&nbsp;</td>
				</tr>
				<tr>
					<td><span class="valCell" valName="b3"></span>&nbsp;</td>
					<td><span class="valCell" valName="b20"></span>&nbsp;</td>
					<td><span class="valCell" valName="b37"></span>&nbsp;</td>
					<td><span class="valCell" valName="b54"></span>&nbsp;</td>
					<td><span class="valCell" valName="b12"></span>&nbsp;</td>
					<td><span class="valCell" valName="b29"></span>&nbsp;</td>
					<td><span class="valCell" valName="b46"></span>&nbsp;</td>
					<td><span class="valCell" valName="b63"></span>&nbsp;</td>
				</tr>
				<tr>
					<td><span class="valCell" valName="b4"></span>&nbsp;</td>
					<td><span class="valCell" valName="b21"></span>&nbsp;</td>
					<td><span class="valCell" valName="b38"></span>&nbsp;</td>
					<td><span class="valCell" valName="b55"></span>&nbsp;</td>
					<td><span class="valCell" valName="b13"></span>&nbsp;</td>
					<td><span class="valCell" valName="b30"></span>&nbsp;</td>
					<td><span class="valCell" valName="b47"></span>&nbsp;</td>
					<td><span class="valCell" valName="b64"></span>&nbsp;</td>
				</tr>
				<tr>
					<td><span class="valCell" valName="b5"></span>&nbsp;</td>
					<td><span class="valCell" valName="b22"></span>&nbsp;</td>
					<td><span class="valCell" valName="b39"></span>&nbsp;</td>
					<td><span class="valCell" valName="b56"></span>&nbsp;</td>
					<td><span class="valCell" valName="b14"></span>&nbsp;</td>
					<td><span class="valCell" valName="b31"></span>&nbsp;</td>
					<td><span class="valCell" valName="b48"></span>&nbsp;</td>
					<td><span class="valCell" valName="b65"></span>&nbsp;</td>
				</tr>
				<tr>
					<td><span class="valCell" valName="b6"></span>&nbsp;</td>
					<td><span class="valCell" valName="b23"></span>&nbsp;</td>
					<td><span class="valCell" valName="b40"></span>&nbsp;</td>
					<td><span class="valCell" valName="b57"></span>&nbsp;</td>
					<td><span class="valCell" valName="b15"></span>&nbsp;</td>
					<td><span class="valCell" valName="b32"></span>&nbsp;</td>
					<td><span class="valCell" valName="b49"></span>&nbsp;</td>
					<td><span class="valCell" valName="b66"></span>&nbsp;</td>
				</tr>
				<tr>
					<td><span class="valCell" valName="b7"></span>&nbsp;</td>
					<td><span class="valCell" valName="b24"></span>&nbsp;</td>
					<td><span class="valCell" valName="b41"></span>&nbsp;</td>
					<td><span class="valCell" valName="b58"></span>&nbsp;</td>
					<td><span class="valCell" valName="b16"></span>&nbsp;</td>
					<td><span class="valCell" valName="b33"></span>&nbsp;</td>
					<td><span class="valCell" valName="b50"></span>&nbsp;</td>
					<td><span class="valCell" valName="b67"></span>&nbsp;</td>
				</tr>
				<tr>
					<td><span class="valCell" valName="b8"></span>&nbsp;</td>
					<td><span class="valCell" valName="b25"></span>&nbsp;</td>
					<td><span class="valCell" valName="b42"></span>&nbsp;</td>
					<td><span class="valCell" valName="b59"></span>&nbsp;</td>
					<td><span class="valCell" valName="b17"></span>&nbsp;</td>
					<td><span class="valCell" valName="b34"></span>&nbsp;</td>
					<td><span class="valCell" valName="b51"></span>&nbsp;</td>
					<td><span class="valCell" valName="b68"></span>&nbsp;</td>
				</tr>
				<tr>
					<td><span class="valCell" valName="b9"></span>&nbsp;</td>
					<td><span class="valCell" valName="b26"></span>&nbsp;</td>
					<td><span class="valCell" valName="b43"></span>&nbsp;</td>
					<td><span class="valCell" valName="b60"></span>&nbsp;</td>
					<td><span class="valCell" valName="b18"></span>&nbsp;</td>
					<td><span class="valCell" valName="b35"></span>&nbsp;</td>
					<td><span class="valCell" valName="b52"></span>&nbsp;</td>
					<td><span class="valCell" valName="b69"></span>&nbsp;</td>
				</tr>
				<tr>
					<td><span class="valCell" valName="b10"></span>&nbsp;</td>
					<td><span class="valCell" valName="b27"></span>&nbsp;</td>
					<td><span class="valCell" valName="b44"></span>&nbsp;</td>
					<td><span class="valCell" valName="b61"></span>&nbsp;</td>
					<td colspan="4">缴获物品总价值<span class="valCell" valName="b70"></span>元</td>
				</tr>
				<tr>
					<td>审核意见</td>
					<td colspan="3"><span class="valCell" valName="b71"></span>&nbsp;</td>
					<td>批准意见</td>
					<td colspan="3"><span class="valCell" valName="b77"></span>&nbsp;</td>
				</tr>
				<tr>
					<td colspan="4">审核人：<span class="valCell" valName="b72"></span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;审核时间：<span class="valCell" valName="b76"></span>&nbsp;</td>
					<td colspan="4">批准人：<span class="valCell" valName="b78"></span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;批准时间：<span class="valCell" valName="b79"></span>&nbsp;</td>
				</tr>
				<tr>
					<td>处理结果</td>
					<td  colspan="7" style="text-align: left;"><span class="valCell" valName="b73"></span>
					<br/><br/>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;承办人：<span class="valCell" valName="b74"></span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;撤销案件原因：<span class="valCell" valName="b75"></span></td>
				</tr>
			</table>
		</div>
	</div>
	
</body>
<script type="text/javascript"
	src="<%=context%>/scripts/showWrit/padjb.js"></script>
</html>