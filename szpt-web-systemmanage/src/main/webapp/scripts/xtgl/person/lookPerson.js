(function($) {
	"use strict";
	var frameData = $.util.topWindow().$.layerAlert.getFrameInitData(window);
	var pageIndex = frameData.index;// 当前弹窗index
	var initData = frameData.initData;

	$(document).ready(function() {
		initLookPersonPage(initData.object);
		$(".pa_lookPage_close").on("click", function() {// 关闭
			$.util.topWindow().$.layerAlert.closeWithLoading(pageIndex); // 关闭弹窗
		});
	});

	/**
	 * 初始化人员信息查看页面
	 * 
	 * @param personAdminBean
	 *            选择行人员对象数据
	 */
	function initLookPersonPage(personAdminBean) {
		if ($.util.exist(personAdminBean)) {// 不为空
			$("#pa_lookPage_name").text(
					personAdminBean.name == null ? "" : personAdminBean.name);
			$("#pa_lookPage_sex").text(
					personAdminBean.sex == null ? "" : personAdminBean.sex);
			$("#pa_lookPage_job").text(
					personAdminBean.job == null ? "" : personAdminBean.job);
			$("#pa_lookPage_policeNo").text(
					personAdminBean.policeNo == null ? ""
							: personAdminBean.policeNo);
			$("#pa_lookPage_statusCardNo").text(
					personAdminBean.statusCardNo == null ? ""
							: personAdminBean.statusCardNo);
			$("#pa_lookPage_nationality").text(
					personAdminBean.nationality == null ? ""
							: personAdminBean.nationality);
			$("#pa_lookPage_politicsType").text(
					personAdminBean.politicsType == null ? ""
							: personAdminBean.politicsType);
			$("#pa_lookPage_diploma").text(
					personAdminBean.diploma == null ? ""
							: personAdminBean.diploma);
			$("#pa_lookPage_unit").text(
					personAdminBean.unitFullName == null ? ""
							: personAdminBean.unitFullName);
			$("#pa_lookPage_movePhone").text(
					personAdminBean.movePhone == null ? ""
							: personAdminBean.movePhone);
			$("#pa_lookPage_officePhone").text(
					personAdminBean.officePhone == null ? ""
							: personAdminBean.officePhone);
			$("#pa_lookPage_status").text(
					personAdminBean.status == null ? ""
							: personAdminBean.status);
		}
	}
})(jQuery);