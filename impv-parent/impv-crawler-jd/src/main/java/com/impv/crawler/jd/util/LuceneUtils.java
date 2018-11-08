package com.impv.crawler.jd.util;

import org.apache.lucene.document.FieldType;
import org.apache.lucene.document.FieldType.NumericType;

public class LuceneUtils {
	
	/**
	 * 不索引、不分词、存储
	 */
	public static final FieldType FIELD_TYPE_STORE_NO_INDEX = new FieldType();

	/**
	 * Long类型 不索引、不分词、存储
	 */
	public static final FieldType FIELD_TYPE_LONG_STORE_NO_INDEX = new FieldType();
	
	/**
	 * Integer类型 不索引、不分词、存储
	 */
	public static final FieldType FIELD_TYPE_INTEGER_STORE_NO_INDEX = new FieldType();

	/**
	 * 索引、不分词、存储
	 */
	public static final FieldType FIELD_TYPE_STORE_INDEX_NO_TOKEN = new FieldType();
	
	static{
		FIELD_TYPE_STORE_NO_INDEX.setStored(true);
		FIELD_TYPE_STORE_NO_INDEX.setIndexed(false);
		FIELD_TYPE_STORE_NO_INDEX.setTokenized(false);
		FIELD_TYPE_STORE_NO_INDEX.freeze();
		
		FIELD_TYPE_LONG_STORE_NO_INDEX.setStored(true);
		FIELD_TYPE_LONG_STORE_NO_INDEX.setIndexed(false);
		FIELD_TYPE_LONG_STORE_NO_INDEX.setTokenized(false);
		FIELD_TYPE_LONG_STORE_NO_INDEX.setNumericType(NumericType.LONG);
		FIELD_TYPE_LONG_STORE_NO_INDEX.freeze();
		
		FIELD_TYPE_INTEGER_STORE_NO_INDEX.setStored(true);
		FIELD_TYPE_INTEGER_STORE_NO_INDEX.setIndexed(false);
		FIELD_TYPE_INTEGER_STORE_NO_INDEX.setTokenized(false);
		FIELD_TYPE_INTEGER_STORE_NO_INDEX.setNumericType(NumericType.INT);
		FIELD_TYPE_INTEGER_STORE_NO_INDEX.freeze();
		
		FIELD_TYPE_STORE_INDEX_NO_TOKEN.setStored(true);
		FIELD_TYPE_STORE_INDEX_NO_TOKEN.setIndexed(true);
		FIELD_TYPE_STORE_INDEX_NO_TOKEN.setTokenized(false);
		FIELD_TYPE_STORE_INDEX_NO_TOKEN.freeze();
	}

}
