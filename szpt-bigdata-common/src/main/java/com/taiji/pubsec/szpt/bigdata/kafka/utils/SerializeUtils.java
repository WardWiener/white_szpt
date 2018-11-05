package com.taiji.pubsec.szpt.bigdata.kafka.utils;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

import org.apache.commons.io.IOUtils;

/**
 * Created with IntelliJ IDEA.
 * User: zhangguangyin
 * Date: 14-9-17
 * Time: 下午3:54
 * To change this template use File | Settings | File Templates.
 */
public class SerializeUtils {

  /**
   * 序列化
   */
  public static byte[] serialize(Serializable obj) {
    ObjectOutputStream oos = null;
    ByteArrayOutputStream baos = null;
    try {
      baos = new ByteArrayOutputStream();
      oos = new ObjectOutputStream(baos);
      oos.writeObject(obj);

      return baos.toByteArray();
    } catch (Exception e) {
      return new byte[0];
    } finally {
      IOUtils.closeQuietly(baos);
      IOUtils.closeQuietly(oos);
    }
  }

  /**
   * 反序列化
   */
  public static Object unserialize(byte[] value) {
    ByteArrayInputStream bais = null;
    ObjectInputStream ois = null;
    try {
      // 反序列化
      bais = new ByteArrayInputStream(value);
      ois = new ObjectInputStream(bais);

      return ois.readObject();
    } catch (Exception e) {
      return null;
    } finally {
      IOUtils.closeQuietly(ois);
      IOUtils.closeQuietly(bais);
    }
  }

  
  public static void main(String[] args) {
	  System.out.println(new String(("哈哈哈").getBytes()));
}
}
