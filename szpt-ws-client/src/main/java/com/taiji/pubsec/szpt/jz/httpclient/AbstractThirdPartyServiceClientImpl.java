package com.taiji.pubsec.szpt.jz.httpclient;

import net.sf.json.JSONObject;

import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class AbstractThirdPartyServiceClientImpl {

	protected static final String RESPONSE_JSONS = "response_json";

	private static final Logger logger = LoggerFactory
			.getLogger(AbstractThirdPartyServiceClientImpl.class);

	protected ServiceInvocationByClientException produceServiceInvocationException(
			Logger log, Exception e) {
		log.error("发生异常", e);
		return new ServiceInvocationByClientException("客户端调用服务异常", e);
	}

	protected ResultBean invokeService(String url, JSONObject request)
			throws ServiceInvocationByClientException {
		CloseableHttpClient httpclient = HttpClients.createDefault();
		HttpPost post = new HttpPost(url);
		logger.debug("request json: {}, {}", url, request.toString());

		ResultBean resultBean = new ResultBean();
		try {
			StringEntity s = new StringEntity(request.toString(), "UTF-8");
			s.setContentEncoding("UTF-8");
			s.setContentType("application/json");
			post.setEntity(s);

			HttpResponse res = httpclient.execute(post);
			String response = EntityUtils.toString(res.getEntity());
			logger.debug("response json： {}", response);
			JSONObject resultJson = JSONObject.fromObject(response)
					.getJSONObject("result");
			resultBean = ResultBean.fromJson(resultJson);
			resultBean.getResults().put(RESPONSE_JSONS,
					JSONObject.fromObject(response));
		} catch (Exception e) {
			resultBean.setMessage(e.getMessage());
			resultBean.setSuccess(false);
			throw produceServiceInvocationException(logger, e);
		} finally {
			return resultBean;
		}
	}

}
