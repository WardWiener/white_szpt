package com.taiji.pubsec.szpt.dpp.jdbc;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.taiji.pubsec.szpt.dpp.common.PropertiesAnalysis;

public class JDBCWrapper implements Serializable{
	private static JDBCWrapper jdbcInstance = null;
	private static String URL = new PropertiesAnalysis().analyze("URL");
	private static String USERNAME = new PropertiesAnalysis().analyze("USERNAME");
	private static String PASSWORD = new PropertiesAnalysis().analyze("PASSWORD");
//	public static LinkedBlockingQueue<Connection> dbConnetionPool = new LinkedBlockingQueue<Connection>();
	static {
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	public static JDBCWrapper getJDBCInstance() throws InterruptedException {
		if (jdbcInstance == null) {
			synchronized (JDBCWrapper.class) { // 只有一个线程进去
				if (jdbcInstance == null) { // 第二个 线程发现不为空，返回了
					jdbcInstance = new JDBCWrapper();
				}
			}

		}
		return jdbcInstance;
	}

	private JDBCWrapper() throws InterruptedException {
//		for (int i = 0; i < 10; i++) {
//			// 一次建立10个连接
//			try {
//				Connection conn = DriverManager.getConnection(URL, USERNAME,
//						PASSWORD);
//				dbConnetionPool.put(conn);
//			} catch (SQLException e) {
//				e.printStackTrace();
//			}
//		}

	}

	public Connection getConnetion() {
		Connection conn = null;
		try {
			conn = DriverManager.getConnection(URL, USERNAME,
					PASSWORD);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return conn;
	}

	// 通用操作 查询、插入、删除；spark是一批一批的数据的批处理
	public int[] doBatch(String sqlText, List<Object[]> paramsList) {

		Connection conn = getConnetion();

		int[] result = null;
		PreparedStatement preparedStatement = null;
		try {
			conn.setAutoCommit(false);
			preparedStatement = conn.prepareStatement(sqlText);
			for (Object[] parameters : paramsList) {
				for (int i = 0; i < parameters.length; i++) {
					preparedStatement.setObject(i + 1, parameters[i]);
				}
				preparedStatement.addBatch();
			}
			result = preparedStatement.executeBatch();
			conn.commit();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (preparedStatement != null) {
				try {
					preparedStatement.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}

			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}

		return result;
	}

	/**
	 * 查询数量
	 * 
	 * @param sqlText
	 * @param paramsList
	 * @return
	 */
	public int doQueryCount(String sqlText, List<Object[]> paramsList) {
		Connection conn = getConnetion();
		ResultSet result = null;
		PreparedStatement preparedStatement = null;
		try {
			preparedStatement = conn.prepareStatement(sqlText);
			for (Object[] parameters : paramsList) {
				for (int i = 0; i < parameters.length; i++) {
					preparedStatement.setObject(i + 1, parameters[i]);
				}

			}
			result = preparedStatement.executeQuery();

			int rowCount = 0;
			while (result.next()) {
				rowCount++;
			}
			return rowCount;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (result != null) {
				try {
					result.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if (conn != null ) {
				try {
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		return 0;
	}

	/**
	 * 查询符合条件数据的主键 主键默认为id
	 * 
	 * @param sqlText
	 * @param paramsList
	 * @return
	 */
	public List<String> doQueryPK(String sqlText, List<Object[]> paramsList) {
		Connection conn = getConnetion();
		ResultSet result = null;
		PreparedStatement preparedStatement = null;
		try {
			preparedStatement = conn.prepareStatement(sqlText);
			for (Object[] parameters : paramsList) {
				for (int i = 0; i < parameters.length; i++) {
					preparedStatement.setObject(i + 1, parameters[i]);
				}

			}
			result = preparedStatement.executeQuery();

			List<String> list = new ArrayList<String>();
			while (result.next()) {
				list.add(result.getString("id"));
			}
			return list;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (result != null) {
				try {
					result.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		return null;
	}

	public void doQuery(String sqlText, List<Object[]> paramsList,
			ExecuteCallBack callback) {

		Connection conn = getConnetion();
		ResultSet result = null;
		PreparedStatement preparedStatement = null;
		try {
			preparedStatement = conn.prepareStatement(sqlText);
			for (Object[] parameters : paramsList) {
				for (int i = 0; i < parameters.length; i++) {
					preparedStatement.setObject(i + 1, parameters[i]);
				}

			}
			result = preparedStatement.executeQuery();

			try {
				callback.resultCallBack(result);
			} catch (Exception e) {
				e.printStackTrace();
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (result != null) {
				try {
					result.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}

			if (preparedStatement != null) {
				try {
					preparedStatement.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}

			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}

	}

	// 弄个回调函数
	public interface ExecuteCallBack {
		void resultCallBack(ResultSet result) throws Exception;
	}

}